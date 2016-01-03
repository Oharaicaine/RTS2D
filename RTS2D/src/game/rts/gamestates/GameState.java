package game.rts.gamestates;

import org.lwjgl.glfw.GLFW;

import game.rts.camera.Camera;
import game.rts.entity.Enemy;
import game.rts.entity.EntityManager;
import game.rts.entity.Player;
import game.rts.input.KeyboardInput;
import game.rts.main.Main;
import game.rts.maths.Vector3f;
import game.rts.world.World;

public class GameState extends State {
	
	private World world;
	private EntityManager entityManager;
	private Player player;
	private Camera camera;

	public GameState() {
	}

	@Override
	public void init() {
		world = new World();
		entityManager = new EntityManager();
		camera = new Camera();
		EntityManager.createEntity(new Player(new Vector3f((float)(Main.width/2), (float)Main.height/2, 0.1f), 64f));
		EntityManager.createEntity(new Enemy(new Vector3f((float)(Main.width/2)+64, (float)Main.height/2, 0.1f), 64f));
		
	}

	@Override
	public void update() {
		world.update();
		camera.update();
		entityManager.update();
		
		if(KeyboardInput.keys[GLFW.GLFW_KEY_ESCAPE]){
			Main.running = false;
		}
	}

	@Override
	public void render() {
		world.render();
		entityManager.render();
	}
	
	

}
