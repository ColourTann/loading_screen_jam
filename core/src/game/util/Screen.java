package game.util;

import game.Main;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class Screen extends Group{
	
	public Screen() {

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		preDraw(batch);
		super.draw(batch, parentAlpha);
		postDraw(batch);
	}
	public abstract void preDraw(Batch batch);
	public abstract void postDraw(Batch batch);
}
