package game.screens.minigames.space.particle;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.util.Colours;
import game.util.Draw;
import game.util.Particle;

public class EngineParticle extends Particle{
	static float speed=10;
	static float speedVariance=20;
	static final float rotationVariance=.5f;
	public EngineParticle(int x, int y, float angle) {
		angle += rand(-rotationVariance, rotationVariance);
		this.x=x; this.y=y;
		float mySpeed=(float) (speed+Math.random()*speedVariance);
		this.dx=(float) Math.cos(angle)*mySpeed;
		this.dy=(float) Math.sin(angle)*mySpeed;
		setupLife(.5f);
		this.colour=Math.random()>.8?Colours.light:Colours.blue;
	}
	
	@Override
	public void tick(float delta) {
		this.x+=dx*delta;
		this.y+=dy*delta;
		dx*=.999f;
		dy*=.999f;
	}

	@Override
	public void draw(Batch batch) {
		batch.setColor(colour);
		Draw.fillRectangle(batch, x, y, 1, 1);
	}

}
