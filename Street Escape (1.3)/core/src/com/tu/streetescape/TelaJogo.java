package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
	public Fases mapa;
	private Rectangle exitN, exitS, exitO, exitL;
	public int salax, salay;
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
	private Rectangle bossRect;
	private boolean oneCheck = true;
	
	//Transeunte
	private Rectangle transeunte;
	private Transeunte trans;
	private float contTransLife = 0;
	
	//Itens
	private int randItem, numItem = 0, k = 0;
	private double addlife;
	private Array<Rectangle> lifeItem;
	private Rectangle tempItem;
	
	//Outros
	private float contFimJogo = 0, contNextFase = 0;
	private boolean deadOnce = true;
	
	//Prédios
	private Predios build;
	private Array<Boolean> ladoColisao;
	
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
		jogo.bossDano1 = Gdx.audio.newSound(Gdx.files.internal("Sons/Boss dano 1.mp3"));
		jogo.bossDano2 = Gdx.audio.newSound(Gdx.files.internal("Sons/Boss dano 2.mp3"));
		jogo.bossTiro1 = Gdx.audio.newSound(Gdx.files.internal("Sons/Boss atira 1.mp3"));
		jogo.bossTiro2 = Gdx.audio.newSound(Gdx.files.internal("Sons/Boss atira 1.mp3"));
		jogo.bossMorre = Gdx.audio.newSound(Gdx.files.internal("Sons/Boss morre.mp3"));
		
		//Declaração de elementos
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		trans = new Transeunte(jogo);
		
		mapa = new Fases(1);
		
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
		
		build = new Predios(jogo);
		
		ladoColisao = new Array<Boolean>();
		for(int i = 0; i < 4; i++){
			ladoColisao.add(false);
		}
		
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
		numarte = build.setPredios();
		
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
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				if(mapa.getNumFase() == 1){
					salax = 7;
					salay = 0;
				}else if(mapa.getNumFase() == 2){
					salax = 1;
					salay = 0;
				}else if(mapa.getNumFase() == 3){
					salax = 4;
					salay = 2;
				}
			}
		}
		
		if(jogo.getTransLife() > 0){
			if(Gdx.input.isKeyPressed(Keys.W) && (transeunte.y + jogo.persoheight <= jogo.HEIGHT) && !ladoColisao.get(0)){ //up
				transeunte.y += 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.S) && (transeunte.y >= 0) && !ladoColisao.get(1)){ //down
				transeunte.y -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.A) && (transeunte.x >= 0) && !ladoColisao.get(2)){ //left
				transeunte.x -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.D) && (transeunte.x + jogo.persowidth <= jogo.WIDTH) && !ladoColisao.get(3)){ //right
				transeunte.x += 200 * Gdx.graphics.getDeltaTime();
			}
			
			if(mapa.sala[salax][salay].enemy == true || checaSalaBoss()){
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
		
		build.condCollide(transeunte, build.actualCollisionTrans);
		build.updateColisao(ladoColisao, build.actualCollisionTrans, transeunte);
		
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
				if(!checaSalaBoss()) GeraEnemy();
				transeunte.y = jogo.HEIGHT - jogo.persoheight - 10;
			}
			if(transeunte.overlaps(exitL) && mapa.sala[salax][salay].exitR == true){
				deletaItens();
				salax = salax + 1;
				i = 1;
				if(!checaSalaBoss()) GeraEnemy();
				transeunte.x = 10;
			}
			if(transeunte.overlaps(exitN) && mapa.sala[salax][salay].exitU == true){
				deletaItens();
				salay = salay - 1;
				i = 1;
				if(!checaSalaBoss()) GeraEnemy();
				transeunte.y = 10;
			}
			if(transeunte.overlaps(exitO) && mapa.sala[salax][salay].exitL == true){
				deletaItens();
				salax = salax - 1;
				i = 1;
				if(!checaSalaBoss()) GeraEnemy();
				transeunte.x = jogo.WIDTH - jogo.persowidth - 10;
			}
		}
		
		if(jogo.isDebug() == true){
			jogo.renderer.begin(ShapeType.Line);
			jogo.renderer.setColor(Color.GREEN);
			
			for(int i = 0; i < 8; i++){
				if(build.colisaoPredios.get(i) == true){
					jogo.renderer.rect(build.predios.get(i).x, build.predios.get(i).y, build.predios.get(i).width, build.predios.get(i).height);
				}
			}
			jogo.renderer.end();
		}
		
		while(k < numItem){
			tempItem = lifeItem.get(k);
			
			jogo.batch.begin();
			jogo.batch.draw(artes.arrayBucks.get(k), lifeItem.get(k).x - lifeItem.get(k).width/2,
					lifeItem.get(k).y - lifeItem.get(k).height/2,
					lifeItem.get(k).width*2, lifeItem.get(k).height*2);
			jogo.batch.end();
			
			if(transeunte.overlaps(tempItem)){
				addlife = jogo.getTransLife();
				addlife += 2;
				jogo.setTransLife(addlife);
				
				numItem--;
				lifeItem.removeIndex(k);
				artes.arrayBucks.removeIndex(k);
				
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
		
		if(numItem > 0){
			for(@SuppressWarnings("unused") TextureRegion itensArray : artes.arrayBucks){
				
			}
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
				
				build.condCollide(temprect, tempenemy.actualCollision);
				build.updateColisao(tempenemy.ladoColisao, tempenemy.actualCollision, temprect);
				
				if(tempenemy.morto){
					enemy.removeIndex(j);
					numEnemy--;
					
					randItem = MathUtils.random(9);
					if(randItem > 6){
						Rectangle item = new Rectangle(temprect.x, temprect.y, jogo.persowidth/2, jogo.persoheight/2);
						lifeItem.add(item);						
						numItem++;
						artes.arrayBucks.add(artes.itensArray[0][1]);
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
		
		//condição de quebrar o ciclo e ir pra próxima fase
		if(contNextFase >= 5){
			jogo.temaboss2.stop();
			jogo.temajogo.play();
			play = false;
			play2 = false;
			deadOnce = true;
			
			deletaItens();
			trans.direcTiros.clear();
			trans.tiros.clear();
			
			int faseAnterior = mapa.getNumFase();
			mapa = new Fases(faseAnterior + 1);
			
			if(mapa.getNumFase() == 2){
				salax = 9;
				salay = 4;
			}else if(mapa.getNumFase() == 3){
				salax = 0;
				salay = 0;
			}
			
			mapa.sala[salax][salay].enemy = false;
			oneCheck = true;
			
			transeunte.x = 380;
			transeunte.y = 200;
			
			contNextFase = 0;
		}
		
		//condição de quebrar o ciclo e ir pra tela de créditos
		if(contFimJogo >= 5){
			jogo.telacreditos = new TelaCreditos(jogo);
			jogo.setScreen(jogo.telacreditos);
		}
	}
	
	public void GeraEnemy(){		
		numEnemy = MathUtils.random(1, 4);
		
		i = 1;
		while(i <= numEnemy){
			Rectangle mau = new Rectangle();
			
			if(!checaSalaBoss()){
				mau = new Rectangle(MathUtils.random(build.predmeiocima.x, build.preddircima.x - jogo.persowidth),
						MathUtils.random(build.predmeiobai.height, build.predmeiocima.y - jogo.persoheight), jogo.persowidth, jogo.persoheight);
				
				boolean proximidadetrans = checaProxRect(mau, transeunte);
				while(proximidadetrans == true){
					mau.x = MathUtils.random(build.predmeiocima.x, build.preddircima.x - jogo.persowidth);
					mau.y = MathUtils.random(build.predmeiobai.height, build.predmeiocima.y - jogo.persoheight);
					proximidadetrans = checaProxRect(mau, transeunte);
				}
			}else{
				mau = new Rectangle(MathUtils.random(0, jogo.WIDTH - jogo.persowidth),
						MathUtils.random(0, jogo.HEIGHT - jogo.persoheight), jogo.persowidth, jogo.persoheight);
				
				boolean proximidadetrans = checaProxRect(mau, transeunte);
				while(proximidadetrans == true){
					mau.x = MathUtils.random(0, jogo.WIDTH - jogo.persowidth);
					mau.y = MathUtils.random(0, jogo.HEIGHT - jogo.persoheight);
					proximidadetrans = checaProxRect(mau, transeunte);
				}
			}
			
			if(mapa.getNumFase() == 1){
				Enemy mal = new Enemy(jogo, mau, 1);
				enemy.add(mal);
			}else if(mapa.getNumFase() == 2){
				Enemy mal = new Enemy(jogo, mau, MathUtils.random(1, 2));
				enemy.add(mal);
			}else if(mapa.getNumFase() == 3){
				Enemy mal = new Enemy(jogo, mau, 3);
				enemy.add(mal);
			}
			
			i++;
		}
	}
	
	//Deleta itens de recover se não forem pegos
	private void deletaItens(){
		numItem = 0;
		lifeItem.clear();
	}
	
	//Condição de sala de boss
	public boolean checaSalaBoss(){
		if(salax == 7 && salay == 0 && mapa.getNumFase() == 1){
			return true;
		}
		if(salax == 1 && salay == 0 && mapa.getNumFase() == 2){
			return true;
		}
		if(salax == 4 && salay == 2 && mapa.getNumFase() == 3){
			return true;
		}
		return false;
	}
	
	private void salaBoss(){
		if(checaSalaBoss()){
			if(oneCheck){
				j = 0;
				bloqueiaSaida = true;
				
				jogo.temajogo.stop();
				jogo.temajogo.dispose();
				
				bossRect = new Rectangle(jogo.WIDTH/2 - jogo.bosswidth/2, jogo.HEIGHT - (jogo.bossheight + 10), jogo.bosswidth, jogo.bossheight);
				boss = new Boss(jogo, bossRect, mapa.getNumFase());
				
				oneCheck = false;
			}
			
			if(jogo.isMusic()){
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
				
				jogo.renderer.setColor(Color.MAROON);
				
				if(boss.type == 1){
					for(Rectangle down : boss.tirodown){
						jogo.renderer.rect(down.x, down.y, down.width, down.height);
					}
					for(Rectangle left : boss.tiroleft){
						jogo.renderer.rect(left.x, left.y, left.width, left.height);
					}
					for(Rectangle right : boss.tiroright){
						jogo.renderer.rect(right.x, right.y, right.width, right.height);
					}
				}
				
				jogo.renderer.setColor(Color.PINK);
				for(Rectangle rect : trans.tiros){
					jogo.renderer.rect(rect.x, rect.y, rect.width, rect.height);
				}
				
				jogo.renderer.end();
			}
			
			boss.machine.update();
			if(boss.type == 1) boss.movAtk();
			trans.movAtk(bossRect, boss);
			
			if(bossRect.overlaps(transeunte) && !jogo.getTransLifeCounter()){
				double temptranslife = jogo.getTransLife();
				temptranslife -= 4;
				jogo.setTransLife(temptranslife);
				jogo.setTransLifeCounter(true);
			}
			
			if(boss.morto){
				bossRect.setPosition(jogo.WIDTH*2, jogo.HEIGHT*2);
				
				if(mapa.getNumFase() < 3){
					contNextFase += Gdx.graphics.getDeltaTime();
				}else{
					contFimJogo += Gdx.graphics.getDeltaTime();
				}
				jogo.batch.begin();
				jogo.gameoverfont.setColor(Color.GREEN);
				if(mapa.getNumFase() < 3){
					jogo.gameoverfont.drawMultiLine(jogo.batch, "     You Won!\nTry Next Stage!", 79, jogo.HEIGHT/2 + 100);
				}else{
					jogo.gameoverfont.drawMultiLine(jogo.batch, "You Won!", jogo.WIDTH/2 - 200, jogo.HEIGHT/2 + 20);
				}
				jogo.batch.end();
				
				if(jogo.isSound() && deadOnce){
					jogo.bossMorre.play();
					deadOnce = false;
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
		jogo.bossDano1.dispose();
		jogo.bossDano2.dispose();
		jogo.bossTiro1.dispose();
		jogo.bossTiro2.dispose();
		jogo.bossMorre.dispose();
	}

}
