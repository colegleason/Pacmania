import java.util.ArrayList;


public class Map {
	public GhostList ghosts = new GhostList();
	private int width;
	private int height;
	private int scale;
	private ArrayList<MapObject> objects;
	private MapObject[][] tiles;
	
	public Map(int width, int height, int scale){
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.objects = new ArrayList<MapObject>();
		this.tiles = new MapObject[this.height][this.width];
	}
	
	public int getScale(){
		return this.scale;
	}
	
	public void addObject(MapObject obj){
		objects.add(obj);
		int[] pos = obj.getPosition();
		this.setTile(pos[0], pos[1], obj);
		if (obj instanceof Ghost)
			ghosts.add((Ghost) obj);
	}
	
	public void deleteObject(MapObject obj){
		for(int i = 0; i < objects.size(); i++) {
			if (objects.get(i) == obj)
				objects.remove(i);
		}
	}
	
	public void drawMap() {
		Zen.setColor(0, 0, 0);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(this.scale);
		}
	}
	
	public MapObject getTile(int row, int column) {
		if ((row >= 0 && row < tiles.length) && (column >= 0 && column < tiles[0].length))
				return this.tiles[row][column];
		else return null;
	}
	
	public void setTile(int row, int column, MapObject obj) {
		if ((row >= 0 && row < tiles.length) && (column >= 0 && column < tiles[0].length))
			this.tiles[row][column] = obj;
	}
	
	public boolean checkCollision(int row, int column, MapObject obj) {
		MapObject tile = getTile(row, column);
		if (tile instanceof Wall)
			return true;
		else if (obj instanceof Pacman && tile instanceof Pellet) {
			this.deleteObject(tile);
			Game.incrScore(10);
			return false;
		}
		else return false;
	}
	

}