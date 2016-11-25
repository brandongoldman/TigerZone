/**
 * Created by David on 11/22/2016.
 */
import java.util.HashSet;
import java.util.Set;

public class FeatureArea {
    Set<Tiger> tiger;
    private boolean hasTiger;
    private boolean completed;
    Set<Position> areaCoor;
    Set<Boundary> openBoundary;

    public FeatureArea(){
        areaCoor = new HashSet<Position>();
        openBoundary = new HashSet<Boundary>();
        tiger = new HashSet<Tiger>();
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
    public boolean getHasTiger(){return hasTiger;}
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
    public void setHasTiger(boolean hasTiger){this.hasTiger=hasTiger;}

    public boolean equals(FeatureArea Area){
        return this.areaCoor.containsAll(Area.areaCoor)&&Area.areaCoor.containsAll(this.areaCoor)&&this.openBoundary.containsAll(Area.openBoundary)&&Area.openBoundary.containsAll(this.openBoundary);
    }
}
