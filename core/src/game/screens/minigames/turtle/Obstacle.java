package game.screens.minigames.turtle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.Main;
import game.util.Colours;
import game.util.Draw;

public class Obstacle extends Actor{
	enum ObstacleType{Spike(Main.atlas.findRegion("turtle/spike")), Mound(Main.atlas.findRegion("turtle/mound"));
		TextureRegion region;
		private ObstacleType(TextureRegion region){
			this.region=region;
		}
	}
	
	ObstacleType type;
	float speed;
	public Obstacle(ObstacleType type, float speed) {
		this.type=type;
		this.speed=speed;
		setSize(type.region.getRegionWidth(), type.region.getRegionHeight());
		setPosition(Main.width, Main.height/2);
		if(type==ObstacleType.Spike){
			setY(getY()+5);
		}
	}
	
	public void act(float delta) {
		TurtleGame.get().turtle.checkCollision(this);
		setX(getX()-speed);
		if(getX()<-5){
			remove();
			TurtleGame.get().incrementScore();
		}
	};
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(Colours.white);
		Draw.draw(batch, type.region, getX()-type.region.getRegionWidth()/2, getY());
		super.draw(batch, parentAlpha);
	}

}
