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
		
		//Posicionamento da c�mera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		
		//Declara��o de classes
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		
		//Declara��o de elementos
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
		
		//Atualiza��o da c�mera
		jogo.camera.update();
		
		//M�todo de atalho dos bot�es de config
		settings.configButtons(jogo.temamenu);
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
