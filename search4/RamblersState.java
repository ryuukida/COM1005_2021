import java.util.*;

public class RamblersState extends SearchState{
    
    private int xCoord;
    private int yCoord;
    private Coords coordinate;

    /**
     * Method to return Coords object stored in state
     * @return coordinate Coords object of this state
     */
    public Coords getCoord() {
        return coordinate;
    }

    public RamblersState(Coords coord, int lc) {
        xCoord = coord.getx();
        yCoord = coord.gety();
        coordinate = coord;
        super.localCost = lc;
    }

    public boolean goalPredicate(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch)searcher;
        RamblersState goalState = (RamblersState) rsearcher.getGoalState();
        return (goalState.getx() == xCoord && goalState.gety() == yCoord);

    }

    public ArrayList<SearchState> getSuccessors(Search searcher) {
        System.out.println("Getting successors");
        RamblersSearch rsearcher = (RamblersSearch)searcher;
        TerrainMap terrainMap = rsearcher.getTmap();
        int [][] tmap = terrainMap.getTmap();
        ArrayList<SearchState> sList = new ArrayList<>();
        int x = xCoord;
        int y = yCoord;
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
        // System.out.println("Unvetted Successor coords: ");
        // for (Coords state : sCoords) {
        //     System.out.println(state.getx() + ", " + state.gety());
        // } 
        // Remove any coords that do not fall within map
        vetCoords(sCoords, terrainMap);
        // System.out.println("Vetted Successor coords: ");
        // for (Coords state : sCoords) {
        //     System.out.println(state.getx() + ", " + state.gety());
        // } 
        // Add all the successor states to sList
        Iterator<Coords> iterator = sCoords.iterator();
        while (iterator.hasNext()) {
            Coords coord = iterator.next();
            sList.add(new RamblersState(coord, tmap[coord.getx()][coord.gety()]));  
        }
        // System.out.println("Successor states: ");
        // for (SearchState s : sList) {
            
        //     RamblersState state = (RamblersState)s;
        //     System.out.println(state.getx() + ", " + state.gety());
        // } 
        return sList;
    }

    /**
     * Helper method to return array list of succesor coords that are within the map
     */
    private void vetCoords(ArrayList<Coords> sCoords, TerrainMap terrainMap) {
        ListIterator<Coords> iterator = sCoords.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            // System.out.println("Index: " + index);
            Coords coord = iterator.next();
            int sX = coord.getx();
            int sY = coord.gety();
            if ( (sX + 1 > terrainMap.getWidth() || sX - 1 < 0) || (sY + 1 > terrainMap.getHeight() || sY - 1 < 0) ) {
                // System.out.println("Removing " + coord.getx() + ", " + coord.gety());
                iterator.remove();
                if (iterator.hasPrevious())
                    iterator.previous();
            }
            
        }
    }

    public boolean sameState(SearchState s2) {
        RamblersState state1 = (RamblersState)s2;
        return (state1.getx() == xCoord && state1.gety() == yCoord);
    }

    public int getx() {
        return xCoord;
    }

    public int gety() {
        return yCoord;
    }

    public String toString() {
        return "X: " + xCoord + ", Y: " + yCoord;
    }
}