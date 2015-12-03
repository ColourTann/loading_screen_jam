package game.screens.unlock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.TannFont;

public class UnlockBox extends Group{
	private static int gap = 4, border = 2, top = 10, bottom = 10, extraText=10;
	String name;
	boolean allUnlocked;
	public UnlockBox(String name, Unlock[] unlocks, boolean allUnlocked) {
		this.allUnlocked=allUnlocked;
		this.name=name;
		int rows = (unlocks.length+1)/2;
		setSize(gap*3+border*2+Unlock.width*2, gap*(rows+1)+border*rows+Unlock.height*rows+top+bottom+extraText);
		setPosition((int)(Main.width/2-getWidth()/2), (int)(Main.height/2-getHeight()/2));
		for(int i=0;i<unlocks.length;i++){
			Unlock u = unlocks[i];
			addActor(u);
			u.setPosition(border+gap+(i%2)*(u.getWidth()+gap)+(unlocks.length%2)*(i==unlocks.length-1?1:0)*(gap/2+u.getWidth()/2), bottom+border+gap+(i/2)*(u.getHeight()+gap));
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color borderCol=null;
		Color backgroundCol=null;
		Color textCol=null;
		switch(Main.coloursUnlocked){
		case 2:
			borderCol=Colours.light;
			backgroundCol=Colours.dark;
			textCol=Colours.light;
			break;
		case 3:
			borderCol=Colours.blue;
			backgroundCol=Colours.dark;
			textCol=Colours.light;
			break;
		case 4:
			borderCol=Colours.red;
			backgroundCol=Colours.dark;
			textCol=Colours.blue;
			break;
			

		}
		batch.setColor(borderCol);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		batch.setColor(backgroundCol);
		Draw.fillRectangle(batch, getX()+border, getY()+border, getWidth()-border*2, getHeight()-border*2);
		batch.setColor(textCol);
		String s = "loaded game: "+name;
		if(allUnlocked) s= "all games loaded, space to cycle";
		TannFont.font.draw(batch, s, (int)(getX()+getWidth()/2), (int)(getY()+getHeight()-top/1.2), Align.center);
		TannFont.font.draw(batch, "loaded new features:", (int)(getX()+getWidth()/2), (int)(getY()+getHeight()-top-extraText/1.2), Align.center);
		TannFont.font.draw(batch, "press space to continue", (int)(getX()+getWidth()/2), (int)(getY()+bottom/1.2), Align.center);
		super.draw(batch, parentAlpha);
	}
}
