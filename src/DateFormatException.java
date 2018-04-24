
/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */

 
/**
 * Class DateFormatException creates user defined exception to throw when dates
 * are formatted incorrectly
 */
public class DateFormatException extends Exception {

	/**
	 * Default constructor
	 */
	public DateFormatException() {
	}

	/**
	 * Creates an exception that is thrown when Rent period ends before it
	 * begins
	 * 
	 * @param message - explanation for exception
	 *          
	 */
	public DateFormatException(String message) {
		super(message);
	}
}
