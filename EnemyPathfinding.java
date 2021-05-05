/*
 * version 1.0
 * date 4/27/2021
 * Author Andrew Parent
 * allows the enemies to pathfind to the player
 * notes: will have to be upgraded in the future
 */
public class EnemyPathfinding {
	//fields
	private int playerX, playerY, enemyX, enemyY;
	//no constructor since the fields will all be changing constantly 

	//mutator methods
	//sets playerX to the proper value
	public void setPlayerX (int playerX) {
		this.playerX = playerX;
	}

	//sets playerY to the proper value
	public void setPlayerY (int playerY) {
		this.playerY = playerY;
	}	

	//sets enemyX to the proper value
	public void setEnemyX (int enemyX) {
		this.enemyX = enemyX;
	}	

	//sets enemyY to the proper value
	public void setEnemyY (int enemyY) {
		this.enemyY = enemyY;
	}
	
	//decides the proper direction and outputs 1 for north, 2 for east, 3 for south,
	//and 4 for west. There is no diagonal or going around obstacles yet
	public int pathfinding () {
		int distanceX = playerX - enemyX;
		int distanceY = playerY - enemyY;
		int distanceXPositive;
		int distanceYPositive;
		int direction;
		
		if (distanceX < 0) {
			distanceXPositive = distanceX * -1;
		} else {
			distanceXPositive = distanceX;
		}
		if (distanceY < 0) {
			distanceYPositive = distanceY * -1;
		} else {
			distanceYPositive = distanceY;
		}
		
		if (distanceYPositive > distanceXPositive) {
			if (distanceYPositive == distanceY) {
				//this is assuming that the grid is on quadrant 1 (0,0 at the bottom left corner) 
				//I think eclipse is normally on 4. We can change it later if you want to do it differently
				direction = 1;
			} else {
				direction = 3;
			}
		} else {
			if (distanceXPositive == distanceX) {
				direction = 2;
			} else {
				direction = 4;
			}
		}
		
		return direction;
	}
	
}
