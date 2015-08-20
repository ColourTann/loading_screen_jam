package game.screens.testScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import game.Main;
import game.util.Fonts;
import game.util.Mouse;
import game.util.Screen;
import game.util.TextWisp;

public class FontScreen extends Screen{

	int fontX, fontY;
	
	private static FontScreen self;
	public static FontScreen get(){
		if(self==null) self= new FontScreen();
		return self;
	}
	
	@Override
	public void preDraw(Batch batch) {
	}

	@Override
	public void postDraw(Batch batch) {
		
		for(int i=0;i<4;i++){
			Fonts.fontSizes[i].draw(batch, "Different sizes!", getX()+0, getY()-30+(float) (getHeight()-(Math.pow(i+.5, 2.3)+i*1)*5));
		}
		int x= 10; int y=50;
		Fonts.battlenet.draw(batch, "Font: battlenet", getX()+x, getY()+y);
		x+=100;
		Fonts.helvetipixel.draw(batch, "Font: helvetipixel", getX()+x, y);
		x+=100;
		Fonts.pixelarial.draw(batch, "Font: pixelarial", getX()+x, getY()+y);
		x=10;
		y+=50;
		Fonts.o4b3.draw(batch, "Font: 04b3", getX()+x, getY()+y);
		x+=100;
		Fonts.visitor.draw(batch, "Font: visitor", getX()+x, getY()+y);
		x+=100;
		Fonts.pixelmix.draw(batch, "Font: pixelmix", getX()+x, getY()+y);
		

		
	}

	@Override
	public void preTick(float delta) {
		fontX=Mouse.getX();
		fontY=Mouse.getY();
		addParticle(new TextWisp("more words!", fontX, fontY));
	}

	@Override
	public void postTick(float delta) {
	}
	
	

}
