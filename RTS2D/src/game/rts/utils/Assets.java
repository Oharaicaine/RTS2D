package game.rts.utils;

import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;

public class Assets {

	private static Texture stone, player, map;
	
	private static VertexArray tileMesh;
	
	
	public static void loadAll(){
		stone = new Texture("res/stonefloor.png");
		player = new Texture("res/player.png");
		map = new Texture("res/map2.png");
		
		tileMesh = new VertexArray(Index.vertcies(96f, 0.0f), Index.indices(), Index.tcs());
	}
	
	public static Texture getStone() {
		return stone;
	}
	public static Texture getPlayer() {
		return player;
	}
	public static Texture getMap() {
		return map;
	}
	
	public static VertexArray getTileMesh() {
		return tileMesh;
	}
	
}
