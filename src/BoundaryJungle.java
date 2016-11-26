/**
 * Created by David on 11/25/2016.
 */
import java.util.Set;
import java.util.HashSet;

public class BoundaryJungle {
    Position position;
    Set<Integer> miniTile;

    public BoundaryJungle(Position position, Set<Integer> miniTile){
        this.position=position;
        this.miniTile=new HashSet<Integer>();
        this.miniTile=miniTile;
    }

    public boolean equals(BoundaryJungle other){
        return this.position.x==other.position.x&&this.position.y==other.position.y&&this.miniTile.containsAll(other.miniTile)&&this.miniTile.size()==other.miniTile.size();
    }
}
