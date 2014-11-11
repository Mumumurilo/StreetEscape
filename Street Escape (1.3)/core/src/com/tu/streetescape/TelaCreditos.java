package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class TelaCreditos implements Screen{
	private final MainGame jogo;
	
	private Texture background;
	
	public TelaCreditos(final MainGame jogo){
		this.jogo = jogo;
		
		//Deleta tela anterior
		jogo.telajogo.dispose();
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		
		background = new Texture(Gdx.files.internal("bg.png"));
	}

	@Override
	public void render(float delta) {
		jogo.batch.begin();
		jogo.batch.draw(background, 0, 0, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.end();
		
		jogo.camera.update();
		
		if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)){
			jogo.reset = true;
			jogo.telainicio = new Inicio(jogo);
			jogo.setScreen(jogo.telainicio);
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
