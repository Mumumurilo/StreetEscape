package com.tu.streetescape;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Arte {
	final MainGame jogo;
	
	//Declaração das imagens e artes utilizadas no jogo
	public Texture forninho;
	public Texture menu2;
	
	private Texture ALL, UDR, UDL, LRD, LRU, RD, RU, LD, LU, UD, LR, R, D, L, U;
	public ArrayList<Texture> salas;
	
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
		salas.add(ALL = new Texture(Gdx.files.internal("ALL.png")));//1, 1111
		salas.add(UDR = new Texture(Gdx.files.internal("UDR.png")));//2, 1101
		salas.add(UDL = new Texture(Gdx.files.internal("UDL.png")));//3, 1110
		salas.add(LRD = new Texture(Gdx.files.internal("LRD.png")));//4, 1011
		salas.add(LRU = new Texture(Gdx.files.internal("LRU.png")));//5, 111
		salas.add(RD = new Texture(Gdx.files.internal("RD.png")));//6, 1001
		salas.add(RU = new Texture(Gdx.files.internal("RU.png")));//7, 101
		salas.add(LD = new Texture(Gdx.files.internal("LD.png")));//8, 1010
		salas.add(LU = new Texture(Gdx.files.internal("LU.png")));//9, 110
		salas.add(UD = new Texture(Gdx.files.internal("UD.png")));//10, 1100
		salas.add(LR = new Texture(Gdx.files.internal("LR.png")));//11, 11
		salas.add(L = new Texture(Gdx.files.internal("L.png")));//12, 10
		salas.add(D = new Texture(Gdx.files.internal("D.png")));//13, 1000
		salas.add(R = new Texture(Gdx.files.internal("R.png")));//14, 1
		salas.add(U = new Texture(Gdx.files.internal("U.png")));//15, 100
		
		life = new Array<Texture>();
		
		life.add(life6 = new Texture(Gdx.files.internal("Arte/0 Heart.png")));
		life.add(life5 = new Texture(Gdx.files.internal("Arte/05 Heart.png")));
		life.add(life4 = new Texture(Gdx.files.internal("Arte/1 Heart.png")));
		life.add(life3 = new Texture(Gdx.files.internal("Arte/15 Hearts.png")));
		life.add(life2 = new Texture(Gdx.files.internal("Arte/2 Hearts.png")));
		life.add(life1 = new Texture(Gdx.files.internal("Arte/25 Hearts.png")));
		life.add(life0 = new Texture(Gdx.files.internal("Arte/3 Hearts.png")));
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