package game.screens.minigames.space.ship;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.util.Colours;
import game.util.Draw;

public class Projectile extends Actor{
	static float speed=200;
	static float speedVariance=50;
	static final float rotationVariance=.25f;
	static final float drag=.94f;
	static final float initialLife=.35f;
	static final float lifeVariance=.3f;
	float dx, dy;
	int size=1;
	boolean dead;
	float life;
	public Projectile(float x, float y, float rotation) {
		rotation+=Math.random()*rotationVariance*2-rotationVariance;
		setPosition(x, y);
		float mySpeed=(float) (speed+Math.random()*speedVariance);
		this.dx=(float) Math.cos(rotation)*mySpeed;
		this.dy=(float) Math.sin(rotation)*mySpeed;
		this.life=(float) (initialLife+Math.random()*lifeVariance);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
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
		super.draw(batch, parentAlpha);
	}

}
