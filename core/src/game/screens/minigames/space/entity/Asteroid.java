package game.screens.minigames.space.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.Main;
import game.screens.minigames.space.SpaceGame;
import game.screens.minigames.space.entity.SpaceEntity.HitResult;
import game.util.Colours;
import game.util.Draw;
import game.util.Sounds;

public class Asteroid extends SpaceEntity{
	private static TextureRegion image = Main.atlas.findRegion("space/asteroid");
	private static float speed = 15;
	public Asteroid() {
		super(2);
		setSize(image.getRegionWidth(), image.getRegionHeight());
		setupInitalPosition();
	}
	
	private void setupInitalPosition() {
		int r = (int) (Math.random()*4);
		
		switch(r){
		case 0:
			setPosition(-getWidth(), (float)Math.random()*Main.height);
			break;
		case 1:
			setPosition(Main.width+getWidth(), (float)Math.random()*Main.height);
			break;
		case 2:
			setPosition((float)Math.random()*Main.width, -getHeight());
			break;
		case 3:
			setPosition((float)Math.random()*Main.width, Main.height+getHeight());
			break;
		
		}
	}
	
	@Override
	public void damage(int damage) {
		super.damage(damage);
		if(dead){
			Sounds.playSound("space_asteroidkill");
			SpaceGame.get().incrementScore();
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		float dx = Main.width/2-getX();
		float dy = Main.height/2-getY();
		float dist = (float) Math.sqrt(dx*dx+dy*dy);
		dx/=dist;
		dy/=dist;
		setPosition(getX()+dx*delta*speed, getY()+dy*delta*speed);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.white);
		Draw.draw(batch, image, getX()-getWidth()/2, getY()-getHeight()/2);
		super.draw(batch, parentAlpha);
	}

	@Override
	public HitResult collisionResult(ThingType hitBy) {
		switch(hitBy){
		case Enemy:
			break;
		case Player:
			break;
		case PlayerShot:
			return HitResult.Damage;
		default:
			break;
		}
		return  HitResult.Nothing;
	}

	@Override
	public ThingType getType() {
		return ThingType.Enemy;
	}

	@Override
	public void collideWith(SpaceEntity e) {
		if(dead||e.dead)return;
		switch(collisionResult(e.getType())){
		case Absorb:
			break;
		case Damage:
			break;
		case Nothing:
			break;
		default:
			break;	
		}
	}
}
