package game.screens.minigames;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import game.screens.minigames.turtle.Obstacle;
import game.screens.unlock.Unlock;
import game.screens.unlock.UnlockBox;
import game.util.Screen;

public abstract class Minigame extends Screen{
	protected int score =0, highscore =0;
	public LoadingBar thisBar;
	public abstract String getName();
	public abstract Unlock[] getUnlocks();
	protected boolean paused;
	boolean found;
	String nextName;
	float speed;
	UnlockBox box;
	
	public static Array<Integer> activeKeys = new Array<Integer>();
	
	public Minigame(String nextName, float speed) {
		this.speed=speed;
		this.nextName=nextName;
		
		if(getUnlocks().length>0){
			box = new UnlockBox(getName(), getUnlocks());
			addActor(box);
		}
		else{
			start();
		}
	}
	protected boolean started;
	public void start(){
		started=true;
		setup();
		addLoadingBar(new LoadingBar(speed));
	}
	
	public abstract void setup();
	
	public void addLoadingBar(LoadingBar l){
		addActor(l);
		thisBar=l;
		thisBar.name=nextName;
	}
	@Override
	public void act(float delta) {
//		if(paused)return;
		super.act(delta);
	}

	@Override
	public void keyPress(int keycode) {
		if(keycode==Input.Keys.SPACE){
			if(box!=null&&!started){
				box.remove();
				start();
			}
		}
		switch(keycode){
		case Input.Keys.SPACE:
			if(thisBar.progress==1) nextGame();
			break;
		}
	}
	
	public void incrementScore(){
		score++;
		highscore=Math.max(score, highscore);
	}
	
	public void resetScore(){
		score=0;
	}
	
	public abstract void resetGame();
	
	public void reset(){
		resetScore();
		resetGame();
	}
	
	protected abstract void nextGame();
}
