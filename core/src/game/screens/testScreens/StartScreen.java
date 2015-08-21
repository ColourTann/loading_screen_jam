package game.screens.testScreens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;

import game.util.Fonts;
import game.util.Screen;
import game.util.TextBox;

public class StartScreen extends Screen{

	
	
	private static StartScreen self;
	TextBox tb;
	public static StartScreen get(){
		if(self==null) self= new StartScreen();
		return self;
	}
	
	public StartScreen() {
tb = new TextBox("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", 200);
tb.setPosition(50, 50);
addActor(tb);
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
