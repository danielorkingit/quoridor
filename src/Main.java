import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Player> player = new ArrayList<>();
		
		// Get players
		
		System.out.println("Enter the name of the first player: ");
		player.add(new Player(sc.nextLine(), 0));
		
		System.out.println("Enter the name of the second player: ");
		player.add(new Player(sc.nextLine(), 1));
		
		Court court = new Court(player.get(0), player.get(1));
		
		for (Player p : player) {
			p.provideCourt(court);
		}
		
		// Main loop
		court.print();
		while (true) {
			for (Player p : player) {
				System.out.println("Its " + p.name + " move.");
				System.out.println("Move (m) \nPlace wall (w) \nGive up (x) \nPrint field (p) \n\n");
				while (p.makeMove(sc.nextLine()) == true) {
					System.out.println("Move (m) \nPlace wall (w) \nGive up (x) \nPrint field (p) \n\n");
				}

				if(p.checkWin() == true) {
					System.out.println(p.name + " has won.");
					court.print();
					break;
				}
				court.print();
			}
			break;
		}
	}

}
