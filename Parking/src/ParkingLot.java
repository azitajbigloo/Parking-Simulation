// Azita Jafarbigloo - 300123059

// File: ParkingLot.java
// Description: Contains ParkingLot class used to create a ParkingLot object.
import java.text.DecimalFormat;

public class ParkingLot 
{
    // Attributes - all private
       /* Add your attributes here */
    private Car [] spots;        // an array that represents the parking lot spots
                                 // is null when not car is present in the spot, and references a Car 
                                 // object when occupied. 
    private double dailyRevenue; //daily revenue
    private double hourlyRate;   // hourly rate
    private double maxCharge;    // maximum charge
    private int capacity;        // parking lot capacity
    private static DecimalFormat curFmt = new DecimalFormat("$ #0.00");  // For formating currency values
    private String name = null;  // name of the lot, initial value null
    
    // CONSTRUCTOR
    // Sets up the parking lot object.  Note that the spots array is of fixed size (value of capacity)
    // an initially shall contain only null elements.
    // Parameters: 
    //         name - name for the lot
    //         rate - value for hourlyRate
    //         max - value for maxCharge
    //         capacity - value for capacity
     
    public ParkingLot (String name, double rate, double max, int capacity)
    {
    	spots = new Car[capacity];   // set the size of the array spots equal to capacity
    	// make the spots array null
    	for ( int t = 0; t < capacity ; t++) {
    		spots[t] = null;
    	}
    	this.name = name;  
    	this.hourlyRate = rate;
    	this.maxCharge = max;
    	this.capacity = capacity;
    }
    
    // Getter Methods
    public double getDailyRevenue() { return dailyRevenue; }
    public double getHourlyRate() { return hourlyRate; }
    public double getMaxCharge() { return maxCharge; }
    public int getCapacity() { return capacity; }
    
    // Reset method for clearing revenue (i.e. setting to 0)
    public void resetDailyRevenue() {  
   
    	this.dailyRevenue = 0;
    		
    }
    // Reset method for update (also resets the spots array).
    // Parameters:
    //     rate - new rate
    //     max  - new maximum value
    //     capacity - new capacity
    public void resetLot(double rate, double max, int capacity)
    {
    	resetDailyRevenue();         // reset daily revenue, make it 0
    	this.hourlyRate = rate;      // set the new hourly rate
    	this.maxCharge = max;        // set the new maximum charge
    	this.capacity = capacity;    // set the new capacity
    	// make spots array null
    	for(int i = 0; i < capacity ; i++) {
    		if(spots[i] != null)
    		{	spots[i] = null;}
    	}	
    }

    // Method: findSpot
    // Description: searches the spots array of the
    //              parking lot to find the index of the first available parking
    //              spot (i.e. entry is null).  
    //              If the lot is full, it returns the value of capacity.
    private int findSpot()
    {
    	int availableLotIndex = -1;      // the index of the available lot, initial value -1
    	int result = -1;                 // result for storing the result of the method, initial value -1
    	// search for a empty spot in the spots array
    	for (int i = capacity -1; i >= 0 ; i--) {
    		if (spots[i] == null)
    			availableLotIndex = i;
    	}
    	// check if any spot is found
    	if (availableLotIndex == -1)
    	{	result = capacity;}           // no available spot, set the result equal to capacity
    	else
    	{	result = availableLotIndex;}  // empty spot is found, set the result equal to the index of the spot
    	
    	return result;                   // return result
    }

    // Method: findCar -- 
    // Parameters: 
    //     c - reference to a Car object.
    // Description: Searches the spot array for the
    //              given car.  If it is found it, returns the index of the car; if
    //              not it returns the capacity of the parking lot.
    private int findCar(Car c)
    {
    	int carIndex = -1;      // the index of the car's spot, initial value -1
    	int result = -1;        // result for storing the result of the method, initial value -1
    	// search for the car in the spots array
    	for ( int i = 0; i < capacity ; i++) {
    		if(spots[i] == c)
    			carIndex = i;
    	}
    	// check if car was found
    	if (carIndex == -1)
    	{	result = capacity;}  // if not, set the result equal to the capacity
    	else 
    	{	result = carIndex;}  // if it was found set the result equal to the index of the car's spot
    	 
    	return result;           // return result
    }

    // Method: carEnter
    // Parameters: 
    //      c - a reference to a Car object  (car entering lot)
    //      t - a reference to a Time object (entering time)
    // Description: Allows a car to enter the lot and park in an
    //              available spot (see findSpot method). The cars enteringTime
    //              is updated when the car parks in the available spot and outputs
    //              a message that the car has parked in the lot.
    //              If no spot is available, or the car is already in the lot,
    //              then an error message is printed.
    public void carEnter(Car c, Time t)
    {
    	// check if there is empty spot in the lot (spots array)
    	if(findSpot() != capacity) {
    		
    		// check if car is not in the lot (spots array)
    		if (findCar(c) == capacity) {
    			
    			spots[findSpot()] = c;   // park the car in the first available spot
    			c.setEnteringTime(t);    //	entering time updated
    			System.out.print( c + " entered "+ name + " \n");  // show message that car is parked in the spot in the lot
    		}
    		
    		// if the was already in the parking lot, show an error message
    		else 
    		{	System.out.print(t + " - Car " + c.getPlateNumber() +" is already in "+ name + " \n");	}
    	}
    	
    	// if there is no available spot in the parking lot, show an error message
    	else 
    	{	System.out.print(t +" - Lot is full. Car " + c.getPlateNumber() +" cannot enter "+ name +"\n");}
    }

