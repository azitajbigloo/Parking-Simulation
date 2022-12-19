// Azita Jafarbigloo - 300123059

// File: Time.java
// Description: The file contains the Time class used to represent time.  The class is based on the Time
//              Class presented in Lab 10.
import java.text.DecimalFormat;

public class Time
{
	// Attributes
    private int hour;    // The time's hour value between 0 and 23
    private int minute;  // The ime's minute value between 0 and 59;
 
    // Constructor
    /* 
     * Sets the time to the specified hour and minute values.
     * <p>
     * The minute value should be between 0 and 59. If the value is 60 or
     * larger, the minutes are taken mod 60, and the hour value is adjusted
     * accordingly. For example, 76 minutes becomes 16 minutes, and 1 hour is
     * added.
     * After adjusting the minute value, the hour value is taken mod 24, so that
     * the hour is between 0 and 23.
     * Paramters: h, Hours value
     *            m, Minutes value
     */
     public Time( int h, int m )
     {
    	  // If minutes value is too large, adjust it
    	  // by mod 60, and add to hours value.
    	  if ( m > 59 )
    	  {
    	   h = h + m / 60; // determine hours to add
    	   m = m % 60; // puts minutes in range
    	  }
    	  else
    	  {
    	   ; // do nothing
    	  }
    	  this.hour = h % 24; // puts hours in range
    	  this.minute = m;
     }
     // Getter Methods     
     public int getHour( ) { return hour; } // Returns the hour value.
     public int getMinute( ) { return minute; } // Returns the minute value. 

     /* Method: isEqual
      * Class method to compare two times for equivalence.
      * Parameter: t, reference to a Time object
      * Returns: true if the the time of this object is equivalent to the time of the object referenced
      *          by t, and false otherwise.
      */
     public boolean isEqual( Time t )
     {
      return ( this.hour == t.hour && this.minute == t.minute );
     }

     /* Method: isBefore
      * Parameter: t - reference to a time object
      * Returns true if the time value of 'this' is strictly before the
      * time value of the Time object referenced by t.
      *     If the two time values are equal, this method will return false.
      *     First, the hours are compared. If this does not determine the result,
      * then the minutes are compared.
      * Returns: True if 'this' is before 't' and false otherwise.
      */
     public boolean isBefore( Time t )
     {
         boolean result;

         // First, check the hours. If they are different, we
         // have the result.

         if ( this.hour < t.hour )
         {
            result = true;
         }
         else
         {
             if ( this.hour > t.hour )
             {
              result = false;
             }
             else
             {
              // The two hours are equal. The result
              // depends on comparing the minutes values.
              result = this.minute < t.minute;
             }
            }
         return result;
     }

     /* Method: isAfter
      * Parameter: t - reference to a time object
      * Returns true if the time value of 'this' is strictly before the
      * time value of the Time object referenced by t.
      *     If the two time values are equal, this method will return false.
      *     Use the isBefore and equals methods to determine the return value of 
      *     this method,
      * Returns: True if 'this' is after 't' and false otherwise.
      */
     public boolean isAfter( Time t )
     {
         boolean result = true;
         // First, check if is before
         if ( this.isBefore(t) == true)
         {
            result = false;
         }
         if ( this.isEqual(t) == true)
         {
            result = false;
         }
         return result;
     }

     /* Method: duration
      * Returns the duration from 'this' to 't'.
      * <p>
      * Here are some sample durations:<br>
      * 14:37 to 14:38 => 0.0166667 hours
      * 14:37 to 14:36 => 23.983333 hours
      * 14:37 to 14:37 => 0 hours
      * 14:37 to 15:37 => 1 hour
      * 15:37 to 14:37 => 23 hours
      * 14:37 to 15:01 => 0.4 hours
      * 14:37 to 17:01 => 2.4 hours
      * 23:59 to 00:01 => 0.033333 hours
      * 
      * @param t Determine the duration from 'this' to 't'
      * @return The duration as a double value that represents the duration
      * in number of hours.
      */
     public double duration( Time t )
     {
         double result;
         int resultHour;
         int resultMinute;

         // Determine the minutes first.  The result may affect the
         // hours duration later.
         
         resultHour = 0;

         // Take difference between two minute values.
         
         resultMinute = t.minute - this.minute;

         // If difference was negative, add 60 minutes to reset the
         // value to between 0 and 59.  However, we took those minutes
         // from the hour value, so subtract 1 from the result hour.
         
         if ( resultMinute < 0 )
         {
             resultMinute = resultMinute + 60;
             resultHour = -1;
         }

         // Now, take difference in result hour, and add to the
         // result hour determined previously during the
         // minutes calculation.
         
         resultHour = resultHour + (t.hour - this.hour);

         // If the result is negative, add 24 hours to reset the
         // value to be between 0 and 24.
         
         if ( resultHour < 0 )
         {
             resultHour = resultHour + 24;
         }

         // Compute duration in units of hours
         
         result = resultHour + resultMinute/60.0;      
         return(result);
     }
     
     // Method: toString()
     // Description: provides a string representation of the time
     //     for the time object.  This method is a standard method
     //     that allows a reference to a Time object to be included
     //     in a concatenation expression: for example, if tm is a
     //     reference to a time object (with the hour=2 and minute=47), then 
     //     the expression
     //            "The time is "+tm
     //     gives the string "The time is 02:47".
     public String toString()
     {
    	 DecimalFormat df = new DecimalFormat("00");
    	 String result = df.format(hour) + ":" + df.format(minute);
    	 return(result);
     }
}
