package game.rts.player;


import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.input.MouseButton;
import game.rts.input.MouseInput;
import game.rts.main.Main;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class Player {
	
	private float SIZE = 64f;
	private VertexArray mesh;
	private Texture texture;
	
	private Vector3f position = new Vector3f((float)Main.width/2, (float)Main.height/2, 0.0f);
	private float rot;
	private float delta = 0.0f;
	private float moveToX, moveToY;

	public Player() {
		mesh = new VertexArray(Index.vertcies(SIZE, 0.1f), Index.indices(), Index.tcs());
		texture = new Texture("res/player.png");
		moveToX = position.x;
		moveToY = position.y;
	}
	
	
	
	public void update(){
		if(MouseButton.mouseOne){
			moveToX = (float) MouseInput.mouseX;
			moveToY = (float) MouseInput.mouseY;
		}
		
		double diffX = moveToX - position.x;
		double diffY = moveToY - position.y;
		
		float angle =(float)(Math.atan2(diffY, diffX) * 180 /Math.PI);
		
		if(diffX > 5 || diffX < -5)position.x += Math.cos(angle * Math.PI/180) * 2f;
		if(diffY > 5 || diffY < -5)position.y += Math.sin(angle * Math.PI/180) * 2f;
		
	}
	
	public void render(){
		Shader.Player.enable();
		Shader.Player.setUniformMat4f("view_matrix", Matrix4f.translate(position));
		Shader.Player.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f(-Camera.position.x, -Camera.position.y, 0f)));
		texture.bind();
		mesh.render();
		texture.unbind();
		Shader.Player.disable();
		
	}

}
