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
		
		//Posicionamento da c�mera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		
		//Declara��o de classes
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		
		//Declara��o de elementos
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
		
		//Atualiza��o da c�mera
		jogo.camera.update();
		
		//M�todo de atalho dos bot�es de config
		settings.configButtons();
		
		//Condi��o para m�sica parar ou tocar
		settings.condMusica(jogo.temamenu);

		if(Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyPressed(Keys.SPACE)){	
			jogo.telajogo = new TelaJogo(jogo);
			
			jogo.setScreen(jogo.telajogo);
		}
	}

	@Override
	public void resize(int width, int height) { //Acho que n�o faremos uso desse m�todo
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() { //Tudo que � feito quando a tela � aberta (antes do loop principal)
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() { //Tudo o que � feito quando a tela � fechada (depois de fechar o loop principal)
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() { //Tudo o que � feito quando o bot�o HOME do Android � pressionado
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() { //Tudo o que � feito quando o jogo � retomado
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() { //Tudo o que pesa na mem�ria precisa ser jogado fora aqui
		
	}

}
