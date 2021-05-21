import java.util.*;

public class RamblersState extends SearchState{
    
    private int xCoord;
    private int yCoord;

    public TerrainState(Coords coords) {
        xCoord = coords.getx();
        yCoord = coords.gety();
    }

    public boolean goalPredicate(Search searcher) {

    }

    public ArrayList<SearchState> getSuccessors(Search searcher) {

    }

    public boolean sameState(SearchState s2) {
        
    }

}