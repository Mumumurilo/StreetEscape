package com.tu.streetescape;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

public enum BossState implements State<Boss>{
	ATACAR(){
		@Override
		public void update(Boss entity) {
			
		}
	},
	CHAMA_ENEMY(){
		@Override
		public void update(Boss entity) {
			
		}
	},
	TOMA_DANO(){
		@Override
		public void update(Boss entity) {
			
		}
	},
	MORRER(){
		@Override
		public void update(Boss entity) {
						
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
