package game.screens.minigames.snake;

import com.badlogic.gdx.Input;

public class EnemySnakey extends Snakey{

	public EnemySnakey(Tile start, int size) {
		super(start);
		this.size=size;
	}

	public int[] possibleMoves = new int[]{Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT};
	
	@Override
	public void turn() {
		super.turn();
		if(dead)return;
		if(Math.random()>.99)size++;
		int prevDX=dx;
		int prevDY=dy;
		Tile t = getNextTile();
		if(t==null||t.trapped()){
			for(int i:possibleMoves){
				changeDirection(i);
				Tile t1= getNextTile();
				if(t1!=null&&!t1.trapped()) break;
				dx=prevDX;
				dy=prevDY;
			}
		}
		
		else if(Math.random()>.8){
			double r = Math.random();
			for(int i:possibleMoves){
				if(r<.25){
					changeDirection(i);
					Tile t1= getNextTile();
					if(t1==null||t1.trapped()){
						dx=prevDX;
						dy=prevDY;
					}
					break;
				}
				r-=.25;	
			}
		}
		Tile next = getNextTile();
		if(next==null){
			die(DeathType.HitEdge);
		}
		else enterTile(next);
	}
}
