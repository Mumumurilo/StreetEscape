package com.tu.streetescape;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Predios {
	
	private final MainGame jogo;
	
	private Calculos calc;
	
	public Rectangle predesqcima, predmeiocima, preddircima, predesq, preddir, predesqbai, predmeiobai, preddirbai;
	private boolean esqcima, meiocima, dircima, Esq, Dir, esqbai, meiobai, dirbai;
	public Array<Rectangle> predios;
	public Array<Boolean> colisaoPredios, actualCollisionTrans;
	
	public Predios(final MainGame jogo){
		this.jogo = jogo;
		
		calc = new Calculos(jogo);
		
		predesqcima = new Rectangle(0, (2*jogo.HEIGHT/3) + jogo.corrigealtura,
				(jogo.WIDTH/3) + jogo.corrigelargura, (jogo.HEIGHT/3) - jogo.corrigealtura);
		predmeiocima = new Rectangle(predesqcima.width, (2*jogo.HEIGHT/3) + jogo.corrigealtura,
				jogo.largurarua, (jogo.HEIGHT/3) - jogo.corrigealtura);
		preddircima = new Rectangle(((jogo.WIDTH/3) * 2) - jogo.corrigelargura, (2*jogo.HEIGHT/3) + jogo.corrigealtura,
				(jogo.WIDTH/3) + jogo.corrigelargura, (jogo.HEIGHT/3) - jogo.corrigealtura);
		predesq = new Rectangle(0, jogo.HEIGHT/3,
				jogo.WIDTH/3, jogo.HEIGHT/3);
		preddir = new Rectangle((jogo.WIDTH/3) * 2, jogo.HEIGHT/3,
				jogo.WIDTH/3, jogo.HEIGHT/3);
		predesqbai = new Rectangle(0, 0,
				(jogo.WIDTH/3) + jogo.corrigelargura, jogo.HEIGHT/3 - jogo.corrigealtura);
		predmeiobai = new Rectangle(predesqbai.width, 0,
				jogo.largurarua, jogo.HEIGHT/3 - jogo.corrigealtura);
		preddirbai = new Rectangle((jogo.WIDTH/3) * 2 - jogo.corrigelargura, 0,
				(jogo.WIDTH/3) + jogo.corrigelargura, jogo.HEIGHT/3 - jogo.corrigealtura);
		
		colisaoPredios = new Array<Boolean>();
		colisaoPredios.add(esqcima);
		colisaoPredios.add(meiocima);
		colisaoPredios.add(dircima);
		colisaoPredios.add(Esq);
		colisaoPredios.add(Dir);
		colisaoPredios.add(esqbai);
		colisaoPredios.add(meiobai);
		colisaoPredios.add(dirbai);
		
		predios = new Array<Rectangle>();
		predios.add(predesqcima);
		predios.add(predmeiocima);
		predios.add(preddircima);
		predios.add(predesq);
		predios.add(preddir);
		predios.add(predesqbai);
		predios.add(predmeiobai);
		predios.add(preddirbai);
		
		actualCollisionTrans = new Array<Boolean>();
		
		for(int i = 0; i < 8; i++){
			float x = predios.get(i).x;
			float y = predios.get(i).y;
			
			x = x + predios.get(i).width/2;
			y = y + predios.get(i).height/2;
			
			actualCollisionTrans.add(false);
		}
	}
	
	public int setPredios(){
		if(!jogo.telajogo.checaSalaBoss()){	
			if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1111){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 0;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1101){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 1;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1110){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 2;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1011){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 3;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 111){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 4;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1001){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 5;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 101){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 6;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1010){
				resetColisaoPredios();	
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 7;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 110){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 8;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1100){
				resetColisaoPredios();	
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 9;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 11){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 10;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1000){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(7, true);
				return 12;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 10){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 11;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 1){
				resetColisaoPredios();
				colisaoPredios.set(0, true);
				colisaoPredios.set(1, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 14;
			}else if(jogo.telajogo.mapa.sala[jogo.telajogo.salax][jogo.telajogo.salay].getID() == 100){
				resetColisaoPredios();		
				colisaoPredios.set(0, true);
				colisaoPredios.set(2, true);
				colisaoPredios.set(3, true);
				colisaoPredios.set(4, true);
				colisaoPredios.set(5, true);
				colisaoPredios.set(6, true);
				colisaoPredios.set(7, true);
				return 13;
			}
		}else{
			for(int i = 0; i <= 7; i++){
				colisaoPredios.set(i, false);
			}
		}
		return 0;
	}
	
	private void resetColisaoPredios(){
		for(int i = 0; i < 8; i++){
			colisaoPredios.set(i, false);
		}
	}
	
	public void condCollide(Rectangle perso, Array<Boolean>actualCollision){
		for(int i = 0; i < 8; i++){
			if(colisaoPredios.get(i)){
				if(calc.checaColisao(predios.get(i), perso)){
					actualCollision.set(i, true);
				}else if(!calc.checaColisao(predios.get(i), perso)){
					actualCollision.set(i, false);
				}
			}
		}
	}
	
	public void updateColisao(Array<Boolean> ladoColisao, Array<Boolean> actualCollision, Rectangle perso){
		for(int j = 0; j < 4; j++){
			ladoColisao.set(j, false);
		}
		
		for(int i = 0; i < 8; i++){
			if(colisaoPredios.get(i)){
				if(calc.checaColisao(perso, predios.get(i))){
					if(calc.checaLadoColisao(predios.get(i), perso) == 1){
						ladoColisao.set(0, true);
					}
					if(calc.checaLadoColisao(predios.get(i), perso) == 2){
						ladoColisao.set(1, true);
					}
					if(calc.checaLadoColisao(predios.get(i), perso) == 3){
						ladoColisao.set(2, true);
					}
					if(calc.checaLadoColisao(predios.get(i), perso) == 4){
						ladoColisao.set(3, true);
					}
				}else{
					actualCollision.set(i, false);
				}
			}
		}
	}
	
	public void setActualCollisionArray(Array<Boolean> array){
		for(int i = 0; i < 8; i++){
			array.add(false);
		}
	}
}
