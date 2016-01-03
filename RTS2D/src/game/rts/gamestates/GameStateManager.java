package game.rts.gamestates;

import java.util.ArrayList;

public class GameStateManager {
	
	public static ArrayList<State> gameStates;
	private static int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;

	
	public GameStateManager() {
		gameStates = new ArrayList<State>();
		
		gameStates.add(new MenuState());
		gameStates.add(new GameState());
		
		setState(MENUSTATE);
		
		//currentState = GAMESTATE;
	}
	
	public static void setState(int state){
		currentState = state;
		gameStates.get(state).init();
	}
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void render(){
		gameStates.get(currentState).render();
	}

}
