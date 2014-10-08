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
	private double anguloDoTiro;
	
	public StateMachine<Enemy> machine;
	public Array<Rectangle> atk;
	
	public Enemy(MainGame jogo, Rectangle enemy, int type) {
		super(jogo);
		this.enemy = enemy;
		this.type = type;
		
		machine = new DefaultStateMachine<Enemy>(this, EnemyState.ANDAR);
		atk = new Array<Rectangle>();
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
	
	public void atacar(){
		trans = jogo.getTrans();
		
		float randAtk = MathUtils.random(1000000000f, 3000000000f);
		
		//1 = manifestantes, 2 = policiais, 3 = Nontendistas
		if(TimeUtils.nanoTime() - lastAtkTime > randAtk){
			if(type == 1){ //Bombas de vinagre
				Rectangle rect = new Rectangle(enemy.x + jogo.persowidth/2, enemy.y + jogo.persoheight/2, jogo.persowidth/3, jogo.persoheight/3);
				atk.add(rect);
			}else if(type == 2){
				//Policial não atira nada! Ma oeeeeee
			}else if(type == 3){ //Consoles de mesa
				Rectangle rect = new Rectangle(enemy.x + jogo.persowidth/2, enemy.y + jogo.persoheight/2, jogo.persowidth/2, jogo.persoheight/2);
				atk.add(rect);
			}
			lastAtkTime = TimeUtils.nanoTime();
			anguloDoTiro = super.getAngleDaReta(enemy, trans);
		}
	}
	
	public void movAtk(){
		Iterator<Rectangle> iter = atk.iterator();
		
		while(iter.hasNext()){
			Rectangle recta = iter.next();
			recta.x = (float) (recta.x - 3 * Math.sin(anguloDoTiro));
			recta.y = (float) (recta.y - 3 * Math.cos(anguloDoTiro));
			
			if(recta.overlaps(trans)){
				//Definir um set com "true" pra dano do trans
				iter.remove();
			}
			if(recta.x >= jogo.WIDTH || recta.x <= 0 || recta.y >= jogo.HEIGHT || recta.y <= 0){
				iter.remove();
			}
		}
	}
}
