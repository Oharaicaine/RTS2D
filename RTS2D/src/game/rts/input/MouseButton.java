package game.rts.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButton extends GLFWMouseButtonCallback {

	public static boolean mouseRight = false;
	public static boolean mouseLeft = false;
	
	
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && action == GLFW.GLFW_PRESS)mouseRight = true;
		else mouseRight = false;

		if(button == GLFW.GLFW_MOUSE_BUTTON_LEFT && action == GLFW.GLFW_PRESS)mouseLeft = true;
		else mouseLeft = false;
	}

}
