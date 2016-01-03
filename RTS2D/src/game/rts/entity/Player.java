package game.rts.entity;


import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.input.MouseButton;
import game.rts.input.MouseInput;
import game.rts.main.Main;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Assets;
import game.rts.utils.Index;

public class Player extends Entity{
	
	private float moveToX, moveToY;

	public Player(Vector3f pos, float size) {
		super(pos, size);
		mesh = new VertexArray(Index.vertcies(size, 0.1f), Index.indices(), Index.tcs());
		texture = Assets.getPlayer();
		moveToX = pos.x;
		moveToY = pos.y;
	}
	
	public void update(){
		super.update();
		if(isSelected){
		if(MouseButton.mouseRight){
			moveToX = (float) MouseInput.mouseX;
			moveToY = (float) MouseInput.mouseY;
		}
		
		double diffX = moveToX - pos.x;
		double diffY = moveToY - pos.y;
		
		float angle =(float)(Math.atan2(diffY, diffX) * 180 /Math.PI);
		
		if(diffX > 5 || diffX < -5)pos.x += Math.cos(angle * Math.PI/180) * 2f;
		if(diffY > 5 || diffY < -5)pos.y += Math.sin(angle * Math.PI/180) * 2f;
		}
	}
	
	@Override
	public void render(){
		Shader.Entity.enable();
		Shader.Entity.setUniformMat4f("view_matrix", Matrix4f.translate(pos));
		Shader.Entity.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f(-Camera.position.x, -Camera.position.y, 0f)));
		texture.bind();
		mesh.render();
		texture.unbind();
		Shader.Entity.disable();
	}

}
