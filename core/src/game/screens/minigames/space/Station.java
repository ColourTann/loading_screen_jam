package game.screens.minigames.space;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.util.Colours;
import game.util.Draw;

public class Station extends Actor{
	private static TextureRegion image = Main.atlas.findRegion("space/station");
	static final int hpWidth =image.getRegionWidth();
	static final int hpHeight =2;
	static final int hpOffset =2;
	float hp=1;
	
	public Station() {
		setSize(image.getRegionWidth(), image.getRegionHeight());
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(Colours.white);
		Draw.draw(batch, image, (int)(getX()-getWidth()/2), (int)(getY()-getHeight()/2));
		batch.setColor(Colours.red);
		Draw.fillRectangle(batch, getX()-getWidth()/2, getY()-getHeight()/2 - hpOffset - hpHeight, hpWidth, hpHeight);
		batch.setColor(Colours.blue);
		Draw.fillRectangle(batch, getX()-getWidth()/2, getY()-getHeight()/2 - hpOffset - hpHeight, hpWidth, hpHeight);
	}
	
}
