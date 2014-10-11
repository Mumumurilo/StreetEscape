package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TelaJogo extends Calculos implements Screen{
	private final MainGame jogo;
	
	private Settings settings;
	private Arte artes;
	
	private int idsala = 0;
	private Rectangle exitN, exitS, exitO, exitL;
	
	private int numEnemy, i = 1, j = 0;
	private Array<Enemy> enemy;
	private Rectangle temprect;
	private Enemy tempenemy;
	
	private Rectangle transeunte;
	private Transeunte trans;
		
	public TelaJogo(final MainGame jogo){
		super(jogo);
		this.jogo = jogo;
		
		//Posicionamento da c�mera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
				
		//Declara��o de telas
		
		//Declara��o de elementos
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		trans = new Transeunte(jogo);
		
		enemy = new Array<Enemy>();

		exitN = new Rectangle(264, jogo.HEIGHT - 1, 533 - 264, 5);
		exitS = new Rectangle(264, 1, 533 - 264, 5);
		exitL = new Rectangle(jogo.WIDTH - 1, 107, 5, 370 - 107);
		exitO = new Rectangle(1, 107, 5, 370 - 107);
		
		transeunte = new Rectangle(380, 200, jogo.persowidth, jogo.persoheight);
		
		/*Essa vari�vel ter� que se alterar a cada mudan�a de sala. Ter� que se integrar �
		 * l�gica de mudan�a de salas. Est� aqui s� por provis�rio mesmo.*/
		numEnemy = MathUtils.random(1, 4);
	}

	@Override
	public void render(float delta) {
		
		//Desenhos
		jogo.batch.begin();
		
			//Fundo das salas
		jogo.batch.draw(artes.salas.get(idsala), 0, 0, jogo.WIDTH, jogo.HEIGHT);
		
		jogo.batch.end();
		
		//Atualiza��o da c�mera e renderer (para debug)
		jogo.camera.update();
		jogo.renderer.setColor(Color.RED);
		
		//Atividade do personagem
		if(settings.debug = true && jogo.getTransLife() > 0){
			jogo.renderer.begin(ShapeType.Filled);
			jogo.renderer.rect(transeunte.getX(), transeunte.getY(), jogo.persowidth, jogo.persoheight);
			jogo.renderer.end();
		}
		
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
			
			System.out.println(TimeUtils.nanoTime() - trans.lastShotTime + " = " + TimeUtils.nanoTime() + " - " + trans.lastShotTime);
			if(TimeUtils.nanoTime() - trans.lastShotTime > 20000000){
				if(Gdx.input.isKeyJustPressed(Keys.UP)){
					System.out.println("Deu tiro!");
					trans.atirar();
					trans.direcTiros.add(1);
				}
				if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
					System.out.println("Deu tiro!");
					trans.atirar();
					trans.direcTiros.add(2);
				}
				if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
					System.out.println("Deu tiro!");
					trans.atirar();
					trans.direcTiros.add(3);
				}
				if(Gdx.input.isKeyJustPressed(Keys.RIGHT)){
					System.out.println("Deu tiro!");
					trans.atirar();
					trans.direcTiros.add(4);
				}
				trans.lastShotTime = TimeUtils.nanoTime();
			}
		}
				
		if(transeunte.overlaps(exitS)){
			transeunte.y = jogo.HEIGHT - jogo.persoheight - 10;
			if(idsala == 0){
				idsala = 1;
			}
			if(idsala == 3){
				idsala = 0;
			}
		}
		if(transeunte.overlaps(exitL)){
			transeunte.x = 10;
			if(idsala == 0){
				idsala = 2;
			}
			if(idsala == 4){
				idsala = 0;
			}
		}
		if(transeunte.overlaps(exitN)){
			transeunte.y = 10;
			if(idsala == 0){
				idsala = 3;
			}
			if(idsala == 1){
				idsala = 0;
			}
		}
		if(transeunte.overlaps(exitO)){
			transeunte.x = jogo.WIDTH - jogo.persowidth - 10;
			if(idsala == 0){
				idsala = 4;
			}
			if(idsala == 2){
				idsala = 0;
			}
		}
		
		if(settings.debug == true){
			jogo.renderer.begin(ShapeType.Filled);
			jogo.renderer.setColor(Color.GREEN);
			jogo.renderer.rect(exitN.x, exitN.y, exitN.width, exitN.height);
			jogo.renderer.rect(exitS.x, exitS.y, exitS.width, exitS.height);
			jogo.renderer.rect(exitL.x, exitL.y, exitL.width, exitL.height);
			jogo.renderer.rect(exitO.x, exitO.y, exitO.width, exitO.height);
			jogo.renderer.end();
		}
		
		if(jogo.getTransLife() <= 0){
			transeunte.setPosition(1000, 680); //Tempor�rio. Penso que teremos uma anima��o de morte, correto? Vamos perguntar pra Ana
		}
		
		//Atividade do inimigo
		GeraEnemy();
		
		while(j < numEnemy){
			tempenemy = enemy.get(j);
			temprect = tempenemy.enemy;
			
			tempenemy.machine.update();
			tempenemy.movAtk();
			trans.movAtk(temprect);
			
			if(settings.debug = true){
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
		
		//Fonte da GUI (tudo o que for GUI deve ficar aqui em baixo para que nenhum desenho se sobreponha � ela)
		jogo.batch.begin();
		jogo.GUIFont.setColor(Color.WHITE);
		jogo.GUIFont.draw(jogo.batch, "Life: " + jogo.getTransLife(), 10, 460);
		jogo.batch.end();
	}
	
	private void GeraEnemy(){
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
			 * ATEN��O: A terceira refer�ncia do construtor de Enemy � o tipo de inimigo. Acredito que ter�amos que criar algo que modificasse 
			 * o tipo dependendo da varia��o das salas. Temos que discutir isso! Por default, estou deixando 1 (manifestantes).
			 */
			Enemy mal = new Enemy(jogo, mau, 1);
			enemy.add(mal);
			
			i++;
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
		// TODO Auto-generated method stub
		
	}

}
