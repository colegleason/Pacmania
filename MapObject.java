
public class MapObject {
	private int[] position = new int[2];	
	private String sprite;
	
	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}
	
	public int[] getPosition() {
		return this.position;
	}

	public void setSprite(String name) {
		this.sprite = "sprites/" + name;
	}
	
	public void draw(int scale) {
		Zen.drawImage(this.sprite, this.position[0]*scale, this.position[1]*scale);
	}
	

}
