import java.util.ArrayList;
import java.io.File;

public class RamblersSearchAstart {
	public static void main(String [] args) {
		int initX = 0;
		int initY = 0;
		int goalX = 250;
		int goalY = 248;

		TerrainMap tmap = new TerrainMap("diablo.pgm");
		SearchState goalState = (SearchState) new RamblersState(new Coords(goalY, goalX), 0, 0);
		int rmCost = Math.abs(initX - ((RamblersState)goalState).getx()) + Math.abs(initY - ((RamblersState)goalState).gety());
		SearchState initState = (SearchState) new RamblersState(new Coords(initY, initX), 0, rmCost);
		
		RamblersSearch searcher = new RamblersSearch(tmap, (RamblersState)goalState);

		float res = searcher.runSearchE(initState, "AStar");
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
