package com.tu.streetescape;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Transeunte extends Calculos{
	
	private float life = 3;
	private Array<Rectangle> tipoTiros;
	
	private boolean firstshot = true;
	
	public Transeunte(MainGame jogo) {
		super(jogo);
		
		tipoTiros = new Array<Rectangle>();
	}
	
	public void atirar(){
		if(firstshot){
			super.geraTiro(tipoTiros);
			firstshot = false;
		}
		
		int randShotType = MathUtils.random(1, 60);
		
		if(randShotType >= 1 && randShotType < 10){
			
		}else if(randShotType >= 10 && randShotType < 30){
			
		}else{
			
		}
	}
	
	public float getLife(){
		return life;
	}
	
	public void setLife(float life){
		this.life = life;
	}

}
