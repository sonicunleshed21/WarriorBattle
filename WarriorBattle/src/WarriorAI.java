import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class WarriorAI extends JPanel implements ActionListener {
	// these are the fields that are used throughout the project.
	// the finals are values that should not be changed
	static final int SCREEN_WIDTH = 700;
	static final int SCREEN_HEIGHT = 700;
	static final int CELL_SIZE = 25;
	static final int DELAY = 14;
	// this keeps track of the player's x and y values as well as assigns
	// values at the start
	int playerX = SCREEN_WIDTH/2;
	int playerY = SCREEN_HEIGHT-CELL_SIZE;
	// this keeps track of how many walls and enemies are on the board
	int wallCount = 75;
	int enemyCount = 1;
	// this will be where the star will be right now the star isn't implemented
	// the star reveals itself once all enemies are defeated
	int starX;
	int StarY;
	// this keeps track of the last move made by the player
	int lastMove = 0;
	int count = 0;
	int actionPoints = 0;
	// this keeps track of all the enemy's x and y values
	int enemyX[] = new int [enemyCount];
	int enemyY[] = new int [enemyCount];
	// this keeps track of the cords of all the walls
	int x [] = new int [wallCount+1];
	int y [] = new int [wallCount+1];
	// this stores the basic attacks cords
	int attackX[] = new int [8];
	int attackY[] = new int [8];
	// this keeps the directional attack's cords
	int attackDirectionX = -25;
	int attackDirectionY = -25;
	// these booleans are used for certain things such as generating walls and enemies and attacks
	boolean attack = false;
	boolean once = true;
	boolean twice = true;
	boolean programActive = false;
	Timer timer;
	Random random;
	BasicEnemy firstEnemy = new BasicEnemy (playerX , playerY);
	// this starts the game
	WarriorAI () {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.addKeyListener(new keyInput());
		firstEnemy = new BasicEnemy (playerX, playerY);
		for (int i = 0; i<enemyCount;i++) {
			int a = -1;
			int b = -1;
			//while (a%25 != 0) {
				a = random.nextInt((int)(SCREEN_WIDTH/CELL_SIZE))*CELL_SIZE;
			//}
			//while (b%25 != 0) {
				b = random.nextInt(650/CELL_SIZE)*CELL_SIZE;
			//}
			enemyX[i] = a;
			enemyY[i] = b;
			firstEnemy.setX(a);
			firstEnemy.setY(b);
		}
		startBattle();

	}
	// this starts the battle by causing everything to start
	public void startBattle() {
		programActive = true;
		timer = new Timer(DELAY,this);
		timer.start();
		int a = -1;
		int b = -1;
		while (a%25 != 0) {
			a = random.nextInt(700);
		}
		while (b%25 != 0) {
			b = random.nextInt(650);
		}
		enemyX[0] = a;
		enemyY[0] = b;
	}
	// this is a superclass that triggers all of the other methods that involve graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawScreen(g);
		Player(g);
		walls(g);
		enemy(g);
	}
	// this will be used to draw the attacks
	public void drawScreen (Graphics g) {
		/*
for (int i = 0; i<8;i++) {
g.setColor(Color.CYAN);
g.fillRect(attackX[i],attackY[i], CELL_SIZE,CELL_SIZE);
}
		 */
		g.setColor(Color.CYAN);
		g.fillRect(attackDirectionX,attackDirectionY, CELL_SIZE,CELL_SIZE);
	}
	// this draws the player
	public void Player(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(playerX, playerY, CELL_SIZE, CELL_SIZE);

	}
	// this generates the walls the first time its called and everytime after it draws the walls
	public void walls(Graphics g) {
		g.setColor(Color.GRAY);
		if (twice == true) {
			for (int i = 0; i<wallCount;i++) {
				int a = -1;
				int b = -1;
				while (a%25 != 0) {
					a = random.nextInt(700);
				}
				while (b%25 != 0) {
					b = random.nextInt(650);
				}
				x[i] = a;
				y[i] = b;
			}
		}
		twice = false;
		for (int i = 0; i<wallCount;i++) {
			g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
		}
	}
	// this generates the enemies the first time its called and everytime after it draws them
	/*
public void enemy(Graphics g) {
g.setColor(Color.RED);
if (once == true) {
for (int i = 0; i<enemyCount;i++) {
int a = -1;
int b = -1;
while (a%25 != 0) {
a = random.nextInt(700);
}
while (b%25 != 0) {
b = random.nextInt(650);
}
enemyX[i] = a;
enemyY[i] = b;
}
}
once = false;
for (int i = 0; i<enemyCount;i++) {
g.fillRect(enemyX[i], enemyY[i], CELL_SIZE, CELL_SIZE);
}
}
	 */
	public void enemy(Graphics g) {

		g.setColor(Color.RED);
		if (actionPoints==0) {
			firstEnemy.setPlayerX(playerX);
			firstEnemy.setPlayerY(playerY);
			firstEnemy.movement();
			actionPoints = 3;
		}

		for (int i = 0; i<enemyCount;i++) {
			g.fillRect(firstEnemy.getX(), firstEnemy.getY(), CELL_SIZE, CELL_SIZE);
		}
	}
	// this moves the player in a certain direction, which is entered by the player
	public void move(int a) {
		if (a == 1) {
			if (0 != playerX) {
				playerX-=CELL_SIZE;
			}
		}
		if (a == 2) {
			if (SCREEN_WIDTH-CELL_SIZE != playerX) {
				playerX+=CELL_SIZE;
			}
		}
		if (a == 3) {
			if (0 != playerY) {
				playerY-=CELL_SIZE;
			}
		}
		if (a == 4) {
			if (SCREEN_WIDTH-CELL_SIZE != playerY) {
				playerY+=CELL_SIZE;
			}
		}
		actionPoints--;
		attack = false;
	}
	// this method will be used to calculate the attack
	public void attack() {
		/*
attackX[0] = playerX-CELL_SIZE;
attackX[1] = playerX;
attackX[2] = playerX+CELL_SIZE;
attackX[3] = playerX-CELL_SIZE;
attackX[4] = playerX+CELL_SIZE;
attackX[5] = playerX-CELL_SIZE;
attackX[6] = playerX;
attackX[7] = playerX+CELL_SIZE;

attackY[0] = playerY-CELL_SIZE;
attackY[1] = playerY-CELL_SIZE;
attackY[2] = playerY-CELL_SIZE;
attackY[3] = playerY;
attackY[4] = playerY;
attackY[5] = playerY+CELL_SIZE;
attackY[6] = playerY+CELL_SIZE;
attackY[7] = playerY+CELL_SIZE;
		 */
		if (lastMove == 1) {
			attackDirectionX = playerX-CELL_SIZE;
			attackDirectionY = playerY;
		}
		if (lastMove == 2) {
			attackDirectionX = playerX+CELL_SIZE;
			attackDirectionY = playerY;
		}
		if (lastMove == 3) {
			attackDirectionX = playerX;
			attackDirectionY = playerY-25;
		}
		if (lastMove == 4) {
			attackDirectionX = playerX;
			attackDirectionY = playerY+25;
		}
	}
	// this will be used to take the attacks offscreen so they dont kill enemies
	public void attackEvade() {
		if (attack == false) {
			attackX[0] = -25;
			attackX[1] = -25;
			attackX[2] = -25;
			attackX[3] = -25;
			attackX[4] = -25;
			attackX[5] = -25;
			attackX[6] = -25;
			attackX[7] = -25;

			attackY[0] = -25;
			attackY[1] = -25;
			attackY[2] = -25;
			attackY[3] = -25;
			attackY[4] = -25;
			attackY[5] = -25;
			attackY[6] = -25;
			attackY[7] = -25;
			attackDirectionX = -25;
			attackDirectionY = -25;
		}
	}
	// this is used to check if a player has run into a wall
	public void checkCollisions() {
		for (int i = 0; i<wallCount;i++) {
			if (playerX == x[i] && playerY == y[i] &&lastMove == 2) {
				playerX-=CELL_SIZE;
				break;
			}
			if (playerX == x[i] && playerY == y[i] &&lastMove == 1) {
				playerX+=CELL_SIZE;
				break;
			}
			if (playerX == x[i] && playerY == y[i] &&lastMove == 3) {
				playerY+=CELL_SIZE;
				break;
			}
			if (playerX == x[i] && playerY == y[i] &&lastMove == 4) {
				playerY-=CELL_SIZE;
				break;
			}
		}
	}
	// this will be used to check if an attack has hit an enemy
	public void attackHitEnemy() {
		for (int i = 0;i<8;i++) {
			int a = attackX[i];
			int b = attackY[i];
			for (int j = 0; j<enemyCount;j++) {
				int c = enemyX[j];
				int d = enemyY[j];
				if (a==c && b==d) {
					enemyX[j] = -25;
					enemyY[j] = -25;
				}
			}
		}
	}
	// this runs every tick and checks if the enemys are hit and takes the attack of screen
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (programActive == true) {
			checkCollisions();
			attackEvade();
			attackHitEnemy();
		}
		repaint();
	}
	public class keyInput extends KeyAdapter{
		@Override
		//The source for the KeyEvent and extending KeyAdapter are from a video by
		// bro code where they are making a snake game
		// this takes a key input and a key event and checks if the key is important
		// if it is then it registers a number as the move. This also keeps the code crisp
		// and doesn't overwrite anything
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				move(1);
				lastMove = 1;
				break;
			case KeyEvent.VK_RIGHT:
				move(2);
				lastMove = 2;
				break;
			case KeyEvent.VK_UP:
				move(3);
				lastMove = 3;
				break;
			case KeyEvent.VK_DOWN:
				move(4);
				lastMove = 4;
				break;
			case KeyEvent.VK_SPACE:
				if (attack == false) {
					attack = true;
					attack();
				} else {
					attack = false;
				}
			}
		}

	}

}