import java.util.ArrayList;

import org.javatuples.Quartet;

public class Court {
	
	Object[][] court = new Object[17][17];
	
	
	{
		for (int i = 0 ; i < 17 ; i += 2) {
			for (int j = 0; j < 17 ; j += 2) {
				court[i][j] = new Field();
			}
		}
	}
	
	public Court(Player p1, Player p2) {
		court[p1.getPosition().getValue1()][p1.getPosition().getValue0()] =  p1;
		court[p2.getPosition().getValue1()][p2.getPosition().getValue0()] =  p2;
	}
	
	public void print() {
		for (int i = 0 ; i < 17 ; i++) {
			for (int j = 0; j < 17 ; j++) {
				if (court[i][j] instanceof Field) System.out.print("00 "); 
				if (court[i][j] instanceof Wall) System.out.print("WW ");
				if (court[i][j] instanceof Player) System.out.print("PP "); 
				if (court[i][j] == null) System.out.print("-  "); 
			}
			System.out.println();
		}
	}

	public int[] getPosibleMoves(Player player) {
		
		int[] result = new int[4]; // 0: no 1: yes 2: over player
		
		for (int i = 0 ; i < 4 ; i++) {
			result[i] = 1;
		}
		
		int x = player.getPosition().getValue0();
		int y = player.getPosition().getValue1();
		
		// Check upwart
		
		try{
			if (!(court[y-2][x] instanceof Field) || (court[y-1][x] instanceof Wall)) {
				// check if player instead
				if (court[y-2][x] instanceof Player && !(court[y-1][x] instanceof Wall)) {
					// check over player
					if (!(court[y-4][x] instanceof Field)) {
						result[0] = 0;
					} else if (!(court[y-3][x] instanceof Wall)){
						result[0] = 2;
					}
				} else {
					result[0] = 0;
				}
			}
		}catch(Exception e) {
			result[0] = 0;
		}
		
		// Check right
		
		try{
			if (!(court[y][x+2] instanceof Field) || (court[y][x+1] instanceof Wall)) {
				// check if player instead
				if (court[y][x+2] instanceof Player && !(court[y][x+1] instanceof Wall)) {
					// check over player
					if (!(court[y][x+4] instanceof Field)) {
						result[1] = 0;
					} else if (!(court[y][x+3] instanceof Wall)){
						result[1] = 2;
					}
				} else {
					result[1] = 0;
				}
			}
		}catch(Exception e) {
			result[1] = 0;
		}
		
		// Check left
		
		try{
			if (!(court[y][x-2] instanceof Field) || (court[y][x-1] instanceof Wall)) {
				// check if player instead
				if (court[y][x-2] instanceof Player && !(court[y][x-1] instanceof Wall)) {
					// check over player
					if (!(court[y][x-4] instanceof Field)) {
						result[2] = 0;
					} else if (!(court[y][x-3] instanceof Wall)){
						result[2] = 2;
					}
				} else {
					result[2] = 0;
				}
			}
		}catch(Exception e) {
			result[2] = 0;
		}
		
		// Check downwart
		
		try{
			if (!(court[y+2][x] instanceof Field) || (court[y+1][x] instanceof Wall)) {
				// check if player instead
				if (court[y+2][x] instanceof Player && !(court[y+1][x] instanceof Wall)) {
					// check over player
					if (!(court[y+4][x] instanceof Field)) {
						result[3] = 0;
					} else if (!(court[y+3][x] instanceof Wall)){
						result[3] = 2;
					}
				} else {
					result[3] = 0;
				}
			}
		}catch(Exception e) {
			result[3] = 0;
		}
		
		return result;
	}

	public void update() {
		
		for (int i = 0 ; i < 17 ; i++) {
			for (int j = 0; j < 17 ; j++) {
				if(court[i][j] instanceof Player) {
					// Move Player check i and j
					if (i != ((Player) court[i][j]).getPosition().getValue1() || j != ((Player) court[i][j]).getPosition().getValue0()) {
						Player p = (Player) court[i][j];
						court[i][j] = new Field();
						court[p.getPosition().getValue1()][p.getPosition().getValue0()] =  p;
					}
				}
				// Wall
				
			}
		}
		
	}

	public boolean addWall(int x1, int y1, int x2, int y2) {
		
		// check if player is able to move
		
		if (court[y1][x1] == null && court[y2][x2] == null && (Math.abs(x1-x2) == 2 || Math.abs(y2-y1) == 2)) {
			court[y1][x1] = new Wall(x1,y1,x2,y2);
			court[y2][x2] = court[y1][x1];
			court[(y1+y2)/2][(x1+x2)/2] = court[y1][x1];
			return true;
		}
		
		return false;
	}
	
}
