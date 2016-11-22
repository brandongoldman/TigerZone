/**
 * Created by David on 11/22/2016.
 */
import java.util.HashSet;
import java.util.Set;

public class FeatureArea {
    private Tiger tiger;
    private boolean hasTiger;
    private boolean completed;
    Set<Position> area;
    private Edge typeOfArea;

    public FeatureArea(int type){
        area = new HashSet<Position>();
        typeOfArea=new Edge(type);
        completed = false;
        hasTiger=false;
    }

    public void addTiger(Tiger newTiger){
        tiger=newTiger;
        hasTiger=true;
    }

    public Edge getTypeOfArea() {
        return typeOfArea;
    }
    public boolean getCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
}
