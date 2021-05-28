import java.util.ArrayList;
import java.util.List;

public class RamblersSearchBB {
	public static void main(String [] args) {
		int initX = 0;
		int initY = 0;
		int goalX = 10;
		int goalY = 10;

		TerrainMap tmap = new TerrainMap("tmc.pgm");
		SearchState initState = (SearchState) new RamblersState(new Coords(initX, initY), 0);
		SearchState goalState = (SearchState) new RamblersState(new Coords(goalX, goalY), 0);
		RamblersSearch searcher = new RamblersSearch(tmap, (RamblersState)goalState);

		String res = searcher.runSearch(initState, "breadthFirst");
		// Get list of nodes/coords that make up the path for the solution
		SearchNode n = searcher.currentNode;
		ArrayList<Coords> pathCoords = getCoordPathList(n);
		// System.out.println("Printing path ");
		// for (Coords c : pathCoords) {
		// 	System.out.println(c);
		// }
		System.out.println(res);

		tmap.showPath(pathCoords);
		tmap.writeTMap("path.pgm");
	}

	private static ArrayList<Coords> getCoordPathList(SearchNode n) {
		ArrayList<Coords> pathCoords = new ArrayList<>();
		SearchNode currentNode = n;
		while (currentNode.getParent() != null) {
			RamblersState state = (RamblersState)currentNode.get_State();
			pathCoords.add(state.getCoord());
			currentNode = currentNode.getParent();
			// Add final node to list
			if (currentNode.getParent() == null) {
				state = (RamblersState)currentNode.get_State();
				pathCoords.add(state.getCoord());
			}
		}
		return pathCoords;
	}
}
