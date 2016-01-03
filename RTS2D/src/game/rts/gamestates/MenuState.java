package game.rts.gamestates;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Rectangle2D;

import org.lwjgl.glfw.GLFW;

import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.input.KeyboardInput;
import game.rts.input.MouseButton;
import game.rts.input.MouseInput;
import game.rts.main.Main;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class MenuState extends State{
	
	private VertexArray mesh;
	private Texture texture;
	private Rectangle2D.Float button;

	public MenuState() {
		mesh = new VertexArray(Index.vertciesWightHeight(200f, 100f, 0.1f), Index.indices(), Index.tcs());
		texture = new Texture("res/player.png");
		button = new Rectangle2D.Float((Main.width/2)-100, (Main.height/2)-50,
				200, 100);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		if(KeyboardInput.keys[GLFW.GLFW_KEY_SPACE]){
			GameStateManager.setState(GameStateManager.GAMESTATE);
		}
		if(button.contains(MouseInput.mouseScreenX, MouseInput.mouseScreenY) && MouseButton.mouseLeft){
			GameStateManager.setState(GameStateManager.GAMESTATE);
		}
		
	}

	@Override
	public void render() {
		
		Shader.Basic.enable();
		Shader.Basic.setUniformMat4f("view_matrix", Matrix4f.translate(new Vector3f(Main.width/2, Main.height/2, 0.0f)));
		Shader.Basic.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f()));
		texture.bind();
		mesh.render();
		texture.unbind();
		Shader.Basic.disable();
		
		
	}

}
