package game.screens.minigames.space;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

import game.Main;
import game.screens.minigames.Minigame;
import game.screens.minigames.space.ship.Projectile;
import game.screens.minigames.space.ship.Ship;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.util.Colours;

public class SpaceGame extends Minigame{

	private static SpaceGame self;
	Array<Projectile> projectiles = new Array<Projectile>();
	public static SpaceGame get(){
		if(self==null) self = new SpaceGame();
		return self;
	}
	
	
	Station station;
	Ship ship;
	private SpaceGame() {
		super(null, 1);
	}

	@Override
	public void setup() {
		Main.coloursUnlocked=4;
		station = new Station();
		station.setPosition(Main.width/2, Main.height/2);
		addActor(station);
		ship = new Ship();
		addActor(ship);
		for(int i=0;i<30;i++){
			addActor(new Star());
		}
	} 
	
	@Override
	public String getName() {
		return "space";
	}

	@Override
	public Unlock[] getUnlocks() {
		return new Unlock[]{new KeyUnlock('z'), new KeyUnlock('x'), new ColourUnlock("brown", Colours.red)};
	}

	

	@Override
	public void resetGame() {
	}

	@Override
	protected void nextGame() {
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
	
	@Override
	public void keyPress(int keycode) {
		super.keyPress(keycode);
		if(ship!=null) ship.keyPress(keycode);
	}

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		addActor(projectile);
	}

}
