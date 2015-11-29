package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.screens.minigames.LoadingBar;
import game.screens.minigames.Minigame;
import game.screens.testScreens.GameScreen;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.screens.unlock.UnlockBox;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;

public class SnakeGame extends Minigame{
	private static SnakeGame self;
	public Grid g;
	public static SnakeGame get(){
		if(self==null) self= new SnakeGame();
		return self;
	}
	private SnakeGame() {
		super("fighter", .4f);
	}
	
	@Override
	public void setup() {
		g = new Grid();
		addActor(g);
	}
	
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.dark);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
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
		return "snake";
	}
	@Override
	public Unlock[] getUnlocks() {
		return new Unlock[]{new KeyUnlock('<'), new KeyUnlock('>'), new ColourUnlock("blue", Colours.mixer)};
	}
	@Override
	public void keyPress(int keycode) {
		super.keyPress(keycode);
		g.s.keyPress(keycode);
	}

	@Override
	protected void nextGame() {
		
	}

}
