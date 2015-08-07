package game.screens;

import com.badlogic.gdx.graphics.g2d.Batch;

import game.Main;
import game.util.Draw;
import game.util.Particle;

public class Orbiter extends Particle{

	Orbiter() {
		setupLife(.5f);
		x=rand(0,Main.width);
		y=rand(0,Main.height);
		dx=rand(-200,200);
		dy=rand(-200,200);
		dead=false;	
	}
	
	@Override
	public void tick(float delta) {
		x+=dx*delta;
		y+=dy*delta;
		
	}

	@Override
	public void draw(Batch batch) {
		batch.setColor(1,1,1,ratio);
		Draw.drawCenteredScaled(batch, Draw.getSq(), x, y, 10, 10);
	}

}
