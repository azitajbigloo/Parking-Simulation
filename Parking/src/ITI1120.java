// Azita Jafarbigloo - 300123059

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;

	/**
	 * The purpose of this class is to provide convenient methods for reading input
	 * from the keyboard.
	 * <P>
	 * Two sets of read methods are provided.
	 * <P>
	 * <ul>
	 * <li> <CODE>readInt()</CODE>, <CODE>readDouble()</CODE>,
	 * <code>readChar()</code>, <code>readBoolean()</code>,
	 * <code>readString()</code>: read one value of the specified type
	 * <li> <CODE>readIntLine()</CODE>,<CODE>readDoubleLine()</CODE>,<CODE>
	 * readCharLine()</CODE>: read all values from a line, create an array of the
	 * proper length, and put the values into the array.
	 * </ul>
	 * <P>
	 * None of these methods allow values of different types to be read from the
	 * same line of input.
	 * <P>
	 * Note that <CODE>Integer.parseInt()</CODE> does not allow a leading + so,
	 * for example, +123 will be rejected. However, the <CODE>double</CODE> reader
	 * does not require a leading 0 so, for example, -.2 will be accepted.
	 * <P>
	 * 
	 * @author Alan Williams
	 */

	public class ITI1120
	{
		private static final BufferedReader keyboard = new BufferedReader(
				new InputStreamReader( System.in ) );

		/**
		 * The private constructor means that instances of this class cannot be
		 * constructed.
		 */
		private ITI1120( )
		{
		}

		/**
		 * Read a single <CODE>int</CODE> value from the keyboard.
		 * <P>
		 * If the user enters a non-<CODE>int</CODE> value, a
		 * {@link NumberFormatException} will be thrown, and the program will
		 * terminate. Typing return without entering any value will also produce
		 * this result.
		 * 
		 * @return An <CODE>int</CODE> containing the value entered from the
		 *         keyboard.
		 */

		public static int readInt( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			return Integer.parseInt( inputString );
		}

		/**
		 * Read a single <CODE>double</CODE> value from the keyboard.
		 * <P>
		 * If the user enters a non-<CODE>double</CODE> value, a
		 * {@link NumberFormatException}will be thrown, and the program will
		 * terminate. Typing return without entering any value will also produce
		 * this result.
		 * 
		 * @return A <CODE>double</CODE> containing the value entered from the
		 *         keyboard.
		 */

		public static double readDouble( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			return Double.parseDouble( inputString );
		}

		/**
		 * Read a single <CODE>boolean</CODE> value from the keyboard.
		 * <P>
		 * If the user enters any variation of the word "true" in upper, lower, or
		 * mixed case, the result will be the <code>boolean</code> value
		 * <code>true</code>. Otherwise, the result will be the
		 * <code>boolean</code> value <code>false</code>.
		 * 
		 * @return A <CODE>boolean</CODE> containing the value entered from the
		 *         keyboard.
		 */

		public static boolean readBoolean( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			// The following will convert the String to a boolean. Any version of
			// the word "true" should result in a true value; all other strings
			// will result in a value of false.

			return Boolean.valueOf( inputString ).booleanValue( );
		}

		/**
		 * Read a single <CODE>char</CODE> value from the keyboard.
		 * <P>
		 * If the types return without entering any character, a
		 * {@link StringIndexOutOfBoundsException} will be thrown, and the program
		 * will terminate. Otherwise, the first character of what the user typed
		 * will be returned as a <code>char</code> value.
		 * 
		 * @return A <CODE>char</CODE> containing the value entered from the
		 *         keyboard.
		 */

		public static char readChar( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			// Return first character from string.

			return inputString.charAt( 0 );
		}

		/**
		 * Read an <B>array</B> of <CODE>double</CODE> values from the keyboard.
		 * <P>
		 * The values must be separated by at least one space as they are entered.
		 * <P>
		 * If the user enters a non-<CODE>double</CODE> value, a
		 * {@link NumberFormatException}will be thrown, and the program will
		 * terminate.
		 * <P>
		 * If all values are legal <CODE>double</CODE> values, a new array is
		 * created. The length of the array is set by counting the number of values
		 * entered by the user. The values are then entered into the corresponding
		 * array positions.
		 * 
		 * @return An <B>array</B> of <CODE>double</CODE> values containing the
		 *         set of values entered from the keyboard.
		 */

		public static double[] readDoubleLine( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			// The StringTokenizer will split the string at each run of
			// white space characters.

			StringTokenizer st = new StringTokenizer( inputString );

			// Find out how many values were entered.

			int numberOfValues = st.countTokens( );

			// Create the array.

			double[] values = new double[numberOfValues];

			// Each token will be a String. Try to convert each token from
			// a String to a double. If any value doesn't convert, the
			// exception will be thrown.

			for ( int index = 0; index < numberOfValues; index = index + 1 )
			{
				// Get the next token.

				String stringValue = st.nextToken( );

				// Convert token to a double, and insert in array.

				values[index] = Double.parseDouble( stringValue );
			}
			return values;
		}

		/**
		 * Read an <B>array</B> of <CODE>int</CODE> values from the keyboard.
		 * <P>
		 * The values must be separated by at least one space as they are entered.
		 * <P>
		 * If the user enters a non-<CODE>int</CODE> value, a
		 * {@link NumberFormatException} will be thrown, and the program will
		 * terminate.
		 * <P>
		 * If all values are legal <CODE>double</CODE> values, a new array is
		 * created. The length of the array is set by counting the number of values
		 * entered by the user. The values are then entered into the corresponding
		 * array positions.
		 * 
		 * @return An <B>array</B> of <CODE>double</CODE> values containing the
		 *         set of values entered from the keyboard.
		 */

		public static int[] readIntLine( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			// The StringTokenizer will split the string at each run of
			// white space characters.

			StringTokenizer st = new StringTokenizer( inputString );

			// Find out how many values were entered.

			int numberOfValues = st.countTokens( );

			// Create the array.

			int[] values = new int[numberOfValues];

			// Each token will be a String. Try to convert each token from
			// a String to an int. If any value doesn't convert, the
			// exception will be thrown.

			for ( int index = 0; index < numberOfValues; index = index + 1 )
			{
				// Get the next token.

				String stringValue = st.nextToken( );

				// Convert token to an int, and insert in array.

				values[index] = Integer.parseInt( stringValue );
			}
			return values;
		}

		/**
		 * Read an <B>array</B> of <CODE>char</CODE> values from the keyboard.
		 * <P>
		 * After the user has entered a string of characters, hitting 'enter'
		 * returns to the calling method. <B>All characters including spaces are put
		 * in the array.</B>
		 * <P>
		 * The String entered by the user is converted to an array of characters of
		 * the appropriate length.
		 * 
		 * @return An <B>array </B> of <CODE>char</CODE> values containing the set
		 *         of characters entered from the keyboard.
		 */

		public static char[] readCharLine( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			// Convert the entire String that was entered to an array
			// of chars.

			return inputString.toCharArray( );
		}

		/**
		 * Read a String from the keyboard.
		 * <P>
		 * 
		 * @return A {@link String} containing the user's input.
		 */

		public static String readString( )
		{
			// Get the user's input string.

			String inputString = getInput( );

			// Return the string that was entered.

			return inputString;
		}

		/**
		 * This method gets the input from the keyboard as a String.
		 * <p>
		 * The intention of this method is to catch the possible {@link IOException}
		 * that could be thrown by the <code>readline()</code> method, so that the
		 * <code>main()</code> method does not have to declare that it throws this
		 * exception.
		 * <p>
		 * If the exception occurs, a stack trace is printed and the program is
		 * terminated.
		 * 
		 * @return A {@link String} with the input typed from the keyboard.
		 */
		private static String getInput( )
		{
			String inputString = null;
			try
			{
				inputString = keyboard.readLine( );
			}
			catch ( IOException e )
			{
				e.printStackTrace( );
				
				// The following may not work real well with Dr. Java
				
				System.exit( 0 );
			}
			return inputString;
		}

	}


