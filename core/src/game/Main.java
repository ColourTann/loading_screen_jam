package game;

import game.screens.GameScreen;
import game.util.Screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Main extends ApplicationAdapter {
	public static int width=800,height=640;
	SpriteBatch batch;
	Stage stage;
	OrthographicCamera cam;
	public static TextureAtlas atlas;
	public static Main self;
	Screen currentScreen;
	Screen previousScreen;
	@Override
	public void create () {
		self=this;
		atlas= new TextureAtlas(Gdx.files.internal("atlas_image.atlas"));
		stage = new Stage();
		cam =(OrthographicCamera) stage.getCamera();
		batch = (SpriteBatch) stage.getBatch();
		Gdx.input.setInputProcessor(stage);
		setScreen(new GameScreen());
		setScreen(new GameScreen(), TransitionType.LEFT, Interpolation.pow2Out, 1);
		
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
		batch.end();
	}
	
	public void update(float delta){
		stage.act(delta);
		useIntegerPositions(currentScreen);
		useIntegerPositions(previousScreen);
	}
	
	public void useIntegerPositions(Screen screen){
		screen.setPosition((int)screen.getX(), (int)screen.getY());
	}
}
