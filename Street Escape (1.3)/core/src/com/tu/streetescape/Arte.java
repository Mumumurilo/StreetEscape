package com.tu.streetescape;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Arte {
	final MainGame jogo;
	
	//Declaração das imagens e artes utilizadas no jogo
	public Texture forninho;
	public Texture menu2;
	
	private Texture ALL, UDR, UDL, LRD, LRU, RD, RU, LD, LU, UD, LR, R, D, L, U;
	public ArrayList<Texture> salas;
	public ArrayList<Texture> personagem;
	
	public TextureRegion[][] transeunte, man1, man2;
	public Texture trananda, man1anda, man2anda;
	public TextureRegion currentFrame, currentFrameman1, currentFrameman2;
	
	public Array<Texture> life;
	private Texture life6, life5, life4, life3, life2, life1, life0;
	
	public Texture logotu;
	
	public Arte(final MainGame jogo){
		this.jogo = jogo;
		
		forninho = new Texture(Gdx.files.internal("menu.png"));
		logotu = new Texture(Gdx.files.internal("Arte/TU Logo.png"));
		menu2 = new Texture(Gdx.files.internal("menu2.png"));
		
		salas = new ArrayList<Texture>();
		//D = 1000, U = 100, L = 10, R = 1
		salas.add(ALL = new Texture(Gdx.files.internal("ALL.png")));//0, 1111
		salas.add(UDR = new Texture(Gdx.files.internal("UDR.png")));//1, 1101
		salas.add(UDL = new Texture(Gdx.files.internal("UDL.png")));//2, 1110
		salas.add(LRD = new Texture(Gdx.files.internal("LRD.png")));//3, 1011
		salas.add(LRU = new Texture(Gdx.files.internal("LRU.png")));//4, 111
		salas.add(RD = new Texture(Gdx.files.internal("RD.png")));//5, 1001
		salas.add(RU = new Texture(Gdx.files.internal("RU.png")));//6, 101
		salas.add(LD = new Texture(Gdx.files.internal("LD.png")));//7, 1010
		salas.add(LU = new Texture(Gdx.files.internal("LU.png")));//8, 110
		salas.add(UD = new Texture(Gdx.files.internal("UD.png")));//9, 1100
		salas.add(LR = new Texture(Gdx.files.internal("LR.png")));//10, 11
		salas.add(L = new Texture(Gdx.files.internal("L.png")));//11, 10
		salas.add(D = new Texture(Gdx.files.internal("D.png")));//12, 1000
		salas.add(U = new Texture(Gdx.files.internal("U.png")));//13, 100
		salas.add(R = new Texture(Gdx.files.internal("R.png")));//14, 1
		
		life = new Array<Texture>();
		
		life.add(life6 = new Texture(Gdx.files.internal("Arte/0 Heart.png")));
		life.add(life5 = new Texture(Gdx.files.internal("Arte/05 Heart.png")));
		life.add(life4 = new Texture(Gdx.files.internal("Arte/1 Heart.png")));
		life.add(life3 = new Texture(Gdx.files.internal("Arte/15 Hearts.png")));
		life.add(life2 = new Texture(Gdx.files.internal("Arte/2 Hearts.png")));
		life.add(life1 = new Texture(Gdx.files.internal("Arte/25 Hearts.png")));
		life.add(life0 = new Texture(Gdx.files.internal("Arte/3 Hearts.png")));
		

		trananda = new Texture(Gdx.files.internal("Arte/perandando.png"));
		
		transeunte = TextureRegion.split(trananda, 512, 512);
		
		man1anda = new Texture(Gdx.files.internal("Arte/man1andando.png"));
		
		man1 = TextureRegion.split(man1anda, 512, 512);
		
		man2anda = new Texture(Gdx.files.internal("Arte/man2andando.png"));
		
		man2 = TextureRegion.split(man2anda, 512, 512);
		
	}
	
	public void dispose(){
		ALL.dispose();
		UDR.dispose();
		UDL.dispose();
		LRD.dispose();
		LRU.dispose();
		RD.dispose();
		RU.dispose();
		LD.dispose();
		LU.dispose();
		UD.dispose();
		LR.dispose();
		R.dispose();
		D.dispose();
		L.dispose();
		U.dispose();
		
		forninho.dispose();
		logotu.dispose();
		menu2.dispose();
		
		life6.dispose();
		life5.dispose();
		life4.dispose();
		life3.dispose();
		life2.dispose();
		life1.dispose();
		life0.dispose();
	}
}