package com.tu.streetescape;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Transeunte extends Calculos{
	
	private double life = 3;
	public double lastShotTime;
	private Array<Rectangle> tipoTiros;
	public Array<Rectangle> tiros;
	public Array<Integer> direcTiros;
	
	public Transeunte(MainGame jogo) {
		super(jogo);
		
		tipoTiros = new Array<Rectangle>();
		tiros = new Array<Rectangle>();
		direcTiros = new Array<Integer>();
	}
	
	public void atirar(){
		super.geraTiro(tipoTiros);
		
		int randShotType = MathUtils.random(1, 60);
		
		if(randShotType >= 1 && randShotType < 10){
			tiros.add(tipoTiros.get(0));
		}else if(randShotType >= 10 && randShotType < 30){
			tiros.add(tipoTiros.get(1));
		}else{
			tiros.add(tipoTiros.get(2));
		}
		
		tipoTiros.removeRange(0, 2);
	}
	
	public void movAtk(Rectangle enemy){
		Iterator<Rectangle> iter = tiros.iterator();
		Iterator<Integer> iterint = direcTiros.iterator();
		
		while(iter.hasNext() && iterint.hasNext()){
			Rectangle rect = iter.next();
			int direcao = iterint.next();
			
			if(direcao == 1){ //Up
				rect.y += 150 * Gdx.graphics.getDeltaTime();
			}
			if(direcao == 2){ //Down
				rect.y -= 150 * Gdx.graphics.getDeltaTime();
			}
			if(direcao == 3){ //Left
				rect.x -= 150 * Gdx.graphics.getDeltaTime();
			}
			if(direcao == 4){ //Right
				rect.x += 150 * Gdx.graphics.getDeltaTime();
			}
						
			if(rect.overlaps(enemy)){
				iter.remove();
				iterint.remove();
			}
			
			if(rect.x >= jogo.WIDTH || rect.x <= 0 || rect.y >= jogo.HEIGHT || rect.y <= 0){
				iter.remove();
				iterint.remove();
			}
		}
	}
	
	public double getLife(){
		return life;
	}
	
	public void setLife(double life){
		this.life = life;
	}

}
