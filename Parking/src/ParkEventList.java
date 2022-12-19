// Azita Jafarbigloo - 300123059

import java.util.Arrays; 
// File: ParkEventList.java
// Description: Contains the ParkEventList class used to create an object which has a list of
//              ParkEvent objects.

public class ParkEventList 
{
	// List of park events (an array of objects)
    ParkEvent [] events = new ParkEvent[0];  // initially set with 0 elements
    
    /*
     * Method: add
     * Parameters: ev - a parking event
     * Returns: nothing.
     * Description: Places the event into the list "events" according
     *              to the value of its event time.
     */
    public void add(ParkEvent ev)
    {
    	// check if events has any objects or not, size is zero means there is no events in the array
    	if(events.length == 0) {
    		events = Arrays.copyOf(events, events.length + 1);     // copy events array to a new array with size + 1
    		events[0] = ev;        // set ev in the first position of the events array 
    		 // System.out.print("         zero          \n ");
    	}
    	// check if events size is bigger than 0 AND if whether ev time is before or equal the first event in the events array
    	else if(events.length != 0 && (ev.getTime().isBefore(events[0].getTime()) || ev.getTime().isEqual(events[0].getTime())))
    	{	addStart(ev);}          // call addStart method to add ev at the start of the events array
    	
    	// check if events size is bigger that 0 AND if whether ev time is after or equal the last object in the events array
    	else if(events.length != 0 && (ev.getTime().isAfter(events[events.length -1 ].getTime()) || ev.getTime().isEqual(events[events.length - 1].getTime())))
    	{	addEnd(ev);}           // call addEnd method to add ev at the end of the events array
    	
    	// if none of the above is true and size is bigger that 0, call the addMiddle method
    	else if(events.length != 0)
    	{	addMiddle(ev);}        // call addMiddle method to add ev at in the middle of the events array, in the right place
    	
    }
    /*Method: addStart
    * Parameters: ev - a parking event, the same as add method
    * Returns: Does not return anything
    * Description: adds an object(event) to the start of(position 0) the events array
    */
    private void addStart(ParkEvent ev) {
    	
    	events = Arrays.copyOf(events, events.length + 1);    //create new array from old array and allocate one more element
    	// move all the objects in the events array to the their next position in the array 
    	for( int i = events.length - 2  ; i >= 0 ; i--) {
    		events[i+1] = events[i]; 
    	}
    	events[0] = ev;  // add ev at position 0
    	}
    
    /*Method: addEnd
     * Parameters: ev - a parking event, the same as add method
     * Returns: Does not return anything
     * Description: adds an object(event) at the end of(position length-1) the events array
     */
    private void addEnd(ParkEvent ev) {
    	
    	events = Arrays.copyOf(events, events.length + 1);    //create new array from old array and allocate one more element
 	    events[events.length - 1] = ev;  // add ev to the last position
    }
    
    /*Method: addMiddel
     * Parameters: ev - a parking event, the same as add method
     * Returns: Does not return anything
     * Description: adds an object(event) in the middle of the events array
     */
    private void addMiddle(ParkEvent ev) {
    	
    	events = Arrays.copyOf(events, events.length + 1);  //create new array from old array and allocate one more element
    	// move the objects after ev (based on time) to the next position in the array and add ev 
    	for( int i = events.length - 2 ; i >= 0 ; i--) {
    		// check if ev time is before events[i] time
    		if( ev.getTime().isBefore(events[i].getTime())) {
    			events[i+1] = events[i]; 
    			events[i] = ev;
    		}
    	}
    }

    /*
     * Method: removeFirstEvent
     * Parameters: none
     * Returns: ev - a parking event or null if events is empty
     * Description: Removes the first event from the "events" list
     *              and returns its reference.
     */
    public ParkEvent removeFirstEvent()
    {
    	ParkEvent result = null;   // an object to store the first objects value 
    	// check for the first object with value not equal to null and make it null
    	for(int i = 0; i < events.length ; i++) {		
    		if(events[i] != null) {
    			result = events[i];
    			events[i] = null;    
    			break;
    		}
    	}
    	return result;   // return the value of the object 
    }

    /*
     * Method: getEvent
     * Parameters: ix - index of event to get
     * Returns: ParkEvent - a reference to a ParkEvent or null if events is empty
     * Description: Returns the reference to the ParkEvent object in "events" at
     *              index ix.
     */
    public ParkEvent getEvent(int ix)
    {
    	return events[ix];
    }
    
    /*
     * Method: print()
     * Description: Displays the list of events.  Notes that using the reference
     *              to an Event object in the System.out.println, will have the
     *              toString method in the Event class executed to create the 
     *              string to be displayed.
     */
     public void print()
    {
    	 System.out.print("--------------------------------------------------- \n");
    	 for( int t = 0; t < events.length ; t++) {
    		 if ( events[t] != null)
    		 { System.out.print(events[t].toString() + "\n");}
    	 }
    	 System.out.print("--------------------------------------------------- \n");
    }

}
