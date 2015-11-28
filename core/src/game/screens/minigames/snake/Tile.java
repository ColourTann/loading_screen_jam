package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.util.Colours;
import game.util.Draw;

public class Tile extends Actor{
	public static int tileSize=5;
	int gridX, gridY;
	int snake;
	public Tile(int gridX, int gridY) {
		this.gridX=gridX; this.gridY=gridY;
		setPosition(gridX*tileSize, gridY*tileSize);
		setSize(tileSize, tileSize);
	}
	
	public void turn(){
		if(snake>0) snake--;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(snake>0){
			batch.setColor(Colours.light);
			Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		}
		super.draw(batch, parentAlpha);
	}
	
	public Tile getTile(int dx, int dy){
		return Snake.get().g.getTile(gridX+dx, gridY+dy);
	}

	public void snakeIt(int duration) {
		this.snake=duration;
	}
	
}
