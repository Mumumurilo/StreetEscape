package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class TelaCreditos implements Screen{
	private final MainGame jogo;
	
	private Settings settings;
	
	private Texture background;
	
	public TelaCreditos(final MainGame jogo){
		this.jogo = jogo;
		
		//Deleta tela anterior
		jogo.telajogo.dispose();
		jogo.temajogo.dispose();
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.setProjectionMatrix(jogo.camera.combined);
		jogo.renderer.setProjectionMatrix(jogo.camera.combined);
		
		//bg
		background = new Texture(Gdx.files.internal("credits.png"));
		
		//Config
		settings = new Settings(jogo);
		
		//Música
		jogo.temacreditos = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 1.mp3"));
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
		
		if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)){
			jogo.reset = true;
			jogo.temacreditos.dispose();
			jogo.create();
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
		
	}

}
