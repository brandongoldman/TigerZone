/**********************************************************************

    Created By: Group N

    Logic:

        This class is suppose to represent the crocodiles. 
        Properties below will help in the scoring and
        in the placement of the Crocodiles on specific parts
        of the game board.

***********************************************************************/


public class Crocodile{
	
    private int crocodileOwner;
    private int crocodilePlacement;
    private boolean edgeHasRoad;
    private char locationType;


    //Empty Constructor for Testing    
    public Crocodile(){

    }

    //Public Constructor for Full Integration
    public Crocodile(int crocodileOwner, int crocodilePlacement, boolean edgeHasRoad, char locationType){

        this.crocodileOwner = crocodileOwner;
        this.crocodilePlacement = crocodilePlacement;
        this.edgeHasRoad = edgeHasRoad;
        this.locationType = locationType;

    }
    
    //return the owner int of the tiger
    public int getOwner()
    {
        return crocodileOwner;
    }
    
    
    //detect what location on a tile that the tiger placed
    public int getCrocodilePlacement(){
        return crocodilePlacement;   
    }

    //don't know how this will happen, but screw it 
    public void setCrocodilePlacement(){
        this.crocodilePlacement = crocodilePlacement;
    }
    
    //detect whether the tiger is placed on a road type
    public boolean edgeRoad(){
        
        if(crocodilePlacement == 'r'){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
