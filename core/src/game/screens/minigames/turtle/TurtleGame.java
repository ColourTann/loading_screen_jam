package game.screens.minigames.turtle;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;

import game.Main;
import game.Main.TransitionType;
import game.screens.minigames.Minigame;
import game.screens.minigames.snake.SnakeGame;
import game.screens.minigames.turtle.Obstacle.ObstacleType;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;
import game.util.TannFont;

public class TurtleGame extends Minigame{
	
	Array<Obstacle> obstacles = new Array<Obstacle>();
	private static TurtleGame self;
	public static TurtleGame get(){
		if(self==null) self= new TurtleGame();
		return self;
	}
	
	Turtle turtle;
	float obstacleTicks=0;
	private TurtleGame(){
		super("snake", .4f);
		Minigame.activeKeys.add(Input.Keys.UP);
		Minigame.activeKeys.add(Input.Keys.DOWN);
		Main.coloursUnlocked=2;
	}
	
	@Override
	public void setup() {
		turtle = new Turtle();
		addActor(turtle);
		turtle.setPosition(5, Main.height/2);
	}
	
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), Main.height/2);
		Draw.fillRectangle(batch, getX(), Main.height/2+ObstacleType.Spike.region.getRegionHeight()+5, getWidth(), Main.height/2);
		batch.setColor(Colours.dark);
		TannFont.font.draw(batch, "Score: "+score, 50, (int)(Main.height*.3f));
		TannFont.font.draw(batch, "Highscore: "+highscore, 100, (int)(Main.height*.3f));
	}

	@Override
	public void postDraw(Batch batch) {
	
	}

	@Override
	public void preTick(float delta) {
		if(!started) return;
		obstacleTicks+=delta;
		
		if(obstacleTicks>Math.max(.83, 1.5f-score*.025f)){
			Obstacle o = new Obstacle(ObstacleType.values()[(int) (Math.random()*2)], 1);
			addActor(o);
			obstacles.add(o);
			obstacleTicks=0;
		}
	}
	
	

	@Override
	public void postTick(float delta) {
	}

	@Override
	public void keyPress(int keycode) {
		super.keyPress(keycode);
		if(started) turtle.keyPress(keycode);
	}
	@Override
	public String getName() {
		return "turtle";
	}
	@Override
	public Unlock[] getUnlocks() {
		return new Unlock[]{
				new KeyUnlock('{'), new KeyUnlock('}'), new ColourUnlock("light", Colours.light), new ColourUnlock("dark", Colours.dark)
		};
	}

	@Override
	protected void nextGame() {
		Main.self.setScreen(SnakeGame.get(), TransitionType.LEFT, Interpolation.pow2Out, .5f);
	}

	@Override
	public void resetGame() {
		for(Obstacle o: obstacles){
			o.remove();
		}
		obstacles.clear();
	}
	

}
