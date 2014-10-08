package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class TelaJogo extends Calculos implements Screen{
	private final MainGame jogo;
	
	private Settings settings;
	private Arte artes;
	
	private int idsala = 0;
	private Rectangle exitN, exitS, exitO, exitL;
	
	private int numEnemy, i = 1, j = 0;
	private Array<Enemy> enemy;
	//private Array<Rectangle> atk;
	private Rectangle temprect;
	private Enemy tempenemy;
	
	private Rectangle transeunte;
		
	public TelaJogo(final MainGame jogo){
		super(jogo);
		this.jogo = jogo;
		
		//Posicionamento da câmera
		jogo.camera.setToOrtho(false, jogo.WIDTH, jogo.HEIGHT);
				
		//Declaração de telas
		
		//Declaração de elementos
		artes = new Arte(jogo);
		settings = new Settings(jogo);
		
		enemy = new Array<Enemy>();
		//atk = new Array<Rectangle>();

		exitN = new Rectangle(264, jogo.HEIGHT - 1, 533 - 264, 5);
		exitS = new Rectangle(264, 1, 533 - 264, 5);
		exitL = new Rectangle(jogo.WIDTH - 1, 107, 5, 370 - 107);
		exitO = new Rectangle(1, 107, 5, 370 - 107);
		
		transeunte = new Rectangle(380, 200, jogo.persowidth, jogo.persoheight);
		
		/*Essa variável terá que se alterar a cada mudança de sala. Terá que se integrar à
		 * lógica de mudança de salas. Está aqui só por provisório mesmo.*/
		numEnemy = MathUtils.random(1, 4);
	}

	@Override
	public void render(float delta) {
		
		//Desenhos
		jogo.batch.begin();
		jogo.batch.draw(artes.salas.get(idsala), 0, 0, jogo.WIDTH, jogo.HEIGHT);
		jogo.batch.end();
		
		//Atualização da câmera e renderer (para debug)
		jogo.camera.update();
		jogo.renderer.setColor(Color.RED);
		
		//Atividade do personagem
		if(settings.debug = true){
			jogo.renderer.begin(ShapeType.Filled);
			jogo.renderer.rect(transeunte.getX(), transeunte.getY(), jogo.persowidth, jogo.persoheight);
			jogo.renderer.end();
		}
		
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
		
		//Atividade do inimigo
		GeraEnemy();
		
		while(j < numEnemy){
			tempenemy = enemy.get(j);
			temprect = tempenemy.enemy;
			
			tempenemy.machine.update();
			tempenemy.movAtk();
			
			if(settings.debug = true){
				jogo.renderer.begin(ShapeType.Filled);
				
				jogo.renderer.setColor(Color.PURPLE);
				for(Rectangle recta : tempenemy.atk){
					jogo.renderer.rect(recta.x, recta.y, recta.width, recta.height);
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
		// TODO Auto-generated method stub
		
	}

}
