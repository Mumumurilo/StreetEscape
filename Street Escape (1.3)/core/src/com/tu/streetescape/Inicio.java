package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;

public class Inicio implements Screen{
	private final MainGame jogo;
	
	//Classes utilizadas
	private Arte artes;
	private Settings settings;
	
	//Contador de logo
	private float somaLogo = 0;
	
	public Inicio(final MainGame jogo){ //Construtor. Serve para instanciar os elementos das telas
		this.jogo = jogo;
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		
		//Declaração de classes
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		
		//Declaração de elementos
		jogo.temamenu = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 3.mp3"));
		jogo.temamenu.setLooping(true);
		if(jogo.isMusic()){
			jogo.temamenu.play();
		}
	}

	@Override
	public void render(float delta) { //"loop" principal da tela
		if(jogo.reset == true){
			jogo.telacreditos.dispose();
			jogo.reset = false;
			somaLogo = 5;
		}
		
		if(somaLogo <= 4){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			jogo.batch.begin();
			jogo.batch.draw(artes.logotu, 0, 0, jogo.WIDTH, jogo.HEIGHT);
			jogo.batch.end();
		}
		
		if(somaLogo > 4){
			//Desenho do forninho
			jogo.batch.begin();
			jogo.batch.draw(artes.forninho, 0, 0, jogo.WIDTH, jogo.HEIGHT);
			jogo.batch.end();
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){	
				jogo.telajogo = new TelaJogo(jogo);
				
				jogo.setScreen(jogo.telajogo);
			}
		}
		
		//Atualização da câmera
		jogo.camera.update();
		
		//Método de atalho dos botões de config
		settings.configButtons(jogo.temamenu);
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
