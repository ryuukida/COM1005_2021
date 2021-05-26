import java.util.*;

public class RamblersState extends SearchState{
    
    private int xCoord;
    private int yCoord;

    public RamblersState(Coords coord, int lc) {
        this.xCoord = coord.getx();
        this.yCoord = coord.gety();
        super.localCost = lc;
    }

    public boolean goalPredicate(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch)searcher;
        RamblersState goalState = searcher.getGoalState();
        return (goalState.xCoord.getx() == xCoord.getx() && goalState.gety() == yCoord.gety());

    }

    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch)searcher;
        int [][] tmap = rsearcher.getTmap();
        ArrayList<SearchState> sList = new ArrayList<>();
        int x = xCoord.getx();
        int y = yCoord.gety();
        // Add all potential successor states relative to current state in
        // array list
        ArrayList<Coords> sCoords = new ArrayList<>();
        // Horizontal
        sCoords.add(new Coords(x - 1, y));
        sCoords.add(new Coords(x + 1, y)); 
        sCoords.add(new Coords(x, y - 1)); 
        sCoords.add(new Coords(x, y + 1)); 
        // Vertical
        sCoords.add(new Coords(x - 1, y - 1)); 
        sCoords.add(new Coords(x + 1, y + 1)); 
        sCoords.add(new Coords(x + 1, y - 1)); 
        sCoords.add(new Coords(x - 1, y + 1)); 
        
        // Remove any coords that do not fall within map
        vetCoords(sCoords);

        // Add all the successor states to sList
        iterator<Coords> iterator = sCoords.iterator();
        while (iterator.hasNext()) {
            Coord coord = iterator.next();
            sList.add(new RamblersState(coord, tmap[coord.getx()][coord.gety()]));  
        }  
    }

    /**
     * Helper method to return array list of succesor coords that are within the map
     */
    private ArrayList<Coords> vetCoords(ArrayList<Coords> sCoords) {
        ListIterator<Coords> iterator = sCoords.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            Coord coord = iterator.next();
            int sX = coord.getx();
            int sY = coord.gety();
            if ( (sX + 1 > tmap.width || sX - 1 < tmap.width) || (sY + 1 > tmap.height || sY - 1 < tmap.height) ) {
                iterator.remove();
            }
            iterator.previous();
        }

        return sCoords;
    }

    public boolean sameState(SearchState s2) {
        RamblersState state1 = (RamblersState)s2;
        return (state1.xCoord.getx() == xCoord.getx() && state1.xCoord.gety());
    }
}