package game.screens.minigames.rat;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.screens.minigames.Minigame;
import game.screens.minigames.snake.SnakeGame;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.util.Colours;

public class RatGame extends Minigame{
	private static RatGame self;
	Rat rat;
	public static RatGame get(){
		if(self==null) self= new RatGame();
		return self;
	}
	private RatGame() {
		super("flib", .0005f);
		rat = new Rat();
		addActor(rat);
	}

	@Override
	public String getName() {
		return "rat";
	}

	@Override
	public Unlock[] getUnlocks() {
		return new Unlock[]{new KeyUnlock('z'), new ColourUnlock("brown", Colours.red)};
	}

	@Override
	public void setup() {
	}

	@Override
	public void resetGame() {
	}

	@Override
	protected void nextGame() {
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
	public void keyPress(int keycode) {
		super.keyPress(keycode);
		rat.keyPress(keycode);
	}
}
