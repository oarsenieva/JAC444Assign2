
/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class Labs, creates an instance of labs, stores its state and behaviour
 * Contains an array of instances of class Lab
 */
public class Labs {

	public Lab[] labs; // a collection of labs of type array
	public int numberOfLabs; // number of labs in collection

	/**
	 * Class constructor receives number of labs to store, creates and
	 * initializes an instance of class Labs
	 * 
	 * @param numberOfLabs - number of labs stored in array
	 *           
	 */
	public Labs(int numberOfLabs) {

		// TODO
		this.numberOfLabs = numberOfLabs;
		labs = new Lab[numberOfLabs];

	}

	/**
	 * Creates a lab from file
	 * 
	 * @param labName - name of new lab
	 *           
	 * @param labFileName - file with data for new lab
	 *           
	 * @return new lab created
	 */
	public Lab addDevicesToLab(String labName, String labFileName) {

		Lab lab = buildLabFromFile(labName, labFileName);
		 System.out.println("Lab = " + labName + "\n[\n" + lab + "]");
		return lab;
	}

	/**
	 * Adds devices in the file into new a lab
	 * 
	 * @param labName - name of new lab
	 *           
	 * @param fileName - file with data for new lab
	 *           
	 * @return new lab with devices from file
	 */
	public Lab buildLabFromFile(String labName, String fileName) {

		Lab lab = new Lab(labName);
		MobileDevice md = null;
		String s;

		try (BufferedReader br = new BufferedReader(new FileReader(/*"./Root/"*/ "../" + fileName))) {

			while ((s = br.readLine()) != null) {

				// TODO
				String[] result = s.split(",");// parse string
				String name = result[0];
				int num = Integer.parseInt(result[1]);

				md = new MobileDevice(name, num);// create device
				md.setLab(lab);

				lab.addDevice(md);// add device to lab
			}

			for (int i = 0; i < numberOfLabs; i++) {

				if (labs[i] == null) {
					labs[i] = lab;
					break;
				}
			}

			 System.out.println("Created "+lab);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lab;
	}

	/**
	 * Searches all labs in collection for first instance of a device
	 * 
	 * @param md - device to find
	 *           
	 * @return lab containing device or null if not found
	 */
	public Lab isThereDeviceInLabs(MobileDevice md) {

		// TODO
		for (int i = 0; i < labs.length; i++) {

			if (labs[i].isThereDevice(md) == true)
				return labs[i];
		}
		return null;
	}

	/**
	 * Finds first available instance of mobile device and rents it
	 * 
	 * @param md - device to be rented
	 *           
	 * @param requestDate - start date for rent period
	 *           
	 * @param dueDate - end date for rent period
	 *           
	 * @return lab device was rented from or null
	 */
	public Lab rentDeviceAvailable(MobileDevice md, String requestDate, String dueDate) {

		Lab foundLab = null;
		// TODO
		MobileDevice target = null;// actual device to change

		for (int i = 0; i < labs.length; i++) {// cycle through labs in array

			if (labs[i].isThereDevice(md) == true) {// if a lab's device search function returns true

				// get lab and device
				foundLab = labs[i];
				target = foundLab.getDevice(md);

				if (target != null) {// if device found is not null

					if (target.getRs() != null) {// rent request exists - device may be rented - do extra checks

						if (target.isRented(foundLab) == false && target.isDeviceOverdue() == false) {// if device available create rent request
							foundLab.rentRequest(target, requestDate, dueDate);
							break;
						}

					} else {// no rent request - device is not rented create rent request
						foundLab.rentRequest(target, requestDate, dueDate);
						break;
					}
				}
			}
		}
		return foundLab;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (!(o instanceof Object)) {
			return false;
		}

		Labs l = (Labs) o;
		if (!labs.equals(l.labs)) {
			return false;
		}
		if (numberOfLabs != l.numberOfLabs) {
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

		int result = (labs != null ? labs.hashCode() : 0);
		result = 31 * result + numberOfLabs;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String r = "Labs " + "[\n";

		for (int i = 0; i < numberOfLabs; i++) {
			r = r + labs[i].toString() + " \n";
		}

		return r = r + "]";
	}
}
