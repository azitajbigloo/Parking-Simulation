// Azita Jafarbigloo - 300123059

// File: A5Q2.java
// Description: for testing the ParkEvent and ParkEventList classes

public class TestQ2 
{
	/* Method: main
	 * Description: Runs all tests for testing the ParkEvent and ParkEventList classes.
	 */
	public static void main(String[] args) 
	{
		// Set up an array of strings giving plates for the cars
		String [] plates = { "CSI 356", "ITI 193", "ELG 245", "PHY 391", "CEG 109", "CSV 346", "MCG 243", "MAT 234", "CHG 485", "MCB 204" };
		// Definition of variables
		int index;                            // index to traverse arrays
		Car [] cars = new Car[plates.length]; // Array of car objects
		ParkEventList pkEvList;		          // Reference to a park event list
		ParkingLot p1, p2;                    // Two parking lots
		ParkEvent ev;
		
		// Create the array of cars
		for(index = 0; index < plates.length; index = index + 1)
		{
			cars[index] = new Car(plates[index]);
		}
		// Create 2 parking lots with default values.
	    p1 = new ParkingLot("Lot 1", 3.0, 12.0, 4);
	    p2 = new ParkingLot("Lot 2", 3.5, 15.0, 8);
	    // Create park event list object
	    pkEvList = new ParkEventList();
	    
	    //--------------------Adding Events------------------
	    System.out.println("****************Adding Events**********************");
	    pkEvList.print();  // Show empty list
	    ev = new ParkEvent(cars[0],p1,new Time(10,30),ParkEvent.ENTER);
	    pkEvList.add(ev);  // First event added
		System.out.println("Added event: "+ev);
	    pkEvList.print(); 
	    ev = new ParkEvent(cars[1],p2,new Time(9,0),ParkEvent.ENTER);
	    pkEvList.add(ev);  // Add to beginning
		System.out.println("Added event: "+ev);
	    pkEvList.print(); 
	    ev = new ParkEvent(cars[2],p1,new Time(14,0),ParkEvent.ENTER);
	    pkEvList.add(ev);  // Add to end
		System.out.println("Added event: "+ev);
	    pkEvList.print(); 
	    ev = new ParkEvent(cars[0],p1,new Time(13,45),ParkEvent.LEAVE);
	    pkEvList.add(ev);  // Insert
		System.out.println("Added event: "+ev);
	    pkEvList.print(); 
	    ev = new ParkEvent(cars[1],p2,new Time(10,15),ParkEvent.LEAVE);
	    pkEvList.add(ev);  // Insert
		System.out.println("Added event: "+ev);
	    pkEvList.print(); 
	    //-----------------Remove the events from the list
	    System.out.println("\n****************Removing Events**********************");
	    do
	    {
	    	ev = pkEvList.removeFirstEvent();
	    	if(ev != null)
	    	{
	    		System.out.println("Removed event: "+ev);
	    		pkEvList.print();
	    	}
	    } while(ev != null);		
	}

}
