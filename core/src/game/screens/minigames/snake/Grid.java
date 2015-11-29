package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import game.Main;
import game.screens.minigames.LoadingBar;
import game.screens.minigames.Minigame;

public class Grid extends Group{
	int tilesAcross=Main.width/Tile.tileSize;
	int tilesDown=(Main.height-LoadingBar.loadingBarHeight)/Tile.tileSize;
	Tile[][] tiles = new Tile[tilesAcross][tilesDown];
	static final float secondsPerMove=.15f;
	float move = 0;
	Snakey s;
	public Grid() {
		setPosition(0, 0);
		for(int x=0;x<tilesAcross;x++){
			for(int y=0;y<tilesDown;y++){
				Tile t= new Tile(x,y);
				tiles[x][y] = t;
				addActor(t);
			}
		}
		s = new Snakey(tiles[4][tilesDown/2]);
		addActor(s);
		
	}
	
	public void addPellet(){
		boolean found = false;
		while(!found){
			found = getRandomTile().addPellet();
		}
	}
	
	public Tile getRandomTile(){
		return tiles[(int)(Math.random()*tilesAcross)][(int)(Math.random()*tilesDown)];
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		move += delta;
		if(move>=secondsPerMove){
			move-=secondsPerMove;
			turn();
		}
		super.act(delta);
	}
	
	public void turn(){
		for(int x=0;x<tilesAcross;x++){
			for(int y=0;y<tilesDown;y++){
				tiles[x][y].turn();
			}
		}
		s.turn();
	}
	
	public Tile getTile(int x, int y) {
		return tiles[(x+tilesAcross)%tilesAcross][(y+tilesDown)%tilesDown];
	}
	
}
