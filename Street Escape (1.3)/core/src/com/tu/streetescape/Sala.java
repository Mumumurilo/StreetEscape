package com.tu.streetescape;

public class Sala {
	
	boolean enemy;
	boolean exitL, exitR, exitU, exitD;
	int idEnemy;
	
	
	
	//1 = guardas, 2 = manifestantes, 3 = nontendistas, 4 = boss
	
	public Sala(int id, boolean enemy){
		this.idEnemy = id;
		this.enemy = enemy;
		position = new int[10][5];
	}
	
	public void setPosition(int x, int y){
		
	}
	
}
