/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class Helper defines methods that can be used elsewhere in the project to
 * simplify operations
 *
 */
public class Helper {

	/**
	 * Checks if a date is valid
	 * 
	 * @param date - date to validate
	 *           
	 * @return true if date is valid, false if not
	 */
	public static boolean isValidDate(String date) {
		boolean valid = true;

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setLenient(false);
		try {
			formatter.parse(date);
		} catch (ParseException e) {
			valid = false;
		}
		return valid;
	}

	/**
	 * Checks if date format is valid
	 * 
	 * @param date - date to validate
	 *          
	 * @throws DateFormatException
	 */
	public static void checkDate(String date) throws DateFormatException {

		if (!Helper.isValidDate(date)) {

			try {
				throw new DateFormatException("Invalid data format " + date + " it should be MM/dd/yyyy");
			} catch (DateFormatException e) {
				e.getMessage();
			}
		}
	}

	/**
	 * Find number of days between two dates
	 * 
	 * @param date1 - first date
	 *           
	 * @param date2 - second date
	 *           
	 * @return number of days between two dates
	 * @throws DateFormatException
	 */
	public static long timeDifference(String date1, String date2) throws DateFormatException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		if (!isValidDate(date1) || !isValidDate(date2)) {
			throw new DateFormatException();
		}

		Date d1 = null;
		Date d2 = null;
		long diffDays = 0;

		try {
			d1 = dateFormat.parse(date1);
			d2 = dateFormat.parse(date2);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);

			return diffDays;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffDays;
	}

	/**
	 * Gets and returns today's date from system
	 * 
	 * @return today's date
	 */
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Prints formatted message about a device that is available in a lab
	 * 
	 * @param md - device to describe
	 *           
	 * @param rentDate - date at which device is/will be available
	 *           
	 * @param lab - lab that contains device to describe
	 *           
	 * @return formatted message string
	 */
	public static String printAvailable(MobileDevice md, String rentDate, Lab lab) {
		return "Device (" + md.deviceName + ", " + md.valueTag + ") is available at " + rentDate + " from lab: "
				+ lab.labName;
	}

	/**
	 * Prints formatted message about a device that is not available at a
	 * certain date
	 * 
	 * @param md - device to describe
	 *           
	 * @param rentDate - date at which device is/will be unavailable
	 *           
	 * @return formatted message string
	 */
	public static String printUnavailable(MobileDevice md, String rentDate) {
		return "Device " + md + " is not available for " + rentDate;
	}

	/**
	 * Prints formatted message about a device that does not exist
	 * 
	 * @param md - device to describe
	 *           
	 * @return formatted message string
	 */
	public static String printNonexistent(MobileDevice md) {
		return "Device " + md + " does not exist! ";
	}

	/**
	 * Receives a date and counts to a new date a number of days in the future
	 * 
	 * @param startDate - date from which to start counting
	 *           
	 * @param days - number of days to the second date
	 *           
	 * @return date in a number of days in the future
	 */
	public static String getFutureDate(String startDate, int days) {

		String[] temp = startDate.split("/");
		int month = Integer.parseInt(temp[0]);
		int day = Integer.parseInt(temp[1]);
		int year = Integer.parseInt(temp[2]);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(year, month - 1, day);

		calendar.add(Calendar.DATE, days);

		String endDate = dateFormat.format(calendar.getTime());

		return endDate;
	}

}
