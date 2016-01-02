package game.rts.tiles;

import java.awt.geom.Rectangle2D;

import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class Tiles {

	protected static float size = 64.0f;
	protected VertexArray mesh;
	protected Texture texture;
	
	protected Vector3f pos;
	protected Rectangle2D.Float bounds;

	public Tiles(Vector3f pos) {	
		this.pos = pos;
		bounds = new Rectangle2D.Float(pos.x - (size/2.0f), pos.y - (size/2.0f), size, size);
		mesh = new VertexArray(Index.vertcies(size, 0.0f), Index.indices(), Index.tcs());
		texture = new Texture("res/stonefloor.png");
		
	}
	
	public void update() {
	}
	
	public void render() {

		Shader.Basic.setUniformMat4f("view_matrix", Matrix4f.translate(pos));
		Shader.Basic.setUniformMat4f("camera_matrix", Matrix4f.translate(new Vector3f(-Camera.position.x, -Camera.position.y, 0f)));
		texture.bind();
		mesh.render();
		texture.unbind();


	}
	
	public static float getSize() {
		return size;
	}
	
	public Vector3f getPos() {
		return pos;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public Rectangle2D.Float getBounds() {
		return bounds;
	}
}
