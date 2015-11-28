package game.screens.unlock;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import game.util.Colours;
import game.util.Draw;

public class Unlock extends Group{
	public static int width=60, height=17;
	public Unlock() {
		setSize(width, height);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		super.draw(batch, parentAlpha);
	}
}
