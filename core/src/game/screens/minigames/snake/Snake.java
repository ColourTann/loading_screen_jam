package game.screens.minigames.snake;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.screens.minigames.LoadingBar;
import game.screens.testScreens.GameScreen;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;

public class Snake extends Screen{
	private static Snake self;
	public static Snake get(){
		if(self==null) self= new Snake();
		return self;
	}
	private Snake() {
		addActor(new LoadingBar());
	}
	
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.dark);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
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
