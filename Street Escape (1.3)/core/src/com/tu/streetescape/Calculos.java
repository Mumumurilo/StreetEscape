package com.tu.streetescape;

import com.badlogic.gdx.math.Rectangle;
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
		
		if(distx <= 200 && disty <= 200 && distx >= -200 && disty >= -200){
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
	
	protected void geraTiro(Array<Rectangle>tiros){
		Rectangle tiro1 = new Rectangle(jogo.getTrans().x + jogo.persowidth/4, jogo.getTrans().y + jogo.persoheight/4, 
				jogo.persowidth/4, jogo.persoheight/4);
		Rectangle tiro2 = new Rectangle(jogo.getTrans().x + jogo.persowidth/4, jogo.getTrans().y + jogo.persoheight/4, 
				jogo.persowidth/3, jogo.persoheight/3);
		Rectangle tiro3 = new Rectangle(jogo.getTrans().x + jogo.persowidth/4, jogo.getTrans().y + jogo.persoheight/4, 
				jogo.persowidth/2, jogo.persoheight/2);
		
		tiros.add(tiro1);
		tiros.add(tiro2);
		tiros.add(tiro3);
	}
	
	protected boolean checaColisao(Rectangle stopped, Rectangle walking){
		if(stopped.overlaps(walking)){
			return true;
		}else{
			return false;
		}
	}
	
	protected int checaLadoColisao(Rectangle stopped, Rectangle walking, float centerxs, float centerys, float centerxw, float centeryw){
		if(centeryw - centerys < (stopped.height/2) + walking.height/2 || centerys - centeryw < (stopped.height/2) + walking.height/2){
			if(centerxw - centerxs < (stopped.width/2) + walking.width/2 && centerxw - centerxs > 0){ //Esquerda
				return 2;
			}
			if(centerxs - centerxw < (stopped.width/2) + walking.width/2 && centerxs - centerxw > 0){ //Direita
				return 1;
			}
		}
		if(centerxw - centerxs < (stopped.width/2) + walking.width/2 || centerxs - centerxw < (stopped.width/2) + walking.width/2){
			if(centerys - centeryw < (stopped.height/2) + walking.height/2 && centerys - centeryw > 0){ //Baixo
				return 4;
			}
			if(centeryw - centerys < (stopped.height/2) + walking.height/2 && centeryw - centerys > 0){ //Cima
				return 3;
			}
		}
		return 0;
	}
}
