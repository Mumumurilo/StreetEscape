package com.tu.streetescape;

public class Sala {
	
	boolean enemy;
	boolean exitL, exitR, exitU, exitD;
	int idEnemy, id;
	
	public void setEnemy(boolean tem){
		this.enemy = tem;
	}
	
	public boolean getEnemy(){
		return enemy;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
}
