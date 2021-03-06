package game.rts.world;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.main.Main;
import game.rts.maths.Vector3f;
import game.rts.tiles.Tiles;

import game.rts.utils.Utils;

public class World {
	
	private BufferedImage map;
	private int mapSize = 100;
	

	public static ArrayList<Tiles> tiles = new ArrayList<Tiles>();
	
	public static CopyOnWriteArrayList<Tiles> worldMap = new CopyOnWriteArrayList<Tiles>();
	public static CopyOnWriteArrayList<Tiles> cameraMap = new CopyOnWriteArrayList<Tiles>();
	

	public World() {	
		map = Utils.loadBufferedImage("res/map2.png");
		initMap();
	}
	
	private void initMap() {
		for(int x = 0; x < mapSize; x++){
			for(int y = 0; y < mapSize; y++){
				int col = map.getRGB(x, y);
				switch(col & 0xFFFFFF){
					case 0x808080:
						worldMap.add(new Tiles(new Vector3f(x*96, y*96, 0.0f)));
						break;
					case 0x404040:
						worldMap.add(new Tiles(new Vector3f(x*96, y*96, 0.0f)));
						break;	
				}
			}
		}		
	}

	public void update(){
		for(Tiles tiles: worldMap){
			tiles.update();
			if(Utils.distanceBetweenVector3f(new Vector3f(Camera.position.x+(Main.width/2), Camera.position.y+(Main.height/2), 0), tiles.getPos()) < 800){
				if(!cameraMap.contains(tiles))cameraMap.add(tiles);	
			}else if(cameraMap.contains(tiles))cameraMap.remove(tiles);
		}
	}
	
	public void render(){
		Shader.Basic.enable();
		if(!cameraMap.isEmpty()){
			for(Tiles tile : cameraMap){
				tile.render();
			}
		}
		Shader.Basic.disable();
	}

}
