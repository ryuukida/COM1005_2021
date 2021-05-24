import java.util.*;

public class RamblersSearch extends Search {
    private TerrainMap tmap;
    private RamblersState initState;
    private RamblersState goalState;

    public TerrainMap getTmap() {
        return tmap;
    }

    public RamblersState getGoalState() {
        return goalState;
    }

    public RamblersSearch(TerrainMap tmap, RamblersState initState, RamblersState goalState) {
        this.initState = initState;
        this.goalState = goalState;
        this.tmap = tmap;
    }

}