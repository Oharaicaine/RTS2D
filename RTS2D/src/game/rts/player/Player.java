package game.rts.player;

import org.lwjgl.glfw.GLFW;

import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.input.KeyboardInput;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class Player {
	
	private float SIZE = 1.0f;
	private VertexArray mesh;
	private Texture texture;
	
	private Vector3f position = new Vector3f();
	private float rot;
	private float delta = 0.0f;

	public Player() {
		mesh = new VertexArray(Index.vertcies(1f, 0.1f), Index.indices(), Index.tcs());
		texture = new Texture("res/player.png");
	}
	
	public void update(){
		if(KeyboardInput.keys[GLFW.GLFW_KEY_W])
			position.y+=0.1f;
		if(KeyboardInput.keys[GLFW.GLFW_KEY_S])
			position.y-=0.1f;
		if(KeyboardInput.keys[GLFW.GLFW_KEY_A])
			position.x-=0.1f;
		if(KeyboardInput.keys[GLFW.GLFW_KEY_D])
			position.x+=0.1f;
	}
	
	public void render(){
		Shader.Player.enable();
		Shader.Player.setUniformMat4f("view_matrix", Matrix4f.translate(position));
		texture.bind();
		mesh.render();
		Shader.Player.disable();
		
	}

}