    // Method: carLeave
    // Parameters: 
    //     c - reference to a Car object (car leaving)
    //     t - reference to a Time object (leaving time).
    // Description: Allows the car to leave.  The occupied spot is
    //              set to null, the dailyRevenue is updated.  The parking fee
    //              is computed on an 1/2 hour basis.  If the charge is greater than
    //              maxCharge, then only maxCharge is added to the revenue.
    //              If the the car is not in the parking lot or the leaving time
    //              is earlier than the entering time, then an error message is printed.
    public void carLeave(Car c, Time t)
    {
    	// check if leaving time is after or equal entering time
    	if(t.isAfter(c.getEnteringTime()) || t.isEqual(c.getEnteringTime())) {
    		
    		// check if the car is in the parking lot
    		if(findCar(c) != capacity) {
    			spots[findCar(c)] = null;   // set the car value in the spots array to null, empty its spot
    			dailyRevenue += getParkingCharge(c,t);  // daily revenue updated
    			// show a message that the car left the lot and its parking charge
    			System.out.print(t + " - Car " + c.getPlateNumber() + " left " + name +" and paid " + curFmt.format(getParkingCharge(c,t)) + "\n");
    		}
    		// if the car is not in the lot, show an error message
    		else {
    			System.out.print(t + " - Car " + c.getPlateNumber() + " is not in "+ name + "\n"); }  // if the car is not in the parking lot
    	}
    	//if leaving time is earlier that entering time, show an error message
    	if( t.isBefore(c.getEnteringTime())){
    		System.out.print(t + " - Car "+ c + "is not in " + name + "\n");
    	}	
    }
    
    // Method: print
    // Description: Prints the name of the lot and the contents of the parking lot 
    //             (see the example output). Also gives the total daily revenue.
    public void print()
    {	
    		System.out.print(">----------------"+ name +"----------------< \n");
    		for (int z = 0; z < capacity ; z++) {
    			System.out.print("+----------------");
    		}

    		System.out.print("+\n");
    		for (int z = 0; z < capacity ; z++) {
    			System.out.print("|     ");
    			if(spots[z] == null)    /// if spot it null print empty in the spot
    			{	System.out.print("Empty      ");}
    			else
    			{	System.out.print( spots[z].getPlateNumber()+ "    ");}   // if spot is not null print the cars information(plate number)
    		}
    		System.out.print("|\n");
    		for (int z = 0; z < capacity ; z++) {
    			System.out.print("+----------------");
    		}
    		System.out.print("+\n");
	    	System.out.print("Daily Revenue: " + curFmt.format(getDailyRevenue()) +"\n");   // print the daily revenue
	    	System.out.print(">--------------------------------<\n");
 		
    	}
    
    // Method: toString
    // Description: Allows the references to a parkingLot object to be included
    //              in a String concatenation expression. Simply returns the lot name.
    public String toString()
    {
    	
    	return name;   // return the lot's name in the string format
    }
    /* Method: getParkingCharge
     * Parameters: Car c and Time leaveTime, the same as carLeave(c,t) method
     * Return: returns the parking charge
     * Description: calculate the parking charge for each car based on the entering time, leaving time. 
     * if charge is bigger that the maxCharge returns the maximum charge, if not returns the calculated charge
     */
    private double getParkingCharge(Car c, Time leaveTime) {
    	
    	int enterTimeHour = c.getEnteringTime().getHour();       // hour of entering time
    	int enterTimeMinute = c.getEnteringTime().getMinute();   // minute of entering time
    	int leaveTimeHour = leaveTime.getHour();                 // hour of leaving time
    	int leaveTimeMinute = leaveTime.getMinute();             //minute of leaving time
    	double parkingCharge = 0;                                // parking charge, initial value 0.00 $
    	
    	//check is leaving time is bigger or equal entering time 
    	if (leaveTimeHour >= enterTimeHour) {
    		
    		// if leaving minute - leaving minute is bigger than 30, charge for a full hour + the number of complete hours
    		if(leaveTimeMinute - enterTimeMinute > 30 )  
    		{	parkingCharge = ((leaveTimeHour - enterTimeHour) + 1.0)* hourlyRate;}
    		// if leaving minute - leaving minute is less than 0 but bigger than -30 ( means that the total minutes passed is bigger than 30), charge for a full hour + the number of complete hours(leavehour - enterhour -1)
    		if((leaveTimeMinute - enterTimeMinute > -30) && (leaveTimeMinute - enterTimeMinute < 0 ))
    		{	parkingCharge = ((leaveTimeHour - enterTimeHour))* hourlyRate;}
    		// if leaving minute - leaving minute is less than 30, charge for half an hour + the number of complete hours
    		if(Math.abs(leaveTimeMinute - enterTimeMinute) <= 30  && (leaveTimeMinute - enterTimeMinute > 0)) 
    		{	 parkingCharge = ((leaveTimeHour - enterTimeHour) + 0.5)* hourlyRate;}
    		// if leaving minute - leaving minute is less than -30 (means that the total minutes passed is less than 30), charge for half an hour + the number of complete hours (leavehour - enterhour -1)
    		if(leaveTimeMinute - enterTimeMinute < -30)
    		{	parkingCharge = ((leaveTimeHour - enterTimeHour)- 1.0 + 0.5)* hourlyRate;}
    		// if leaving minute equals leaving minute just charge for the number of complete hours 
    		if(leaveTimeMinute - enterTimeMinute == 0) 
    		{	parkingCharge = (leaveTimeHour - enterTimeHour)* hourlyRate;}
    	}
    	// if parking charge calculate above is more that maximum charge, set the parking charge equal to the maxCharge
    	if (parkingCharge > maxCharge)
    	{	parkingCharge = maxCharge;} 
    	
    	return parkingCharge;      // return parking charge
    }
}
