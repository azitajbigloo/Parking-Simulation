// Azita Jafarbigloo - 300123059

// Files A5Simulate.java
// Description:  Contains the A5Simulate class that provides a user interface for running
//               simulations of 2 parking lots.  All methods in this class are static.

import java.text.DecimalFormat;
import java.util.Random;

public class TestSimulate 
{
	// Make parking lots class variables
	private static ParkingLot p1;
	private static ParkingLot p2;
	// Use for formatting dollar values
	private static DecimalFormat curFmt = new DecimalFormat("$ ##.00");

	/**
	 * Method: main()
	 * Description:  Overall control of the user interface.  Initially sets up a set of cars, i.e. an
	 *               array of Car objects. Also sets up 2 ParkingLot objects.  Allows the user two types
	 *               of simulations.
	 *                   1) Give a log of parking events that occurred.
	 *                   2) Allows to trace each event in a simulation.
	 *                   3) Change the configuration of the parking lots.
	 */
	public static void main(String[] args) 
	{
		// Set up an array of strings giving plates for the cars
		String [] plates = { "CSI 356", "ITI 193", "ELG 245", "PHY 391", "CEG 109", "CSV 346", "MCG 243", "MAT 234", "CHG 485", "MCB 204" };
		// Definition of variables
		int index;                            // index to traverse arrays
		Car [] cars = new Car[plates.length]; // Array of car objects
		ParkEventList pkEvList;		  		  // Reference to a park event list
		int select;                           // menu selection
		int seed;                             // random number seed
		
		// Create the array of cars
		for(index = 0; index < plates.length; index = index + 1)
		{
			cars[index] = new Car(plates[index]);
		}
		// Interact with the user - loop until indication from user
		// Create 2 parking lots with default values.
	    p1 = new ParkingLot("Lot 1", 3.0, 12.0, 4);
	    p2 = new ParkingLot("Lot 2", 3.5, 15.0, 8);

		do
		{
			// Show Lot configurations
			displayLotConfig(p1);
			displayLotConfig(p2);
			System.out.println("\n1) Show Events Log");
			System.out.println("2) Trace Events");
			System.out.println("3) Change lot configuration");
			System.out.println("4) Exit");
			System.out.print("Enter your choice (1-4): ");
			select = ITI1120.readInt();
			if(select < 1 || select > 4)
				System.out.println("Invalid choice");
			else if( select != 4)
			{
				// Get see for initialising random number generator
				if(select != 3) // Setup required for running simulations 
				{
					// Clear revenues in parking lot
					p1.resetDailyRevenue();
					p2.resetDailyRevenue();
	                seed = newValue("seed",0,Integer.MAX_VALUE);
			        pkEvList = createEventList(cars,seed);
	                // pkEvList.print();   // can be used for debugging purposes
			        if(select == 1)
			        	executeEvents(pkEvList, false);				
			        if(select == 2)
			        	executeEvents(pkEvList, true);
				}
				else  // select == 3
		            setupParkingLots();		        	
			}
		} while(select != 4);
        System.out.println("Terminated");
	}
	
	/* Method: setupParkingLots
	 * Description:  Used to change the configuration of the 2 parking lots.
	 */
	private static void setupParkingLots()
	{
		char ans;
		System.out.print("Do you which to change either lot? (y or n) ");
		ans = getYNanswer();
		if(ans == 'y')
		{
			setupSingleLot(p1);
			setupSingleLot(p2);			
		}
	}
	
	/* Method: setupSingleLot
	 * Description:  For setting the configuration of a single lot.
	 * Parameters:
	 *     lot - reference to a ParkingLot object.
	 */
	private static void setupSingleLot(ParkingLot lot)
	{
		char ans;
		double rate;  // New hourly rate
		double max;   // new maximum charge
		int capacity;  // new capacity
		displayLotConfig(lot);
		System.out.print("Do you wish to change the lot? ( y or n) ");
		ans = getYNanswer();
		if(ans == 'y')
		{
			rate = newValue("hourly rate", 1.0, 20.0);  // with minimum and maximum values
			max = newValue("maximum charge", 3.0, 60.0);
			capacity = newValue("capacity", 2, 10);
			lot.resetLot(rate,max,capacity);	
			System.out.println("New setup for "+lot+"\n"+
			           "    Rate: "+curFmt.format(lot.getHourlyRate())+"\n"+
			           "    Max Charge: "+curFmt.format(lot.getMaxCharge())+"\n"+
	                   "    Capacity: "+lot.getCapacity()+"\n");
			System.out.print("Hit any key to continue ...");
			ITI1120.readCharLine();
		}
		
	}

	/* Method: displayLotConfig
	 * Description: Displays the configuration of a parking lot. 
	 * Parameters:
	 *     lot - reference to a ParkingLot object.
	 */
	private static void displayLotConfig(ParkingLot lot)
	{
		System.out.println("Configuration for "+lot+"\n"+
		           "    Rate: "+curFmt.format(lot.getHourlyRate())+"\n"+
		           "    Max Charge: "+curFmt.format(lot.getMaxCharge())+"\n"+
                "    Capacity: "+lot.getCapacity());		
	    System.out.println("----------------------------------------");
	}
	
