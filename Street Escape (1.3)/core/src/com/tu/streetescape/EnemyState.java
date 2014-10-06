package com.tu.streetescape;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

public enum EnemyState implements State<Enemy>{
	ANDAR(){
		@Override
		public void update(Enemy entity) {
			if(entity.estaLonge()){
				entity.movEnemy();
			}else{
				System.out.println("Transeunte tá perto, tô parado!");
			}
		}
	},
	ATACAR(){
		@Override
		public void update(Enemy entity) {
			// TODO Auto-generated method stub
			
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
	public boolean onMessage(Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}

}
