// Azita Jafarbigloo - 300123059

public class TestQ1 
{
	/* Method: main()
	 * Description:  Executes a series of tests for testing the execution of the ParkingLot class.
	 */
	public static void main(String[] args) 
	{
		String [] plates = { "CSI 356", "ITI 193", "ELG 245", "PHY 391", "CEG 109" };
		// Definition of variables
		int ix;                            // index to traverse arrays
		Car [] cars = new Car[plates.length]; // Array of car objects		
		// Create the array of cars
		for(ix = 0; ix < plates.length; ix++)
		{
			cars[ix] = new Car(plates[ix]);
		}
		// Create Parking Lot
		// Charge $ 3.50/hour
		// Maximum: $ 8.00
		// Number of parking spots: 4
		ParkingLot lot = new ParkingLot("Test Lot", 3.5, 8.00, 4);		
		//------------------Enter Leave Tests--------------------//
		System.out.println("\n***********************Enter Leave Tests*********************");
		enterLeaveTest(lot,cars[0],new Time(9,00), new Time(9,20));   // < 20 minutes
		enterLeaveTest(lot,cars[0],new Time(9,00), new Time(10,00));   // 1 hour
		enterLeaveTest(lot,cars[0],new Time(9,10), new Time(11,10));  // 2 hours
		enterLeaveTest(lot,cars[0],new Time(9,30), new Time(10,45));  //  1.15 hours
		enterLeaveTest(lot,cars[0],new Time(9,00), new Time(14,00));   //  maximum charge
		//-------------------Testing changes in lot ------------//
		System.out.println("\n***********************Test Changes In Lot*********************");
		lot.resetLot(lot.getHourlyRate(), lot.getMaxCharge(), lot.getCapacity()); // resets daily revenue
		lot.print();
		lot.carEnter(cars[0], new Time(9,0));   // car enters
		lot.carEnter(cars[1], new Time(9,1));   // car enters
		lot.carEnter(cars[2], new Time(9,2));   // car enters
		lot.carEnter(cars[3], new Time(9,3));   // car enters - lot is full
		lot.print();
		lot.carLeave(cars[1], new Time(10,1));   // car leaves - 2nd spot is empty
		lot.print();
		lot.carLeave(cars[3], new Time(10,3));   // car leaves - 4th spot is empty
		lot.print();
		lot.carEnter(cars[3], new Time(11,3));   // car enters - should be in spot 2.
		lot.print();
		lot.carLeave(cars[0], new Time(11,4));   // car leaves - first spot is empty
		lot.print();
		lot.carEnter(cars[4], new Time(11,5));   // car enters - should be in first spot.
		lot.print();		
		//-------------------Testing Bad Enter - Leave Actions------------//
		System.out.println("\n***********************Bad Enter - Leave Actions*********************");
		lot.resetLot(lot.getHourlyRate(), lot.getMaxCharge(), lot.getCapacity()); // resets daily revenue and clears lot
		lot.print();
		lot.carEnter(cars[0], new Time(9,0));   // car enters
		lot.carEnter(cars[0], new Time(9,0));   // Same car enters
		lot.carLeave(cars[1], new Time(9,30));  // car leaving not present in lot
		lot.carEnter(cars[1], new Time(9,1));   // car enters
		lot.carEnter(cars[2], new Time(9,2));   // car enters
		lot.carEnter(cars[3], new Time(9,3));   // car enters - lot is full
		lot.carEnter(cars[4], new Time(9,4));   // car enters - no room left in lot
	}
	
	/* Method enterLeaveTest
	 * Description: For testing the carEnter, print and carLeave() methods
	 * Parameters:
	 *    lot - reference to a ParkiingLot object
	 *    car - reference to a Car object
	 *    enter - reference to a Time object - gives time a car enters a lot
	 *    leave - reference to a Time object - gives time a car leaves a lot
	 */
	private static void enterLeaveTest(ParkingLot lot, Car car, Time enter, Time leave)
	{
		System.out.println("---------------------------Enter Leave Test-----------------------");
		// Car enters lot
		lot.carEnter(car, enter);
		lot.print();  // Display contents of lot
		// Car leaves lot
		lot.carLeave(car, leave);
		lot.print();
		System.out.println("-------------------------------------------------------------------");
	}

}
