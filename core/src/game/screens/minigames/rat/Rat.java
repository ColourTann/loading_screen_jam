package game.screens.minigames.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.util.Draw;

public class Rat extends Actor{
	static TextureRegion[] imageWalk = new TextureRegion[5];
	static TextureRegion[] imageJump = new TextureRegion[]{Main.atlas.findRegion("rat/jump0"), Main.atlas.findRegion("rat/jump1")};
	static final int tailWidth=17;
	static final int bodyWidth;
	static{
		for(int i=1;i<=imageWalk.length;i++){
			imageWalk[i-1]=Main.atlas.findRegion("rat/rat"+i);
		}
		bodyWidth=imageWalk[0].getRegionWidth()-tailWidth;
	}
	final float jumpHeight=120;
	float index;
	float dy;
	float gravity=220;
	public Rat() {
		setPosition(5, 5);
		setSize(imageWalk[0].getRegionWidth(), imageWalk[0].getRegionHeight());
	}
	boolean facingRight=true;
	final float horizontalSpeed=40;
	@Override
	public void act(float delta) {
		super.act(delta);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			setX(getX()+horizontalSpeed*delta);
			index+=10*delta;
			index=index%imageWalk.length;
			facingRight=true;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			setX(getX()-horizontalSpeed*delta);
			index+=10*delta;
			index=index%imageWalk.length;
			facingRight=false;
		}
		else{
			index=0;
		}
		
		dy-=gravity*delta;
		setY(getY()+dy*delta);
		if(getY()<5){
			setY(5);
			dy=0;
		}
	}
	
	public void keyPress(int keycode){
		switch(keycode){
		case Input.Keys.Z:
			jump();
			break;
		}
	}
	
	void jump(){
		dy=jumpHeight;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1,1,1,1);
		float drawX=0;
		TextureRegion toDraw= imageWalk[(int)index];
		if(dy!=0){
			if(dy>-20){
				toDraw=imageJump[0];
			}
			else toDraw=imageJump[1];
			
		}
		if(facingRight){
			drawX=getX()-tailWidth-bodyWidth/2;
		}
		else{
			drawX=getX()+getWidth()-bodyWidth/2;
		}
		int bonusY=0;
		switch((int)index){
		case 2: bonusY++;
		case 3: bonusY++;
		case 4: bonusY--; break;
		}
		Draw.drawScaled(batch, toDraw, drawX, getY()+bonusY, facingRight?1:-1, 1);
		super.draw(batch, parentAlpha);
	}

}
