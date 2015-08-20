package game.util;



import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;

public class TextBox extends Actor{
	private static Color dummyColor=Colours.white;
	private static BitmapFont defaultFont=Fonts.font;
	BitmapFont font = defaultFont;
	public static int gap =8;
	int wrapWidth;
	String text;
	int fontHeight;
	Runnable r;
	boolean moused;
	public TextBox(String text){
		Fonts.bounds.setText(font, text);
		setup(text, defaultFont, (int)Fonts.bounds.width+gap*2);
	}
	
	public TextBox(String text, int boxWidth) {
		setup(text, defaultFont, boxWidth);
	}
	
	public TextBox(String text, BitmapFont font, int boxWidth) {
		setup(text, font, boxWidth);
	}
	
	public void addClickAction(final Runnable r){
		addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {	
				r.run();
				return false;
			}
		});
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
	
	private void setup(String text, BitmapFont font, int boxWidth){
		this.font=font;
		this.text=text;
		this.wrapWidth=boxWidth-gap*2;
		Fonts.bounds.setText(font, text, dummyColor, wrapWidth, Align.center, true);
		
		setSize(boxWidth, (int)(gap*2+Fonts.bounds.height));
		System.out.println(getHeight()+":"+Fonts.bounds);
		fontHeight= (int) (Fonts.bounds.height/2+getHeight()/2);
		fontHeight=0;
		fontHeight=(int) (getHeight()-gap);
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		font.setColor(Colours.light);
		Border.draw(batch, getX(), getY(), getWidth(), getHeight(), moused);
		font.draw(batch, text, getX()+gap, getY()+fontHeight, wrapWidth, Align.center, true);
		super.draw(batch, parentAlpha);
	}

}
