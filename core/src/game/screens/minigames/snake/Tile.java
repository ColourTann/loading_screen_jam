package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import game.screens.testScreens.GameScreen;
import game.util.Colours;
import game.util.Draw;

public class Tile extends Actor{
	public static int tileSize=3;
	int gridX, gridY;
	boolean pellet;
	public Tile(int gridX, int gridY) {
		this.gridX=gridX; this.gridY=gridY;
		setPosition(gridX*tileSize, gridY*tileSize+SnakeGame.scoreSize);
		setSize(tileSize, tileSize);
	}
	
	public void turn(){

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(pellet){
			batch.setColor(Colours.blue);
			Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
			batch.setColor(Colours.light);
			Draw.fillRectangle(batch, getX()+1, getY()+1, 1, 1);
		}
		super.draw(batch, parentAlpha);
	}
	
	public Tile getTile(int dx, int dy){
		return SnakeGame.get().grid.getTile(gridX+dx, gridY+dy);
	}

	public boolean addPellet(){
		if(SnakeGame.get().grid.collide(this))return false;
		pellet=true;
		return true;
	}
	
	public enum TileEnterResult{Nothing, Death, Food};
	public TileEnterResult enterWithSnake(int duration, boolean player) {
		if(SnakeGame.get().grid.collide(this)){
			return TileEnterResult.Death;
		}
		if(pellet){
			pellet=false;
			SnakeGame.get().grid.addPellet();
			return TileEnterResult.Food;
		}
		return TileEnterResult.Nothing;
	}
	
	public boolean occupied(){
		return SnakeGame.get().grid.collide(this);
	}
	
	public boolean trapped(){
		if(occupied())return true;
		for(Tile t:getTilesWithinOne()){
			if(!t.occupied())return false;
		}		
		return true;
	}
	
	public Array<Tile> getTilesWithinOne(){
		Array<Tile> result = new Array<Tile>();
		Tile t;
		t= getTile(1, 0);
		if(t!=null) result.add(t);
		t= getTile(-1, 0);
		if(t!=null) result.add(t);
		t= getTile(0, 1);
		if(t!=null) result.add(t);
		t= getTile(0, -1);
		if(t!=null) result.add(t);
		return result;
	}

	static Tile[] tiles = new Tile[2];
	public void drawSnake(Batch batch, boolean player, Tile previous, Tile next) {
		batch.setColor(Colours.blue);
		
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		batch.setColor(player?Colours.light:Colours.dark);
		Draw.fillRectangle(batch, getX()+1, getY()+1, 1, 1);
		tiles[0]=previous;
		tiles[1]=next;
		for(Tile t:tiles){
			if(t==null)continue;
			int dx = t.gridX-gridX;
			int dy = t.gridY-gridY;
			Draw.fillRectangle(batch, getX()+1+dx, getY()+1+dy, 1, 1);			
		}
		
	}
	public String toString(){
		return gridX+":"+gridY;
	}

	public int distanceToPlayer() {
		int dx=SnakeGame.get().grid.player.t.gridX-gridX;
		int dy=SnakeGame.get().grid.player.t.gridY-gridY;
		return Math.abs(dx)+Math.abs(dy);
	}

	public void reset() {
		pellet=false;
	}
}
