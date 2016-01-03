package game.rts.utils;

public class Index {

	private Index(){}
	
	public static float[] vertcies(float size, float renderDepth){
		float[] vertices = new float[] {
				-size / 2.0f, -size / 2.0f, renderDepth,
				-size / 2.0f,  size / 2.0f, renderDepth,
				size / 2.0f,  size / 2.0f, renderDepth,
				size / 2.0f, -size / 2.0f, renderDepth
			};
	
		return vertices;
	}
	
	public static float[] vertciesWightHeight(float width, float height, float renderDepth){
		float[] vertices = new float[] {
				-width / 2.0f, -height / 2.0f, renderDepth,
				-width / 2.0f,  height / 2.0f, renderDepth,
				width / 2.0f,  height / 2.0f, renderDepth,
				width / 2.0f, -height / 2.0f, renderDepth
			};
	
		return vertices;
	}
	
	public static byte[] indices(){
		byte[] indices = new byte[] {
				0, 1, 2,
				2, 3, 0
			};
		return indices;
	}
			
	public static float[] tcs(){
		float[] tcs = new float[] {
				0, 1,
				0, 0,
				1, 0,
				1, 1
			};
		return tcs;
	}
}
