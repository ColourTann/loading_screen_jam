package game.screens.minigames.space;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.util.Colours;
import game.util.Draw;

public class Star extends Actor{
	
	float speed;
	
	public Star() {
		resetPosition();
	}
	
	public void resetPosition(){
		speed=(float) (Math.random()*1+.5f);
		setPosition((float)Math.random()*Main.width, (float)Math.random()*Main.height);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), 1, 1);
		super.draw(batch, parentAlpha);
	}

}
