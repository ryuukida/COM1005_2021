import java.util.*;

public class TerrainState extends SearchState{
    
    private int xCoord;
    private int yCoord;

    public TerrainState(Coords coords) {
        xCoord = coords.getX();
        yCoord = coords.getY();
    }

}