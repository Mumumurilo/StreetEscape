package com.tu.streetescape;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MainGame extends Game {
	//Declara��o de telas do jogo
	public Inicio telainicio;
	public TelaJogo telajogo;
	
	//Declara��o de elementos globais, utilizados em todas as classes do jogo
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public ShapeRenderer renderer;
	public Transeunte transeunte;
	public BitmapFont gameoverfont, GUIFont;
	public boolean debug;
	public boolean music;
	public boolean sound;
	public final int WIDTH = 800;
	public final int HEIGHT = 480;	
	public final int persowidth = 70;
	public final int persoheight = 80;
	
	private Rectangle unitrans;
	
	@Override
	public void create() { //M�todo aonde tudo � instanciado
		//Elementos globais
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		renderer = new ShapeRenderer();
		transeunte = new Transeunte(this);
		GUIFont = new BitmapFont(Gdx.files.internal("Fontes/GUIFont.fnt"));
		
		//Telas
		telainicio = new Inicio(this);
		telajogo = new TelaJogo(this);
		
		//Mudan�a de tela (direciona o jogo para ir � tela seguinte)
		this.setScreen(telainicio);			
	}
	
	//Getters and Setters de debug
	public void setDebug(boolean debug){
		debug = this.debug;
	}
	
	public void setSound(boolean sound){
		sound = this.sound;
	}
	
	public void setMusic(boolean music){
		music = this.music;
	}

	@Override
	public void dispose() { //Tudo o que pesa na mem�ria precisa ser jogado fora aqui
		//Elementos globais
		batch.dispose();
		renderer.dispose();
		GUIFont.dispose();
		
		//Telas
		telainicio.dispose();
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
}
