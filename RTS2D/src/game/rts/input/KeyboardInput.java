package game.rts.input;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardInput extends GLFWKeyCallback{

	public static boolean[] keys = new boolean[512];
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW_RELEASE;
	}
	
	public boolean isKeyDown(int key){
		return keys[key];
		
	}
	

}
