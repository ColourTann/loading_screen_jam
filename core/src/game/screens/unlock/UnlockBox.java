package game.screens.unlock;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.TannFont;

public class UnlockBox extends Group{
	private static int gap = 4, border = 2, top = 10, bottom = 10;
	String name;
	public UnlockBox(String name, Unlock[] unlocks) {
		this.name=name;
		setSize(gap*3+border*2+Unlock.width*2, gap*3+border*2+Unlock.height*2+top+bottom);
		setPosition((int)(Main.width/2-getWidth()/2), (int)(Main.height/2-getHeight()/2));
		for(int i=0;i<unlocks.length;i++){
			Unlock u = unlocks[i];
			addActor(u);
			u.setPosition(border+gap+(i%2)*(u.getWidth()+gap), bottom+border+gap+(i/2)*(u.getHeight()+gap));
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		batch.setColor(Colours.dark);
		Draw.fillRectangle(batch, getX()+border, getY()+border, getWidth()-border*2, getHeight()-border*2);
		batch.setColor(Colours.light);
		TannFont.font.draw(batch, "loaded game: "+name, (int)(getX()+getWidth()/2), (int)(getY()+getHeight()-top/1.2), Align.center);
		TannFont.font.draw(batch, "press a key to continue", (int)(getX()+getWidth()/2), (int)(getY()+bottom/1.2), Align.center);
		super.draw(batch, parentAlpha);
	}
}
