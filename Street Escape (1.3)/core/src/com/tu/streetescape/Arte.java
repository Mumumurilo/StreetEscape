package com.tu.streetescape;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Arte {
	final MainGame jogo;
	
	//Declaração das imagens e artes utilizadas no jogo
	public Texture forninho;
	public Texture menu2;
	
	private Texture ALL, UDR, UDL, LRD, LRU, RD, RU, LD, LU, UD, LR, R, D, L, U;
	public ArrayList<Texture> salas;
	public ArrayList<Texture> personagem;
	
	private TextureRegion[][] transeunte;
	private Texture trananda;
	private TextureRegion currentTransFrame;
	
	private boolean isActing = false, isShooting = false;
	
	private TextureRegion[][] transAtira;
	private Texture tranatira;
	
	private TextureRegion[][] transDano;
	private Texture trandano;
	
	private TextureRegion[][] manifAnda;
	private Texture manifanda;
	
	public Array<Texture> life;
	private Texture life6, life5, life4, life3, life2, life1, life0;
	
	public Texture logotu;
	
	public Texture itens;
	public TextureRegion[][] itensArray;
	
	public Array<TextureRegion> arrayBucks;
	
	private int frameCounter = 1;
	
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
		
		tranatira = new Texture(Gdx.files.internal("Arte/ATQ MAIN.png"));
		transAtira = TextureRegion.split(tranatira, 512, 512);
		
		trandano = new Texture(Gdx.files.internal("Arte/MAIN DANO.png"));
		transDano = TextureRegion.split(trandano, 512, 512);
		
		manifanda = new Texture(Gdx.files.internal("Arte/MANIF1 WALK.png"));
		manifAnda = TextureRegion.split(manifanda, 512, 512);
		
		itens = new Texture(Gdx.files.internal("Arte/ITENS.png"));
		itensArray = TextureRegion.split(itens, 512, 512);
		
		arrayBucks = new Array<TextureRegion>();
	}
	
	public void arteLadoTrans(int lado, Rectangle trans, boolean isMoving){
		if(!isActing && !isShooting){
			if(frameCounter >= 1 && frameCounter <= 10 && isMoving){
				if(lado == 1) currentTransFrame = transeunte[1][1]; //up
				if(lado == 2) currentTransFrame = transeunte[2][1]; //down
				if(lado == 3) currentTransFrame = transeunte[0][1]; //left
				if(lado == 4) currentTransFrame = transeunte[0][1]; //right
				
			}else if(frameCounter > 10 && frameCounter <= 20 || !isMoving){
				if(lado == 1) currentTransFrame = transeunte[1][0]; //up
				if(lado == 2) currentTransFrame = transeunte[2][0]; //down
				if(lado == 3) currentTransFrame = transeunte[0][0]; //left
				if(lado == 4) currentTransFrame = transeunte[0][0]; //right
				
			}else if(frameCounter > 20 && frameCounter <= 30 && isMoving){
				if(lado == 1) currentTransFrame = transeunte[1][2]; //up
				if(lado == 2) currentTransFrame = transeunte[2][2]; //down
				if(lado == 3) currentTransFrame = transeunte[0][2]; //left
				if(lado == 4) currentTransFrame = transeunte[0][2]; //right
			}
			
			//Condição de flipar para esquerda
			if(lado == 3){
				currentTransFrame.flip(true, false);
			}
				
			jogo.batch.begin();
			jogo.batch.draw(currentTransFrame, trans.x, trans.y, jogo.persowidth, jogo.persoheight);
			jogo.batch.end();
			
			//Desflipa
			if(lado == 3){
				currentTransFrame.flip(true, false);
			}
			
			frameCounter++;
			
			if(frameCounter > 30){
				frameCounter = 0;
			}
		}
	}
	
	public void spriteTransAtira(int lado, Rectangle trans){
		if(frameCounter >= 1 && frameCounter <= 7){
			if(lado == 1) currentTransFrame = transAtira[1][2]; //up
			if(lado == 2) currentTransFrame = transAtira[2][1]; //down
			if(lado == 3) currentTransFrame = transAtira[1][0]; //left
			if(lado == 4) currentTransFrame = transAtira[1][0]; //right
			
		}else if(frameCounter > 7 && frameCounter <= 15){
			if(lado == 1) currentTransFrame = transAtira[2][0]; //up
			if(lado == 2) currentTransFrame = transAtira[2][2]; //down
			if(lado == 3) currentTransFrame = transAtira[1][1]; //left
			if(lado == 4) currentTransFrame = transAtira[1][1]; //right
		}
		
		//Condição de flipar para esquerda
		if(lado == 3){
			currentTransFrame.flip(true, false);
		}
			
		jogo.batch.begin();
		jogo.batch.draw(currentTransFrame, trans.x, trans.y, jogo.persowidth, jogo.persoheight);
		jogo.batch.end();
		
		//Desflipa
		if(lado == 3){
			currentTransFrame.flip(true, false);
		}
		
		frameCounter++;
		
		if(frameCounter > 15){
			isShooting = false;
			frameCounter = 0;
		}
	}
	
	public void spriteTransDano(Rectangle trans, int lado){
		if(lado == 1) currentTransFrame = transDano[0][1]; //up
		if(lado == 2) currentTransFrame = transDano[0][2]; //down
		if(lado == 3) currentTransFrame = transDano[0][0]; //left
		if(lado == 4) currentTransFrame = transDano[0][0]; //right
		
		//Condição de flipar para esquerda
		if(lado == 3){
			currentTransFrame.flip(true, false);
		}
			
		jogo.batch.begin();
		jogo.batch.draw(currentTransFrame, trans.x, trans.y, jogo.persowidth, jogo.persoheight);
		jogo.batch.end();
		
		//Desflipa
		if(lado == 3){
			currentTransFrame.flip(true, false);
		}
		
		frameCounter++;
		
		if(frameCounter > 30){
			isActing = false;
			frameCounter = 0;
		}
	}
	
	public void arteLadoEnemy(int lado, Rectangle enemyRect, Enemy enemy, boolean isMoving, int type){
		if(enemy.frameCounter >= 1 && enemy.frameCounter <= 10 && isMoving){
			if(lado == 1) enemy.currentEnemyFrame = manifAnda[1][1]; //up
			if(lado == 2) enemy.currentEnemyFrame = manifAnda[2][1]; //down
			if(lado == 3) enemy.currentEnemyFrame = manifAnda[0][1]; //left
			if(lado == 4) enemy.currentEnemyFrame = manifAnda[0][1]; //right
			
		}else if(enemy.frameCounter > 10 && enemy.frameCounter <= 20 || !isMoving){
			if(lado == 1) enemy.currentEnemyFrame = manifAnda[1][0]; //up
			if(lado == 2) enemy.currentEnemyFrame = manifAnda[2][0]; //down
			if(lado == 3) enemy.currentEnemyFrame = manifAnda[0][0]; //left
			if(lado == 4) enemy.currentEnemyFrame = manifAnda[0][0]; //right
			
		}else if(enemy.frameCounter > 20 && enemy.frameCounter <= 30 && isMoving){
			if(lado == 1) enemy.currentEnemyFrame = manifAnda[1][2]; //up
			if(lado == 2) enemy.currentEnemyFrame = manifAnda[2][2]; //down
			if(lado == 3) enemy.currentEnemyFrame = manifAnda[0][2]; //left
			if(lado == 4) enemy.currentEnemyFrame = manifAnda[0][2]; //right
		}
		
		//Condição de flipar para esquerda
		if(lado == 3){
			enemy.currentEnemyFrame.flip(true, false);
		}
			
		jogo.batch.begin();
		jogo.batch.draw(enemy.currentEnemyFrame, enemyRect.x, enemyRect.y, jogo.persowidth, jogo.persoheight);
		jogo.batch.end();
		
		//Desflipa
		if(lado == 3){
			enemy.currentEnemyFrame.flip(true, false);
		}
		
		enemy.frameCounter++;
		
		if(enemy.frameCounter > 30){
			enemy.frameCounter = 0;
		}
	}
	
	public void setFrameCounter(int value){
		frameCounter = value;
	}
	
	public void setIsActing(boolean value){
		isActing = value;
	}
	
	public boolean getIsActing(){
		return isActing;
	}
	
	public boolean getIsShooting() {
		return isShooting;
	}

	public void setIsShooting(boolean isShooting) {
		this.isShooting = isShooting;
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
		
		trananda.dispose();
		tranatira.dispose();
		trandano.dispose();
		manifanda.dispose();		
	}
}