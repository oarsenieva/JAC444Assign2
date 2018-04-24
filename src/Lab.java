/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


import java.util.Iterator;
import java.util.Vector;

/**
 * Class Lab, creates a lab, stores its state and behaviour Contains a vector of
 * instances of class MobileDevice
 */
public class Lab implements MaxTagValue {

	String labName; // lab name
	Vector<MobileDevice> devices; // data structure that keeps all devices

	/**
	 * Class constructor receives lab name, creates and initializes an instance
	 * of class Lab
	 * 
	 * @param labName - name of new lab
	 *            
	 */
	public Lab(String labName) {

		// TODO
		this.labName = labName;
		devices = new Vector<MobileDevice>();
	}

	/**
	 * Adds a device to lab inventory
	 * 
	 * @param md - device to be added
	 *           
	 */
	public void addDevice(MobileDevice md) {

		// TODO
		devices.add(md);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String r = "";
		// TODO
		r = r + "Lab " + labName + "[\n";

		for (int i = 0; i < devices.size(); i++) {
			r = r + " " + devices.get(i).toString() + " \n";
		}
		return r = r + "]";
	}

	/**
	 * searches lab to see if device is in inventory
	 * 
	 * @param md - device to look for
	 *           
	 * @return true if device in inventory, false if not
	 */
	public boolean isThereDevice(MobileDevice md) {

		boolean found = false;
		// TODO

		for (MobileDevice device : devices) {

			if (md.deviceName.equals(device.deviceName) && md.valueTag == device.valueTag) {
				found = true;
			}
		}
		return found;
	}

	/**
	 * returns a specific device stored in lab
	 * 
	 * @param md - device to retrieve
	 *            
	 * @return device stored in lab collection
	 */
	public MobileDevice getDevice(MobileDevice md) {

		MobileDevice result = null;

		for (MobileDevice device : devices) {

			if (md.deviceName.equals(device.deviceName) && md.valueTag == device.valueTag) {
				result = device;
				break;
			}
		}
		return result;
	}

	/**
	 * lets device a device know it has been returned and is no longer rented
	 * 
	 * @param md - device that was returned
	 *           
	 */
	public void returnRentedDevice(MobileDevice md) {

		MobileDevice target = getDevice(md);
		target.returnDevice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see MaxTagValue#findMaximumValueTag()
	 */
	public int findMaximumValueTag() {

		// TODO
		int maxVal = 0;

		for (MobileDevice device : devices) {

			if (device.valueTag > maxVal) {
				maxVal = device.valueTag;
			}
		}
		return maxVal;
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

		Lab lab = (Lab) o;
		if (!labName.equals(lab.labName)) {
			return false;
		}
		if (!devices.equals(lab.devices)) {
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

		int result = labName != null ? labName.hashCode() : 0;
		result = 31 * result + (devices != null ? devices.hashCode() : 0);
		return result;
	}

	/**
	 * Attempts to rent out a device from lab
	 * 
	 * @param wanted - device to be rented
	 *           
	 * @param requestDate - start date for rent period
	 *           
	 * @param dueDate - end date for rent period
	 *           
	 * @return true if device was rented out successfully, false if not
	 */
	public boolean rentRequest(MobileDevice wanted, String requestDate, String dueDate) {

		// TODO
		return wanted.rentDevice(requestDate, dueDate, this);
	}
}
