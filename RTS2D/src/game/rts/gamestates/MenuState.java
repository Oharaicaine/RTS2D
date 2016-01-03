package game.rts.gamestates;

import java.awt.geom.Rectangle2D;

import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.input.MouseButton;
import game.rts.input.MouseInput;
import game.rts.main.Main;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class MenuState extends State{
	
	private VertexArray buttonMesh;
	private VertexArray backgroundMesh;
	private Texture buttonTexture;
	private Texture backgroundTexture;
	private Rectangle2D.Float button;
	
	private boolean loading = false;

	public MenuState() {
		buttonMesh = new VertexArray(Index.vertciesWightHeight(200f, 100f, 0.1f), Index.indices(), Index.tcs());
		backgroundMesh = new VertexArray(Index.vertciesWightHeight(Main.width, Main.height, 0.0f), Index.indices(), Index.tcs());
		buttonTexture = new Texture("res/start.png");
		backgroundTexture = new Texture("res/stonefloor.png");
		button = new Rectangle2D.Float((Main.width/2)-100, (Main.height/2)-50,
				200, 100);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		if(button.contains(MouseInput.mouseScreenX, MouseInput.mouseScreenY) && MouseButton.mouseLeft){
			GameStateManager.setState(GameStateManager.GAMESTATE);
		}
		
	}

	@Override
	public void render() {
		
		Shader.Basic.enable();
		// button render
		Shader.Basic.setUniformMat4f("view_matrix", Matrix4f.translate(new Vector3f(Main.width/2, Main.height/2, 0.0f)));
		Shader.Basic.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f()));
		buttonTexture.bind();
		buttonMesh.render();
		buttonTexture.unbind();
		// Background
		Shader.Basic.setUniformMat4f("view_matrix", Matrix4f.translate(new Vector3f(Main.width/2, Main.height/2, 0.0f)));
		Shader.Basic.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f()));
		backgroundTexture.bind();
		backgroundMesh.render();
		backgroundTexture.unbind();
		
		Shader.Basic.disable();
		
		
	}

}
