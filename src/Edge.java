/***********************************************************************

	Created By: Group N

	Logic:
	
		Association of the Edges are based on three integers:
		
		1. Jungle (int --> 0)
		2. Game Trial (int --> 1)
		3. Lake (int --> 2)

************************************************************************/

public class Edge {
	

	//Edge Type Variable
	private int edgeType;

	//public constructor for class
	public ZoneEdge(int edgeType){
		if(edgeType < 0 || edgeType >= 2){
			System.out.println("We are outside the realm of possibilities.");
		}
		this.edgeType = edgeType;
	}

	//get Edge characteristic
	public getZoneEdgeType(){
		return edgeType;
	}

	//set Edge characteristic
	public setZoneEdgeType(int edgeType){
		this.edgeType = edgeType;
	}

}