	/* Method: newValue
	 * Description: Obtain from the user a real value within the minimum and maximum values.
	 * Parameters:
	 * 	   name: a name for prompting the user
	 *     min: the minimum value 
	 *     max: the maximum value
	 */
	private static double newValue(String name, double min, double max)
	{
		double val;
		do
		{
			System.out.print("Please enter a value for "+name+": ");
			val = ITI1120.readDouble();
			if(val < min || val > max)
				System.out.println("Invalid value, must be between "+min+" and "+max);
			
		} while(val < min || val > max);
		return(val);
	}

	/* Method: newValue
	 * Description: Obtain from the user an integer value within the minimum and maximum values.
	 * Parameters:
	 * 	   name: a name for prompting the user
	 *     min: the minimum value 
	 *     max: the maximum value
	 */
	private static int newValue(String name, int min, int max)
	{
		int val;
		do
		{
			System.out.print("Please enter a value for "+name+": ");
			val = ITI1120.readInt();
			if(val < min || val > max)
				System.out.println("Invalid value, must be between "+min+" and "+max);
			
		} while(val < min || val > max);
		return(val);
	}
	/* Method: getYNanswer
	 * Description: Obtain from the user y (yes) or n (no) answer from the user.
	 * Return value: Character 'y' or 'n'.
	 */
	private static char getYNanswer()
	{
		char ans = '\0';
		while(ans != 'y' && ans != 'n')
		{
			ans = ITI1120.readChar();
			if(ans != 'y' && ans != 'n')
			{
				System.out.print("Please answer y or n: ");
			}
		}
		return(ans);
	} 

	/* Method: createEventList
	 * Description:  Generates an array of ParkingEvent objects.  The array is ordered accoding to the
	 *               event times of each ParkEvent object.  The event times and parking lot are detemined
	 *               randomly.  Each car in the car array enters and leaves one of the parking lots.
	 * Parameters:
	 *     cars: reference to an array of Car objects
	 *     seed: a seed for the random number generator
	 */
	private static ParkEventList createEventList(Car [] cars, int seed)
	{
		int ix;   // Index for scanning cars array
	    ParkEventList pkEvList = new ParkEventList();  // The Parking Event List
	    int enterTime, leaveTime;  // times a car enters and leaves a parking lot in minutes after 9am
	    ParkEvent enterEv, leaveEv;   // reference to parking events
	    ParkingLot plot;  // reference to parking lot.

	    // Setup the list of parking events
	    // Parking time is between 9am and 11pm, thus a total of 14 hours (= 840 minutes)
	    // Must enter at least by 10pm, thus enter time < 780 minutes after 9am
	    Random rnd = new Random(seed);  // Random number generator
		for(ix = 0; ix < cars.length; ix++)
		{
			// Enter and leave times choosen randomly.
			enterTime = (int) (rnd.nextDouble() * 780);  // Enter time before 10pm
			leaveTime = (int) (enterTime + (rnd.nextDouble() * (840-enterTime)));
			if(rnd.nextDouble() < 0.5) // Select parking lot randomly
			{
				plot = p1;
			}
			else
			{
				plot = p2;
			}
			// Create the ParkingEvent objects and add to pkEvList
			enterEv = new ParkEvent(cars[ix], plot, new Time(9,enterTime), ParkEvent.ENTER );			
			leaveEv = new ParkEvent(cars[ix], plot, new Time(9,leaveTime), ParkEvent.LEAVE );
			// The add will place the event in the list according to the time of the event.
			pkEvList.add(enterEv);
			pkEvList.add(leaveEv);
		}
		return(pkEvList);
	}

	/* Method: executeEvents
	 * Description: this method executes the events in the given ParkEventList object.  Allows
	 *              the possibility of tracing the execution one event at a time.
	 * Parameters:
	 *     evList:  reference to a ParkEventList array
	 *     trace:   set to true for tracing the execution and false otherwise.
	 */	
	private static void executeEvents(ParkEventList evList, boolean trace)
	{
		ParkEvent ev;  // Reference to a parking event
	    // Let's execute the events
		ev = evList.removeFirstEvent();
		//evList.print();   // Prints complete list - can be used for debugging
	    while(ev != null)
	    {
	    	if(ev.isEnterEvent())
	    	{ ev.getParkingLot().carEnter(ev.getCar(), ev.getTime()); }
	    	else
	    	{
	    		if(ev.isLeaveEvent())
	    		{ ev.getParkingLot().carLeave(ev.getCar(), ev.getTime()); }
	    		else
	    		{ System.out.println("Invalid event type for event "+ev.getCar()); }
	    	}	
	    	// Display results of event
	    	if(trace)
	    	{
		    	p1.print();
		    	p2.print();
		    	System.out.println("--------------------------------------");
		    	System.out.println("Press any key to go to next event");
		    	ITI1120.readCharLine();	    		
	    	}
			ev = evList.removeFirstEvent();
	    }
	    if(!trace)  // If not tracing give the status of each lot at the end of the simulation.
	    {
	    	p1.print();
	    	p2.print();
	    }
	}	
}

