package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	
	//Itens
	private int randItem, numItem = 0, k = 0;
	private double addlife;
	private Array<Rectangle> lifeItem;
	private Rectangle tempItem;
	
	//Outros
	private float contFimJogo = 0;
	private boolean deadOnce = true;
	
	//Android
	private Rectangle cima, baixo, esq, dir, shootcima, shootbaixo, shootesq, shootdir;
	private Vector3 touchPos;
	
	//Condi��es m�sica
	private boolean playing, play = false, play2 = false;
		
	public TelaJogo(final MainGame jogo){
		super(jogo);
		this.jogo = jogo;
		
		//Posicionamento da c�mera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.setProjectionMatrix(jogo.camera.combined);
		jogo.renderer.setProjectionMatrix(jogo.camera.combined);
		
		//C�mera para o controller de Android
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
		
		//Declara��o de elementos
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
		
		//Desenhos
		
		if(mapa.sala[salax][salay].getID() == 1111){
			numarte = 0;
		}else if(mapa.sala[salax][salay].getID() == 1101){			
			numarte = 1;			
		}else if(mapa.sala[salax][salay].getID() == 1110){			
			numarte = 2;			
		}else if(mapa.sala[salax][salay].getID() == 1011){			
			numarte = 3;			
		}else if(mapa.sala[salax][salay].getID() == 111){			
			numarte = 4;			
		}else if(mapa.sala[salax][salay].getID() == 1001){			
			numarte = 5;			
		}else if(mapa.sala[salax][salay].getID() == 101){			
			numarte = 6;			
		}else if(mapa.sala[salax][salay].getID() == 1010){			
			numarte = 7;			
		}else if(mapa.sala[salax][salay].getID() == 110){
			numarte = 8;			
		}else if(mapa.sala[salax][salay].getID() == 1100){			
			numarte = 9;			
		}else if(mapa.sala[salax][salay].getID() == 11){			
			numarte = 10;			
		}else if(mapa.sala[salax][salay].getID() == 1000){			
			numarte = 12;			
		}else if(mapa.sala[salax][salay].getID() == 10){			
			numarte = 11;			
		}else if(mapa.sala[salax][salay].getID() == 1){			
			numarte = 14;			
		}else if(mapa.sala[salax][salay].getID() == 100){			
			numarte = 13;			
		}	
		
		jogo.batch.begin();
		
		//Fundo das salas		
		jogo.batch.draw(artes.salas.get(numarte), 0, 0, jogo.WIDTH, jogo.HEIGHT);
		
		jogo.batch.end();
		
		//Atualiza��o da c�mera e renderer (para debug)
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
				transeunte.y += 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.S) && (transeunte.y >= 0)){ //down
				transeunte.y -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.A) && (transeunte.x >= 0)){ //left
				transeunte.x -= 200 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.D) && (transeunte.x + jogo.persowidth <= jogo.WIDTH)){ //right
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
		
		//Bloqueia sa�das
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
			jogo.renderer.begin(ShapeType.Filled);
			jogo.renderer.setColor(Color.GREEN);
			jogo.renderer.rect(exitN.x, exitN.y, exitN.width, exitN.height);
			jogo.renderer.rect(exitS.x, exitS.y, exitS.width, exitS.height);
			jogo.renderer.rect(exitL.x, exitL.y, exitL.width, exitL.height);
			jogo.renderer.rect(exitO.x, exitO.y, exitO.width, exitO.height);
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
		
		//Fonte da GUI (tudo o que for GUI deve ficar aqui em baixo para que nenhum desenho se sobreponha � ela)
		jogo.batch.begin();
		
		//Life (cora��es na tela)
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
		
		//Contador que n�o permite o enemy atacar o transeunte por um tempo depois de levar dano
		if(jogo.getTransLifeCounter()){
			contTransLife += Gdx.graphics.getDeltaTime();
			if(contTransLife >= 3){
				jogo.setTransLifeCounter(false);
				contTransLife = 0;
			}
		}
		
		if(contFimJogo >= 5){
			//condi��o de quebrar o ciclo e ir pra tela de cr�ditos
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
			 * ATEN��O: A terceira refer�ncia do construtor de Enemy � o tipo de inimigo. Acredito que ter�amos que criar algo que modificasse 
			 * o tipo dependendo da varia��o das salas. Temos que discutir isso! Por default, estou deixando 1 (manifestantes).
			 */
			Enemy mal = new Enemy(jogo, mau, 1);
			enemy.add(mal);
			
			i++;
		}
	}
	
	//Deleta itens de recover se n�o forem pegos
	private void deletaItens(){
		numItem = 0;
		lifeItem.clear();
	}
	
	//Condi��o de sala de boss
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
	
	//Outros m�todos
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
