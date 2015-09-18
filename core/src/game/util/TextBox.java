package game.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;

public class TextBox extends Group{
	public static int gap = 8;
	String string;
	TextRenderer box;
	boolean moused;
	public TextBox(String string) {
		setup(string, -1);
	}
	
	public TextBox(String string, int width) {
		setup(string,width);
	}
	
	public void setup(String string, int width){
		this.string=string;
		if(width!=-1)box = new TextRenderer(string, width-gap*2);
		else box = new TextRenderer(string);
		box.setPosition(gap, gap);
		if(width==-1)width=(int) (box.getWidth()+gap*2);
		setSize(width, box.getHeight()+gap*2);
		addActor(box);
	}
	
	public void makeMouseable(){
		addListener(new InputListener(){
			public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				moused=true;
			}

			public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
				moused=false;
			}
		});
	}
	
	public void addClickAction(final Runnable r){
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				r.run();
				return false;
			}
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Border.draw(batch, getX(), getY(), getWidth(), getHeight(), moused);
		super.draw(batch, parentAlpha);
	}
	
}
