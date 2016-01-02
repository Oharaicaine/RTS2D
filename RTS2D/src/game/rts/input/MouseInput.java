package game.rts.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import game.rts.camera.Camera;
import game.rts.main.Main;

public class MouseInput extends GLFWCursorPosCallback{

	public static double mouseX, mouseY, mouseScreenX, mouseScreenY;
	
	@Override
	public void invoke(long window, double mouseX, double mouseY) {
		
		//System.out.println(mouseX);
		
		this.mouseX = mouseX + Camera.position.x;
		this.mouseY = (Main.height - mouseY) + Camera.position.y;
		this.mouseScreenX = mouseX;
		this.mouseScreenY = Main.height - mouseY;
		
	}

}
