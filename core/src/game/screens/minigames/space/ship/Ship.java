package game.screens.minigames.space.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.screens.minigames.space.SpaceGame;
import game.screens.minigames.space.particle.EngineParticle;
import game.util.Draw;

public class Ship extends Actor{
	private static TextureRegion image = Main.atlas.findRegion("space/ship");
	static final float rotationSpeed=4;
	static final float accelSpeed=5;
	static final float reverseSpeed=2;
	static final float drag = .02f;
	static final float secondsPerParticle=.03f;
	float particleTick;
	float rotation;
	float speed;
	
	public Ship() {
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		doInput(delta);
		doMovement(delta);
		doDrag(delta);
	}
	
	private void doDrag(float delta) {
		speed *= Math.pow(delta, drag);
	}

	void doMovement(float delta) {
		setX((float) (getX()+Math.cos(rotation)*speed));
		setY((float) (getY()+Math.sin(rotation)*speed));
		if(particleTick>=secondsPerParticle){
			particleTick-=secondsPerParticle;
			SpaceGame.get().addParticle(new EngineParticle((int)getX(), (int)getY(), (float)Math.PI+rotation));
		}
	}

	void doInput(float delta){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) rotation+=delta*rotationSpeed;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rotation-=delta*rotationSpeed;
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			particleTick+=delta;
			speed+=delta*accelSpeed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) speed-=delta*reverseSpeed;
	}
	
	public void keyPress(int keycode){
		switch(keycode){
		case Input.Keys.Z:
			shoot();
			break;
		}
	}
	
	void shoot(){
		for(int i=0;i<10;i++) SpaceGame.get().addProjectile(new Projectile(getX(), getY(), rotation));
		speed-=1.5;
		SpaceGame.get().shake(1);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(1,1,1,1);
		Draw.drawCenteredRotatedScaled(batch, image, getX(), getY(), 1, 1, rotation);
	}
}
