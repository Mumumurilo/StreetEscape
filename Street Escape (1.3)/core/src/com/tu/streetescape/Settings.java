package com.tu.streetescape;

//Essa classe serve apenas para ativar e desativar as coisas. N�O ESQUECER DE ADICIONAR CONDI��ES
public class Settings extends MainGame{
	@SuppressWarnings("unused")
	private final MainGame jogo;
	
	public Settings(final MainGame jogo){
		this.jogo = jogo;
		jogo.setDebug(true);
		jogo.setMusic(true);
		jogo.setSound(true);
	}	
}