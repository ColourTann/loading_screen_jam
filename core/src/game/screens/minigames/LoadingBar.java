package game.screens.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Align;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.Noise;
import game.util.TannFont;

public class LoadingBar extends Group{
	public static final int loadingBarHeight=10;
	private static final float secondsPerCheck=.05f;
	private String[] loadingMessages = new String[]{
			"reticulating splines", "parsing level data", "building objects", "loading models", "initialising sprites",
			"finding worthy opponent", "upgrading pixels", "connecting to gamenet", "caching sounds", "importing headers",
			"aligning vertices", "placing mountains", "eroding landscape", "baking shadows", "incubating features",
			"creating common uplink", "downloading better game", "performing illegal operation", "seeding RNG",
			"harmonising oscillations", "escalating tickets", "generating bresenham lines", "reverting changelists"


	};
	private float secondCounter;
	public float progress = 0;
	private String currentString;
	float speed;
	String name;
	public LoadingBar(String name, float speed, boolean fullScreen) {
		this.name=name;
		this.speed=speed;
		setSize(Main.width, fullScreen?Main.height:loadingBarHeight);
		setPosition(0, Main.height-getHeight());
		randomiseString();
	}

	private void progress() {
		float toAdd=(float)(Math.pow(getFactor(), 5)/10f);
		progress += toAdd;
		if(toAdd>.025)randomiseString();
		if(progress>=1){
			progress=1;
			currentString="finished loading";
		}
	}

	public float getFactor(){
		float freq = .3f;
		float noise = (float)(Noise.noise(0, Main.ticks * freq));
		if(noise<0) noise++;
		return noise;
	}

	private void randomiseString() {
		currentString=loadingMessages[(int)(Math.random()*loadingMessages.length)];
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		clip.width=getWidth();
		if(progress>=1) return;
		secondCounter+=delta*speed;
		while(secondCounter>=secondsPerCheck){
			secondCounter-=secondsPerCheck;
			if(Math.random()>getFactor()){
				progress();
			}
		}
	}
	Rectangle scissors = new Rectangle();
	Rectangle clip =new Rectangle(getX(), getY(), getWidth()*progress, Gdx.graphics.getHeight());
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.dark);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		batch.setColor(Colours.light);
		String toDraw="Loading "+name+": "+(int)(progress*100)+"% "+currentString;
		TannFont.font.draw(batch, toDraw, (int)(getX()+2), (int)(getY()+getHeight()/2-TannFont.font.getHeight()/2));
		Draw.fillRectangle(batch, getX(), getY(), getWidth()*progress, getHeight());
		batch.setColor(Colours.dark);
		batch.flush();
		
		clip.width=getWidth()*progress;
		ScissorStack.calculateScissors(getStage().getCamera(), batch.getTransformMatrix(), clip, scissors);
		boolean added =(ScissorStack.pushScissors(clip));
		if(added){
			TannFont.font.draw(batch, toDraw, (int)(getX()+2), (int)(getY()+getHeight()/2-TannFont.font.getHeight()/2));
			batch.flush();
			ScissorStack.popScissors();
		}
		super.draw(batch, parentAlpha);
	}


}
