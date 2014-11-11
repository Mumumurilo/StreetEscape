package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

//Essa classe serve apenas para ativar e desativar as coisas. NÃO ESQUECER DE ADICIONAR CONDIÇÕES
public class Settings extends MainGame{
	private final MainGame jogo;
	
	public Settings(final MainGame jogo){
		this.jogo = jogo;
		jogo.setDebug(true);
		jogo.setMusic(true);
		jogo.setSound(true);
	}
	
	public void configButtons(){
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.N)){
			if(jogo.isSound() == true){
				jogo.setSound(false);
			}else{
				jogo.setSound(true);
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.M)){
			if(jogo.isMusic() == true){
				jogo.setMusic(false);
			}else{
				jogo.setMusic(true);
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.ALT_LEFT) && Gdx.input.isKeyJustPressed(Keys.P)){
			if(jogo.isDebug() == true){
				jogo.setDebug(false);
			}else{
				jogo.setDebug(true);
			}
		}
	}
}