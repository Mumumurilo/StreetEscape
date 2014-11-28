package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TelaJogo extends Calculos implements Screen{
	private final MainGame jogo;
	
	//Classes
	private Arte artes;
	private Settings settings;
	private OrthographicCamera cam2;
	
	//Salas
	private Fases mapa;
	private Rectangle exitN, exitS, exitO, exitL;
	private int salax, salay;
	private int numarte;
	private boolean bloqueiaSaida = false;
	
	//Enemy
	private int numEnemy, i = 1, j = 0;
	private Array<Enemy> enemy;
	private Rectangle temprect;
	private Enemy tempenemy;
	private float lastToque;
	
	//Boss
	private Boss boss;
	private boolean oneCheck = true;
	
	//Transeunte
	private Rectangle transeunte;
	private Transeunte trans;
	private float contTransLife = 0;
	private int movx = 0;
	
	//Itens
	private int randItem, numItem = 0, k = 0;
	private double addlife;
	private Array<Rectangle> lifeItem;
	private Rectangle tempItem;
	
	//Outros
	private float contFimJogo = 0;
	private boolean deadOnce = true;
	private Rectangle predesqcima, predmeiocima, preddircima, predesq, preddir, predesqbai, predmeiobai, preddirbai;
	private boolean esqcima, meiocima, dircima, Esq, Dir, esqbai, meiobai, dirbai;
	private Array<Boolean> colisaoPredios;
	private Array<Rectangle> predios;
	
	//Android
	private Rectangle cima, baixo, esq, dir, shootcima, shootbaixo, shootesq, shootdir;
	private Vector3 touchPos;
	
	//Condições música
	private boolean playing, play = false, play2 = false;
		
	public TelaJogo(final MainGame jogo){
		super(jogo);
		this.jogo = jogo;
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.setProjectionMatrix(jogo.camera.combined);
		jogo.renderer.setProjectionMatrix(jogo.camera.combined);
		
		//Câmera para o controller de Android
		cam2 = new OrthographicCamera();
		cam2.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
				
		//Deleta tela anterior
		jogo.telainicio.dispose();
		jogo.temamenu.dispose();
		
		//Efeitos sonoros
		jogo.enemDano = Gdx.audio.newSound(Gdx.files.internal("Sons/Enemy dano.mp3"));
		jogo.enemMorre = Gdx.audio.newSound(Gdx.files.internal("Sons/Enemy morre.mp3"));
		jogo.enemTiro = Gdx.audio.newSound(Gdx.files.internal("Sons/Enemy tiro.mp3"));
		jogo.enemNDano = Gdx.audio.newSound(Gdx.files.internal("Sons/Enemy N emo dano.mp3"));
		jogo.enemNMorre = Gdx.audio.newSound(Gdx.files.internal("Sons/Enemy N emo morre.mp3"));
		jogo.transDano1 = Gdx.audio.newSound(Gdx.files.internal("Sons/Trans dano 1.mp3"));
		jogo.transDano2 = Gdx.audio.newSound(Gdx.files.internal("Sons/Trans dano 2.mp3"));
		jogo.transMorre = Gdx.audio.newSound(Gdx.files.internal("Sons/Trans morre.mp3"));
		jogo.transAtira = Gdx.audio.newSound(Gdx.files.internal("Sons/Trans atira.mp3"));
		jogo.transRecoverLife = Gdx.audio.newSound(Gdx.files.internal("Sons/Life.mp3"));
		
		//Declaração de elementos
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		trans = new Transeunte(jogo);
		
		mapa = new Fases();
		
		salax = 1;
		salay = 2;
		
		enemy = new Array<Enemy>();
		
		lifeItem = new Array<Rectangle>();

		exitN = new Rectangle(264, jogo.HEIGHT - 1, 533 - 264, 5);
		exitS = new Rectangle(264, 1, 533 - 264, 5);
		exitL = new Rectangle(jogo.WIDTH - 1, 107, 5, 370 - 107);
		exitO = new Rectangle(1, 107, 5, 370 - 107);
		
		cima = new Rectangle(jogo.persowidth + 10, (jogo.persoheight*2) + 10, jogo.persowidth, jogo.persoheight);
		baixo = new Rectangle(jogo.persowidth + 10, 10, jogo.persowidth, jogo.persoheight);
		dir = new Rectangle((jogo.persowidth*2) + 10, jogo.persoheight + 10, jogo.persowidth, jogo.persoheight);
		esq = new Rectangle(10, jogo.persoheight + 10, jogo.persowidth, jogo.persoheight);
		
		predesqcima = new Rectangle(0, jogo.HEIGHT, jogo.WIDTH/3, -(jogo.HEIGHT/3 - jogo.corrigealtura));
		predmeiocima = new Rectangle(jogo.WIDTH/3, jogo.HEIGHT, jogo.WIDTH/3, -(jogo.HEIGHT/3 - jogo.corrigealtura));
		preddircima = new Rectangle((jogo.WIDTH/3) * 2,jogo.HEIGHT, jogo.WIDTH/3, -(jogo.HEIGHT/3 - jogo.corrigealtura));
		predesq = new Rectangle(0, jogo.HEIGHT/3, jogo.WIDTH/3, jogo.HEIGHT/3);
		preddir = new Rectangle((jogo.WIDTH/3) * 2 + 10, jogo.HEIGHT/3, jogo.WIDTH/3, jogo.HEIGHT/3);
		predesqbai = new Rectangle(0, 0, jogo.WIDTH/3, jogo.HEIGHT/3 - jogo.corrigealtura);
		predmeiobai = new Rectangle(jogo.WIDTH/3, 0, jogo.WIDTH/3, jogo.HEIGHT/3 - jogo.corrigealtura);
		preddirbai = new Rectangle((jogo.WIDTH/3) * 2, 0, jogo.WIDTH/3, jogo.HEIGHT/3 - jogo.corrigealtura);
		
		colisaoPredios = new Array<Boolean>();
		colisaoPredios.add(esqcima);
		colisaoPredios.add(meiocima);
		colisaoPredios.add(dircima);
		colisaoPredios.add(Esq);
		colisaoPredios.add(Dir);
		colisaoPredios.add(esqbai);
		colisaoPredios.add(meiobai);
		colisaoPredios.add(dirbai);
		
		predios = new Array<Rectangle>();
		predios.add(predesqcima);
		predios.add(predmeiocima);
		predios.add(preddircima);
		predios.add(predesq);
		predios.add(preddir);
		predios.add(predesqbai);
		predios.add(predmeiobai);
		predios.add(preddirbai);
		
		shootcima = new Rectangle(jogo.WIDTH - (2*jogo.persowidth - 10), (jogo.persoheight*2) + 10, jogo.persowidth, jogo.persoheight);
		shootbaixo = new Rectangle(jogo.WIDTH - (2*jogo.persowidth - 10), 10, jogo.persowidth, jogo.persoheight);
		shootesq = new Rectangle(jogo.WIDTH - (3*jogo.persowidth - 10), jogo.persoheight + 10, jogo.persowidth, jogo.persoheight);
		shootdir = new Rectangle(jogo.WIDTH - (jogo.persowidth - 10), jogo.persoheight + 10, jogo.persowidth, jogo.persoheight);
		
		touchPos = new Vector3();
		
		transeunte = new Rectangle(380, 200, jogo.persowidth, jogo.persoheight);
		
		jogo.temajogo = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 2.mp3"));
		jogo.temajogo.setLooping(true);
		if(jogo.isMusic()){
			jogo.temajogo.play();
		}
		
		jogo.temaboss1 = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 4-1.mp3"));
		jogo.temaboss2 = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 4-2.mp3"));
		jogo.temaboss2.setLooping(true);
	}

	@Override
	public void render(float delta) {
		//Elementos exclusivos de cada tipo de sala
		if(mapa.sala[salax][salay].getID() == 1111){
			resetColisaoPredios();
			numarte = 0;
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1101){
			resetColisaoPredios();
			numarte = 1;
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1110){
			resetColisaoPredios();
			numarte = 2;
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1011){
			resetColisaoPredios();
			numarte = 3;
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 111){
			resetColisaoPredios();
			numarte = 4;
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1001){
			resetColisaoPredios();
			numarte = 5;
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 101){
			resetColisaoPredios();
			numarte = 6;
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1010){
			resetColisaoPredios();
			numarte = 7;	
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 110){
			resetColisaoPredios();
			numarte = 8;	
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1100){
			resetColisaoPredios();
			numarte = 9;		
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 11){
			resetColisaoPredios();
			numarte = 10;
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1000){
			resetColisaoPredios();
			numarte = 12;	
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 10){
			resetColisaoPredios();
			numarte = 11;
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 1){
			resetColisaoPredios();
			numarte = 14;		
			colisaoPredios.set(0, true);
			colisaoPredios.set(1, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}else if(mapa.sala[salax][salay].getID() == 100){
			resetColisaoPredios();
			numarte = 13;			
			colisaoPredios.set(0, true);
			colisaoPredios.set(2, true);
			colisaoPredios.set(3, true);
			colisaoPredios.set(4, true);
			colisaoPredios.set(5, true);
			colisaoPredios.set(6, true);
			colisaoPredios.set(7, true);
		}	
		
		//Desenhos---------------------------------------------------------------------------------------------------------------------------------
		jogo.batch.begin();
		
		//Fundo das salas		
		jogo.batch.draw(artes.salas.get(numarte), 0, 0, jogo.WIDTH, jogo.HEIGHT);
		
		jogo.batch.end();
		
		//Atualização da câmera e renderer (para debug)
		jogo.camera.update();
		jogo.renderer.setColor(Color.RED);
		
		//Atividade do personagem
		if(jogo.isDebug() == true && jogo.getTransLife() > 0){
			jogo.renderer.begin(ShapeType.Filled);
			jogo.renderer.rect(transeunte.getX(), transeunte.getY(), jogo.persowidth, jogo.persoheight);
			
			if(jogo.androidGameplay){
				jogo.renderer.setColor(Color.YELLOW);
				jogo.renderer.rect(cima.x, cima.y, cima.width, cima.height);
				jogo.renderer.rect(baixo.x, baixo.y, baixo.width, baixo.height);
				jogo.renderer.rect(esq.x, esq.y, esq.width, esq.height);
				jogo.renderer.rect(dir.x, dir.y, dir.width, dir.height);
				jogo.renderer.rect(shootcima.x, shootcima.y, shootcima.width, shootcima.height);
				jogo.renderer.rect(shootbaixo.x, shootbaixo.y, shootbaixo.width, shootbaixo.height);
				jogo.renderer.rect(shootesq.x, shootesq.y, shootesq.width, shootesq.height);
				jogo.renderer.rect(shootdir.x, shootdir.y, shootdir.width, shootdir.height);
			}
			
			jogo.renderer.end();
		}
		
		settings.configButtons(jogo.temajogo);
		
		//Debug de boss!
		if(jogo.isDebug()){
			if(Gdx.input.isKeyPressed(Keys.ESCAPE) && mapa.getNumFase() == 1){
				salax = 7;
				salay = 0;
			}
		}
		
		if(jogo.getTransLife() > 0){
			if(Gdx.input.isKeyPressed(Keys.W) && (transeunte.y + jogo.persoheight <= jogo.HEIGHT)){ //up
				if(movx < 12){
					artes.currentFrame = artes.transeunte[1][0];
					
					jogo.batch.begin();
					
					jogo.batch.draw(artes.currentFrame, transeunte.x, transeunte.y, jogo.persowidth, jogo.persoheight);
					
					jogo.batch.end();
				}
				if(movx >= 12 && movx <= 23){
					artes.currentFrame = artes.transeunte[1][1];
					
					jogo.batch.begin();
					
					jogo.batch.draw(artes.currentFrame, transeunte.x, transeunte.y, jogo.persowidth, jogo.persoheight);
					
					jogo.batch.end();
				}
				if(movx >= 24 && movx <= 36){
					artes.currentFrame = artes.transeunte[1][2];
					
					jogo.batch.begin();
					
					jogo.batch.draw(artes.currentFrame, transeunte.x, transeunte.y, jogo.persowidth, jogo.persoheight);
					
					jogo.batch.end();
				}
				movx++;
				if(movx > 36){
					movx = 0;
				}
				transeunte.y += 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.S) && (transeunte.y >= 0)){ //down
				transeunte.y -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.A) && (transeunte.x >= 0)){ //left
				transeunte.x -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.D) && (transeunte.x + jogo.persowidth <= jogo.WIDTH) ){ //right
				transeunte.x += 200 * Gdx.graphics.getDeltaTime();
			}
			
			if(mapa.sala[salax][salay].enemy == true){
				if(Gdx.input.isKeyJustPressed(Keys.UP)){
					trans.atirar();
					if(trans.tiroValido){
						trans.direcTiros.add(1);
						trans.tiroValido = false;
					}
				}
				if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
					trans.atirar();
					if(trans.tiroValido){
						trans.direcTiros.add(2);
						trans.tiroValido = false;
					}
				}
				if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
					trans.atirar();
					if(trans.tiroValido){
						trans.direcTiros.add(3);
						trans.tiroValido = false;
					}
				}
				if(Gdx.input.isKeyJustPressed(Keys.RIGHT)){
					trans.atirar();
					if(trans.tiroValido){
						trans.direcTiros.add(4);
						trans.tiroValido = false;
					}
				}
			}
			
			for(i = 0; i < 5; i++){
				if(Gdx.input.isTouched(i)){
					touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
					cam2.unproject(touchPos);
					
					if(cima.contains(touchPos.x, touchPos.y)){
						transeunte.y += 150 * Gdx.graphics.getDeltaTime();
					}
					if(baixo.contains(touchPos.x, touchPos.y)){
						transeunte.y -= 150 * Gdx.graphics.getDeltaTime();
					}
					if(esq.contains(touchPos.x, touchPos.y)){
						transeunte.x -= 150 * Gdx.graphics.getDeltaTime();
					}
					if(dir.contains(touchPos.x, touchPos.y)){
						transeunte.x += 150 * Gdx.graphics.getDeltaTime();
					}
					
					if(mapa.sala[salax][salay].enemy == true){
						if(shootcima.contains(touchPos.x, touchPos.y)){
							trans.atirar();
							if(trans.tiroValido){
								trans.direcTiros.add(1);
								trans.tiroValido = false;
							}
						}
						if(shootbaixo.contains(touchPos.x, touchPos.y)){
							trans.atirar();
							if(trans.tiroValido){
								trans.direcTiros.add(2);
								trans.tiroValido = false;
							}
						}
						if(shootesq.contains(touchPos.x, touchPos.y)){
							trans.atirar();
							if(trans.tiroValido){
								trans.direcTiros.add(3);
								trans.tiroValido = false;
							}
						}
						if(shootdir.contains(touchPos.x, touchPos.y)){
							trans.atirar();
							if(trans.tiroValido){
								trans.direcTiros.add(4);
								trans.tiroValido = false;
							}
						}
					}
				}
			}
		}
		
		//condCollide(transeunte);
		
		//Bloqueia saídas
		if(!checaSalaBoss()){
			if(mapa.sala[salax][salay].enemy == true){
				bloqueiaSaida = true;
			}else{
				bloqueiaSaida = false;
			}
		}
		
		if(checaSalaBoss()){
			salaBoss();
		}
		
		if(!bloqueiaSaida){
			if(transeunte.overlaps(exitS) && mapa.sala[salax][salay].exitD == true){
				deletaItens();
				i = 1;
				salay = salay + 1;
				GeraEnemy();
				transeunte.y = jogo.HEIGHT - jogo.persoheight - 10;
			}
			if(transeunte.overlaps(exitL) && mapa.sala[salax][salay].exitR == true){
				deletaItens();
				salax = salax + 1;
				i = 1;
				GeraEnemy();
				transeunte.x = 10;
			}
			if(transeunte.overlaps(exitN) && mapa.sala[salax][salay].exitU == true){
				deletaItens();
				salay = salay - 1;
				i = 1;
				GeraEnemy();
				transeunte.y = 10;
			}
			if(transeunte.overlaps(exitO) && mapa.sala[salax][salay].exitL == true){
				deletaItens();
				salax = salax - 1;
				i = 1;
				GeraEnemy();
				transeunte.x = jogo.WIDTH - jogo.persowidth - 10;
			}
		}
		
		if(jogo.isDebug() == true){
			jogo.renderer.begin(ShapeType.Line);
			jogo.renderer.setColor(Color.GREEN);
			
			for(int i = 0; i < 8; i++){
				if(colisaoPredios.get(i) == true){
					jogo.renderer.rect(predios.get(i).x, predios.get(i).y, predios.get(i).width, predios.get(i).height);
				}
			}
			
			jogo.renderer.end();
		}
		
		while(k < numItem){
			tempItem = lifeItem.get(k);
			if(transeunte.overlaps(tempItem)){
				addlife = jogo.getTransLife();
				addlife += 2;
				jogo.setTransLife(addlife);
				
				numItem--;
				lifeItem.removeIndex(k);
				
				if(jogo.isSound()){
					jogo.transRecoverLife.play();
				}
			}
			
			if(jogo.isDebug()){
				jogo.renderer.begin(ShapeType.Filled);
				jogo.renderer.setColor(Color.ORANGE);
				for(Rectangle life : lifeItem){
					jogo.renderer.rect(life.x, life.y, life.width, life.height);
				}
				jogo.renderer.end();
			}
			k++;
		}
		if(k >= numItem){
			k = 0;
		}
		
		if(jogo.getTransLife() <= 0){
			transeunte.setPosition(1000, 680);
		}
		if(jogo.getTransLife() > 6){
			jogo.setTransLife(6);
		}
		
		//Atividade do inimigo---------------------------------------------------------------------------------------------------------------
		
		if(mapa.sala[salax][salay].enemy){
			while(j < numEnemy){
				tempenemy = enemy.get(j);
				temprect = tempenemy.enemy;
				
				tempenemy.machine.update();
				tempenemy.movAtk();
				trans.movAtk(temprect, tempenemy, numEnemy);
				
				if(tempenemy.morto){
					enemy.removeIndex(j);
					numEnemy--;
					
					randItem = MathUtils.random(9);
					if(randItem > 6){
						Rectangle item = new Rectangle(temprect.x, temprect.y, jogo.persowidth/2, jogo.persoheight/2);
						lifeItem.add(item);
						numItem++;
					}
					
					if(jogo.isSound()){
						if(tempenemy.getType() == 3){
							jogo.enemNMorre.play();
						}else{
							jogo.enemMorre.play();
						}
					}
				}
				
				if(temprect.overlaps(transeunte) && (TimeUtils.nanoTime() - lastToque) >= 2000000000 && !jogo.getTransLifeCounter()){
					double temptranslife = jogo.getTransLife();
					temptranslife -= 2;
					jogo.setTransLife(temptranslife);
					jogo.setTransLifeCounter(true);
					lastToque = TimeUtils.nanoTime();
					
					if(jogo.isSound()){
						boolean randDano = MathUtils.randomBoolean();
						if(jogo.getTransLife() >= 1){
							if(randDano){
								jogo.transDano1.play();
							}
							if(!randDano){
								jogo.transDano2.play();
							}
						}
					}
				}
				
				if(jogo.isDebug() == true){
					jogo.renderer.begin(ShapeType.Filled);
					
					jogo.renderer.setColor(Color.PURPLE);
					for(Rectangle recta : tempenemy.atk){
						jogo.renderer.rect(recta.x, recta.y, recta.width, recta.height);
					}
					
					jogo.renderer.setColor(Color.PINK);
					for(Rectangle rect : trans.tiros){
						jogo.renderer.rect(rect.x, rect.y, rect.width, rect.height);
					}
					
					jogo.renderer.setColor(Color.BLUE);
					jogo.renderer.rect(temprect.getX(), temprect.getY(), jogo.persowidth, jogo.persoheight);
					jogo.renderer.end();
				}
				j++;
			}
			if(j >= numEnemy){
				j = 0;
			}
			if(numEnemy == 0){
				mapa.sala[salax][salay].enemy = false;
				trans.direcTiros.clear();
				trans.tiros.clear();
			}
		}
		
		//Fonte da GUI (tudo o que for GUI deve ficar aqui em baixo para que nenhum desenho se sobreponha à ela)
		jogo.batch.begin();
		
		//Life (corações na tela)
		int momlife = (int) jogo.getTransLife();
		if(momlife < 0){
			momlife = 0;
		}
		
		jogo.batch.draw(artes.life.get(momlife), 10, 420);
		
		jogo.batch.end();
		
		if(jogo.getTransLife() <= 0){
			if(deadOnce){
				if(jogo.isSound()){
					jogo.transMorre.play();
				}
				deadOnce = false;
			}
			contFimJogo += Gdx.graphics.getDeltaTime();
			jogo.batch.begin();
			jogo.gameoverfont.setColor(Color.GREEN);
			jogo.gameoverfont.draw(jogo.batch, "Game Over!", jogo.WIDTH/2 - 245, jogo.HEIGHT/2 + 20);
			jogo.batch.end();
		}
		
		//Contador que não permite o enemy atacar o transeunte por um tempo depois de levar dano
		if(jogo.getTransLifeCounter()){
			contTransLife += Gdx.graphics.getDeltaTime();
			if(contTransLife >= 3){
				jogo.setTransLifeCounter(false);
				contTransLife = 0;
			}
		}
		
		if(contFimJogo >= 5){
			//condição de quebrar o ciclo e ir pra tela de créditos
			jogo.telacreditos = new TelaCreditos(jogo);
			jogo.setScreen(jogo.telacreditos);
		}
	}
	
	private void GeraEnemy(){
		numEnemy = MathUtils.random(1, 4);
		
		while(i <= numEnemy){
			Rectangle mau = new Rectangle(MathUtils.random(jogo.WIDTH/3, (jogo.WIDTH/3)*2),
					MathUtils.random(jogo.HEIGHT/3, (jogo.HEIGHT/3)*2), jogo.persowidth, jogo.persoheight);
			
			boolean proximidadetrans = checaProxRect(mau, transeunte);
			while(proximidadetrans == true){
				mau.x = MathUtils.random(jogo.WIDTH/3, (jogo.WIDTH/3)*2);
				mau.y = MathUtils.random(jogo.HEIGHT/3, (jogo.HEIGHT/3)*2);
				proximidadetrans = checaProxRect(mau, transeunte);
			}
						
			/*
			 * ATENÇÃO: A terceira referência do construtor de Enemy é o tipo de inimigo. Acredito que teríamos que criar algo que modificasse 
			 * o tipo dependendo da variação das salas. Temos que discutir isso! Por default, estou deixando 1 (manifestantes).
			 */
			Enemy mal = new Enemy(jogo, mau, 1);
			enemy.add(mal);
			
			i++;
		}
	}
	
	//Deleta itens de recover se não forem pegos
	private void deletaItens(){
		numItem = 0;
		lifeItem.clear();
	}
	
	//Condição de sala de boss
	private boolean checaSalaBoss(){
		if(salax == 7 && salay == 0 && mapa.getNumFase() == 1){
			return true;
		}
		if(salax == 0 && salay == 0 && mapa.getNumFase() == 2){
			return true;
		}
		if(salax == 0 && salay == 0 && mapa.getNumFase() == 3){
			return true;
		}
		return false;
	}
	
	private void salaBoss(){
		if(checaSalaBoss()){
			if(oneCheck){
				mapa.sala[salax][salay].enemy = false;
				bloqueiaSaida = true;
				
				jogo.temajogo.stop();
				jogo.temajogo.dispose();
				
				boss = new Boss(jogo, 1);
				
				oneCheck = false;
			}
			
			if(jogo.isSound()){
				if(!play){
					jogo.temaboss1.play();
					play = true;
				}
				playing = jogo.temaboss1.isPlaying();
				if(playing == false && play2 == false){
					jogo.temaboss2.play();
					play2 = true;
				}
			}
			
			if(jogo.isDebug() == true){
				jogo.renderer.setColor(Color.TEAL);
				jogo.renderer.begin(ShapeType.Filled);
				jogo.renderer.rect(boss.bossRect.x, boss.bossRect.y, boss.bossRect.width, boss.bossRect.height);
				jogo.renderer.end();
			}
		}
	}
	
	private void resetColisaoPredios(){
		for(int i = 0; i < 8; i++){
			colisaoPredios.set(i, false);
		}
	}
	
	private void condCollide(Rectangle perso){
		Rectangle intersection = new Rectangle();
		
		for(int i = 0; i < 8; i++){
			if(colisaoPredios.get(i)){
				Intersector.intersectRectangles(perso, predios.get(i), intersection);				
				
				if(jogo.isDebug()){
					jogo.renderer.setColor(Color.YELLOW);
					jogo.renderer.begin(ShapeType.Line);
					jogo.renderer.rect(intersection.x, intersection.y, intersection.width, intersection.height);
					jogo.renderer.end();
				}
				
				if(intersection.x > perso.x){
					//perso.y = predios.get(i).y - predios.get(i).height;
					System.out.println("A");
				}
				if(intersection.y > perso.y){
					//perso.y = predios.get(i).y + predios.get(i).height;
					System.out.println("B");
				}
				if(intersection.x + intersection.width < perso.x + perso.width){
					//perso.x = predios.get(i).x + predios.get(i).width;
					System.out.println("C");
				}
				if(intersection.y + intersection.height < perso.y + perso.height){
					//perso.x = predios.get(i).x - predios.get(i).width;
					System.out.println("D");
				}
			}
		}
	}
	
	//Outros métodos
	public Rectangle getTrans(){
		return transeunte;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//Sons
		jogo.enemDano.dispose();
		jogo.enemMorre.dispose();
		jogo.enemNDano.dispose();
		jogo.enemNMorre.dispose();
		jogo.enemTiro.dispose();
		jogo.transAtira.dispose();
		jogo.transDano1.dispose();
		jogo.transDano2.dispose();
		jogo.transMorre.dispose();
	}

}
