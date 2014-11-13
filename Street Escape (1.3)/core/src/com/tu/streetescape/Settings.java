package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;

//Essa classe serve apenas para ativar e desativar as coisas. NÃO ESQUECER DE ADICIONAR CONDIÇÕES
public class Settings extends MainGame{
	private final MainGame jogo;
	
	public Settings(final MainGame jogo){
		this.jogo = jogo;
	}
	
	public void configButtons(){
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.N) && jogo.isSound()){
			jogo.setSound(false);
		}else if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.N) && !jogo.isSound()){
			jogo.setSound(true);
		}
		
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.M) && jogo.isMusic()){
			jogo.setMusic(false);
		}else if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.M) && !jogo.isMusic()){
			jogo.setMusic(true);
		}
		
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.ALT_LEFT) && Gdx.input.isKeyJustPressed(Keys.L)
				&& jogo.isDebug()){
			jogo.setDebug(false);
		}else if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.ALT_LEFT) && Gdx.input.isKeyJustPressed(Keys.L)
				&& !jogo.isDebug()){
			jogo.setDebug(true);
		}
	}
	
	public void condMusica(Music music){
		if(!jogo.isMusic() && music.isPlaying()){
			music.stop();
		}else if(jogo.isMusic() && !music.isPlaying()){
			music.play();
		}
	}
}