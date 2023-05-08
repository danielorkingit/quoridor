import java.util.Scanner;

import org.javatuples.Pair;

public class Player {
	
	//FieldOfView fov;
	public String name;
	public int id;
	
	private int x;
	private int y;
	
	private int walls;
	
	private Court court;
	
	
	public Player(String name, int id) {
		
		//fov = new FieldOfView(this, court);
		this.name = name;
		this.id = id;
		
		if (id == 0) {
			this.x = 8;
			this.y = 0;
		}
		
		if (id == 1) {
			this.x = 8;
			this.y = 16;
		}
		
	}
	
	public void makeMove(String input) {
		
		Scanner sc1 = new Scanner(System.in);
		
		if (input.equals("m")) {
			int[] possibleMoves = court.getPosibleMoves(this);
			System.out.println("Upwart (u): " + possibleMoves[0] + " Right (r): " + possibleMoves[1] + " Left (l): " + possibleMoves[2] + " Downwart (d): " + possibleMoves[3]);
			
			while (true) {
				String direction = sc1.nextLine();
				
				if (direction.equals("u")) {
					if(possibleMoves[0] == 1) {
						this.y -= 2;
						break;
					}
					if(possibleMoves[0] == 2) {
						this.y -= 4;
						break;
					}
					
				}
				if (direction.equals("r")) {
					if(possibleMoves[1] == 1) {
						this.x += 2;
						break;
					}
					if(possibleMoves[1] == 2) {
						this.x += 4;
						break;
					}
				}
				if (direction.equals("l")) {
					if(possibleMoves[2] == 1) {
						this.x -= 2;
						break;
					}
					if(possibleMoves[2] == 2) {
						this.x -= 4;
						break;
					}
				}
				if (direction.equals("d")) {
					if(possibleMoves[3] == 1) {
						this.y += 2;
						break;
					}
					if(possibleMoves[3] == 2) {
						this.y += 4;
						break;
					}
				}
				System.out.println("Invalid input. ");
				System.out.println("Upwart (u): " + possibleMoves[0] + " Right (r): " + possibleMoves[1] + " Left (l): " + possibleMoves[2] + " Downwart (d): " + possibleMoves[3]);
			}
			court.update();
		}
		if (input.equals("w")) {
			if (walls < 10) {
				while (true) {
					System.out.println("x-coordinate of first wall: ");
					int x1 = sc1.nextInt();
					
					System.out.println("y-coordinate of first wall: ");
					int y1 = sc1.nextInt();
					
					System.out.println("x-coordinate of second wall: ");
					int x2 = sc1.nextInt();
					
					System.out.println("y-coordinate of second wall: ");
					int y2 = sc1.nextInt();
					
					if (court.addWall(x1,y1,x2,y2) == true) {
						walls += 1;
						break;
					}
					
					System.out.println("Invalid input.");
				}
			} else {
				System.out.println("No walls left.");			
			}

			
		}
		
	}
	
	public Pair<Integer, Integer> getPosition () {
		return new Pair<Integer, Integer>(x,y);
	}
	
	public void provideCourt(Court court) {
		this.court = court;
	}

	public boolean checkWin() {
		if (id == 0) {
			if (y == 16) {
				return true;
			}
		}
		if (id == 1) {
			if (y == 0) {
				return true;
			}
		}
		
		return false;
	}

}
