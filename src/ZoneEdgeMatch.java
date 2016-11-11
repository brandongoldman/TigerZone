public class ZoneEdgeMatch{
	

	//Corner values
	boolean cornerA;
	boolean cornerB;
	boolean cornerC;
	boolean cornerD;

	//opposite values
	boolean oppositeAC;
	boolean oppositeBD;

	//public constructor
	public ZoneEdgeMatch(boolean cornerA, 
						 boolean cornerB, 
						 boolean cornerC,
						 boolean cornerD,
						 boolean oppositeAC,
						 boolean oppositeBD){

		this.cornerA = cornerA;
		this.cornerB = cornerB;
		this.cornerC = cornerC;
		this.cornerD = cornerD;

		this.oppositeAC = oppositeAC;
		this.oppositeBD = oppositeBD;

	}






	//Everything below:
		//Getter and Setter methods

	public getCornerA(){
		return cornerA;
	}

	public getCornerB(){
		return cornerB;
	}

	public getCornerC(){
		return cornerC;
	}

	public getCornerD(){
		return cornerD;
	}

	public getOppositeAC(){
		return oppositeAC;
	}

	public getOppositeBD(){
		return oppositeBD;
	}

	public setCornerA(boolean cornerA){
		this.cornerA = cornerA;
	}

	public setCornerB(boolean cornerB){
		this.cornerB = cornerB;
	}

	public setCornerC(boolean cornerC){
		this.cornerC = cornerC;
	}

	public setCornerD(boolean cornerD){
		this.cornerD = cornerD;
	}

	public setOppositeAC(boolean oppositeAC){
		this.oppositeAC = oppositeAC;
	}

	public setOppositeBD(boolean oppositeBD){
		this.oppositeBD = oppositeBD;
	}


}