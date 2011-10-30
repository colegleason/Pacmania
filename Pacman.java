
public class Pacman extends MapObject {
	private int[] direction = {0,0};
	private int lives = 3;
	private static Pacman instance = null;
	
	/**
	 * Private constructor to defeat instantiation
	 */
	private Pacman() {
		super.setSprite("Ultra Pacman Right.gif");	
		
	}
	
	/**
	 * Returns the singular Pacman instance.
	 * @return Pacman instance.
	 */
	public static Pacman getInstance() {
		if (instance == null)
			instance = new Pacman();
		return instance;
	}
	
	public void setDirection(int x , int y) {
		this.direction[0] = x;
		this.direction[1] = y;
	}
	
	public void move(Map map) {
		int[] pos = this.getPosition();
		int x = pos[0] + this.direction[0];
		int y = pos[1] + this.direction[1];
		if(map.checkCollision(x, y) == false)
			this.setPosition(x, y);
			map.setTile(x, y, this);
			map.setTile(pos[0], pos[1], null);
	}
	
	public void kill() {
		this.lives--;
		if(this.lives <= 0)
			Game.end();
	}
	
}
