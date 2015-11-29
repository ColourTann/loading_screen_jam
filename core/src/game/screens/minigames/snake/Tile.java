package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.util.Colours;
import game.util.Draw;

public class Tile extends Actor{
	public static int tileSize=5;
	int gridX, gridY;
	int snake;
	boolean pellet;
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
		if(pellet){
			batch.setColor(Colours.mixer);
			Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		}
		if(snake>0){
			batch.setColor(Colours.light);
			Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		}
		super.draw(batch, parentAlpha);
	}
	
	public Tile getTile(int dx, int dy){
		return SnakeGame.get().g.getTile(gridX+dx, gridY+dy);
	}

	public boolean addPellet(){
		if(snake>0)return false;
		pellet=true;
		return true;
	}
	
	public boolean enterWithSnake(int duration) {
		this.snake=duration;
		if(pellet){
			pellet=false;
			SnakeGame.get().g.addPellet();
			return true;
		}
		return false;
	}
}
