import java.awt.event.KeyEvent;
import java.util.ArrayList;

//UIUC CS125 FALL 2011 MP. File: Example.java, CS125 Project: Pacmania, Version: 2011-10-18T07:44:32-0500.396588000

/**
 * The main Game class that will handle all of that silly logic nonsense.
 * @author cagleas2	
 *
 */
public class Game {
	private static Map map;
	private static Pacman player;
	private static int score;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Zen.create(26*32, 32*32, "");
		loadMap("Maps/1.txt");
		player = Pacman.getInstance();
		while(Zen.isRunning()) {
			map.drawMap();
			checkKeys();
			player.move(map);
			map.ghosts.moveGhosts(map);
			Zen.sleep(100);
			Zen.setColor(255, 255, 255);
			Zen.drawText("" + score, Zen.getZenWidth()-32, Zen.getZenHeight()-32);
			Zen.flipBuffer();
		}

	}
	
	public static void loadMap(String filename) {
		TextIO.readFile(filename);
		ArrayList<String> lines = new ArrayList<String>();
		int scale = TextIO.getlnInt();
		while (!TextIO.eof()) {
			lines.add(TextIO.getln());
		}
		map = new Map(lines.size(), lines.get(0).length(), scale);
		int[] playerPos = new int[2];
		for(int y = 0; y < lines.size(); y++) {
			for(int x = 0; x < lines.get(y).length(); x++) {
				int posX = x;
				int posY = y;
				switch(lines.get(y).charAt(x)) {
					case '-': break;
					case 'P':
						playerPos[0]  = posX;
						playerPos[1]  = posY;
						break;
					case 'G': map.addObject(new Ghost(posX,posY)); break;
					case '.': map.addObject(new Pellet(posX,posY)); break;
					case 'W': map.addObject(new Wall(posX,posY)); break;
					default: break;
				}
			}
		}
		Pacman p = Pacman.getInstance();
		p.setPosition(playerPos[0], playerPos[1]);
		map.addObject(p);
	}
	
	public static void checkKeys() {
		int scale = map.getScale();
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_UP)
				&& player.getPosition()[1]*scale > 0)
			player.setDirection(0, -1);
		else if (Zen.isVirtualKeyPressed(KeyEvent.VK_DOWN) 
				&& player.getPosition()[1]*scale < Zen.getZenHeight())
			player.setDirection(0, 1);
		else if(Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT)
				&& player.getPosition()[0]*scale < Zen.getZenWidth())
			player.setDirection(1, 0);
		else if(Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT)
				&& player.getPosition()[0]*scale > 0)
			player.setDirection(-1, 0);
	}
	
	public static void end() {
		Zen.setColor(0, 0, 0);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		Zen.drawText("GAME OVER", Zen.getZenWidth()/2, Zen.getZenHeight()/2);
	}
	public static void incrScore(int amt) {
		score += amt;
	}

}
