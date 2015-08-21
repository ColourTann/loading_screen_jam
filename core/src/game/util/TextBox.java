package game.util;



import game.Main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

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

	private FrameBuffer buffer;
	private void setup(String text, BitmapFont font, int boxWidth){
		
		this.font=font;
		this.text=text;
		this.wrapWidth=boxWidth-gap*2;
		Fonts.bounds.setText(font, text, dummyColor, wrapWidth, Align.center, true);

		setSize(boxWidth, (int)(gap*2+Fonts.bounds.height));
		
		fontHeight= (int) (Fonts.bounds.height/2+getHeight()/2);
		fontHeight=0;
		fontHeight=(int) (getHeight()-gap);		
		setupLines(text, wrapWidth);
	}



	@Override
	public void draw(Batch batch, float parentAlpha) {

		font.setColor(Colours.light);
		Border.draw(batch, getX(), getY(), getWidth(), getHeight(), moused);
		for(Line l: lines){
			l.render(batch);
		}
//		font.draw(batch, text, getX()+gap, getY()+fontHeight, wrapWidth, Align.center, true);
		
		super.draw(batch, parentAlpha);
	}

	Array<Line> lines = new Array<Line>();

	
	SpriteBatch batch = new SpriteBatch();
	private void setupLines(String entireText, int wrapWidth){
		
		int currentX=0;
		int currentY=0;
		int lineHeight = (int) font.getLineHeight();
		int spaceWidth = (int) Fonts.font.getSpaceWidth();
		char[] charArray = entireText.toCharArray();
		int previousIndex=0;

		Line currentLine= new Line();
	
		for(int index = 0; index<charArray.length; index++){
			char c = charArray[index];

			if(c==' '||index==charArray.length-1){
				String word = entireText.substring(previousIndex, index);
				previousIndex=index+1;
				int length = getLength(word);
				if(currentX+length>wrapWidth){
					currentX=0;
					currentY-=lineHeight;
					lines.add(currentLine);
					currentLine = new Line();
				}
				currentLine.addTextPosition(new TextPosition(word, currentX, currentY));
				System.out.println(word);
				currentX+=length+spaceWidth;
			}
		}
		
		buffer = new FrameBuffer(Format.RGBA8888, wrapWidth, lines.size*spaceWidth, false);
		
		buffer.bind();
		buffer.begin();
		batch.begin();
		batch.setColor(Colours.dark);
		
		
		
		stage.draw();
		

		if(Main.debug)drawFPS(batch);
		
		buffer.end();
		
	}

	
	private int getLength(String word) {
		Fonts.bounds.setText(font, word);
		return (int) Fonts.bounds.width;
	}

	private  class Line{
		Array<TextPosition> textPositions = new Array<TextPosition>();
		public Line() {
		}
		public void addTextPosition(TextPosition tp){
			textPositions.add(tp);
		}
		public void render(Batch batch){
			for(TextPosition tp: textPositions) tp.render(batch);
		}
	}

	private  class TextPosition{
		String text; 
		int x, y;
		public TextPosition(String text, int x, int y) {
			this.text=text; 
			this.x=x; this.y=y;
		}
		public void render(Batch batch){
			font.draw(batch, text, x, y);
		}
	}

}
