package com.tu.streetescape;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Transeunte extends Calculos{
	
	private double life = 3;
	private Array<Rectangle> tipoTiros;
	private Array<Rectangle> tiros;
	
	private boolean firstshot = true;
	
	public Transeunte(MainGame jogo) {
		super(jogo);
		
		tipoTiros = new Array<Rectangle>();
		tiros = new Array<Rectangle>();
	}
	
	public void atirar(){
		if(firstshot){
			super.geraTiro(tipoTiros);
			firstshot = false;
		}
		
		int randShotType = MathUtils.random(1, 60);
		
		if(randShotType >= 1 && randShotType < 10){
			tiros.add(tipoTiros.get(0));
		}else if(randShotType >= 10 && randShotType < 30){
			tiros.add(tipoTiros.get(1));
		}else{
			tiros.add(tipoTiros.get(2));
		}
	}
	
	public double getLife(){
		return life;
	}
	
	public void setLife(double life){
		this.life = life;
	}

}
