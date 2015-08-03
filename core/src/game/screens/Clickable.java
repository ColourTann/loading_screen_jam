package game.screens;

import game.Main;
import game.util.Colours;
import game.util.Draw;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Clickable extends Actor{
	boolean clicked;
	
	public Clickable() {
		setSize((float)Math.random()*5, (float)Math.random()*30);
		setPosition((float)Math.random()*Main.width, (float)Math.random()*Main.height);
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				clicked=true;
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				clicked=false;
			}
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(clicked?Colours.dark:Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		super.draw(batch, parentAlpha);
	}

}
