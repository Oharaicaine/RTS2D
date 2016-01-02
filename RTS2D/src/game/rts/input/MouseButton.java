package game.rts.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButton extends GLFWMouseButtonCallback {

	public static boolean mouseOne = false;
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && action == GLFW.GLFW_PRESS)mouseOne = true;
		else mouseOne = false;

	}

}
