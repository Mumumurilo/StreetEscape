package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class Inicio implements Screen{
	private final MainGame jogo;
	
	//Classes utilizadas
	private Arte artes;
	private Settings settings;
	
	//Contador de logo
	private float somaLogo = -4;
	
	public Inicio(final MainGame jogo){ //Construtor. Serve para instanciar os elementos das telas
		this.jogo = jogo;
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.setProjectionMatrix(jogo.camera.combined);
		jogo.renderer.setProjectionMatrix(jogo.camera.combined);
		
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
			somaLogo = 0;
		}
		
		if(somaLogo <= 0){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			jogo.batch.begin();
			jogo.batch.draw(artes.logotu, 0, 0, jogo.WIDTH, jogo.HEIGHT);
			jogo.batch.end();
		}
		
		if(somaLogo > 0 && somaLogo <= 16){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			//Desenho do forninho
			jogo.batch.begin();
			jogo.batch.draw(artes.forninho, 0, 0, jogo.WIDTH, jogo.HEIGHT);
			jogo.batch.end();
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){	
				jogo.telajogo = new TelaJogo(jogo);
				
				jogo.setScreen(jogo.telajogo);
			}
		}
		
		if(somaLogo > 16 && somaLogo <= 32){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			jogo.batch.begin();
			jogo.batch.draw(artes.menu2, 0, 0);
			
			jogo.GUIFont.setColor(Color.BLACK);
			jogo.GUIFont.drawMultiLine(jogo.batch, "\n\n\n    Transeunte is a simple citizen that wanted to\n" +
					"    take a walk at the biggest avenue of the " +
					"city.\n       However, when he left the subway... He\n" +
					"      discovered that a big manifestation was\n" +
					"                                    happening!\n\n", 10, jogo.HEIGHT - 10);
			jogo.batch.end();
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){	
				somaLogo = 5;
			}
		}
		
		if(somaLogo > 32 && somaLogo <= 48){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			jogo.batch.begin();
			jogo.batch.draw(artes.menu2, 0, 0);
			
			jogo.GUIFont.drawMultiLine(jogo.batch, "\n\n\n\n When he tried to go back to subway he saw that\n" +
					"                              it was just closed!\n" +
					" Besides that, the people on the street started to\n" +
					"                           hurt him! AND NOW!?", 10, jogo.HEIGHT - 10);
			jogo.batch.end();
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){	
				somaLogo = 5;
			}
		}
		
		if(somaLogo > 48 && somaLogo <= 64){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			jogo.batch.begin();
			jogo.batch.draw(artes.menu2, 0, 0);
			
			jogo.GUIFont.drawMultiLine(jogo.batch, "\n\n\n\nTranseunte needs to find the closest and opened\n" +
					"                                    station ASAP!\n" +
					"                                   Or he will DIE!", 10, jogo.HEIGHT - 10);
			jogo.batch.end();
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){	
				somaLogo = 5;
			}
		}
		
		if(somaLogo > 64 && somaLogo <= 72){
			somaLogo += Gdx.graphics.getDeltaTime();
			
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			jogo.batch.begin();
			jogo.batch.draw(artes.menu2, 0, 0);
			
			jogo.GUIFont.drawMultiLine(jogo.batch, "\n\n\n\n\n                                      Yes... DIE!!!\n\n" +
					"                                        Controls:\n" +
					"              Walk: W-S-A-D | Shoot: Arrow Keys", 10, jogo.HEIGHT - 10);
			jogo.batch.end();
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){	
				somaLogo = 5;
			}
		}
		
		if(somaLogo > 72){
			somaLogo = 0;
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
