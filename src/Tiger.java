public abstract class Tiger{
	
    private int tigerOwner;
    private int tigerPlacement;
    private boolean edgeHasRoad;
    char locationType;
    
    
    //return the owner int of the tiger
    public int getOwner(){
        
        return tigerOwner
    
    }
    
    
    //detect what location on a tile that the tiger placed
    public int getTigerPlacement(){
        
        return tigerPlacement;
        
    }
    
    //detect whether the tiger is placed on a road type
    public boolean edgeRoad(){
        
        if(tigerPlacement == 'r'){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
