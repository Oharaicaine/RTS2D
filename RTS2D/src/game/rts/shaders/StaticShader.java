package game.rts.shaders;

import game.rts.camera.Camera;
import game.rts.maths.MathHelper;
import game.rts.maths.Matrix4f;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/game/rts/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/game/rts/shaders/fragmentShader.txt";
	
	private int location_tranformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);

	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		location_tranformationMatrix = super.getUniformLocation("tranformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadTranformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_tranformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = MathHelper.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}

}
