/**
 * Created by David on 11/26/2016.
 */
import java.util.HashSet;
import java.util.Set;

public class Den {
    Position position;
    Set<Position> neighborhood;
    Tiger tiger;
    private boolean hasTiger;

    public Den(Position pos){
        this.position=pos;
        Position right = new Position(pos.getXPosition() + 1, pos.getYPosition());
        Position left = new Position(pos.getXPosition() - 1, pos.getYPosition());
        Position top = new Position(pos.getXPosition(), pos.getYPosition() + 1);
        Position bottom = new Position(pos.getXPosition(), pos.getYPosition() - 1);
        Position cornerTL=new Position(pos.getXPosition()-1, pos.getYPosition()+1);
        Position cornerTR=new Position(pos.getXPosition()+1, pos.getYPosition()+1);
        Position cornerBL=new Position(pos.getXPosition()-1, pos.getYPosition()-1);
        Position cornerBR=new Position(pos.getXPosition()+1, pos.getYPosition()-1);
        neighborhood.add(right);
        neighborhood.add(left);
        neighborhood.add(top);
        neighborhood.add(bottom);
        neighborhood.add(cornerTL);
        neighborhood.add(cornerTR);
        neighborhood.add(cornerBL);
        neighborhood.add(cornerBR);
        hasTiger=false;
    }

    public void addTiger(Tiger newTiger){
        tiger=newTiger;
        hasTiger=true;
    }

    public boolean getHasTiger(){return hasTiger;}
    public void setHasTiger(boolean hasTiger){this.hasTiger=hasTiger;}

}
