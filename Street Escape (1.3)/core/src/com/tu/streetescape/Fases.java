package com.tu.streetescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class Fases{

	FileHandle leitor;
	String textoDoArquivo;
	char[][] fase1;
	char[][] fase2;
	char[][] fase3;
	Sala sala = new Array<Sala>();
	
	
	public Fases(){
		fase1 = new char[10][5];
		fase2 = new char[10][5];
		fase3 = new char[10][5];
		
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
					sala.
					if(fase1[i+1][j] == '1'){
						sala.exitR = true;
					}
					
					if(fase1[i-1][j] == '1'){
						sala.exitL = true;
					}
					
					if(fase1[i][j+1] == '1'){
						sala.exitU = true;
					}
					
					if(fase1[i][j-1] == '1'){
						sala.exitD = true;
					}
					
				}
			}
		}
		
	}
}

