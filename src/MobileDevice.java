/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


/**
 * Class MobileDevice, creates a device, stores its state and behaviour Contains
 * an inner class RentSettings and an instance of class Lab
 */

class MobileDevice {

	String deviceName; // the device name
	int valueTag; // an integer between -100 and 100
	Lab lab; // the lab having this device it its inventory
	RentSettings rs; // rent settings

	/**
	 * Default constructor
	 */
	public MobileDevice() {
	}

	/**
	 * Class constructor receives device name and value tag, creates and
	 * initializes an instance of class MobileDevice
	 * 
	 * @param deviceName - name of mobile device
	 *           
	 * @param valueTag - integer value tag of device
	 *           
	 */
	public MobileDevice(String deviceName, int valueTag) {
		// TODO
		this.deviceName = deviceName;
		this.valueTag = valueTag;
	}

	/**
	 * Sets value for MobileDevice's lab
	 * 
	 * @param lab - new lab this device is in
	 *           
	 */
	public void setLab(Lab lab) {
		// TODO
		this.lab = lab;
	}

	/**
	 * Will set the rent dates; if dates are not valid catch DateFormatException
	 * and return false, if rentDate > dueDate catch RentPeriodException and
	 * return false if one of the exceptions occur there is no RentSettings
	 * object If everything is valid create RentSettings object with given dates
	 * and mark device as rented
	 * 
	 * @param rentDate - start date for rent period
	 *           
	 * @param dueDate - end date for rent period
	 *           
	 * @param lab - lab device is in
	 *           
	 * @return true if operation successful
	 */
	public boolean rentDevice(String rentDate, String dueDate, Lab lab) {
		
		RentSettings rs = null;

		// TODO
		try {
			
			rs = new RentSettings(rentDate, dueDate, lab);
			
		} catch (DateFormatException e) {
			
			System.err.println("Caught DateFormatException: " + e.getMessage());
			rs = null;
			return false;
			
		} catch (RentPeriodException e) {
			
			System.err.println("Caught RentPeriodException: " + e.getMessage());
			rs = null;
			return false;
		}

		this.rs = rs;
		return true;
	}

	/**
	 * Called when rented device has been returned, device is no longer rented,
	 * so destroys the RentSettings object for this device
	 * 
	 * @param lab - lab device is in
	 *           
	 */
	public void returnDevice(Lab lab) {
		// TODO
		if (rs != null && isRented(lab)) {
			rs.borrowed = false;
			rs = null;
		}
	}

	/**
	 * If device is rented finds date when device will be available, If device
	 * not rented return current date
	 * 
	 * @param lab - lab device is in
	 *           
	 * @return the date when this device is available
	 */
	public String availableDate(Lab lab) {
		// TODO
		String result = "";
		
		if (isRented(lab)) {
			result = rs.dueDate;
		} else {
			result = Helper.getCurrentDate();
		}
		return result;
	}

	/**
	 * Checks if device is overdue
	 * 
	 * @return true if the current date is greater than the due date
	 */
	public boolean isDeviceOverdue() {
		// TODO
		boolean result = false;
		String currentDate = Helper.getCurrentDate();
		
		try {
			if (rs != null) {
				if (Helper.timeDifference(currentDate, rs.dueDate) < 0) {
					result = true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception caught in isDeviceOverdue() " + e.getMessage());
			result = false;
		}

		return result;
	}

	/**
	 * Checks if device is rented
	 * 
	 * @param l - lab device is in
	 *           
	 * @return true if borrowed, false if not
	 */
	public boolean isRented(Lab l) {
		// TODO
		boolean rented = true;
		
		if (rs == null) {// if no RentSettings device is not rented
			rented = false;
		} else {// if there is RentSettings device may be rented or not
			rented = rs.borrowed;
		}
		return rented;
	}

	/**
	 * Getter for device RentSettings object
	 * 
	 * @return current RentSettings object
	 */
	public RentSettings getRs() {
		// TODO
		return this.rs;
	}

	/**
	 * Setter for device RentSettings object
	 * 
	 * @param rs
	 */
	public void setRs(RentSettings rs) {
		// TODO
		this.rs = rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO
		if (this == o) {
			return true;
		}
		if (!(o instanceof Object)) {
			return false;
		}

		MobileDevice device = (MobileDevice) o;
		if (!deviceName.equals(device.deviceName)) {
			return false;
		}
		if (valueTag != device.valueTag) {
			return false;
		}
		if (!lab.equals(device.lab)) {
			return false;
		}
		if (!rs.equals(device.rs)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = (deviceName != null ? deviceName.hashCode() : 0);
		result = 31 * result + valueTag;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String s = "";
		// TODO
		if (lab == null) {
			s = deviceName();
		} else {
			if (rs == null) {
				s = "(" + deviceName + ", " + valueTag + " => " + lab.labName + ") ";
			} else {
				s = "(" + deviceName + ", " + valueTag + " => " + lab.labName + ") " + rs.toString();
			}
		}
		return s;
	}

	/**
	 * Prints device name and value tag
	 * 
	 * @return concatenated string containing device name and value tag
	 */
	public String deviceName() {
		return "(" + deviceName + ", " + valueTag + ')';
	}

	/**
	 * Class RentSettings, contained within MobileDevice and created when the
	 * device is rented, stores rent state and behaviour
	 */
	private class RentSettings {

		private String rentDate; // date when the item is requested
		private String dueDate; // date when the item must be returned
		private boolean borrowed = false; // true if the item is rented

		/**
		 * default constructor
		 * 
		 * @throws DateFormatException
		 */
		private RentSettings() throws DateFormatException {
			// TODO
			rentDate = "";
			dueDate = "";
		}

		/**
		 * private constructor, receives rent period dates and lab of device,
		 * creates and initializes a RentSettings object must throw
		 * DateFormatException and RentPeriodException
		 * 
		 * @param rentDate - start date for rent period
		 *           
		 * @param dueDate - end date for rent period
		 *           
		 * @param lab - lab device is in
		 *           
		 * @throws DateFormatException
		 * @throws RentPeriodException
		 */
		private RentSettings(String rentDate, String dueDate, Lab lab) throws DateFormatException, RentPeriodException {
			// TODO

			if (Helper.isValidDate(rentDate) == false || Helper.isValidDate(dueDate) == false) {
				
				throw new DateFormatException();
				
			} else if (Helper.timeDifference(rentDate, dueDate) < 0) {
				
				throw new RentPeriodException();
				
			} else {
				this.rentDate = rentDate;
				this.dueDate = dueDate;
				borrowed = true;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "RentSettings{" + "rentDate='" + rentDate + '\'' + ", dueDate='" + dueDate + '\''
					+ MobileDevice.this.lab.labName + ", borrowed=" + borrowed + '}';
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object o) {
			// TODO
			if (this == o) {
				return true;
			}
			if (!(o instanceof Object)) {
				return false;
			}

			RentSettings rent = (RentSettings) o;
			if (!rentDate.equals(rent.rentDate)) {
				return false;
			}
			if (!dueDate.equals(rent.dueDate)) {
				return false;
			}
			if (borrowed != rent.borrowed) {
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			int result = rentDate != null ? rentDate.hashCode() : 0;
			result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
			result = 31 * result + (borrowed ? 1 : 0);
			return result;
		}
	}
}
