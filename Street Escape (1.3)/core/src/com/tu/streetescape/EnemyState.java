package com.tu.streetescape;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

public enum EnemyState implements State<Enemy>{
	ANDAR(){
		@Override
		public void update(Enemy entity) {
			if(entity.estaLonge() && !entity.tomouDano){
				entity.movEnemy();
			}
			if(!entity.estaLonge()){
				entity.machine.changeState(ATACAR);
				
			}
			if(entity.tomouDano){
				entity.machine.changeState(TOMA_DANO);
			}
		}
	},
	ATACAR(){
		@Override
		public void update(Enemy entity) {
			if(entity.estaLonge() && !entity.tomouDano){
				entity.machine.changeState(ANDAR);
				
			}
			if(!entity.estaLonge()){
				entity.atacar();
				entity.segueTrans();
				
			}
			if(entity.tomouDano){
				entity.machine.changeState(TOMA_DANO);
			}
		}
	},
	TOMA_DANO(){
		@Override
		public void update(Enemy entity) {
			entity.tomaDano();
			if(entity.life <= 0){
				entity.machine.changeState(MORRER);
			}else{
				entity.machine.changeState(ANDAR);
			}
		}
	},
	MORRER(){
		@Override
		public void update(Enemy entity) {
			entity.morto = true;
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
