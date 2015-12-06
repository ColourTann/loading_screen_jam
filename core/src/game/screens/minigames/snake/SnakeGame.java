package game.screens.minigames.snake;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;

import game.Main;
import game.Main.TransitionType;
import game.screens.minigames.LoadingBar;
import game.screens.minigames.Minigame;
import game.screens.minigames.space.SpaceGame;
import game.screens.minigames.turtle.TurtleGame;
import game.screens.testScreens.GameScreen;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.screens.unlock.UnlockBox;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;
import game.util.Sounds;
import game.util.TannFont;

public class SnakeGame extends Minigame{
	private static SnakeGame self;
	public static SnakeGame get(){
		if(self==null) self= new SnakeGame();
		return self;
	}
	public Grid grid;
	public static final int scoreSize=9;
	
	private SnakeGame() {
		super("defender", .6f);
		Minigame.activeKeys.add(Input.Keys.LEFT);
		Minigame.activeKeys.add(Input.Keys.RIGHT);
		Main.coloursUnlocked=3;
	}
	
	@Override
	public void setup() {
		grid = new Grid();
		addActor(grid);
		grid.setup();
	}
	
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.dark);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		
		batch.setColor(Colours.blue);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), scoreSize);
		
		batch.setColor(Colours.light);
		TannFont.font.draw(batch, "score: "+score, (int)getX()+40, (int)getY()+2);
		TannFont.font.draw(batch, "highscore: "+highscore, (int)getX()+100, (int)getY()+2);
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
		return new Unlock[]{new KeyUnlock('<'), new KeyUnlock('>'), new ColourUnlock("blue", Colours.blue)};
	}
	@Override
	public void keyPress(int keycode) {
		super.keyPress(keycode);
		if(!started)return;
		grid.player.keyPress(keycode);
	}
	
	@Override
	public void resetGame() {
		grid.reset();
	}

	@Override
	protected void nextGame() {
		Main.self.setScreen(SpaceGame.get(), TransitionType.LEFT, Interpolation.pow2Out, .5f);
	}

	@Override
	protected void startMusic() {
		Sounds.playMusic(Sounds.get("snake", Music.class));
	}
}
