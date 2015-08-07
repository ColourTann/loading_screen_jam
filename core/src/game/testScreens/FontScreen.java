package game.testScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import game.Main;
import game.screens.GameScreen;
import game.util.Fonts;
import game.util.Screen;

public class FontScreen extends Screen{

	@Override
	public void preDraw(Batch batch) {
	}

	@Override
	public void postDraw(Batch batch) {
		
		for(int i=0;i<4;i++){
			Fonts.fontSizes[i].draw(batch, "Different sizes!", 0, (float) (Gdx.graphics.getHeight()-(Math.pow(i, 1.98)+i*1)*5));
		}
		int x= 10; int y=50;
		Fonts.love.draw(batch, "Font: pixel-love", x, y);
		x+=100;
		Fonts.O4B03.draw(batch, "Font: 04B03", x, y);
		x+=100;
		Fonts.pixelArial.draw(batch, "Font: pixelarial", x, y);
		x=10;
		y+=50;
		Fonts.pixelMix.draw(batch, "Font: pixelmix", x, y);
		x+=100;
		Fonts.visitor.draw(batch, "Font: visitor", x, y);
		x+=100;
		Fonts.pressStartP2.draw(batch, "Font: pressstartp2", x, y);
		
		Fonts.font.draw(batch, "another font", Gdx.input.getX(), Main.height-Gdx.input.getY());
		
	}

	@Override
	public void preTick(float delta) {
	}

	@Override
	public void postTick(float delta) {
	}
	
	

}
