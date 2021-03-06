/**
* Source file that contains the error handling methods
*
* @author  Jehrick Robertson
* @version 1.0
* @since   06-08-2017
*/

package mandelbrotGenerator;

import javax.swing.JOptionPane;

public class ErrorHandler{
	/**
	 * Static class that provides methods for printing error messages
	 */
	public static void printErrorMessage( String message ) {
		/**
		 * Prints out the error message to the console, including the
		 * function name
		 *
		 * @param	message		Error message to print
		 */
		System.err.println( "Error in "
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
		       	+ ": " + message );
	}

	public static void printErrorDialog( String message ) {
		/**
		 * Sends out a dialog that gives out the error message
		 *
		 * @param	message		Error message to print
		 */
		JOptionPane.showMessageDialog( null, message, "Error",
			JOptionPane.ERROR_MESSAGE );
	}

	public static void printVerboseErrorDialog( String message ) {
		/**
		 * Sends out a dialog that gives out the error message
		 * including the function name
		 *
		 * @param	message		Error message to print
		 */
		JOptionPane.showMessageDialog( null, "Error in "
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
		       	+ ": " + message, "Error",
			JOptionPane.ERROR_MESSAGE );
	}
}
