import java.util.*;

public class RamblersSearch extends Search {
    private TerrainMap tmap;
    private RamblersState goalState;

    public TerrainMap getTmap() {
        return tmap;
    }

    public RamblersState getGoalState() {
        return goalState;
    }

    public RamblersSearch(TerrainMap tmap, RamblersState goalState) {
        this.goalState = goalState;
        this.tmap = tmap;
    }

}