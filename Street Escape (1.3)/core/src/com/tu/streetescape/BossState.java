package com.tu.streetescape;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

public enum BossState implements State<Boss>{
	ATACAR(){
		@Override
		public void update(Boss entity) {
			if(!entity.tomouDano){
				entity.andar();
			}
			else{
				entity.machine.changeState(TOMA_DANO);
			}
		}
	},
	TOMA_DANO(){
		@Override
		public void update(Boss entity) {
			entity.tomaDano();
			if(entity.life <= 0){
				entity.machine.changeState(MORRER);
			}else{
				entity.machine.changeState(ATACAR);
			}
		}
	},
	MORRER(){
		@Override
		public void update(Boss entity) {
			entity.morto = true;
		}
	}
	;
	
	@Override
	public void enter(Boss entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Boss entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMessage(Boss entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}

}
