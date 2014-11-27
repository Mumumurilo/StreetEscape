package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.math.Rectangle;

public class Boss extends Calculos{
	
	public Rectangle bossRect;
	private StateMachine<Boss> machine;
	
	private final int bosswidth = 90;
	private final int bossheight = 100;
	
	
	public Boss(final MainGame jogo, int type) {
		super(jogo);
		bossRect = new Rectangle(jogo.WIDTH/2 - bosswidth/2, jogo.HEIGHT - (bossheight + 10), bosswidth, bossheight);
		machine = new DefaultStateMachine<Boss>(this, BossState.ATACAR);
	}
	
	public void atacar(){
		
	}
	
	public void andar(){
		System.out.println("furunfa andar");
		if(bossRect.x < jogo.getTrans().x){
			bossRect.x += 150 * Gdx.graphics.getDeltaTime();
		}
		if(bossRect.x > jogo.getTrans().x){
			bossRect.x -= 150 * Gdx.graphics.getDeltaTime();
		}
	}
	
	private void movAtk(){
		
	}
}
