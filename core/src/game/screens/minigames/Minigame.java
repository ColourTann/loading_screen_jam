package game.screens.minigames;

import game.screens.unlock.Unlock;
import game.screens.unlock.UnlockBox;
import game.util.Screen;

public abstract class Minigame extends Screen{
	public LoadingBar thisBar;
	public abstract String getName();
	public abstract Unlock[] getUnlocks();
	boolean found;
	String nextName;
	public Minigame(String nextName) {
		this.nextName=nextName;
	}
	public void addLoadingBar(LoadingBar l){
		addActor(l);
		thisBar=l;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		if(!found&&thisBar.progress==1){
			found=true;
			showLoaded();
		}
	}
	void showLoaded(){
		addActor(new UnlockBox(nextName, getUnlocks()));
	}
}
