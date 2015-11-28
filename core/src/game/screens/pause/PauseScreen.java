package game.screens.pause;

import game.Main;
import game.Main.TransitionType;
import game.screens.minigames.snake.Snake;
import game.screens.testScreens.GameScreen;

import game.util.Border;
import game.util.Button;
import game.util.Colours;
import game.util.Draw;
import game.util.Screen;
import game.util.Slider;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;

public class PauseScreen extends Group{
	private static int w=120,h=70;
	private static PauseScreen self;
	public static PauseScreen get(){
		if(self==null)self=new PauseScreen();
		return self;
	}
	
	private PauseScreen(){
		setSize(w,h);
		setPosition(Main.width/2-w/2, Main.height/2-h/2);
		addTransitionButton("restart", Snake.get(), (int)(getWidth()/2), getY(.9f), 40);
		
		
		
		Slider.SFX.setPosition(w/2-Slider.SFX.getWidth()/2, getY(.3f));
		addActor(Slider.SFX);
		
		Slider.music.setPosition(w/2-Slider.SFX.getWidth()/2, getY(.55f));
		addActor(Slider.music);
		
		int numScales=5;
		int increment = w/(numScales+1);
		for(int i=0;i<numScales;i++) addScaleButton(i+1, increment*(i+1), getY(.13f), increment-2);
	}
	
	private void addScaleButton(final int scale, int x, int y, int width){
		Button t = new Button("X "+scale, width);
		t.setClickAction(new Runnable() {
			@Override
			public void run() {
				Main.self.setScale(scale);
			}
		});
		t.setPosition((int)(x-t.getWidth()/2), (int)(y-t.getHeight()/2));
		addActor(t);
	}
	
	private void addTransitionButton(String name, final Screen transitionTo, int x, int y, int width){
		Button t = new Button(name, width);
		
		t.setClickAction(new Runnable() {
			@Override
			public void run() {
				Main.self.setScreen(transitionTo, TransitionType.LEFT, Interpolation.pow2Out, .3f);
				Main.self.toggleMenu();
			}
		});
		t.setPosition((int)(x-t.getWidth()/2), (int)(y-t.getHeight()/2));
		addActor(t);
	}

	private int getY(float ratio){
		return (int) (getHeight()*ratio);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		Border.draw(batch, getX(), getY(), getWidth(), getHeight(), false);
		super.draw(batch, parentAlpha);
	}
}
