package game.screens.minigames.snake;

public class PlayerSnakey extends Snakey{
	int nextKeycode=-1;
	boolean turned;
	public PlayerSnakey(Tile start) {
		super(start);
	}

	@Override
	public void turn() {
		super.turn();
		if(dead)return;
		Tile next = t.getTile(dx, dy);
		if(next==null) die(DeathType.HitEdge);
		else enterTile(next);
		turned=false;
		if(nextKeycode!=-1){
			keyPress(nextKeycode);
			nextKeycode=-1;
		}
	}

	public void keyPress(int keycode){
		if(turned){
			nextKeycode=keycode;
			return;
		}
		turned=true;
		changeDirection(keycode);
	}
	
}
