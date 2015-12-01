package game.screens.unlock;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;

import game.screens.minigames.Minigame;
import game.util.Colours;
import game.util.TannFont;

public class KeyUnlock extends Unlock{
	char key;
	public KeyUnlock(char key) {
		super();
		this.key=key;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(Colours.dark);
		TannFont.font.draw(batch, "Key: "+key, (int)(getX()+getWidth()/2), (int)(getY()+getHeight()/2), Align.center);
	}
	
}
