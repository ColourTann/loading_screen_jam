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
	
	@Override
	public void act(float delta) {
		setPosition((int)getX(), (int)getY());
		preTick(delta);
		super.act(delta);
		postTick(delta);
	}
	
	public abstract void preTick(float delta);
	public abstract void postTick(float delta);
}
