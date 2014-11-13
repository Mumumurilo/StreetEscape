package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Inicio implements Screen{
	private final MainGame jogo;
	
	//Classes utilizadas
	private Arte artes;
	private Settings settings;
	
	public Inicio(final MainGame jogo){ //Construtor. Serve para instanciar os elementos das telas
		this.jogo = jogo;
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		
		//Declaração de telas
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		
		//Declaração de elementos
		jogo.temamenu = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 3.mp3"));
		jogo.temamenu.setLooping(true);
		jogo.temamenu.play();
	}

	@Override
	public void render(float delta) { //"loop" principal da tela
		if(jogo.reset == true){
			jogo.telacreditos.dispose();
			jogo.reset = false;
		}
		
		//Desenho do forninho
		jogo.batch.begin();
		jogo.batch.draw(artes.forninho, 0, 0, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.end();
		
		//Atualização da câmera
		jogo.camera.update();
		
		//Método de atalho dos botões de config
		settings.configButtons();
		
		//Condição para música parar ou tocar
		settings.condMusica(jogo.temamenu);

		if(Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyPressed(Keys.SPACE)){	
			jogo.telajogo = new TelaJogo(jogo);
			
			jogo.setScreen(jogo.telajogo);
		}
	}

	@Override
	public void resize(int width, int height) { //Acho que não faremos uso desse método
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() { //Tudo que é feito quando a tela é aberta (antes do loop principal)
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() { //Tudo o que é feito quando a tela é fechada (depois de fechar o loop principal)
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() { //Tudo o que é feito quando o botão HOME do Android é pressionado
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() { //Tudo o que é feito quando o jogo é retomado
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() { //Tudo o que pesa na memória precisa ser jogado fora aqui
		
	}

}
