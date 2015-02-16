package com.tu.streetescape;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Boss extends Calculos{
	
	public Rectangle bossRect;
	public StateMachine<Boss> machine;
	
	public int life = 20, type;
	public boolean tomouDano = false, morto = false;
	
	public float tiroCounter = 0;
	public int randAtk, enemyCounter = 0, counter = 1;
	public Array<Rectangle> tirodown, tiroleft, tiroright;
	private boolean goForward = true;
	
	boolean singleScream = false, one = true;
	
	public Boss(final MainGame jogo, Rectangle bossRect, int type) {
		super(jogo);
		this.bossRect = bossRect;
		this.type = type;
		machine = new DefaultStateMachine<Boss>(this, BossState.ATACAR);
		
		if(type == 1){
			tirodown = new Array<Rectangle>();
			tiroleft = new Array<Rectangle>();
			tiroright = new Array<Rectangle>();
		}
	}
	
	public void atacar(){
		//Bombas de vinagre
		Rectangle rect = new Rectangle(bossRect.x + jogo.bosswidth/2, bossRect.y + jogo.bossheight/2, jogo.bosswidth/3, jogo.bossheight/3);
		Rectangle rectleft = new Rectangle(bossRect.x + jogo.bosswidth/2, bossRect.y + jogo.bossheight/2, jogo.bosswidth/3, jogo.bossheight/3);
		Rectangle rectright = new Rectangle(bossRect.x + jogo.bosswidth/2, bossRect.y + jogo.bossheight/2, jogo.bosswidth/3, jogo.bossheight/3);
		
		tirodown.add(rect);
		tiroleft.add(rectleft);
		tiroright.add(rectright);
	}
	
	public void tomaDano(){
		life--;
		
		if(life > 0){
			tomouDano = false;
		}
		
		if(jogo.isSound() && life > 0){
			boolean randDan = MathUtils.randomBoolean();
			if(randDan){
				jogo.bossDano1.play();
			}else{
				jogo.bossDano2.play();
			}
		}
	}
	
	public void andar(){
		if(jogo.getTransLife() > 0){
			if(bossRect.x + jogo.bosswidth/2 < jogo.getTrans().x + jogo.getTrans().width/2){
				bossRect.x += 100 * Gdx.graphics.getDeltaTime();
			}
			if(bossRect.x + jogo.bosswidth/2 > jogo.getTrans().x + jogo.getTrans().width/2){
				bossRect.x -= 100 * Gdx.graphics.getDeltaTime();
			}
		}
		tiroCounter += Gdx.graphics.getDeltaTime();
		
		if(tiroCounter >= 5 && singleScream && type != 3){ //Tipos 1 e 2
			if(jogo.isSound()){
				boolean randShot = MathUtils.randomBoolean();
				if(randShot){
					jogo.bossTiro1.play();
				}else{
					jogo.bossTiro2.play();
				}
			}
			singleScream = false;
		}
		if(tiroCounter >= 3 && singleScream && type == 3){ //Tipo 3
			if(jogo.isSound()){
				boolean randShot = MathUtils.randomBoolean();
				if(randShot){
					jogo.bossTiro1.play();
				}else{
					jogo.bossTiro2.play();
				}
			}
			singleScream = false;
		}
		
		if(tiroCounter >= 5){			
			if(randAtk == 0 && type == 1){
				atacar();
				tiroCounter = 0;
				singleScream = true;
				
				if(jogo.isSound()){
					boolean randShot = MathUtils.randomBoolean();
					if(randShot){
						jogo.bossTiro1.play();
					}else{
						jogo.bossTiro2.play();
					}
				}
			}else if(randAtk == 0){
				randAtk = MathUtils.random(1, 2);
			}
			
			if(randAtk == 1){
				if(one){
					singleScream = true;
					one = false;
				}
				
				if(type == 1){
					if(goForward){
						if(bossRect.y > jogo.getTrans().y){
							bossRect.y -= 500 * Gdx.graphics.getDeltaTime();
						}else{
							goForward = false;
						}
					}else{
						if(bossRect.y < jogo.HEIGHT - (jogo.bossheight + 10)){
							bossRect.y += 500 * Gdx.graphics.getDeltaTime();
						}else{
							tiroCounter = 0;
							one = true;
						}
					}
				}else if(type == 2){
					if(goForward && counter < 6){
						if(bossRect.y > jogo.getTrans().y - 50){
							bossRect.y -= 500 * Gdx.graphics.getDeltaTime();
						}else{
							goForward = false;
							counter++;
						}
					}else if(counter < 6){
						if(bossRect.y < jogo.getTrans().y + 50){
							bossRect.y += 500 * Gdx.graphics.getDeltaTime();
						}else{
							goForward = true;
							counter++;
						}
					}
					
					if(counter >= 6){
						if(bossRect.y < jogo.HEIGHT - (jogo.bossheight + 10)){
							bossRect.y += 500 * Gdx.graphics.getDeltaTime();
						}else{
							tiroCounter = 0;
							one = true;
						}
					}
				}else if(type == 3){
					randAtk = 2;
				}
			}

			if(randAtk == 2 && !jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].enemy){				
				if(enemyCounter == 0){
					jogo.telajogo.GeraEnemy();
				}
				
				if(enemyCounter == 1){
					jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].enemy = true;
				}
				
				enemyCounter++;
				
				if(enemyCounter >= 2){
					enemyCounter = 0;
					tiroCounter = 0;
					singleScream = true;
				}
			}
			
		}else{
			if(type != 3){
				randAtk = MathUtils.random(2);
			}else{
				randAtk = 2;
			}
		}
	}
	
	public void movAtk(){
		Iterator<Rectangle> iter = tirodown.iterator();
		Iterator<Rectangle> iteresq = tiroleft.iterator();
		Iterator<Rectangle> iterdir = tiroright.iterator();
		
		while(iter.hasNext() || iteresq.hasNext() || iterdir.hasNext()){
			if(iter.hasNext()){
				Rectangle down = iter.next();
				down.y -= 400 * Gdx.graphics.getDeltaTime();
				
				if(down.overlaps(jogo.getTrans()) && !jogo.getTransLifeCounter()){
					iter.remove();
					double translife = jogo.getTransLife() - 2;
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
				
				if(down.x >= jogo.WIDTH || down.x <= 0 || down.y >= jogo.HEIGHT || down.y <= 0){
					iter.remove();
				}
			}if(iteresq.hasNext()){
				Rectangle left = iteresq.next();
				left.y -= 400 * Gdx.graphics.getDeltaTime();
				left.x -= 200 * Gdx.graphics.getDeltaTime();
				
				if(left.overlaps(jogo.getTrans()) && !jogo.getTransLifeCounter()){
					iteresq.remove();
					double translife = jogo.getTransLife() - 2;
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
				
				if(left.x >= jogo.WIDTH || left.x <= 0 || left.y >= jogo.HEIGHT || left.y <= 0){
					iteresq.remove();
				}
			}if(iterdir.hasNext()){
				Rectangle right = iterdir.next();
				right.y -= 400 * Gdx.graphics.getDeltaTime();
				right.x += 200 * Gdx.graphics.getDeltaTime();
				
				if(right.overlaps(jogo.getTrans()) && !jogo.getTransLifeCounter()){
					iterdir.remove();
					double translife = jogo.getTransLife() - 2;
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
				
				if(right.x >= jogo.WIDTH || right.x <= 0 || right.y >= jogo.HEIGHT || right.y <= 0){
					iterdir.remove();
				}
			}
		}
	}
}
