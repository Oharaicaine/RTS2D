package game.rts.camera;

import static org.lwjgl.glfw.GLFW.*;

import game.rts.input.KeyboardInput;
import game.rts.maths.Vector3f;

public class Camera {
	
	public static Vector3f position = new Vector3f();

	public Camera() {
	}
	
	public void update(){
		if(KeyboardInput.keys[GLFW_KEY_W]){
			position.y -= 0.2f;	
		}
		if(KeyboardInput.keys[GLFW_KEY_S]){
			position.y += 0.2f;	
		}
		if(KeyboardInput.keys[GLFW_KEY_A]){
			position.x -= 0.02f;	
		}
		if(KeyboardInput.keys[GLFW_KEY_D]){
			position.x += 0.02f;	
		}
	}

	public Vector3f getPosition() {
		return position;
	}

}
