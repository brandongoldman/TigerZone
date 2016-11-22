/**********************************************************************

    Created By: Group N

    Logic:

        This class is suppose to represent the tigers. 
        Properties below will help in the scoring and
        in the placement of the Tigers on specific parts
        of the game board.

***********************************************************************/


public class Tiger{
	
    private int tigerOwner;
    private int tigerPlacement;


    public Tiger(int owner, int placement){
        tigerOwner=owner;
        tigerPlacement=placement;
    }
    //return the owner int of the tiger
    public int getOwner()
    {
        return tigerOwner
    }
    
    
    //detect what location on a tile that the tiger placed
    public int getTigerPlacement(){
        return tigerPlacement;   
    }

    //don't know how this will happen, but screw it 
    public void setTigerPlacement(){
        this.tigerPlacement = tigerPlacement;
    }

    
    
}
