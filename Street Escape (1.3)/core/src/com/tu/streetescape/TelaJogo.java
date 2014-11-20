package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TelaJogo extends Calculos implements Screen{
	private final MainGame jogo;
	
	private Arte artes;
	private Settings settings;
	
	private int idsala = 0;
	private Rectangle exitN, exitS, exitO, exitL;
	private boolean existeEnemy = false;
	
	private int numEnemy, i = 1, j = 0;
	private Array<Enemy> enemy;
	private Rectangle temprect;
	private Enemy tempenemy;
	private float lastToque;
	
	private Rectangle transeunte;
	private Transeunte trans;
	
	private float contFimJogo = 0;
	private boolean deadOnce = true;
	
	private Rectangle cima, baixo, esq, dir, shootcima, shootbaixo, shootesq, shootdir;
		
	public TelaJogo(final MainGame jogo){
		super(jogo);
		this.jogo = jogo;
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.setProjectionMatrix(jogo.camera.combined);
		jogo.renderer.setProjectionMatrix(jogo.camera.combined);
				
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
		
		//Declaração de elementos
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		trans = new Transeunte(jogo);
		
		enemy = new Array<Enemy>();

		exitN = new Rectangle(264, jogo.HEIGHT - 1, 533 - 264, 5);
		exitS = new Rectangle(264, 1, 533 - 264, 5);
		exitL = new Rectangle(jogo.WIDTH - 1, 107, 5, 370 - 107);
		exitO = new Rectangle(1, 107, 5, 370 - 107);
		
		cima = new Rectangle(0, (jogo.HEIGHT/4)*3, jogo.WIDTH, jogo.HEIGHT/4);
		baixo = new Rectangle(0, 0, jogo.WIDTH, jogo.HEIGHT/4);
		dir = new Rectangle((jogo.WIDTH/4)*3, 0, jogo.WIDTH/4, jogo.HEIGHT);
		esq = new Rectangle(0, 0, jogo.WIDTH/4, jogo.HEIGHT);
		
		shootcima = new Rectangle(jogo.WIDTH/4, jogo.HEIGHT/2, jogo.WIDTH/4, jogo.HEIGHT/4);
		shootbaixo = new Rectangle(jogo.WIDTH/4, jogo.HEIGHT/4, jogo.WIDTH/4, jogo.HEIGHT/4);
		shootesq = new Rectangle(jogo.WIDTH/4, jogo.HEIGHT/4, jogo.WIDTH/4, jogo.HEIGHT/2);
		shootdir = new Rectangle(jogo.WIDTH/2, jogo.HEIGHT/4, jogo.WIDTH/4, jogo.HEIGHT/2);
		
		transeunte = new Rectangle(380, 200, jogo.persowidth, jogo.persoheight);
		
		jogo.temajogo = Gdx.audio.newMusic(Gdx.files.internal("Musica/StreetEscape 2.mp3"));
		jogo.temajogo.setLooping(true);
		if(jogo.isMusic()){
			jogo.temajogo.play();
		}
	}

	@Override
	public void render(float delta) {
		
		//Desenhos
		jogo.batch.begin();
		
		//Fundo das salas
		jogo.batch.draw(artes.salas.get(idsala), 0, 0, jogo.WIDTH, jogo.HEIGHT);
		
		jogo.batch.end();
		
		//Atualização da câmera e renderer (para debug)
		jogo.camera.update();
		jogo.renderer.setColor(Color.RED);
		
		//Atividade do personagem
		if(jogo.isDebug() == true && jogo.getTransLife() > 0){
			jogo.renderer.begin(ShapeType.Filled);
			jogo.renderer.rect(transeunte.getX(), transeunte.getY(), jogo.persowidth, jogo.persoheight);
			jogo.renderer.end();
		}
		
		settings.configButtons(jogo.temajogo);
		
		if(jogo.getTransLife() > 0){
			if(Gdx.input.isKeyPressed(Keys.W) && (transeunte.y + jogo.persoheight <= jogo.HEIGHT)){ //up
				transeunte.y += 150 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.S) && (transeunte.y >= 0)){ //down
				transeunte.y -= 150 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.A) && (transeunte.x >= 0)){ //left
				transeunte.x -= 150 * Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.D) && (transeunte.x + jogo.persowidth <= jogo.WIDTH)){ //right
				transeunte.x += 150 * Gdx.graphics.getDeltaTime();
			}
			
			if(Gdx.input.isTouched()){
				Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				jogo.camera.unproject(touchPos);
				
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
			}
			
			if(existeEnemy){
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
			
			if(Gdx.input.isTouched()){
				Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				jogo.camera.unproject(touchPos);
				
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

		if(transeunte.overlaps(exitS)){
			existeEnemy = true;
			i = 1;
			GeraEnemy();
			transeunte.y = jogo.HEIGHT - jogo.persoheight - 10;
			if(idsala == 0){
				idsala = 1;
			}
			if(idsala == 3){
				idsala = 0;
			}
		}
		if(transeunte.overlaps(exitL)){
			existeEnemy = true;
			i = 1;
			GeraEnemy();
			transeunte.x = 10;
			if(idsala == 0){
				idsala = 2;
			}
			if(idsala == 4){
				idsala = 0;
			}
		}
		if(transeunte.overlaps(exitN)){
			existeEnemy = true;
			i = 1;
			GeraEnemy();
			transeunte.y = 10;
			if(idsala == 0){
				idsala = 3;
			}
			if(idsala == 1){
				idsala = 0;
			}
		}
		if(transeunte.overlaps(exitO)){
			existeEnemy = true;
			i = 1;
			GeraEnemy();
			transeunte.x = jogo.WIDTH - jogo.persowidth - 10;
			if(idsala == 0){
				idsala = 4;
			}
			if(idsala == 2){
				idsala = 0;
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
		
		if(jogo.getTransLife() <= 0){
			transeunte.setPosition(1000, 680);
		}
		
		//Atividade do inimigo----------------------------------------------------------------
		
		if(existeEnemy){
			while(j < numEnemy){
				tempenemy = enemy.get(j);
				temprect = tempenemy.enemy;
				
				tempenemy.machine.update();
				tempenemy.movAtk();
				trans.movAtk(temprect, tempenemy, numEnemy);
				
				if(tempenemy.morto){
					enemy.removeIndex(j);
					numEnemy--;
					
					if(jogo.isSound()){
						if(tempenemy.getType() == 3){
							jogo.enemNMorre.play();
						}else{
							jogo.enemMorre.play();
						}
					}
				}
				
				if(temprect.overlaps(transeunte) && (TimeUtils.nanoTime() - lastToque) >= 2000000000){
					double temptranslife = jogo.getTransLife();
					temptranslife -= 2;
					jogo.setTransLife(temptranslife);
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
				existeEnemy = false;
				trans.direcTiros.clear();
				trans.tiros.clear();
			}
		}
		
		//Fonte da GUI (tudo o que for GUI deve ficar aqui em baixo para que nenhum desenho se sobreponha à ela)
		jogo.batch.begin();
		
		//Life
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
		
		if(contFimJogo >= 5){
			//condição de quebrar o ciclo e ir pra tela de créditos
			jogo.telacreditos = new TelaCreditos(jogo);
			jogo.setScreen(jogo.telacreditos);
		}
	}
	
	private void GeraEnemy(){
		numEnemy = MathUtils.random(1, 4);
		
		while(i <= numEnemy){
			Rectangle mau = new Rectangle(MathUtils.random(jogo.WIDTH - 750, jogo.WIDTH - 50),
					MathUtils.random(jogo.HEIGHT - 430, jogo.HEIGHT - 50), jogo.persowidth, jogo.persoheight);
			
			boolean proximidadetrans = checaProxRect(mau, transeunte);
			while(proximidadetrans == true){
				mau.x = MathUtils.random(jogo.WIDTH - 750, jogo.WIDTH - 50);
				mau.y = MathUtils.random(jogo.HEIGHT - 430, jogo.HEIGHT - 50);
				proximidadetrans = checaProxRect(mau, transeunte);
			}
			if(i >= 2){
				Enemy mal = enemy.get(i - 2);
				boolean proximidadeOtherEnemy = checaProxRect(mau, mal.enemy);
				while(proximidadeOtherEnemy == true){
					mau.x = MathUtils.random(jogo.WIDTH - 750, jogo.WIDTH - 50);
					mau.y = MathUtils.random(jogo.HEIGHT - 430, jogo.HEIGHT - 50);
					proximidadeOtherEnemy = checaProxRect(mau, mal.enemy);
				}
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
