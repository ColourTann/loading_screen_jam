package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import game.Main;
import game.screens.minigames.LoadingBar;
import game.screens.minigames.Minigame;

public class Grid extends Group{
	int tilesAcross=Main.width/Tile.tileSize;
	int tilesDown=(Main.height-LoadingBar.loadingBarHeight-SnakeGame.scoreSize)/Tile.tileSize;
	Tile[][] tiles = new Tile[tilesAcross][tilesDown];
	static final float secondsPerMove=.08f;
	float move = 0;
	Array<Snakey> snakeys = new Array<Snakey>();
	PlayerSnakey player;
	int initialSnakes=6;
	public Grid() {
		setPosition(0, 0);
		for(int x=0;x<tilesAcross;x++){
			for(int y=0;y<tilesDown;y++){
				Tile t= new Tile(x,y);
				tiles[x][y] = t;
				addActor(t);
			}
		}
	}
	
	public void reset() {
		for(int x=0;x<tilesAcross;x++){
			for(int y=0;y<tilesDown;y++){
				Tile t =tiles[x][y];
				t.reset();
			}
		}
		for(Snakey s:snakeys){
			s.remove();
		}
		snakeys.clear();
		setup();
	}
	
	public void setup(){
		addPlayer();
		for(int i=0;i<initialSnakes-1;i++) addSnakey();
		for(int i=0;i<2;i++) addPellet();
	}
	
	public void addPlayer(){
		player =new PlayerSnakey(tiles[4][tilesDown/2]);
		snakeys.add(player);
		addActor(player);
	}
	
	public void addSnakey(){
		Snakey s = new EnemySnakey(getRandomFreeTile(true), (int)(Math.random()*5)+10);
		snakeys.add(s);
		addActor(s);
	}
	
	public void addPellet(){
		getRandomFreeTile(false).addPellet();
	}
	
	public Tile getRandomTile(){
		return tiles[(int)(Math.random()*tilesAcross)][(int)(Math.random()*tilesDown)];
	}
	
	private Tile getRandomFreeTile(boolean farFromPlayer) {
		Tile t= getRandomTile();
		while(t.trapped()||(farFromPlayer&&t.distanceToPlayer()<10)){
			t=getRandomTile();
		}
		return t;
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
			if(move>=secondsPerMove) move=0;
			turn();
		}
		
		if(Math.random()>.9995) addPellet();
		if(Math.random()>.95&&snakeys.size<initialSnakes)addSnakey();
		
		super.act(delta);
	}
	
	public void turn(){
		for(int x=0;x<tilesAcross;x++){
			for(int y=0;y<tilesDown;y++){
				tiles[x][y].turn();
			}
		}
		for(int i=0;i<snakeys.size;i++){
			Snakey s= snakeys.get(i);
			s.turn();
		}
		for(int i=snakeys.size-1;i>=0;i--){
			Snakey s=snakeys.get(i);
			if(s.decomposed)snakeys.removeIndex(i);
		}
		
	}
	
	public boolean collide(Tile t){
		for(Snakey s:snakeys){
			if(s.containsTile(t))return true;
		}
		return false;
	}
	
	public Tile getTile(int x, int y) {
		if(x<0||x>=tiles.length||y<0||y>=tiles[0].length)return null;
		return tiles[(x)][(y)];
	}


	
}
