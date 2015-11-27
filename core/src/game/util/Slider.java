package game.util;

import game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;


public class Slider extends Actor{
	final static int defaultWidth=80, defaultHeight=9;
	final static int gap=1;
	
	//preset sliders//
	public static Slider SFX=  new Slider("SFX", .5f, Colours.dark, Colours.light);
	public static Slider music=  new Slider("Music", .5f, Colours.dark, Colours.light);
	static{
		music.addSlideAction(new Runnable() {
			
			@Override
			public void run() {
				Sounds.updateMusicVolume();
			}
		});
	}
	
	
	private float value;
	private Color backGround, foreGround;
	private boolean dragging;
	private String title;
	public Slider(String title, float base, Color bg, Color fg) {
		this.title=title;
		value=base;
		backGround=bg; foreGround=fg;
		setSize(defaultWidth, defaultHeight);
		
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				dragging=true;
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				dragging=false;
			}
		});
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(dragging){
			value=((Gdx.input.getX()/Main.scale)-getX()-getParent().getX()-gap)/(getWidth()-gap*2);
			value=Math.max(0, Math.min(1, value));
			if(slideAction!=null)slideAction.run();
		}
	}
	
	Runnable slideAction;
	public void addSlideAction(Runnable r){
		this.slideAction=r;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(foreGround);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		batch.setColor(backGround);
		Draw.drawRectangle(batch, getX(), getY(), getWidth(), getHeight(), gap);
		Draw.fillRectangle(batch, getX()+gap, getY()+gap, (getWidth()-gap*2)*value, getHeight()-gap*2);
		batch.setColor(Colours.mixer);
		TannFont.font.draw(batch, title, (int)(getX()+getWidth()/2), (int)(getY()+getHeight()/2-TannFont.font.getHeight()/2), Align.center);		
	}
	public float getValue(){
		return value;
	}
}