package game.screens.minigames.turtle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

import game.Main;
import game.screens.minigames.snake.Grid;
import game.screens.minigames.snake.Snake;
import game.screens.minigames.turtle.Obstacle.ObstacleType;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;
import game.util.TannFont;

public class TurtleGame extends Screen{
	int score =0;
	int highscore =0;
	Array<Obstacle> obstacles = new Array<Obstacle>();
	private static TurtleGame self;
	public static TurtleGame get(){
		if(self==null) self= new TurtleGame();
		return self;
	}
	
	Turtle turtle;
	float obstacleTicks=0;
	private TurtleGame(){
		turtle = new Turtle();
		addActor(turtle);
		turtle.setPosition(5, Main.height/2);
	}
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), Main.height/2);
		Draw.fillRectangle(batch, getX(), Main.height/2+ObstacleType.Spike.region.getRegionHeight()+5, getWidth(), Main.height/2);
	}

	@Override
	public void postDraw(Batch batch) {
		batch.setColor(Colours.dark);
		TannFont.font.draw(batch, "Score: "+score, 50, (int)(Main.height*.3f));
		TannFont.font.draw(batch, "Highscore: "+highscore, 100, (int)(Main.height*.3f));
	}

	@Override
	public void preTick(float delta) {
		obstacleTicks+=delta;
		
		if(obstacleTicks>Math.max(.83, 1.5f-score*.025f)){
			Obstacle o = new Obstacle(ObstacleType.values()[(int) (Math.random()*2)], 1);
			addActor(o);
			obstacles.add(o);
			obstacleTicks=0;
		}
	}
	
	public void incrementScore(){
		score++;
		highscore=Math.max(score, highscore);
	}
	
	public void resetScore(){
		score=0;
		for(Obstacle o: obstacles){
			o.remove();
		}
		obstacles.clear();
	}

	@Override
	public void postTick(float delta) {
	}

	@Override
	public void keyPress(int keycode) {
		turtle.keyPress(keycode);
	}

}
