/**
 * Created by David on 11/25/2016.
 */
import java.util.Set;
import java.util.HashSet;

public class JungleArea {
    Set<Tiger> tiger;
    Set<Position> areaCoor;
    Set<BoundaryJungle> boundary;
    private boolean hasTiger;

    public JungleArea(){
        areaCoor=new HashSet<Position>();
        tiger=new HashSet<Tiger>();
        boundary=new HashSet<BoundaryJungle>();
        hasTiger=false;
    }


    public void addTiger(Tiger newTiger){
        tiger.add(newTiger);
        hasTiger=true;
    }

    public boolean getHasTiger(){return hasTiger;}
    public void setHasTiger(boolean hasTiger){this.hasTiger=hasTiger;}

}
