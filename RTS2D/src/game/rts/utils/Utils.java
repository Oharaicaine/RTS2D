package game.rts.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.rts.maths.Vector3f;

public class Utils {

	public static float lerp(float v0, float v1, float t) {
		float result = ((1-t)*v0 + t*v1);
		//if(result < -0.05f)result = -0.05f;
		//if(result > 0.05f)result = 0.05f;
		  return result;
	}

	public static float Collision(Vector3f vec, Vector3f vec2) {
		if(vec.x < vec2.x){
			if(vec.x - vec2.x < 1.0f){
				System.out.println("coll  left");
			}
			// collision 
		}
		
		return 0;
	}
	
	public static float distanceBetweenVector3f(Vector3f vec1, Vector3f vec2){
		float result = 0;
		result = (float) Math.sqrt(Math.pow((vec2.getX() - vec1.getX()), 2) + Math.pow((vec2.getY() - vec1.getY()), 2));
		return result;
	}
	
	public static BufferedImage loadBufferedImage(String path){
		try {
			File file = new File(path);
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
