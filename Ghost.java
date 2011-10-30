


/**
 * @author cagleas2
 *
 */
public class Ghost extends MapObject{
	private int[] direction = new int[2];
	public Ghost(int x, int y) {
		this.setPosition(x, y);
		super.setSprite("ghost_inky.gif");
	}
	
	
	/**
	 * moves the ghost by changing it's internal position variable
	 * 
	 * @param dx = amount of x to change by
	 * @param dy = amount of y to change by
	 */
	public void move(Map map) {
		int[] pos = this.getPosition();
		int x = pos[0] + this.direction[0];
		int y = pos[1] + this.direction[1];
		if(map.checkCollision(x, y, this) == false) {
			this.setPosition(x, y);
			map.setTile(x, y, this);
			map.setTile(pos[0], pos[1], null);
		}
	}
	
	public void setDirection(int x , int y) {
		this.direction[0] = x;
		this.direction[1] = y;
	}
	
	public void decideMove(Map map) {
		Pacman p = Pacman.getInstance();
		int[] pos = p.getPosition();
		int[] myPos = this.getPosition();
		boolean[] options = new boolean[4];
		int[][] directions = {{ 0, 1 }, {0, -1}, {1, 0}, {-1, 0}};
		for (int i = 0; i < 4; i++) {
			int row = getPosition()[0] + directions[i][0];
			int column = getPosition()[1] + directions[i][1];
			options[i] = map.checkCollision(row, column, this);
		}
		if (pos[0] > myPos[0] && options[2])
			this.setDirection(1,0);
		else if (pos[0] < myPos[0] && options[3])
			this.setDirection(-1,0);
		else if (pos[1] > myPos[1] && options[0])
			this.setDirection(0, 1);
		else if (pos[1] < myPos[1] && options[1])
			this.setDirection(0,-1);
	}
}
