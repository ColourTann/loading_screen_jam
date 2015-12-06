package game.screens.minigames.turtle;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.util.Colours;
import game.util.Draw;
import game.util.Sounds;
import game.util.TextWisp;

public class Turtle extends Actor{

	static TextureRegion[] walk = new TextureRegion[]{Main.atlas.findRegion("turtle/walk0"), Main.atlas.findRegion("turtle/walk1")};
	static TextureRegion jump = Main.atlas.findRegion("turtle/jump");
	static TextureRegion duck = Main.atlas.findRegion("turtle/duck");
	
	public enum State{Walk, Jump, Duck};
	State currentState=State.Walk;
	
	static final float gravity = 80f;
	float dx=0;
	float dy;
	float stateTick=0;
	float invuln=0;
	
	public Turtle() {
		setSize(walk[0].getRegionWidth(), walk[0].getRegionHeight());
	}
	
	public void keyPress(int keycode){
		switch(keycode){
		case Input.Keys.UP: jump(); break;
		case Input.Keys.DOWN: duck(); break;
		}
	}
	
	private void duck() {
		if(stateTick>0) return;
		if(currentState!=State.Walk) return;
		currentState=State.Duck;
		stateTick=.5f;
//		Sounds.playSound("turtle_duck");
	}

	private void jump() {
		if(stateTick>0) return;
		if(currentState!=State.Walk) return;
		currentState=State.Jump;
		dy=40;
//		Sounds.playSound("turtle_duck");
	}

	@Override
	public void act(float delta) {
		stateTick-=delta;
		invuln-=delta;
		switch(currentState){
		case Duck:
			if(stateTick<=0) currentState=State.Walk;
			break;
		case Jump:
			dy-=gravity*delta;
			break;
		case Walk:
			break;
		default:
			break;
		}
		
		
		
		if(getY()<Main.height/2){
			setY(Main.height/2);
			dy=0;
			if(currentState==State.Jump) currentState=State.Walk;
		}
		
		
		
		setPosition(getX()+dx*delta, getY()+dy*delta);
		super.act(delta);
	}
	
	public void ouch(Obstacle o){
		invuln=1.5f;
		String s="unset";
		switch(o.type){
		case Mound:
			s="tripped";
			break;
		case Spike:
			s="spiked";
			break;
		default:
			break;
		}
		TextWisp t = new TextWisp(s, (int)(getX()+getWidth()/2), (int)(Main.height/2+22));
		t.setColor(Colours.dark);
		t.disableAlpha();
		TurtleGame.get().addParticle(t);
		TurtleGame.get().reset();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.white);
		if(invuln>=0 && (Main.ticks%.2f)<.1) return;
		switch(currentState){
		case Duck:
			Draw.draw(batch, duck, getX(), getY());
			break;
		case Jump:
			Draw.draw(batch, jump, getX(), getY());
			break;
		case Walk:
			Draw.draw(batch, walk[(int) ((Main.ticks*6)%2)], getX(), getY());
			break;
		default:
			break;
		
		}
		
		super.draw(batch, parentAlpha);
	}

	public void checkCollision(Obstacle obstacle) {
		if(invuln>0) return;
		float xDist = Math.abs((getX()+getWidth()/2)-obstacle.getX());
		switch(obstacle.type){
		case Mound:
			if(xDist<(obstacle.getWidth())*.8f){
				if(currentState!=State.Jump) ouch(obstacle);
			}
			break;
		case Spike:
			if(xDist<3&&currentState!=State.Duck) ouch(obstacle);
			break;
		default:
			break;
		
		}
	}
	
}
