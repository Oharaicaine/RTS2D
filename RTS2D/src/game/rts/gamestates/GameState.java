package game.rts.gamestates;

import game.rts.camera.Camera;
import game.rts.player.Player;
import game.rts.world.World;

public class GameState extends State {
	
	private World world;
	private Player player;
	private Camera camera;

	public GameState() {
	
	}

	@Override
	public void init() {
		world = new World();
		player = new Player();
		camera = new Camera();
	}

	@Override
	public void update() {
		world.update();
		player.update();
		camera.update();
	}

	@Override
	public void render() {
		world.render();
		player.render();
	}
	
	

}
