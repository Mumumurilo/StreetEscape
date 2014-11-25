package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Fases{

	FileHandle leitor;
	
	String textoDoArquivo;
	
	char[][] fase1;
	char[][] fase2;
	char[][] fase3;
	
	public Sala[][] sala;
	
	int cont = 0;
	
	public Fases(){
		fase1 = new char[10][5];
		fase2 = new char[10][5];
		fase3 = new char[10][5];
		
		sala = new Sala[10][5];
		
		//comparar characteres : if(fase[i][j] == '1')
		
		leitor = Gdx.files.internal("Etc/fase1.txt");
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 5; j++){
				fase1[i][j] = leitor.readString().split("\n")[j].toCharArray()[i];
			}
		}
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 5; j++){
				if(fase1[i][j] == '1'){
					
					sala[i][j].setEnemy(true);
					
					if(fase1[i+1][j] == '1'){
						sala[i][j].exitR = true;
						cont = cont + 1;
					}
					
					if(fase1[i-1][j] == '1'){
						sala[i][j].exitL = true;
						cont = cont + 10;
					}
					
					if(fase1[i][j+1] == '1'){
						sala[i][j].exitU = true;
						cont = cont + 100;
					}
					
					if(fase1[i][j-1] == '1'){
						sala[i][j].exitD = true;
						cont = cont + 1000;
					}
					sala[i][j].setID(cont);
					cont = 0;
				}
			}
		}
		
	}
}

