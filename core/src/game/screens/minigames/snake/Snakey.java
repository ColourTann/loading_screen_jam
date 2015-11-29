package game.screens.minigames.snake;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Snakey extends Actor{


	Tile t;
	int dx, dy;
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
	}

	public void keyPress(int keycode){
	
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
		t.snakeIt(5);
	}

}
