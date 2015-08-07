package game.util;

import game.Main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Border {

	static Color bg = Colours.dark;
	static TextureRegion corner = Main.atlas.findRegion("corner");
	static TextureRegion edge = Main.atlas.findRegion("edge");
	
	public static void draw(Batch batch, float x, float y, float width, float height){
		int offset=corner.getRegionWidth(); //offset to avoid background rectangle and edges going past corners//
		
		//background//
		batch.setColor(bg);
		Draw.fillRectangle(batch, x+offset, y+offset, width-offset*2, height-offset*2);
		
		//corners//
		batch.setColor(1,1,1,1);
		Draw.drawRotatedScaled(batch, corner, x, y, 1, 1, 0);
		Draw.drawRotatedScaled(batch, corner, x+width, y, 1, 1, (float)Math.PI/2);
		Draw.drawRotatedScaled(batch, corner, x+width, y+height, 1, 1, (float)Math.PI);
		Draw.drawRotatedScaled(batch, corner, x, y+height, 1, 1, (float)Math.PI*3/2);
		
		//edges//
		Draw.drawRotatedScaled(batch, edge, x+offset, y, width-offset*2, 1, 0);
		Draw.drawRotatedScaled(batch, edge, x+width, y+offset, height-offset*2, 1, (float)Math.PI/2);
		Draw.drawRotatedScaled(batch, edge, x+width-offset, y+height, width-offset*2, 1, (float)Math.PI);
		Draw.drawRotatedScaled(batch, edge, x, y+height-offset, height-offset*2, 1, (float)Math.PI*3/2);
	}
}