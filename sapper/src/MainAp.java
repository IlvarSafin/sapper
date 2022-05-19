import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainAp {

	public static void main(String[] args) {
		System.out.println((3 + 4) * (5 / 2));
		Scanner scanner = new Scanner(System.in);
		System.out.print("Size: ");
		int size = scanner.nextInt();
		System.out.print("Bombs: ");
		int bombs = scanner.nextInt();
		Road road = new Road(size, size, bombs);
		road.getMap();
		boolean win = true;
		while (win){
			//road.display_map();
			road.display();
			System.out.print("x and y: ");
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			System.out.println();
			if (road.getMap()[i][j] == -1) {
				win = false;
				break;
			}
			road.req(i,j);
			if (road.count())
				break;
		}
		if (win)
			System.out.println("WIN");
		else {
			System.out.println("LOSE");
			road.display_map();
		}
	}
}
