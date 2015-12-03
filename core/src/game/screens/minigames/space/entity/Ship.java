package game.screens.minigames.space.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.screens.minigames.space.SpaceGame;
import game.screens.minigames.space.particle.EngineParticle;
import game.screens.minigames.space.projectile.Pellet;
import game.screens.minigames.space.projectile.Projectile;
import game.util.Colours;
import game.util.Draw;

public class Ship extends SpaceEntity{
	private static TextureRegion image = Main.atlas.findRegion("space/ship");
	static final float rotationSpeed=4;
	static final float accelSpeed=5;
	static final float reverseSpeed=2;
	static final float drag = .02f;
	static final float secondsPerParticle=.06f;
	static final float weaponChargeSpeed=1.2f;
	float particleTick;
	float rotation;
	float speed;
	float weaponCharge=3;
	float flipCharge=1;
	public Ship() {
		super(5);
		reset();
	}
	
	public void reset() {
		weaponCharge=3;
		flipCharge=1;
		rotation=0;
		speed=0;
		setPosition(Main.width/4, Main.height/2);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		doInput(delta);
		doMovement(delta);
		doDrag(delta);
		checkBounds(delta);
		flipTimer-=delta;
		weaponCharge+=delta*weaponChargeSpeed;
		weaponCharge=Math.min(3, weaponCharge);
		flipCharge+=delta*2.5;
	}
	
	private void checkBounds(float delta) {
		if(getX()<getWidth()/2){
			setX(getWidth()/2);
		}
		if(getX()>Main.width-getWidth()/2){
			setX(Main.width-getWidth()/2);
		}
		if(getY()<getHeight()/2){
			setY(getHeight()/2);
		}
		if(getY()>Main.height-getHeight()/2){
			setY(Main.height-getHeight()/2);
		}
	}

	private void doDrag(float delta) {
		speed *= Math.pow(delta, drag);
	}

	void doMovement(float delta) {
		setX((float) (getX()+Math.cos(rotation)*speed));
		setY((float) (getY()+Math.sin(rotation)*speed));
		
		if(flipTimer>0){
			particleTick+=delta*5;
		}
		
		while(particleTick>=secondsPerParticle){
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
		case Input.Keys.X:
			flip();
			break;
		}
	}
	
	Vector2 flipStart;
	Vector2 flipEnd;
	float flipTimer;
	private void flip() {
		if(flipCharge<1)return;
		flipCharge=0;
		rotation+=Math.PI;
		speed=5;
		flipTimer=.3f;
		
	}

	void shoot(){
		if(weaponCharge<1) return;
		weaponCharge--;
		for(int i=0;i<10;i++) SpaceGame.get().addProjectile(new Pellet(getX(), getY(), rotation));
		speed-=1;
		SpaceGame.get().shake(1);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(1,1,1,1);
		Draw.drawCenteredRotatedScaled(batch, image, getX(), getY(), 1, 1, rotation);
		if(flipTimer>0){
			batch.setColor(Colours.light);
		}
	}

	@Override
	public HitResult collisionResult(ThingType hitBy) {
		return HitResult.Nothing;
	}

	@Override
	public ThingType getType() {
		return ThingType.Player;
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

	public float getCharge() {
		return weaponCharge;
	}


}
