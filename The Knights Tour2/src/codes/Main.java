package codes;

public class Main {

	public static void main(String[] args) {
		//create the chess board
		Grid grid = new Grid(8);
		//start knight from second position
		grid.tour(grid.getRoot().getRight(),1);
		// print out total final solutions (if it ever gets there)
		System.out.println("Total" + grid.solutions);

	}

}
