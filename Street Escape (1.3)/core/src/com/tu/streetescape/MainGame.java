package com.tu.streetescape;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MainGame extends Game {
	//Declara��o das telas
	public Inicio telainicio;
	public TelaJogo telajogo;
	public TelaCreditos telacreditos;
	
	//Declara��o de elementos globais, utilizados em todas as classes do jogo
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public ShapeRenderer renderer;
	public Transeunte transeunte;
	public BitmapFont gameoverfont, GUIFont;
	private boolean debug = true;
	private boolean music = true;
	private boolean sound = true;
	public final int WIDTH = 800;
	public final int HEIGHT = 480;	
	public final int persowidth = 70;
	public final int persoheight = 80;
	public final int corrigealtura = 30;
	public final int corrigelargura = 30;
	
	private Rectangle unitrans;
	private boolean transLifeCounter = false;
	
	public boolean reset = false;
	
	//Vai compilar a vers�o para PC ou para Android?
	public boolean androidGameplay = false;
	
	//M�sicas
	public Music temamenu;
	public Music temajogo;
	public Music temacreditos;
	
	//Efeitos sonoros
	public Sound transDano1, transDano2, transMorre, transAtira, transRecoverLife;
	public Sound enemDano, enemMorre, enemTiro;
	public Sound enemNDano, enemNMorre;
	
	@Override
	public void create() { //M�todo aonde tudo � instanciado
		//Elementos globais
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		renderer = new ShapeRenderer();
		transeunte = new Transeunte(this);
		GUIFont = new BitmapFont(Gdx.files.internal("Fontes/GUIFont.fnt"));
		gameoverfont = new BitmapFont(Gdx.files.internal("Fontes/gameover.fnt"));
		
		//Telas
		telainicio = new Inicio(this);
		
		//Mudan�a de tela (direciona o jogo para ir � tela seguinte)
		this.setScreen(telainicio);			
	}
	
	//Getters and Setters de debug
	public void setDebug(boolean debug){
		this.debug = debug;
	}
	
	public void setSound(boolean sound){
		this.sound = sound;
	}
	
	public void setMusic(boolean music){
		this.music = music;
	}
	
	public boolean isDebug() {
		return debug;
	}

	public boolean isMusic() {
		return music;
	}

	public boolean isSound() {
		return sound;
	}

	@Override
	public void dispose() { //Tudo o que pesa na mem�ria precisa ser jogado fora aqui
		//Elementos globais
		batch.dispose();
		renderer.dispose();
		GUIFont.dispose();
	}

	@Override
	public void render() {	//"loop" principal do jogo
		super.render(); //O jogo � renderizado na classe superior (Game)
	}

	@Override
	public void resize(int width, int height) { //Acho que n�o faremos uso desse m�todo
	}

	@Override
	public void pause() { //Tudo o que � feito quando o bot�o HOME do Android � pressionado
	}

	@Override
	public void resume() { //Tudo o que � feito quando o jogo � retomado
	}
	
	public Rectangle getTrans(){ /*Pilantragem que criei (Murilo) pra conseguir pegar o ret�ngulo do transeunte da classe TelaJogo e passar pra Enemy,
	sem precisar referenciar a TelaJogo na Enemy (que ficaria muito porco). */
		unitrans = telajogo.getTrans();
		return unitrans;
	}
	
	public double getTransLife(){
		return transeunte.getLife();
	}
	
	public void setTransLife(double life){
		transeunte.setLife(life);
	}
	
	public void setTransLifeCounter(boolean set){
		this.transLifeCounter = set;
	}
	
	public boolean getTransLifeCounter(){
		return transLifeCounter;
	}
}
