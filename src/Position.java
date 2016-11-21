public class Position
{
	public int x;
	public int y;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	/*public int hashCode()
	{
		return x * 31 + y;
	}*/
	public int getXPosition(){
		return x;
	}

	public int getYPosition(){
		return y;
	}

	public void setXPosition(int x){
		this.x = x;
	}

	public void setYPosition(int y){
		this.y = y;
	}

	public boolean equals(Position position){
		if(this.x==position.getXPosition() && this.y==position.getYPosition()){
			return true;
		}
		return false;

	}
	/*public boolean equals(Object other){
		if (other == this) {
			return true;
		}
		return false;
	}*/

}
