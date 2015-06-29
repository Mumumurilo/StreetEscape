package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class TelaCreditos implements Screen{
	private final MainGame jogo;
	
	private Settings settings;
	
	private Texture background;
	private Texture loadingimg;
	
	//Contador de loading
	private boolean loading = false;
	private float somaLoading = 0;
	
	public TelaCreditos(final MainGame jogo){
		this.jogo = jogo;
		
		//Deleta tela anterior
		jogo.telajogo.dispose();
		jogo.temajogo.dispose();
		jogo.temaboss1.dispose();
		jogo.temaboss2.dispose();
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.setProjectionMatrix(jogo.camera.combined);
		jogo.renderer.setProjectionMatrix(jogo.camera.combined);
		
		//bg
		background = new Texture(Gdx.files.internal("credits.png"));
		
		//loading
		loadingimg = new Texture(Gdx.files.internal("loading.png"));
		
		//Config
		settings = new Settings(jogo);
		
		//Música
		jogo.temacreditos = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 1.ogg"));
		jogo.temacreditos.setLooping(true);
		if(jogo.isMusic()){
			jogo.temacreditos.play();
		}
	}

	@Override
	public void render(float delta) {
		jogo.batch.begin();
		jogo.batch.draw(background, 0, 0, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.end();
		
		jogo.camera.update();
		
		settings.configButtons(jogo.temacreditos);
		
		if(loading){
			somaLoading += Gdx.graphics.getDeltaTime();
			
			jogo.batch.begin();
			jogo.batch.draw(loadingimg, 0, 0, jogo.WIDTH, jogo.HEIGHT);
			jogo.batch.end();
			
			if(somaLoading > 0.1){
				jogo.reset = true;
				jogo.temacreditos.dispose();
				jogo.create();
			}
		}
		
		if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)){
			loading = true;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		background.dispose();
		loadingimg.dispose();
		
	}

}
