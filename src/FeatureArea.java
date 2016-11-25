/**
 * Created by David on 11/22/2016.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class FeatureArea {
    Set<Tiger> tiger;
    int numOfCrocs;
    private boolean hasTiger;
    private boolean hasCrocodile;
    private boolean hasAnimal;
    private boolean completed;
    Set<Position> areaCoor;
    Set<Boundary> openBoundary;
    ArrayList<Integer> animal;
    Set<Integer> uniqueAnimal;

    public FeatureArea(){
        areaCoor = new HashSet<Position>();
        openBoundary = new HashSet<Boundary>();
        tiger = new HashSet<Tiger>();
        animal = new ArrayList<Integer>();
        uniqueAnimal = new HashSet<Integer>();
        completed = false;
        hasTiger=false;
        hasAnimal=false;
        hasCrocodile=false;
        numOfCrocs=0;
    }

    public void addTiger(Tiger newTiger){
        tiger.add(newTiger);
        hasTiger=true;
    }

    public void addCrocodile(int number){
        numOfCrocs=numOfCrocs+number;
        hasCrocodile=true;
    }

    public boolean getCompleted(){
        return completed;
    }
    public boolean getHasAnimal() { return hasAnimal; }
    public boolean getHasCrocodile() { return hasCrocodile; }
    public boolean getHasTiger(){return hasTiger;}
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
    public void setHasTiger(boolean hasTiger){this.hasTiger=hasTiger;}
    public void setHasAnimal(boolean hasAnimal){this.hasAnimal=hasAnimal;}

    public boolean equals(FeatureArea Area){
        return this.areaCoor.containsAll(Area.areaCoor)&&this.openBoundary.containsAll(Area.openBoundary)&&this.areaCoor.size()==Area.areaCoor.size()&&this.openBoundary.size()==Area.openBoundary.size();
    }

    public static void main(String[] args){
        FeatureArea Test= new FeatureArea();
        Test.uniqueAnimal.add(1);
        Test.uniqueAnimal.add(1);
        Test.uniqueAnimal.add(1);
        Test.uniqueAnimal.add(2);
        Test.uniqueAnimal.add(0);
        Test.uniqueAnimal.add(0);

        Test.animal.add(1);
        Test.animal.add(1);
        Test.animal.add(1);
        Test.animal.add(2);
        Test.animal.add(0);
        Test.animal.add(0);

        for(Integer i : Test.uniqueAnimal){
            System.out.print(i);
        }
        System.out.println();
        for(Integer i : Test.animal){
            System.out.print(i);
        }
        System.out.println();
        System.out.print(Test.tiger.size());
    }
}
