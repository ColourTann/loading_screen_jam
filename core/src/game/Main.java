package game;

import game.screens.GameScreen;

import game.testScreens.FontScreen;

import game.screens.InputBlocker;
import game.screens.PauseScreen;
import game.util.Fonts;

import game.util.Screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Main extends ApplicationAdapter {
	public static int width=400,height=320;
	SpriteBatch batch;
	Stage stage;
	OrthographicCamera cam;
	public static TextureAtlas atlas;
	public static Main self;
	Screen currentScreen;
	Screen previousScreen;
	public enum MainState{Normal, Paused}
	@Override
	public void create () {
		self=this;
		atlas= new TextureAtlas(Gdx.files.internal("atlas_image.atlas"));
		stage = new Stage();
		cam =(OrthographicCamera) stage.getCamera();
		batch = (SpriteBatch) stage.getBatch();
		Gdx.input.setInputProcessor(stage);

		setScreen(new FontScreen());


		stage.addListener(new InputListener(){
			public boolean keyDown (InputEvent event, int keycode) {
				
				switch(keycode){
				case Keys.ESCAPE:
					toggleMenu();
					return false;
				}
				
				return true;
			}

			
		});
	}
	
	private void toggleMenu() {
		if(state!=MainState.Paused){
			stage.addActor(InputBlocker.get());
			stage.addActor(PauseScreen.get());
			setState(MainState.Paused);
		}
		else {
			InputBlocker.get().remove();
			PauseScreen.get().remove();
			setState(MainState.Normal);
		}

	}
	
	
	
	private MainState state=MainState.Normal;
	public MainState getState(){
		return state;
	}
	public void setState(MainState state){
		this.state=state;
	}
	public enum TransitionType{LEFT};
	public void setScreen(Screen screen, TransitionType type, Interpolation interp, float speed){
		setScreen(screen);
		switch(type){
		case LEFT:
			screen.setPosition(Main.width, 0);
//			Action a = ;
			screen.addAction(Actions.moveTo(0, 0, speed, interp));
			previousScreen.addAction(Actions.moveTo(-Main.width, 0, speed, interp));
			break;
		}
	}
	
	public void setScreen(Screen screen){
		previousScreen=currentScreen;
		currentScreen=screen;
		stage.addActor(screen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		drawFPS(batch);
		batch.end();
	}
	
	public void drawFPS(Batch batch){
		Fonts.font.draw(batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight()-Fonts.font.getCapHeight());
	}
	
	
	public void update(float delta){
		stage.act(delta);
	}

}
