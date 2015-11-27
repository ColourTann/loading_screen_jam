package game.screens.minigames;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.TannFont;

public class LoadingBar extends Group{
	public static final int loadingBarHeight=8;
	private static final float secondsPerCheck=.1f;
	private String[] loadingMessages = new String[]{
			"reticulating splines", "parsing level data", "building objects", "loading models", "initialising sprites",
			"finding worthy opponent", "upgrading pixels", "connecting to gamenet", "caching sounds", "importing headers",
			"aligning vertices", "placing mountains", "eroding landscape", "baking shadows", "incubating features",
			"creating common uplink", "downloading better game", "performing illegal operation", "seeding RNG",
			"harmonising oscillations", "escalating tickets", "generating bersenham lines", "reverting changelists"
			
			
	};
	private float secondCounter;
	float progress = 0;
	private String currentString;
	float speed;
	public LoadingBar(float speed) {
		this.speed=speed;
		setSize(Main.width, loadingBarHeight);
		setPosition(0, Main.height-getHeight());
		randomiseString();
	}
	
	private void progress() {
		progress += Math.random()*(getFactor())/20f*speed;
		if(progress>=1){
			progress=1;
			currentString="finished loading";
		}
		if(Math.random()<.6*Math.pow(getFactor(), 2))randomiseString();
		
	}

	public float getFactor(){
		float freq = .3f;
		return (float)(Math.sin(Main.ticks*freq)+1)/2f;
	}
	
	private void randomiseString() {
		currentString=loadingMessages[(int)(Math.random()*loadingMessages.length)];
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(progress>=1) return;
		secondCounter+=delta;
		if(secondCounter>=secondsPerCheck){
			secondCounter-=secondsPerCheck;
			if(Math.random()>getFactor()){
				progress();
			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.dark);
		Draw.fillRectangle(batch, getX(), getY(), getWidth(), getHeight());
		batch.setColor(Colours.light);
		Draw.fillRectangle(batch, getX(), getY(), getWidth()*progress, getHeight());
		batch.setColor(Colours.mixer);
		TannFont.font.draw(batch, (int)(progress*100)+"% "+currentString, (int)(getX()+getWidth()/6), (int)(getY()+getHeight()/2-TannFont.font.getHeight()/2));
		super.draw(batch, parentAlpha);
	}
	

}
