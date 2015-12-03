package game.screens.minigames.space.projectile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.screens.minigames.space.SpaceGame;
import game.screens.minigames.space.entity.SpaceEntity;
import game.screens.minigames.space.entity.SpaceEntity.HitResult;
import game.util.Colours;
import game.util.Draw;

public abstract class Projectile extends Actor implements TypeGetter{
	static float speed=200;
	static float speedVariance=50;
	static final float rotationVariance=.25f;
	static final float drag=.94f;
	static final float initialLife=.35f;
	static final float lifeVariance=.3f;
	float dx, dy;
	int size=1;
	public boolean dead;
	float life;
	
	
	@Override
	public abstract void act(float delta);
	
	@Override
	public abstract void draw(Batch batch, float parentAlpha);

	public void checkCollision(SpaceEntity spe) {
		if(dead||spe.dead)return;
		float dx = spe.getX()-getX();
		float dy = spe.getY()-getY();
		float radiusTotal=spe.getWidth()/2+getWidth()/2;
		if(Math.sqrt(dx*dx+dy*dy)<radiusTotal){
			collideWith(spe);
		}
	}

	private void collideWith(SpaceEntity spe) {
		HitResult hr = spe.collisionResult(getType());
		switch(hr){
		case Absorb:
			dead=true;
			break;
		case Damage:
			dead=true;
			spe.damage(getDamage());
			break;
		case Nothing:
			break;
		default:
			break;
		}
	}
	
	public abstract int getDamage();

}
