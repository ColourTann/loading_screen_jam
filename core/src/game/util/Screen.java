package game.util;


import java.util.ArrayList;

import game.Main;
import game.Main.MainState;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class Screen extends Group{
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	private ArrayList<Particle> newParticles = new ArrayList<Particle>();
	public Screen() {

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		preDraw(batch);
		super.draw(batch, parentAlpha);
		drawParticles(batch);
		postDraw(batch);
		
	}
	public abstract void preDraw(Batch batch);
	public abstract void postDraw(Batch batch);
	
	@Override
	public void act(float delta) {
		if(Main.self.getState()==MainState.Paused)return;
		tickParticles(delta);
		super.act(delta);
		postAct(delta);
	}
	public void addParticle(Particle p){
		newParticles.add(p);
	}
	private void tickParticles(float delta) {
		particles.addAll(newParticles);
		newParticles.clear();
		for(int i=particles.size()-1;i>=0;){
			Particle p = particles.get(i);
			p.act(delta);
			if(p.dead)particles.remove(p);
			else i--;
		}
	}

	public void drawParticles(Batch batch){
		for(Particle p : particles) p.draw(batch);
	}
	public abstract void postAct(float delta);
}
