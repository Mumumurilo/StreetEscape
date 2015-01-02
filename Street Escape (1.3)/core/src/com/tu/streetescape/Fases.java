package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Fases{

	private FileHandle leitor;
	
	private char[][] fase1;
	private char[][] fase2;
	private char[][] fase3;
	
	public Sala[][] sala;
	
	private int cont = 0;
	
	private int numFase;
	
	public Fases(int numFase){
		this.numFase = numFase;
		
		fase1 = new char[10][5];
		fase2 = new char[10][5];
		fase3 = new char[10][5];
		
		sala = new Sala[10][5];
		
		leitor = Gdx.files.internal("Etc/fase"+numFase+".map");

		if(numFase == 1){
			geraFases(fase1);
		}
		if(numFase == 2){
			geraFases(fase2);
		}
		if(numFase == 3){
			geraFases(fase3);
		}
	}
	
	private void geraFases(char fase[][]){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 10; j++){
				fase[j][i] = leitor.readString().split("\n")[i].toCharArray()[j];
				System.out.print(fase[j][i]);
			}
			System.out.print("\n");
		}
		System.out.println("----------");

		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 5; j++){
				sala[i][j] = new Sala();
			}
		}
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 5; j++){
				if(fase[i][j] == '1'){
					sala[i][j].enemy = true;
					
					if((i+1) < 10){
						if(fase[i+1][j] == '1'){
							sala[i][j].exitR = true;
							cont = cont + 1;
						}
					}
					
					if((i-1) >= 0){
						if(fase[i-1][j] == '1'){
							sala[i][j].exitL = true;
							cont = cont + 10;
						}
					}
					
					if(j < 4){
						if(fase[i][j+1] == '1' && (j+1) < 5){
							sala[i][j].exitD = true;
							cont = cont + 1000;
						}
					}
					
					if(j > 0){
						if(fase[i][j-1] == '1' && (j-1) >= 0){
							sala[i][j].exitU = true;
							cont = cont + 100;
						}
					}
					sala[i][j].setID(cont);
					cont = 0;
				}
			}
		}
	}
	
	public int getNumFase(){
		return numFase;
	}
	
	public void setNumFase(int numFase){
		this.numFase = numFase;
	}
}

