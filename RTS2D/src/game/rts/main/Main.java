package game.rts.main;

import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import game.rts.input.KeyboardInput;
import game.rts.input.MouseInput;


public class Main implements Runnable{
	
	private Thread thread;
	public boolean running = false;
	
	private GLFWKeyCallback keyCallBack;
	private GLFWCursorPosCallback mouseCallBack;
	
	public long window;
	public static int width = 1200;
	public static int height = 800;

	public static void main(String[] args) {
		Main game = new Main();
		game.start();

	}
	public void start(){
		running =  true;
		thread = new Thread(this, "Main Game");
		thread.start();
	}
	
	public void init(){
		
	}
	
	public void update(){
		glfwPollEvents();	
	}
	
	public void render(){
		//Preparing
		glfwSwapBuffers(window);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//-----
		
	}
	
	@Override
	public void run() {
		loadOpenGL();
		init();
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			render();		
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			if(glfwWindowShouldClose(window) == GL_TRUE){
				running = false;
			}
		}
		keyCallBack.release();
		mouseCallBack.release();
	}
	private void loadOpenGL() {
		if(glfwInit() != GL_TRUE){
			System.err.println("glfw Init failed");
		}
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		window =  glfwCreateWindow(width, height, "Demo", NULL, NULL);
		if(window== NULL){
			System.err.println("Could not create window");
		}
		
		glfwSetKeyCallback(window, keyCallBack = new KeyboardInput());
		glfwSetCursorPosCallback(window, mouseCallBack = new MouseInput());
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window,+ (vidMode.width() - width) / 2,
								+ (vidMode.height() - height) / 2);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		glfwSwapInterval(1);
		
		GL.createCapabilities();
		glClearColor(1f, 1f, 1f, 1f);
		glEnable(GL_DEPTH_TEST);
		
	}

}
