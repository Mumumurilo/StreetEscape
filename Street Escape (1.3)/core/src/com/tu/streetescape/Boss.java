package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Boss extends Calculos{
	
	private Rectangle bossRect;
	
	private float transX;
	
	private final int bosswidth = 90;
	private final int bossheight = 100;
	
	
	public Boss(final MainGame jogo, int type) {
		super(jogo);
		
		bossRect = new Rectangle(jogo.WIDTH/2 - bosswidth/2, jogo.HEIGHT - bossheight/2, bosswidth, bossheight);
		
	}
	
	private void atacar(){
		
	}
	
	private void andar(){
		transX = jogo.getTrans().x;
		
		if(bossRect.x < jogo.getTrans().x){
			bossRect.x += 150 * Gdx.graphics.getDeltaTime();
		}else if(bossRect.x < jogo.getTrans().x){
			bossRect.x += 150 * Gdx.graphics.getDeltaTime();
		}
	}
	
	private void movAtk(){
		
	}
}
