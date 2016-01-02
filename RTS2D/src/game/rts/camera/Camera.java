package game.rts.camera;

import static org.lwjgl.glfw.GLFW.*;

import game.rts.input.KeyboardInput;
import game.rts.input.MouseInput;
import game.rts.main.Main;
import game.rts.maths.Vector3f;

public class Camera {
	
	public static Vector3f position = new Vector3f(0,0,0);
	public static float zoom = 0;

	public Camera() {

	}
	
	public void update(){
		
		if(MouseInput.mouseScreenX > Main.width - 150){
			position.x += 3f;
		}
		if(MouseInput.mouseScreenX < 150){
			position.x -= 3f;
		}
		if(MouseInput.mouseScreenY > Main.height - 150){
			position.y += 3f;
		}
		if(MouseInput.mouseScreenY < 150){
			position.y -= 3f;
		}
		
		//System.out.println(MouseInput.mouseScreenY);
		
		if(KeyboardInput.keys[GLFW_KEY_W]){
			position.y -= 2f;	
		}
		if(KeyboardInput.keys[GLFW_KEY_S]){
			position.y += 2f;	
		}
		if(KeyboardInput.keys[GLFW_KEY_A]){
			position.x += 2f;	
		}
		if(KeyboardInput.keys[GLFW_KEY_D]){
			position.x -= 2f;	
		}
	}

	public Vector3f getPosition() {
		return position;
	}

}
