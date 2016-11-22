import java.lang.Exception;

public class NoCrocodileException extends Exception {

    private int nCrocodiles;
  
    public NoCrocodileException(int numberOfCrocodiles) {
		this.nCrocodiles = numberOfCrocodiles;
    } 

	public int getCrocodiles() {
    
    	return nCrocodiles;
   	
   	}	
 }