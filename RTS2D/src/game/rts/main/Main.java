package game.rts.main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import game.rts.camera.Camera;
import game.rts.graphics.Shader;
import game.rts.input.KeyboardInput;
import game.rts.input.MouseButton;
import game.rts.input.MouseInput;
import game.rts.maths.Matrix4f;
import game.rts.player.Player;
import game.rts.world.World;


public class Main implements Runnable{
	
	private Thread thread;
	public boolean running = false;
	
	private GLFWKeyCallback keyCallBack;
	private GLFWCursorPosCallback mouseCallBack;
	private GLFWMouseButtonCallback mouseButtonCallback;
	
	public long window;
	public static int width = 1200;
	public static int height = 800;
	
	private World world;
	private Player player;
	private Camera camera;

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
		world = new World();
		player = new Player();
		camera = new Camera();
	}
	
	public void update(){
		glfwPollEvents();	
		world.update();
		player.update();
		camera.update();
	}
	
	public void render(){
		//Preparing
		glfwSwapBuffers(window);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//-----
		
		world.render();
		player.render();
		
	}
	
	@Override
	public void run() {
		loadOpenGL();
		loadShaders();
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
		mouseButtonCallback.release();
	}
	private void loadShaders() {
		Shader.loadAll();
		Matrix4f pr_matrix = Matrix4f.orthographic(0.0f, Main.width, 0.0f, Main.height, -1.0f, 1.0f);
		
		Shader.Basic.enable();
		Shader.Basic.setUniformMat4f("projection_matrix", pr_matrix);
		Shader.Basic.setUniform1i("tex", 0);
		Shader.Basic.disable();
		
		Shader.Player.enable();
		Shader.Player.setUniformMat4f("projection_matrix", pr_matrix);
		Shader.Player.setUniform1i("tex", 0);
		Shader.Player.disable();
		
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
		glfwSetMouseButtonCallback(window, mouseButtonCallback = new MouseButton());
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window,+ (vidMode.width() - width) / 2,
								+ (vidMode.height() - height) / 2);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		glfwSwapInterval(1);
		
		GL.createCapabilities();
		glClearColor(1f, 1f, 1f, 1f);
		glEnable(GL_DEPTH_TEST);
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glActiveTexture(GL_TEXTURE0);
		
	}

}
