import java.util.ArrayList;
import java.io.File;

public class RamblersSearchBB {
	public static void main(String [] args) {
		int initX = 4;
		int initY = 6;
		int goalX = 32;
		int goalY = 20;

		TerrainMap tmap = new TerrainMap("diablo.pgm");
		SearchState initState = (SearchState) new RamblersState(new Coords(initY, initX), 0);
		SearchState goalState = (SearchState) new RamblersState(new Coords(goalY, goalX), 0);
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
		String filePath = "path.pgm";
		File pathFilename = new File(filePath);
		if (pathFilename.exists())
			pathFilename.delete();
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
