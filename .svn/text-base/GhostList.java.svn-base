
public class GhostList {
	public Ghost[] ghosts = new Ghost[0];
	private int size = 0;
	
	public GhostList() {}
	
	public void add(Ghost ghost){
		size++;
		if ( size > ghosts.length) {
			Ghost[] old = ghosts;
			ghosts = new Ghost[size*2];
			for(int i = 0; i < old.length; i++) {
				ghosts[i] = old[i];
			}
		}
		ghosts[size - 1] = ghost;
	}
	
	public void createGhost(int x, int y) {
		Ghost ghost = new Ghost(x, y);
		this.add(ghost);
	}

}
