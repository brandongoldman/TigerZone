import java.lang.Exception;

public class NoTigerException extends Exception {

    private int nTigers;
  
    public NoTigerException(int numberOfTigers) {
		this.nTigers = numberOfTigers;
    } 

	public int getTigers() {
    
    	return nTigers;
   	
   	}
   	
 }