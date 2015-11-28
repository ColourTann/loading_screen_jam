package game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;

import game.Main;
import game.Main.TransitionType;
import game.screens.minigames.LoadingBar;
import game.screens.minigames.Minigame;
import game.screens.minigames.snake.Snake;
import game.screens.minigames.turtle.Turtle;
import game.screens.minigames.turtle.TurtleGame;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.util.Colours;
import game.util.Screen;

public class InitialLoadingScreen extends Minigame{
	
	private static InitialLoadingScreen self;
	public static InitialLoadingScreen get(){
		if(self==null) self = new InitialLoadingScreen();
		return self;
	}
	
	private InitialLoadingScreen() {
		super("snake");
		addLoadingBar(new LoadingBar("snake", 10, false));
	}
	
	@Override
	public void preDraw(Batch batch) {
	}

	@Override
	public void postDraw(Batch batch) {
	}

	@Override
	public void preTick(float delta) {
	}

	@Override
	public void postTick(float delta) {
	}

	@Override
	public String getName() {
		return "loading";
	}

	@Override
	public Unlock[] getUnlocks() {
		return new Unlock[]{
				new KeyUnlock('<'), new KeyUnlock('>'), new ColourUnlock("light", Colours.light), new ColourUnlock("dark", Colours.dark)
		};
	}

	@Override
	public void keyPress(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
		case Input.Keys.RIGHT:
			Main.self.setScreen(TurtleGame.get(), TransitionType.LEFT, Interpolation.pow2Out, .5f);
			break;

		default:
			break;
		}
	}	
}
