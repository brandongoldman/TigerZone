/**
 * Created by David on 11/22/2016.
 */
public class Boundary {
    Position position;
    //1=Top, 2=Right, 3=Bottom, 4=Left
    int edge;

    public Boundary(Position position, int edge){
        this.position=position;
        this.edge=edge;
    }
}
