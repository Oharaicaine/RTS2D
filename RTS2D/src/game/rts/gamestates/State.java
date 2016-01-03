package game.rts.gamestates;

public abstract class State {
	
	protected GameStateManager gsm;

	public abstract void init();
	
	public abstract void update();
	
	public abstract void render();
}
