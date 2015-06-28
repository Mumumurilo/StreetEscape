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
	public Texture loading;
	
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
	
	private TextureRegion[][] manif2Anda;
	private Texture manif2anda;
	
	private TextureRegion[][] manifbossAnda;
	private Texture manifbossanda;
	
	private TextureRegion[][] manifAtira;
	private Texture manifatira;
	
	private TextureRegion[][] manif2Atira;
	private Texture manif2atira;
	
	private TextureRegion[][] manifbossAtira;
	private Texture manifbossatira;
	
	private TextureRegion[][] manifDano;
	private Texture manifdano;
	
	private TextureRegion[][] manif2Dano;
	private Texture manif2dano;

	private TextureRegion[][] manifbossDano;
	private Texture manifbossdano;
	
	private TextureRegion[][] poliAnda;
	private Texture polianda;
	
	private TextureRegion[][] polibossAnda;
	private Texture polibossanda;
	
	private TextureRegion[][] poliAtira;
	private Texture poliatira;
	
	private TextureRegion[][] polibossAtira;
	private Texture polibossatira;
	
	private TextureRegion[][] poliDano;
	private Texture polidano;
	
	private TextureRegion[][] polibossDano;
	private Texture polibossdano;
	
	private TextureRegion[][] nintenAnda;
	private Texture nintenanda;
	
	private TextureRegion[][] nintenbossAnda;
	private Texture nintenbossanda;
	
	private TextureRegion[][] nintenAtira;
	private Texture nintenatira;
	
	private TextureRegion[][] nintenbossAtira;
	private Texture nintenbossatira;
	
	private TextureRegion[][] nintenDano;
	private Texture nintendano;
	
	private TextureRegion[][] nintenbossDano;
	private Texture nintenbossdano;
	
	public Array<Texture> life;
	private Texture life6, life5, life4, life3, life2, life1, life0;
	
	public Texture logotu;
	
	public Texture itens;
	public TextureRegion[][] itensArray;
	
	public Array<TextureRegion> arrayBucks;
	public Array<TextureRegion> arrayTransShots;
	
	private int frameCounter = 1;
	
	public Arte(final MainGame jogo){
		this.jogo = jogo;
		
		forninho = new Texture(Gdx.files.internal("menu.png"));
		logotu = new Texture(Gdx.files.internal("Arte/TU Logo.png"));
		menu2 = new Texture(Gdx.files.internal("menu2.png"));
		loading = new Texture(Gdx.files.internal("loading.png"));
		
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
		
		polianda = new Texture(Gdx.files.internal("Arte/POLI1 WALK.png"));
		poliAnda = TextureRegion.split(polianda, 512, 512);
		
		nintenanda = new Texture(Gdx.files.internal("Arte/NINTEN1 WALK.png"));
		nintenAnda = TextureRegion.split(nintenanda, 512, 512);
		
		manif2anda = new Texture(Gdx.files.internal("Arte/HIPSTER WALK.png"));
		manif2Anda = TextureRegion.split(manif2anda, 512, 512);
		
		manifbossanda = new Texture(Gdx.files.internal("Arte/MANIF2 WALK.png"));
		manifbossAnda = TextureRegion.split(manifbossanda, 512, 512);
		
		polibossanda = new Texture(Gdx.files.internal("Arte/POLI2 WALK.png"));
		polibossAnda = TextureRegion.split(polibossanda, 512, 512);
		
		nintenbossanda = new Texture(Gdx.files.internal("Arte/NINTEN2 WALK.png"));
		nintenbossAnda = TextureRegion.split(nintenbossanda, 512, 512);
		
		manifatira = new Texture(Gdx.files.internal("Arte/MANIF1 ATQ.png"));
		manifAtira = TextureRegion.split(manifatira, 512, 512);
		
		poliatira = new Texture(Gdx.files.internal("Arte/POLI1 ATQ.png"));
		poliAtira = TextureRegion.split(poliatira, 512, 512);
		
		nintenatira = new Texture(Gdx.files.internal("Arte/NINTEN1 ATQ.png"));
		nintenAtira = TextureRegion.split(nintenatira, 512, 512);
		
		manif2atira = new Texture(Gdx.files.internal("Arte/HIPSTER ATQ.png"));
		manif2Atira = TextureRegion.split(manif2atira, 512, 512);
		
		manifbossatira = new Texture(Gdx.files.internal("Arte/MANIF2 ATQ.png"));
		manifbossAtira = TextureRegion.split(manifbossatira, 512, 512);
		
		polibossatira = new Texture(Gdx.files.internal("Arte/POLI2 ATQ.png"));
		polibossAtira = TextureRegion.split(polibossatira, 512, 512);
		
		nintenbossatira = new Texture(Gdx.files.internal("Arte/NINTEN2 ATQ.png"));
		nintenbossAtira = TextureRegion.split(nintenbossatira, 512, 512);
		
		manifdano = new Texture(Gdx.files.internal("Arte/MANIF1 DANO.png"));
		manifDano = TextureRegion.split(manifdano, 512, 512);
		
		polidano = new Texture(Gdx.files.internal("Arte/POLI1 DANO.png"));
		poliDano = TextureRegion.split(polidano, 512, 512);
		
		nintendano = new Texture(Gdx.files.internal("Arte/NINTEN1 DANO.png"));
		nintenDano = TextureRegion.split(nintendano, 512, 512);
		
		manif2dano = new Texture(Gdx.files.internal("Arte/HIPSTER DANO.png"));
		manif2Dano = TextureRegion.split(manif2dano, 512, 512);
		
		manifbossdano = new Texture(Gdx.files.internal("Arte/MANIF2 DANO.png"));
		manifbossDano = TextureRegion.split(manifbossdano, 512, 512);
		
		polibossdano = new Texture(Gdx.files.internal("Arte/POLI2 DANO.png"));
		polibossDano = TextureRegion.split(polibossdano, 512, 512);
		
		nintenbossdano = new Texture(Gdx.files.internal("Arte/NINTEN2 DANO.png"));
		nintenbossDano = TextureRegion.split(nintenbossdano, 512, 512);
		
		itens = new Texture(Gdx.files.internal("Arte/ITENS.png"));
		itensArray = TextureRegion.split(itens, 512, 512);
		
		arrayBucks = new Array<TextureRegion>();
		arrayTransShots = new Array<TextureRegion>();
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
		/*Atentar aos tipos de inimigos:
		 * 1 - Manifestante 
		 * 2 - Policial
		 * 3 - Nontendista
		 * 4 - Hipster
		 * 5 - Boss dos Manifestantes
		 * 6 - Boss dos Policiais
		 * 7 - Boss dos Nontendistas
		 */
		
		if(enemy.frameCounter >= 1 && enemy.frameCounter <= 10 && isMoving){
			if(lado == 1){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[1][1]; //up
				if(type == 2) enemy.currentEnemyFrame = poliAnda[1][1]; //up
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[1][1]; //up
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[1][1]; //up
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[1][1]; //up
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[1][1]; //up
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[1][1]; //up
			}
			if(lado == 2){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[2][1]; //down
				if(type == 2) enemy.currentEnemyFrame = poliAnda[2][1]; //down
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[2][1]; //down
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[2][1]; //down
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[2][1]; //down
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[2][1]; //down
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[2][1]; //down
			}
			if(lado == 3){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[0][1]; //left
				if(type == 2) enemy.currentEnemyFrame = poliAnda[0][1]; //left
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[0][1]; //left
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[0][1]; //left
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[0][1]; //left
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[0][1]; //left
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[0][1]; //left
			}
			if(lado == 4){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[0][1]; //right
				if(type == 2) enemy.currentEnemyFrame = poliAnda[0][1]; //right
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[0][1]; //right
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[0][1]; //right
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[0][1]; //right
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[0][1]; //right
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[0][1]; //right
			}
			
		}else if(enemy.frameCounter > 10 && enemy.frameCounter <= 20 || !isMoving){
			if(lado == 1){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[1][0]; //up
				if(type == 2) enemy.currentEnemyFrame = poliAnda[1][0]; //up
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[1][0]; //up
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[1][0]; //up
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[1][0]; //up
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[1][0]; //up
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[1][0]; //up
			}
			if(lado == 2){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[2][0]; //down
				if(type == 2) enemy.currentEnemyFrame = poliAnda[2][0]; //down
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[2][0]; //down
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[2][0]; //down
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[2][0]; //down
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[2][0]; //down
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[2][0]; //down
			}
			if(lado == 3){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[0][0]; //left
				if(type == 2) enemy.currentEnemyFrame = poliAnda[0][0]; //left
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[0][0]; //left
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[0][0]; //left
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[0][0]; //left
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[0][0]; //left
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[0][0]; //left
			}
			if(lado == 4){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[0][0]; //right
				if(type == 2) enemy.currentEnemyFrame = poliAnda[0][0]; //right
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[0][0]; //right
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[0][0]; //right
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[0][0]; //right
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[0][0]; //right
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[0][0]; //right
			}
			
		}else if(enemy.frameCounter > 20 && enemy.frameCounter <= 30 && isMoving){
			if(lado == 1){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[1][2]; //up
				if(type == 2) enemy.currentEnemyFrame = poliAnda[1][2]; //up
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[1][2]; //up
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[1][2]; //up
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[1][2]; //up
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[1][2]; //up
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[1][2]; //up
			}
			if(lado == 2){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[2][2]; //down
				if(type == 2) enemy.currentEnemyFrame = poliAnda[2][2]; //down
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[2][2]; //down
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[2][2]; //down
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[2][2]; //down
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[2][2]; //down
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[2][2]; //down
			}
			if(lado == 3){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[0][2]; //left
				if(type == 2) enemy.currentEnemyFrame = poliAnda[0][2]; //left
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[0][2]; //left
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[0][2]; //left
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[0][2]; //left
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[0][2]; //left
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[0][2]; //left
			}
			if(lado == 4){
				if(type == 1) enemy.currentEnemyFrame = manifAnda[0][2]; //right
				if(type == 2) enemy.currentEnemyFrame = poliAnda[0][2]; //right
				if(type == 3) enemy.currentEnemyFrame = nintenAnda[0][2]; //right
				if(type == 4) enemy.currentEnemyFrame = manif2Anda[0][2]; //right
				if(type == 5) enemy.currentEnemyFrame = manifbossAnda[0][2]; //right
				if(type == 6) enemy.currentEnemyFrame = polibossAnda[0][2]; //right
				if(type == 7) enemy.currentEnemyFrame = nintenbossAnda[0][2]; //right
			}
		}
		
		if(enemy.currentEnemyFrame != null){
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
		}
		
		enemy.frameCounter++;
		
		if(enemy.frameCounter > 30){
			enemy.frameCounter = 0;
		}
	}
	
	public void spriteEnemyAtira(int lado, Rectangle enemyRect, Enemy enemy, int type){
		/*Atentar aos tipos de inimigos:
		 * 1 - Manifestante 
		 * 2 - Policial
		 * 3 - Nontendista
		 * 4 - Hipster
		 * 5 - Boss dos Manifestantes
		 * 6 - Boss dos Policiais
		 * 7 - Boss dos Nontendistas
		 */

		if(enemy.frameCounter >= 1 && enemy.frameCounter <= 15){
			if(lado == 1){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[0][2]; //up
				if(type == 2) enemy.currentEnemyFrame = poliAtira[0][2]; //up
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[0][2]; //up
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[0][2]; //up
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[0][2]; //up
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[0][2]; //up
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[0][2]; //up
			}
			if(lado == 2){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[1][1]; //down
				if(type == 2) enemy.currentEnemyFrame = poliAtira[1][1]; //down
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[1][1]; //down
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[1][1]; //down
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[1][1]; //down
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[1][1]; //down
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[1][1]; //down
			}
			if(lado == 3){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[0][0]; //left
				if(type == 2) enemy.currentEnemyFrame = poliAtira[0][0]; //left
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[0][0]; //left
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[0][0]; //left
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[0][0]; //left
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[0][0]; //left
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[0][0]; //left
			}
			if(lado == 4){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[0][0]; //right
				if(type == 2) enemy.currentEnemyFrame = poliAtira[0][0]; //right
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[0][0]; //right
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[0][0]; //right
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[0][0]; //right
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[0][0]; //right
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[0][0]; //right
			}
			
		}else if(enemy.frameCounter > 15 && enemy.frameCounter <= 30){
			if(lado == 1){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[1][0]; //up
				if(type == 2) enemy.currentEnemyFrame = poliAtira[1][0]; //up
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[1][0]; //up
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[1][0]; //up
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[1][0]; //up
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[1][0]; //up
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[1][0]; //up
			}
			if(lado == 2){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[1][2]; //down
				if(type == 2) enemy.currentEnemyFrame = poliAtira[1][2]; //down
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[1][2]; //down
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[1][2]; //down
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[1][2]; //down
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[1][2]; //down
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[1][2]; //down
			}
			if(lado == 3){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[0][1]; //left
				if(type == 2) enemy.currentEnemyFrame = poliAtira[0][1]; //left
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[0][1]; //left
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[0][1]; //left
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[0][1]; //left
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[0][1]; //left
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[0][1]; //left
			}
			if(lado == 4){
				if(type == 1) enemy.currentEnemyFrame = manifAtira[0][1]; //right
				if(type == 2) enemy.currentEnemyFrame = poliAtira[0][1]; //right
				if(type == 3) enemy.currentEnemyFrame = nintenAtira[0][1]; //right
				if(type == 4) enemy.currentEnemyFrame = manif2Atira[0][1]; //right
				if(type == 5) enemy.currentEnemyFrame = manifbossAtira[0][1]; //right
				if(type == 6) enemy.currentEnemyFrame = polibossAtira[0][1]; //right
				if(type == 7) enemy.currentEnemyFrame = nintenbossAtira[0][1]; //right
			}
		}
		
		//Condição de flipar para esquerda
		if(enemy.currentEnemyFrame != null){
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
		}
		
		enemy.frameCounter++;
		
		if(enemy.frameCounter > 15){
			enemy.isShooting = false;
			enemy.frameCounter = 0;
		}
	}
	
	public void spriteEnemyDano(int lado, Rectangle enemyRect, Enemy enemy, int type){
		/*Atentar aos tipos de inimigos:
		 * 1 - Manifestante 
		 * 2 - Policial
		 * 3 - Nontendista
		 * 4 - Hipsters
		 * 5 - Boss dos Manifestantes
		 * 6 - Boss dos Policiais
		 * 7 - Boss dos Nontendistas
		 */
		
		//EDITAR OS NÚMEROS!
		if(lado == 1){
			if(type == 1) enemy.currentEnemyFrame = manifDano[0][1]; //up
			if(type == 2) enemy.currentEnemyFrame = poliDano[0][1]; //up
			if(type == 3) enemy.currentEnemyFrame = nintenDano[0][1]; //up
			if(type == 4) enemy.currentEnemyFrame = manif2Dano[0][1]; //up
			if(type == 5) enemy.currentEnemyFrame = manifbossDano[0][1]; //up
			if(type == 6) enemy.currentEnemyFrame = polibossDano[0][1]; //up
			if(type == 7) enemy.currentEnemyFrame = nintenbossDano[0][1]; //up
		}
		if(lado == 2){
			if(type == 1) enemy.currentEnemyFrame = manifDano[0][2]; //down
			if(type == 2) enemy.currentEnemyFrame = poliDano[0][2]; //down
			if(type == 3) enemy.currentEnemyFrame = nintenDano[0][2]; //down
			if(type == 4) enemy.currentEnemyFrame = manif2Dano[0][2]; //down
			if(type == 5) enemy.currentEnemyFrame = manifbossDano[0][2]; //down
			if(type == 6) enemy.currentEnemyFrame = polibossDano[0][2]; //down
			if(type == 7) enemy.currentEnemyFrame = nintenbossDano[0][2]; //down
		}
		if(lado == 3){
			if(type == 1) enemy.currentEnemyFrame = manifDano[0][0]; //left
			if(type == 2) enemy.currentEnemyFrame = poliDano[0][0]; //left
			if(type == 3) enemy.currentEnemyFrame = nintenDano[0][0]; //left
			if(type == 4) enemy.currentEnemyFrame = manif2Dano[0][0]; //left
			if(type == 5) enemy.currentEnemyFrame = manifbossDano[0][0]; //left
			if(type == 6) enemy.currentEnemyFrame = polibossDano[0][0]; //left
			if(type == 7) enemy.currentEnemyFrame = nintenbossDano[0][0]; //left
		}
		if(lado == 4){
			if(type == 1) enemy.currentEnemyFrame = manifDano[0][0]; //right
			if(type == 2) enemy.currentEnemyFrame = poliDano[0][0]; //right
			if(type == 3) enemy.currentEnemyFrame = nintenDano[0][0]; //right
			if(type == 4) enemy.currentEnemyFrame = manif2Dano[0][0]; //right
			if(type == 5) enemy.currentEnemyFrame = manifbossDano[0][0]; //right
			if(type == 6) enemy.currentEnemyFrame = polibossDano[0][0]; //right
			if(type == 7) enemy.currentEnemyFrame = nintenbossDano[0][0]; //right
		}
		//Condição de flipar para esquerda
		if(enemy.currentEnemyFrame != null){
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
		}
				
		enemy.frameCounter++;
		
		if(enemy.frameCounter > 30){
			enemy.isActing = false;
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
		loading.dispose();
		
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
		manifatira.dispose();
		manifdano.dispose();
		nintenanda.dispose();
		nintenatira.dispose();
		nintendano.dispose();
		manif2anda.dispose();
		manif2atira.dispose();
		manif2dano.dispose();
		polianda.dispose();
		poliatira.dispose();
		polidano.dispose();
		manifbossanda.dispose();
		manifbossatira.dispose();
		manifbossdano.dispose();
		nintenbossanda.dispose();
		nintenbossatira.dispose();
		nintenbossdano.dispose();
		polibossanda.dispose();
		polibossatira.dispose();
		polibossdano.dispose();
	}
}