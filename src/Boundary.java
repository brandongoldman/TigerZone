/**
 * Created by David on 11/22/2016.
 */
public class Boundary {
    Position position;
    //1=Top, 2=Right, 3=Bottom, 4=Left
    int edge;

    public int hashCode() {
        int result = position.x;
        result = 80 * result + position.y;
        result = 31 * result + edge;
        return result;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boundary)) return false;
        Boundary boundary = (Boundary) o;
        return  position.x==boundary.position.x && position.y==boundary.position.y && edge==boundary.edge;
    }

    public Boundary(Position position, int edge){
        this.position=position;
        this.edge=edge;
    }


}
