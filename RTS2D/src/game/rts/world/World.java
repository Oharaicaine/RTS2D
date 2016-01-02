package game.rts.world;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import game.rts.graphics.Shader;
import game.rts.maths.Vector3f;
import game.rts.tiles.Tiles;

import game.rts.utils.Utils;

public class World {
	
	private BufferedImage map;
	private int mapSize = 20;
	
//	public static ArrayList<Tiles> tiles = new ArrayList<Tiles>();
	
	public static CopyOnWriteArrayList<Tiles> worldMap = new CopyOnWriteArrayList<Tiles>();

	public World() {	
		map = Utils.loadBufferedImage("res/map.png");
		initMap();
	}
	
	private void initMap() {
		for(int x = 0; x < mapSize; x++){
			for(int y = 0; y < mapSize; y++){
				int col = map.getRGB(x, y);
				switch(col & 0xFFFFFF){
					case 0x808080:
						worldMap.add(new Tiles(new Vector3f(x-10.0f, y-10.0f, 0.0f)));
						break;
					case 0x404040:
						//worldMap.add(new Tiles(new Vector3f(x-10.0f, y-10.0f, 0.0f)));
						break;	
				}
			}
		}	
		
	}

	public void update(){

	}
	
	public void render(){
		Shader.Basic.enable();
		for(Tiles tiles : worldMap){
			tiles.render();
		}
		Shader.Basic.disable();
	}

}
