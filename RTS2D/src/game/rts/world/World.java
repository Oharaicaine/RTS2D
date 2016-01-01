package game.rts.world;

import game.rts.graphics.Shader;
import game.rts.graphics.Texture;
import game.rts.graphics.VertexArray;
import game.rts.maths.Matrix4f;
import game.rts.maths.Vector3f;

public class World {
	
	private VertexArray backGround;
	private Texture backGroundTexture;
	
	private int xScroll = 0;
	private int map = 0;

	public World() {
		float[] vertices = new float[]{
			-10.0f, -10.0f*9 / 16, 0.0f,
			-10.0f,  10.0f*9 / 16, 0.0f, 
			 0.0f,   10.0f*9 / 16, 0.0f, 
			 0.0f,  -10.0f*9 / 16, 0.0f
		};
		
		byte[] indices = new byte[]{
			0,1,2,
			2,3,0
		};
		
		float[] textureCoords = new float[]{
			0,1,
			0,0,
			1,0,
			1,1
		};
		
		backGround = new VertexArray(vertices, indices, textureCoords);
		backGroundTexture = new Texture("res/stonefloor.png");
	}
	
	public void update(){
		xScroll--;
		if(-xScroll % 335 == 0) map++;
	}
	
	public void render(){
		backGroundTexture.bind();
		Shader.Basic.enable();
		
		backGround.bind();
		for(int i = map; i < map + 3; i++){
			Shader.Basic.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(i * 10 + xScroll*0.03f,0.0f, 0.0f )));
			backGround.render();
		}
		
		Shader.Basic.disable();
		backGroundTexture.unbind();
	}

}
