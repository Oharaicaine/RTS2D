package game.rts.camera;

import static org.lwjgl.glfw.GLFW.*;

import game.rts.graphics.Shader;
import game.rts.input.KeyboardInput;
import game.rts.input.MouseInput;
import game.rts.input.MouseScroll;
import game.rts.main.Main;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Utils;

public class Camera {
	
	public static Vector3f position = new Vector3f(0,0,0);
	public static float zoom = 0;
	
	private float width = Main.width;
	private float StartWidth = 0.0f;
	private float height = Main.height;
	private float StartHeight = 0.0f;

	public Camera() {

	}
	
	public void update(){
		/*
		if(!MouseScroll.set){
			
			double amount = MouseScroll.scrollY*30;
			width -= amount;
			height -= amount;
			StartWidth += amount;
			StartHeight += amount;
			
			if(width < Main.width)width = Main.width;
			if(height < Main.height)height = Main.height;
			if(StartWidth < 0)StartWidth = 0;
			if(StartHeight < 0)StartHeight = 0;
			
			Matrix4f pr_matrix = Matrix4f.orthographic(StartWidth, width, StartHeight, height, -1.0f, 1.0f);
			Shader.Basic.enable();
			Shader.Basic.setUniformMat4f("projection_matrix", pr_matrix);
			Shader.Basic.disable();
			MouseScroll.set = true;
		}
		*/
		
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
	}

	public Vector3f getPosition() {
		return position;
	}

}
