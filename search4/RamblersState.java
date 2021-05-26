import java.util.*;

public class RamblersState extends SearchState{
    
    private int xCoord;
    private int yCoord;

    public RamblersState(Coords initCoord) {
        this.xCoord = initCoord.getx();
        this.yCoord = initCoord.gety();
    }
    public TerrainState(Coords coords) {
        xCoord = coords.getx();
        yCoord = coords.gety();
    }

    public boolean goalPredicate(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch)searcher;
        RamblersState goalState = searcher.getGoalState();
        return (goalState.xCoord.getx() == xCoord.getx() && goalState.gety() == yCoord.gety());

    }

    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch)searcher;
        ArrayList<SearchState> sList = new ArrayList<>();
        int x = xCoord.getx();
        int y = yCoord.gety();
        // Add successor states in horizontal directions
        sList.add(new RamblersState(new Coords(x - 1, y)));
        sList.add(new RamblersState(new Coords(x + 1, y)));
        sList.add(new RamblersState(new Coords(x, y - 1)));
        sList.add(new RamblersState(new Coords(x, y + 1)));
        // Add successor states in vertical direction
        sList.add(new RamblersState(new Coords(x - 1, y - 1)));
        sList.add(new RamblersState(new Coords(x + 1, y + 1)));
        sList.add(new RamblersState(new Coords(x + 1, y - 1)));
        sList.add(new RamblersState(new Coords(x - 1, y + 1)));
    }

    public boolean sameState(SearchState s2) {
        RamblersState state1 = (RamblersState)s2;
        return (state1.xCoord.getx() == xCoord.getx() && state1.xCoord.gety());
    }
}