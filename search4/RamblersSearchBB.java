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
		System.out.println(res);
	}
}
