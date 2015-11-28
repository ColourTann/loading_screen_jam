package game.screens.unlock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;

import game.util.Colours;
import game.util.Draw;
import game.util.TannFont;

public class ColourUnlock extends Unlock{
	String name;
	Color colour;
	public ColourUnlock(String name, Color colour) {
		super();
		this.name=name;
		this.colour=colour;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(colour==Colours.light){
			batch.setColor(Colours.dark);
			Draw.fillRectangle(batch, getX()+2, getY()+2, getWidth()-4, getHeight()-4);
		}
		batch.setColor(colour);
		TannFont.font.draw(batch, "colour: "+name, (int)(getX()+getWidth()/2), (int)(getY()+getHeight()/2), Align.center);
	}
}
