package com.tu.streetescape;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Arte {
	final MainGame jogo;
	
	//Declaração das imagens e artes utilizadas no jogo
	public Texture forninho;
	
	private Texture cenario1, esq, cima, baixo, dir;
	public ArrayList<Texture> salas;
	
	public Array<Texture> life;
	private Texture life6, life5, life4, life3, life2, life1, life0;
	
	public Texture logotu;
	
	public Arte(final MainGame jogo){
		this.jogo = jogo;
		
		forninho = new Texture(Gdx.files.internal("menu.png"));
		logotu = new Texture(Gdx.files.internal("Arte/TU Logo.png"));
		
		salas = new ArrayList<Texture>();
		
		salas.add(cenario1 = new Texture(Gdx.files.internal("paulista1.jpg")));
		salas.add(cima = new Texture(Gdx.files.internal("saidacima.jpg")));
		salas.add(esq = new Texture(Gdx.files.internal("saidaesq.jpg")));
		salas.add(baixo = new Texture(Gdx.files.internal("saidabaixo.jpg")));
		salas.add(dir = new Texture(Gdx.files.internal("saidadir.jpg")));
		
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
		cenario1.dispose();
		esq.dispose();
		dir.dispose();
		cima.dispose();
		baixo.dispose();
		
		forninho.dispose();
		
		life6.dispose();
		life5.dispose();
		life4.dispose();
		life3.dispose();
		life2.dispose();
		life1.dispose();
		life0.dispose();
	}
}