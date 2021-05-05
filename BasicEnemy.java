
public class BasicEnemy {
	//fields
	private int x;
	private int y;
	private int playerX;
	private int playerY;
	static final int UNIT_SIZE = 25;

	//constructor
	public BasicEnemy (int playerX, int playerY) {
		this.playerX = playerX;
		this.playerY = playerY;
	}
	//methods
	//accessor and mutator methods names are self explanatory
	public void setX (int x) {
		this.x = x;
	}

	public void setY (int y) {
		this.y = y;
	}
	
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}
	
	public void setPlayerX (int playerX) {
		this.playerX = playerX;
	}
	
	public void setPlayerY (int playerY) {
		this.playerY = playerY;
	}
	//moves the character by calling the EnemyPathfinding class and changing
	//the x and y values accordingly if this bumps into the player then it 
	//will (damage in the future and) move backwards
	public void movement() {
		EnemyPathfinding pathfind = new EnemyPathfinding ();
		pathfind.setPlayerX(playerX);
		pathfind.setPlayerY(playerY);
		pathfind.setEnemyX(x);
		pathfind.setEnemyY(y);
		int direction;
		for (int i = 2; i>0; i--) {
			direction = pathfind.pathfinding();
			if (direction == 1) {
				y-= UNIT_SIZE;
			} else if (direction == 2){
				x+= UNIT_SIZE;
			} else if (direction == 3){
				y+= UNIT_SIZE;
			} else if (direction == 4){
				x-= UNIT_SIZE;
			}
			
			if (x == playerX && y == playerY) {
				//damage player here
				if (direction == 1) {
					y+= UNIT_SIZE;
				} else if (direction == 2){
					x-= UNIT_SIZE;
				} else if (direction == 3){
					y-= UNIT_SIZE;
				} else if (direction == 4){
					x+= UNIT_SIZE;
				}
			}
			
			pathfind.setEnemyX(x);
			pathfind.setEnemyY(y);
		}
	}
}
