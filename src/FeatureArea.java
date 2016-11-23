/**
 * Created by David on 11/22/2016.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class FeatureArea {
    private Tiger tiger;
    private boolean hasTiger;
    private boolean completed;
    Set<Position> area;
    ArrayList<Boundary> openBoundary;

    public FeatureArea(){
        area = new HashSet<Position>();
        openBoundary = new ArrayList<Boundary>();
        completed = false;
        hasTiger=false;
    }

    public void addTiger(Tiger newTiger){
        tiger=newTiger;
        hasTiger=true;
    }
    
    public boolean getCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
}
