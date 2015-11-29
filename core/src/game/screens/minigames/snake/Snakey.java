package game.screens.minigames.snake;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Snakey extends Actor{


	Tile t;
	int dx, dy;
	int size=5;
	int nextKeycode=-1;
	boolean turned;
	public Snakey(Tile start) {
		enterTile(start);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public void turn() {
		enterTile(t.getTile(dx, dy));
		turned=false;
		if(nextKeycode!=-1){
			keyPress(nextKeycode);
			nextKeycode=-1;
		}
	}

	public void keyPress(int keycode){
		if(turned){
			nextKeycode=keycode;
			return;
		}
		turned=true;
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

	private void enterTile(Tile t){
		this.t=t;
		boolean b = t.enterWithSnake(size);
		if(b)size++;
	}

}
