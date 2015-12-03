package game.screens.minigames.space.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;

import game.screens.minigames.space.projectile.TypeGetter;

public abstract class SpaceEntity extends Actor implements TypeGetter{
	public boolean dead;
	public int hp;
	
	public SpaceEntity(int hp) {
		this.hp=hp;
	}
	
	public enum ThingType{PlayerShot, Player, Enemy, Station}
	public enum HitResult{Absorb, Nothing, Damage}
	public abstract HitResult collisionResult(ThingType hitBy);
	
	public abstract void collideWith(SpaceEntity e);
	
	public void checkCollision(SpaceEntity spe) {
		if(dead||spe.dead)return;
		float dx = spe.getX()-getX();
		float dy = spe.getY()-getY();
		float radiusTotal=spe.getWidth()/2+getWidth()/2;
		if(Math.sqrt(dx*dx+dy*dy)<radiusTotal){
			collideWith(spe);
		}
	}
	
	public void damage(int damage) {
		hp-=damage;
		if(hp<=0) destroy();
	}

	private void destroy() {
		dead=true;
	}

}
