package game.rts.entity;


import java.awt.Point;
import java.awt.geom.Ellipse2D;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL13.*;

import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.input.MouseButton;
import game.rts.input.MouseInput;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;
import game.rts.utils.Index;

public class Entity {
	
	protected Texture texture;
	protected VertexArray mesh;
	
	protected Texture boxTex;
	protected VertexArray boxMesh;
	
	protected float scale;
	
	protected Vector3f pos;
	protected float size;
	
	protected Ellipse2D.Float hitBox;
	
	protected boolean isSelected = false;

	public Entity(Vector3f pos, float size) {
		this.pos = pos;
		this.size = size;
		boxTex = new Texture("res/box.png");
		boxMesh = new VertexArray(Index.vertcies(size, 0.05f), Index.indices(), Index.tcs());
		hitBox = new Ellipse2D.Float(pos.x-(size/2),pos.y-(size/2), size, size);

	}
	
	public void update(){
		hitBox.x = pos.x-(size/2);
		hitBox.y = pos.y-(size/2);
		if(hitBox.contains(new Point.Float((float)MouseInput.mouseX, (float)MouseInput.mouseY)) &&
				MouseButton.mouseLeft){
			isSelected = true;
		}
	}
	

	
	public void render(){
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public Vector3f getPos() {
		return pos;
	}

}
