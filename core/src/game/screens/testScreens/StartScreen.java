package game.screens.testScreens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;

import game.Main;
import game.util.Fonts;
import game.util.Screen;
import game.util.TextBox;
import game.util.TextRenderer;

public class StartScreen extends Screen{



	private static StartScreen self;
	TextBox tb;
	public static StartScreen get(){
		if(self==null) self= new StartScreen();
		return self;
	}

	public StartScreen() {
		TextRenderer.setImage("test", Main.atlas.findRegion("shiptiny"));
		TextRenderer.setImage("grass", Main.atlas.findRegion("grass"));
		TextRenderer.setImage("spiral", Main.atlas.findRegion("spiral"));
		TextRenderer.setImage("happy", Main.atlas.findRegion("happy"));
		TextRenderer.setImage("lasers", Main.atlas.findRegion("lasers"));
		TextRenderer.setImage("cat", Main.atlas.findRegion("cat"));
		
		for(int i=1;i<3;i++){
			String s="";
			for(int j=0;j<i;j++)s+="a a B";
			tb = new TextBox(s);
			tb.setPosition(50, i*40);
			addActor(tb);	
		}
		
	}

	@Override
	public void preDraw(Batch batch) {
	}

	@Override
	public void postDraw(Batch batch) {

	}

	@Override
	public void preTick(float delta) {


	}

	@Override
	public void postTick(float delta) {
	}

}
