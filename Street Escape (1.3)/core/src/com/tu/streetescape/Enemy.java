package com.tu.streetescape;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Calculos{

	private int randirectmov; 
	private float randtimemov = 0, contmov = 0, randstopmov = 0, contstopmov = 0;
	public Rectangle enemy;
	private Rectangle trans;
	
	private int type;
	private float lastAtkTime;
	private double anguloParaSeguirTrans;
	
	public double life = 2;
	public boolean tomouDano = false, morto = false;
	
	public StateMachine<Enemy> machine;
	public Array<Rectangle> atk;
	private Array<Double> angles;
	
	private Predios build;
	public Array<Boolean> actualCollision, ladoColisao;
	
	public int frameCounter = 0;
	public TextureRegion currentEnemyFrame;
	public int lastFacingSide;
	
	public Enemy(MainGame jogo, Rectangle enemy, int type) {
		super(jogo);
		this.enemy = enemy;
		this.type = type;
		build = new Predios(jogo);
		
		machine = new DefaultStateMachine<Enemy>(this, EnemyState.ANDAR);
		atk = new Array<Rectangle>();
		angles = new Array<Double>();
		
		actualCollision = new Array<Boolean>();
		build.setActualCollisionArray(actualCollision);
		
		ladoColisao = new Array<Boolean>();
		for(int i = 0; i < 4; i++){
			ladoColisao.add(false);
		}
	}
	
	public boolean estaLonge(){
		trans = jogo.getTrans();
		boolean dist = checaProxRect(enemy, trans);
		
		if(dist){ //Traduzindo: if(dist == true){ return false;}
			return !dist;
		}
		return true;
	}

	public void movEnemy(){
		if(contmov >= randtimemov){
			if(contstopmov < randstopmov){
				contstopmov += Gdx.graphics.getDeltaTime();
			}

			if(contstopmov >= randstopmov){
				contstopmov = 0;
				randstopmov = MathUtils.random(1);
				contmov = 0;
				randtimemov = MathUtils.random(3);
				//1 = up, 2 = down, 3 = left, 4 = right
				randirectmov = MathUtils.random(1, 4);
			}
		}
			
		if(contmov < randtimemov){
			contmov += Gdx.graphics.getDeltaTime();
			
			if(randirectmov == 1 && !ladoColisao.get(0)){ //up
				enemy.y += 100 * Gdx.graphics.getDeltaTime();
				jogo.telajogo.artes.arteLadoEnemy(1, enemy, this, true, type);
				lastFacingSide = 1;
			}else if(ladoColisao.get(0)){
				contmov = randtimemov;
			}
			
			if(randirectmov == 2 && !ladoColisao.get(1)){ //down
				enemy.y -= 100 * Gdx.graphics.getDeltaTime();
				jogo.telajogo.artes.arteLadoEnemy(2, enemy, this, true, type);
				lastFacingSide = 2;
			}else if(ladoColisao.get(1)){
				contmov = randtimemov;
			}
			
			if(randirectmov == 3 && !ladoColisao.get(2)){ //left
				enemy.x -= 100 * Gdx.graphics.getDeltaTime();
				jogo.telajogo.artes.arteLadoEnemy(3, enemy, this, true, type);
				lastFacingSide = 3;
			}else if(ladoColisao.get(2)){
				contmov = randtimemov;
			}
			
			if(randirectmov == 4 && !ladoColisao.get(3)){ //right
				enemy.x += 100 * Gdx.graphics.getDeltaTime();
				jogo.telajogo.artes.arteLadoEnemy(4, enemy, this, true, type);
				lastFacingSide = 4;
			}else if(ladoColisao.get(3)){
				contmov = randtimemov;
			}
		}
		
		boolean dist = calculaDistanciadaParede(enemy);
		
		if(dist == true){
			int lado = checaLado(enemy);
			if(lado == 1){
				randirectmov = 2;
			}
			if(lado == 2){
				randirectmov = 1;
			}
			if(lado == 3){
				randirectmov = 3;
			}
			if(lado == 4){
				randirectmov = 4;
			}
		}
		
		if(contmov >= randtimemov){
			if(lastFacingSide == 1) jogo.telajogo.artes.arteLadoEnemy(1, enemy, this, false, type);
			if(lastFacingSide == 2) jogo.telajogo.artes.arteLadoEnemy(2, enemy, this, false, type);
			if(lastFacingSide == 3) jogo.telajogo.artes.arteLadoEnemy(3, enemy, this, false, type);
			if(lastFacingSide == 4) jogo.telajogo.artes.arteLadoEnemy(4, enemy, this, false, type);
		}
	}
	
	public void tomaDano(){
		life--;
		
		if(life > 0){
			tomouDano = false;
		}
	}
	
	public void atacar(){
		trans = jogo.getTrans();
		
		float randAtk = MathUtils.random(1000000000f, 3000000000f);
		
		//1 = manifestantes, 2 = policiais, 3 = Nontendistas
		if(TimeUtils.nanoTime() - lastAtkTime > randAtk){
			if(type == 1){ //Bombas de vinagre
				Rectangle rect = new Rectangle(enemy.x + jogo.persowidth/2, enemy.y + jogo.persoheight/2, jogo.persowidth/3, jogo.persoheight/3);
				atk.add(rect);
				if(jogo.isSound()){
					jogo.enemTiro.play();
				}
								
			}else if(type == 2){
				//Policial não atira nada! Ma oeeeeee
				
			}else if(type == 3){ //Consoles de mesa
				Rectangle rect = new Rectangle(enemy.x + jogo.persowidth/2, enemy.y + jogo.persoheight/2, jogo.persowidth/2, jogo.persoheight/2);
				atk.add(rect);
				if(jogo.isSound()){
					jogo.enemTiro.play();
				}
			}
			lastAtkTime = TimeUtils.nanoTime();
			
			double ang = super.getAngleDaReta(enemy, trans);
			angles.add(ang);
		}
	}
	
	public void movAtk(){						
		Iterator<Rectangle> iter = atk.iterator();
		Iterator<Double> ite = angles.iterator();
		
		while(iter.hasNext() && ite.hasNext()){
			Rectangle recta = iter.next();
			double anguloDoTiro = ite.next();
			recta.x -= (float) (4 * Math.sin(anguloDoTiro));
			recta.y -= (float) (4 * Math.cos(anguloDoTiro));
			
			if(recta.overlaps(trans) && !jogo.getTransLifeCounter()){
				iter.remove();
				ite.remove();
				double translife = jogo.getTransLife() - 1;
				jogo.setTransLife(translife);
				jogo.setTransLifeCounter(true);
				
				jogo.telajogo.artes.setIsActing(true);
				
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
			if(recta.x >= jogo.WIDTH || recta.x <= 0 || recta.y >= jogo.HEIGHT || recta.y <= 0){
				iter.remove();
				ite.remove();
			}
		}
	}
	
	public void segueTrans(){
		anguloParaSeguirTrans = super.getAngleDaReta(enemy, trans);
		
		if(type == 2){
			if(!ladoColisao.get(2) || !ladoColisao.get(3)){
				enemy.x -= (float) ((150 * Gdx.graphics.getDeltaTime()) * Math.sin(anguloParaSeguirTrans));
			}
			if(!ladoColisao.get(0) || !ladoColisao.get(1)){
				enemy.y -= (float) ((150 * Gdx.graphics.getDeltaTime()) * Math.cos(anguloParaSeguirTrans));
			}
		}else{
			if(!ladoColisao.get(2) || !ladoColisao.get(3)){
				enemy.x -= (float) ((100 * Gdx.graphics.getDeltaTime()) * Math.sin(anguloParaSeguirTrans));
			}
			if(!ladoColisao.get(0) || !ladoColisao.get(1)){
				enemy.y -= (float) ((100 * Gdx.graphics.getDeltaTime()) * Math.cos(anguloParaSeguirTrans));
			}
		}
	}
	
	public int getType(){
		return type;
	}
}
