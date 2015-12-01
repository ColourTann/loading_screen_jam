package game.screens.minigames.snake;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import game.Main;
import game.screens.minigames.LoadingBar;
import game.screens.minigames.Minigame;
import game.screens.minigames.snake.Tile.TileEnterResult;
import game.util.TannFont;
import game.util.TextWisp;

public abstract class Snakey extends Actor{


	Tile t;
	int dx=1, dy;
	int size=10;
	boolean dead, decomposed;
	Array<Tile> bodyParts = new Array<Tile>();
	public Snakey(Tile start) {
		enterTile(start);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for(int i=0;i<bodyParts.size;i++){
			Tile t =bodyParts.get(i);
			int prev = i-1;
			int next = i+1;
			Tile prevTile=null;
			Tile nextTile=null;
			if(prev>=0) prevTile=bodyParts.get(prev);
			if(next<bodyParts.size) nextTile=bodyParts.get(next);
			t.drawSnake(batch, this instanceof PlayerSnakey, prevTile, nextTile);
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if(dead&&bodyParts.size==0) decompose();
	}

	private void decompose() {
		decomposed=true;
		if(this instanceof PlayerSnakey) SnakeGame.get().reset();
	}

	public void turn(){
		if(dead&&bodyParts.size>0){
			bodyParts.removeIndex(0);
		}
	}

	public void changeDirection(int keycode){
		switch(keycode){
		case Input.Keys.LEFT:
			if(dx!=0) return;
			dy=0; dx=-1;
			break;
		case Input.Keys.RIGHT:
			if(dx!=0) return;
			dy=0; dx=1;
			break;
		case Input.Keys.UP:
			if(dy!=0) return;
			dy=1; dx=0;
			break;
		case Input.Keys.DOWN:
			if(dy!=0) return;
			dy=-1; dx=0;
			break;
		}
	}

	void enterTile(Tile t){
		TileEnterResult result = t.enterWithSnake(size, this instanceof PlayerSnakey);
		this.t=t;
		bodyParts.add(t);
		if(bodyParts.size>size){
			bodyParts.removeIndex(0);
		}
		switch(result){
		case Death:
			die(DeathType.HitSnake);
			break;
		case Food:
			if(this instanceof PlayerSnakey) SnakeGame.get().incrementScore();
			size+=3;
			break;
		case Nothing:
			break;
		default:
			break;
		}
	}

	public boolean containsTile(Tile t){
		return bodyParts.contains(t, true);
	}

	Tile getNextTile(){
		return t.getTile(dx, dy);
	}

	public enum DeathType{HitSnake, HitEdge, HitSelf};
	public void die(DeathType type){
		String text ="";

		switch(type){
		case HitEdge:
			text="smashed";
			break;
		case HitSnake:
			text="chomped";
			break;
		case HitSelf:
			text="oops";
			break;
		default:
			break;
		}
		TextWisp wisp=new TextWisp(text, (int)(t.getX()), (int)(t.getY())+5);
		int w = wisp.width;
		int h = TannFont.font.getHeight();
		if(wisp.x-w/2<0) wisp.x=w/2;
		if(wisp.x+w/2>Main.width) wisp.x=Main.width-w/2;
		if(wisp.y-h/2<0) wisp.y=h/2;
		if(wisp.y+h/2>Main.height-LoadingBar.loadingBarHeight-10) wisp.y=Main.height-h/2-LoadingBar.loadingBarHeight-10;
		wisp.disableAlpha();
		SnakeGame.get().addParticle(wisp);
		dead=true;
	}

}
