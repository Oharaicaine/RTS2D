package game.rts.entity;

import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class Enemy extends Entity{

	public Enemy(Vector3f pos, float size) {
		super(pos, size);
		mesh = new VertexArray(Index.vertcies(size, 0.1f), Index.indices(), Index.tcs());
		texture = new Texture("res/box.png");

	}
	
	
	
	public void render(){
		//super.render();
		Shader.Entity.enable();
		Shader.Entity.setUniformMat4f("view_matrix", Matrix4f.translate(pos));
		Shader.Entity.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f(-Camera.position.x, -Camera.position.y, 0f)));
		texture.bind();
		mesh.render();
		texture.unbind();
		Shader.Entity.disable();
		
	}

}
