package game.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pools;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;

public class GameScreen extends Screen{
	public GameScreen() {
		setSize(Main.width, Main.height);
		for(int i=0;i<50;i++)addActor(new Clickable());
	}
	
	@Override
	public void preDraw(Batch batch) {
		batch.setColor(Colours.brown);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void postDraw(Batch batch) {
		
	}

	@Override
	public void postAct(float delta) {
		// TODO Auto-generated method stub
		addParticle(new Orbiter());
	}

	
}
