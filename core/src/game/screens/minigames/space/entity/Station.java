package game.screens.minigames.space.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import game.Main;
import game.screens.minigames.space.SpaceGame;
import game.util.Colours;
import game.util.Draw;

public class Station extends SpaceEntity{
	private static TextureRegion image = Main.atlas.findRegion("space/station");
	static final int hpWidth =image.getRegionWidth();
	static final int hpHeight =2;
	static final int shieldHeight =1;
	static final int hpOffset =2;
	float shields=1;
	float secondsForShieldRecharge=12;
	public Station() {
		super(4);
		setSize(image.getRegionWidth(), image.getRegionHeight());
	}	
	
	@Override
	public void damage(int damage) {
		if(shields==1){
			damageShields();
			damage--;
		}
		super.damage(damage);
		if(dead){
			SpaceGame.get().addAction(Actions.delay(1, Actions.run(new Runnable() {
				@Override
				public void run() {
					SpaceGame.get().reset();
				}
			})));
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		shields+=delta/secondsForShieldRecharge;
		shields=Math.min(1, shields);
	}
	
	private void damageShields() {
		shields=0;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(Colours.white);
		Draw.draw(batch, image, (int)(getX()-getWidth()/2), (int)(getY()-getHeight()/2));
		batch.setColor(Colours.blue);
		Draw.fillRectangle(batch, getX()-getWidth()/2, getY()-getHeight()/2 - hpOffset - shieldHeight, hpWidth*shields, shieldHeight);
		batch.setColor(Colours.red);
		Draw.fillRectangle(batch, getX()-getWidth()/2, getY()-getHeight()/2 - hpOffset - shieldHeight - hpHeight, hpWidth/4*hp, hpHeight);
	}

	@Override
	public HitResult collisionResult(ThingType hitBy) {
		switch(hitBy){
		case Enemy:
			return HitResult.Damage;
		case PlayerShot:
			return HitResult.Absorb;
		default:
			break;
		}
		return HitResult.Nothing;
	}

	@Override
	public ThingType getType() {
		return ThingType.Station;
	}
	
	@Override
	public void collideWith(SpaceEntity e) {
		if(dead||e.dead)return;
		switch(collisionResult(e.getType())){
		case Absorb:
			break;
		case Damage:
			damage(1);
			e.dead=true;
			break;
		case Nothing:
			break;
		default:
			break;	
		}
	}
}
