package game.screens.minigames.space;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;

import game.Main;
import game.Main.TransitionType;
import game.screens.minigames.Minigame;
import game.screens.minigames.space.entity.Asteroid;
import game.screens.minigames.space.entity.Ship;
import game.screens.minigames.space.entity.SpaceEntity;
import game.screens.minigames.space.entity.Station;
import game.screens.minigames.space.entity.SpaceEntity.HitResult;
import game.screens.minigames.space.projectile.Projectile;
import game.screens.minigames.turtle.TurtleGame;
import game.screens.unlock.ColourUnlock;
import game.screens.unlock.KeyUnlock;
import game.screens.unlock.Unlock;
import game.util.Colours;
import game.util.Draw;
import game.util.Sounds;
import game.util.TannFont;

public class SpaceGame extends Minigame{

	private static SpaceGame self;
	Array<Projectile> projectiles = new Array<Projectile>();
	Array<SpaceEntity> entities = new Array<SpaceEntity>();
	static final int chargeHeight=2;
	static final int chargeWidth=8;
	float difficulty=0;
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
	public void resetGame() {
		for(Projectile p:projectiles) p.remove();
		for(SpaceEntity e:entities) e.remove();
		projectiles.clear();
		entities.clear();
		ship.reset();
		station.remove();
		entities.removeValue(station, true);
		station = new Station();
		station.setPosition(Main.width/2, Main.height/2);
		entities.add(station);
		addActor(station);
		difficulty=0;
	}
	
	@Override
	public void setup() {
		Main.coloursUnlocked=4;
		
		for(int i=0;i<30;i++){
			addActor(new Star());
		}
		
		station = new Station();
		station.setPosition(Main.width/2, Main.height/2);
		entities.add(station);
		addActor(station);
		ship = new Ship();
		addActor(ship);
		
	} 
	
	@Override
	public String getName() {
		return "space";
	}

	@Override
	public Unlock[] getUnlocks() {
		return new Unlock[]{new KeyUnlock('z'), new KeyUnlock('x'), new ColourUnlock("red", Colours.red)};
	}

	@Override
	protected void nextGame() {
		Main.self.setScreen(TurtleGame.get(), TransitionType.LEFT, Interpolation.pow2Out, .5f);
	}

	@Override
	public void preDraw(Batch batch) {
	}

	@Override
	public void postDraw(Batch batch) {
		batch.setColor(Colours.light);
		TannFont.font.draw(batch, "Score: "+score, (int)getX()+40, Main.height-7);
		TannFont.font.draw(batch, "Highscore: "+highscore, (int)getX()+90, Main.height-7);

		
		if(ship!=null){
			batch.setColor(Colours.red);
			Draw.fillRectangle(batch, Math.round(getX()+Main.width/2-chargeWidth*3/2), Math.round(getY()+5), ship.getCharge()*chargeWidth, chargeHeight);
			batch.setColor(Colours.white);
			Draw.fillRectangle(batch, Math.round(getX()+Main.width/2-chargeWidth*3/2), Math.round(getY()+5), (int)ship.getCharge()*chargeWidth, chargeHeight);
			
		}
	}

	float timeToNextAsteroid;
	@Override
	public void preTick(float delta) {
		if(!started) return;
		difficulty+=delta;
		timeToNextAsteroid-=delta;
		
		if(timeToNextAsteroid<=0){
			addAsteroid();
			timeToNextAsteroid=(float) (1+Math.random()*2)*(Math.max(.3f, 1-(difficulty/50f)));
	
		}
		
		
		for(Projectile p:projectiles){
			for(SpaceEntity spe:entities){				
				p.checkCollision(spe);
			}
		}
		
		for(int i=0;i<entities.size;i++){
			for(int j=0;j<entities.size;j++){
				entities.get(i).checkCollision(entities.get(j));
			}
		}
		
		for(int i=projectiles.size-1;i>=0;i--){
			Projectile p = projectiles.get(i);
			if(p.dead){
				projectiles.removeIndex(i);
				p.remove();
			}
		}
		
		for(int i=entities.size-1;i>=0;i--){
			SpaceEntity e = entities.get(i);
			if(e.dead){
				entities.removeIndex(i);
				e.remove();
			}
		}
	}

	private void addAsteroid() {
		Asteroid a = new Asteroid();
		addActor(a);
		entities.add(a);
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

	@Override
	protected void startMusic() {
		Sounds.playMusic(Sounds.get("space", Music.class));
	}

}
