package game.screens.minigames;

import com.badlogic.gdx.Input;

import game.screens.unlock.Unlock;
import game.screens.unlock.UnlockBox;
import game.util.Screen;

public abstract class Minigame extends Screen{
	public LoadingBar thisBar;
	public abstract String getName();
	public abstract Unlock[] getUnlocks();
	protected boolean paused;
	boolean found;
	String nextName;
	float speed;
	UnlockBox box;
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
		switch(keycode){
		case Input.Keys.UP:
		case Input.Keys.DOWN:
			if(box!=null&&!started){
				box.remove();
				start();
			}
			break;
		case Input.Keys.SPACE:
			if(thisBar.progress==1) nextGame();
			break;
		}
	}
	
	protected abstract void nextGame();
}
