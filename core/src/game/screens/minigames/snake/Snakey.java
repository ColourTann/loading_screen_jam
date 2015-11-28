package game.screens.minigames.snake;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Snakey extends Actor{

	int direction;
	boolean turned;
	Tile t;
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
		turned=false;
		int dx=0, dy=0;
		switch(direction){
		case 0: dx=1; break;
		case 1: dy=1; break;
		case 2: dx=-1; break;
		case 3: dy=-1; break;
		}
		enterTile(t.getTile(dx, dy));
	}

	public void keyPress(int keycode){
		if(turned) return;
		turned=true;
		switch(keycode){
		case Input.Keys.LEFT:
			direction++;
			break;
		case Input.Keys.RIGHT:
			direction--;
			break;
		}
		direction+=4;
		direction%=4;
	}

	private void enterTile(Tile t){
		this.t=t;
		t.snakeIt(5);
	}

}
