package game.rts.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseInput extends GLFWCursorPosCallback{

	public static double mouseX, mouseY;
	
	@Override
	public void invoke(long window, double mouseX, double mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		
	}

}
