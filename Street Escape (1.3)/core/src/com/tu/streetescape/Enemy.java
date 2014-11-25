package com.tu.streetescape;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
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
	
	public Enemy(MainGame jogo, Rectangle enemy, int type) {
		super(jogo);
		this.enemy = enemy;
		this.type = type;
		
		machine = new DefaultStateMachine<Enemy>(this, EnemyState.ANDAR);
		atk = new Array<Rectangle>();
		angles = new Array<Double>();
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
				randirectmov = MathUtils.random(1, 8);
			}
		}
			
		if(contmov < randtimemov){
			contmov += Gdx.graphics.getDeltaTime();
			
			if(randirectmov == 1){ //up
				enemy.y += 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 2){ //down
				enemy.y -= 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 3){ //left
				enemy.x -= 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 4){ //right
				enemy.x += 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 5){ //up and right
				enemy.y += 100 * Gdx.graphics.getDeltaTime();
				enemy.x += 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 6){ //up and left
				enemy.y += 100 * Gdx.graphics.getDeltaTime();
				enemy.x -= 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 7){ //down and right
				enemy.y -= 100 * Gdx.graphics.getDeltaTime();
				enemy.x += 100 * Gdx.graphics.getDeltaTime();
			}
			if(randirectmov == 8){ //down and left
				enemy.y -= 100 * Gdx.graphics.getDeltaTime();
				enemy.x -= 100 * Gdx.graphics.getDeltaTime();
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
			recta.x -= (float) (3 * Math.sin(anguloDoTiro));
			recta.y -= (float) (3 * Math.cos(anguloDoTiro));
			
			if(recta.overlaps(trans) && !jogo.transeunte.getTransLifeCounter()){
				iter.remove();
				ite.remove();
				double translife = jogo.getTransLife() - 1;
				jogo.setTransLife(translife);
				jogo.transeunte.setTransLifeCounter(true);
				
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
			enemy.x -= (float) ((150 * Gdx.graphics.getDeltaTime()) * Math.sin(anguloParaSeguirTrans));
			enemy.y -= (float) ((150 * Gdx.graphics.getDeltaTime()) * Math.cos(anguloParaSeguirTrans));
		}else{
			enemy.x -= (float) ((100 * Gdx.graphics.getDeltaTime()) * Math.sin(anguloParaSeguirTrans));
			enemy.y -= (float) ((100 * Gdx.graphics.getDeltaTime()) * Math.cos(anguloParaSeguirTrans));
		}
	}
	
	public int getType(){
		return type;
	}
}
