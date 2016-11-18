/***********************************

	Created By: Group N

	Logic:

		Association of the Animals are
		based on three integers:
		
		1. Deer (int --> 0)
		2. Boar (int --> 1)
		3. Buffalo (int --> 2)

************************************/

public class Animal {
	

	//Edge Type Variable
	private int animalType;

	//public constructor for class
	public Animal(int animalType){
		if(animalType < 0 || animalType >= 2){
			
			//Should be able to throw an exception

			System.out.println("We are outside the realm of possibilities.");
			return;
		}

		this.animalType = animalType;
	}

	//get Edge characteristic
	public getAnimalType(){
		return edgeType;
	}

	//set Edge characteristic
	public setAnimalType(int animalType){
		this.animalType = animalType;
	}

}