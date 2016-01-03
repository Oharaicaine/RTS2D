package game.rts.entity;

import java.util.concurrent.CopyOnWriteArrayList;

import game.rts.camera.Camera;
import game.rts.main.Main;
import game.rts.maths.Vector3f;
import game.rts.utils.Utils;

public class EntityManager {
	
	public static CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>();
	public static CopyOnWriteArrayList<Entity> cameraEntities = new CopyOnWriteArrayList<Entity>();

	public EntityManager() {

	}
	
	public void update(){
		for(Entity entity : entities){
			entity.update();
			if(Utils.distanceBetweenVector3f(new Vector3f(Camera.position.x+(Main.width/2), Camera.position.y+(Main.height/2), 0), entity.getPos()) < 800){
				if(!cameraEntities.contains(entity))cameraEntities.add(entity);
			}else if(cameraEntities.contains(entity))cameraEntities.remove(entity);
		}
	}
	
	public void render(){
		for(Entity entity : cameraEntities){
			entity.render();
		}
	}
	
	public static void createEntity(Entity entity){
		entities.add(entity);
	}
	
	public static void removeEntity(Entity entity){
		entities.remove(entity);
		if(cameraEntities.contains(entity))cameraEntities.remove(entity);
	}

}
