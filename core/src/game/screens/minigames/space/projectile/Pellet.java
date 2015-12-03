package game.screens.minigames.space.projectile;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.screens.minigames.space.entity.SpaceEntity.ThingType;
import game.util.Colours;
import game.util.Draw;

public class Pellet extends Projectile{

	public Pellet(float x, float y, float rotation) {
		rotation+=Math.random()*rotationVariance*2-rotationVariance;
		setPosition(x, y);
		float mySpeed=(float) (speed+Math.random()*speedVariance);
		this.dx=(float) Math.cos(rotation)*mySpeed;
		this.dy=(float) Math.sin(rotation)*mySpeed;
		this.life=(float) (initialLife+Math.random()*lifeVariance);
	}

	@Override
	public void act(float delta) {
		dx*=drag;
		dy*=drag;
		setPosition(getX()+dx*delta, getY()+dy*delta);
		life-=delta;
		if(life<=0) dead=true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(dead) return;
		batch.setColor(Colours.red);
		Draw.fillRectangle(batch, getX()-size/2, getY()-size/2, size, size);
	}

	@Override
	public int getDamage() {
		return 1;
	}

	@Override
	public ThingType getType() {
		return ThingType.PlayerShot;
	}
	
	

}
