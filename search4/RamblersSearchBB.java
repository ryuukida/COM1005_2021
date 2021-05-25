public class RamblersSearchBB {
	public static void main(String [] args) {
		int initX = 0;
		int initY = 0;
		int goalX = 10;
		int goalY = 10;

		TerrainMap tmap = new TerrainMap("tmc.pgm");
		SearchState initState = (SearchState) new RamblersState(new Coord(initX, initY));
		SearchState goalState = (SearchState) new RamblersState(new Coord(goalX, goalY));
		RamblersSearch searcher = new RamblersSearch(tmap, goalState);

		String res = searcher.runSearch(initState, "breadthFirst");
	}
}
