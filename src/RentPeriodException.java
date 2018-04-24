/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


/**
 * Class RentPeriodException creates user defined exception to throw when a rent
 * period error happens
 */
public class RentPeriodException extends Exception {

	/**
	 * default constructor
	 */
	public RentPeriodException() {
	}

	/**
	 * Creates an exception that is thrown when Rent period ends before it
	 * begins
	 * 
	 * @param message - explanation for exception
	 *           
	 */
	public RentPeriodException(String message) {
		super(message);
	}
}
