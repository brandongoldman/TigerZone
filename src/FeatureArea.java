/**
 * Created by David on 11/22/2016.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class FeatureArea {
    private ArrayList<Tiger> tiger;
    private boolean hasTiger;
    private boolean completed;
    Set<Position> areaCoor;
    Set<Boundary> openBoundary;

    public FeatureArea(){
        areaCoor = new HashSet<Position>();
        openBoundary = new HashSet<Boundary>();
        tiger = new ArrayList<Tiger>();
        completed = false;
        hasTiger=false;
    }

    public void addTiger(Tiger newTiger){
        tiger.add(newTiger);
        hasTiger=true;
    }

    public boolean getCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
}
