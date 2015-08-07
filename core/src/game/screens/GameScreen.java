package game.screens;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.Fonts;
import game.util.Screen;

public class GameScreen extends Screen{
	public GameScreen() {
		setSize(Main.width, Main.height);
		for(int i=0;i<50;i++)addActor(new Clickable());
	}
	
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.brown);
		Draw.fillRectangle(batch, getX(), getY(), getWidth()-5, getHeight());
	}
	
	@Override
	public void postDraw(Batch batch) {
		Fonts.love.draw(batch, "sdioufsiudfh", 50, 50);
	}

	@Override
	public void preTick(float delta) {
	}

	@Override
	public void postTick(float delta) {
	}

	
}
