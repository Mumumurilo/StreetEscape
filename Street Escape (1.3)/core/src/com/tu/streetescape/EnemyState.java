package com.tu.streetescape;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

public enum EnemyState implements State<Enemy>{
	ANDAR(){
		@Override
		public void update(Enemy entity) {
			if(entity.estaLonge() && entity.life > 0){
				entity.movEnemy();
				
			}else if(!entity.estaLonge()){
				entity.machine.changeState(ATACAR);
				
			}else if(entity.life <= 0){
				
				entity.machine.changeState(MORRER);
			}
		}
	},
	ATACAR(){
		@Override
		public void update(Enemy entity) {
			if(entity.estaLonge()){
				entity.machine.changeState(ANDAR);
				
			}else{
				entity.atacar();
				entity.segueTrans();
			}
		}
	},
	TOMA_DANO(){
		@Override
		public void update(Enemy entity) {
			// TODO Auto-generated method stub
			
		}
	},
	MORRER(){
		@Override
		public void update(Enemy entity) {
			// TODO Auto-generated method stub
			
		}
	}
	;
	
	@Override
	public void enter(Enemy entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Enemy entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMessage(Enemy entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}

}
