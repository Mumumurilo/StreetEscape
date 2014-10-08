package com.tu.streetescape;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Calculos {
	protected final MainGame jogo;
	
	public Calculos(final MainGame jogo){
		this.jogo = jogo;
	}
	
	protected boolean calculaDistanciadaParede(Rectangle enemy){
		
		if(jogo.HEIGHT - enemy.y <= 50){
			return true;
		}
		if(jogo.HEIGHT - enemy.y >= 430){
			return true;
		}
		if(jogo.WIDTH - enemy.x <= 50){
			return true;
		}
		if(jogo.WIDTH - enemy.x >= 750){
			return true;
		}
		return false;
	}
	
	protected int checaLado(Rectangle enemy){
		//1 = cima, 2 = baixo, 3 = direita, 4 = esquerda
		if(jogo.HEIGHT - enemy.y <= 50){
			return 1;
		}
		if(jogo.HEIGHT - enemy.y >= 430){
			return 2;
		}
		if(jogo.WIDTH - enemy.x <= 50){
			return 3;
		}
		if(jogo.WIDTH - enemy.x >= 750){
			return 4;
		}
		return 0;
	}
	
	protected boolean checaProxRect(Rectangle enemy, Rectangle trans){
		float enemX = enemy.x + (jogo.persowidth/2);
		float enemY = enemy.y + (jogo.persoheight/2);
		float transX = trans.x + (jogo.persowidth/2);
		float transY = trans.y + (jogo.persoheight/2);
		
		double distance = Math.sqrt((enemX - transX)*(enemX - transX) + (enemY - transY)*(enemY - transY));
		double angle = Math.atan2(enemX - transX, enemY - transY);
		double distx = Math.cos(angle)*distance;
		double disty = Math.sin(angle)*distance;
		
		if(distx <= 220 && disty <= 220 && distx >= -220 && disty >= -220){
			return true;
		}
		return false;
	}
	
	protected double getAngleDaReta(Rectangle enemy, Rectangle trans){
		float enemX = enemy.x + (jogo.persowidth/2);
		float enemY = enemy.y + (jogo.persoheight/2);
		float transX = trans.x + (jogo.persowidth/2);
		float transY = trans.y + (jogo.persoheight/2);

		double angle = Math.atan2(enemX - transX, enemY - transY);
		
		return angle;
	}
	
	protected void geraTiro(Array<Rectangle>tiros, Rectangle enemy){
		Rectangle tiro = new Rectangle(10, 10, enemy.x, enemy.y);
		tiros.add(tiro);
	}
}
