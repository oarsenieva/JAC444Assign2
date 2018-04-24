/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
/**
 * Main class, contains main method
 *
 */
public class Main {

	
	
    /**
     * This is the main method which creates Labs and tests them. 
     * @param args - unused
     */
    public static void main(String[] args) {
    	
        /* TASK 1 - build labs from files - at least two labs */

        System.out.println("\n\n *" + " TASK 1 " + "*");
        //TODO
        System.out.println("Creating labs from file...\n");
        Labs allLabs = new Labs(7);
       
        allLabs.addDevicesToLab("Newnham", "NewnhamLab.txt");
        allLabs.addDevicesToLab("York", "YorkLab.txt");
        allLabs.addDevicesToLab("Lab1", "Lab1.txt");
        allLabs.addDevicesToLab("Lab2", "Lab2.txt");
        allLabs.addDevicesToLab("Lab3", "Lab3.txt");
        allLabs.addDevicesToLab("Lab4", "Lab4.txt");      
        allLabs.addDevicesToLab("Lab5", "Lab5.txt");  
        
        
        
        /* TASK 2 - ask for a device that is not in any lab inventory */

       System.out.println("\n\n *" + " TASK 2 " + "*");
        //TODO
        //1. creating a non existent device
        MobileDevice deviceNotReal=new MobileDevice("Boeing", 737);
        System.out.println("Searching labs for nonexistent device " + deviceNotReal.deviceName() + "...");
        
        //2. send non existent device to search function, get lab returned 
        Lab find = allLabs.isThereDeviceInLabs(deviceNotReal);
        
        //3 check lab, print appropriate output 
        if(find == null){
        	System.out.println(Helper.printNonexistent(deviceNotReal));
        } else{
        	System.out.println(Helper.printAvailable(deviceNotReal, deviceNotReal.availableDate(find), find));
        }
        
        
        
         /* TASK 3 - ask for a device that is in a lab inventory
         *  issue a rent request and print the device
         *  issue the same rent request and print the device
         *  return the device
         *  issue the rent request with new dates and print the device
         */
        System.out.println("\n\n *" + " TASK 3 " + "*");
        //TODO
        
        //1. creating a real device
        MobileDevice deviceExist = new MobileDevice("Android", 25);
        System.out.println("Searching labs for a real device " + deviceExist.deviceName()+"...");
        
        //2. send real device to search function, get lab returned 
        Lab findReal = allLabs.isThereDeviceInLabs(deviceExist);
        
        //3 check lab, print appropriate output 
        if(findReal == null){
        	
        	System.out.println(Helper.printNonexistent(deviceExist));
        	
        } else{
        	
        	System.out.println(Helper.printAvailable(deviceExist, deviceExist.availableDate(findReal), findReal));
        }
        
        // 4. issue rent request and print
        System.out.println("\n**First rent request issued**");
        
        Lab firstRent=allLabs.rentDeviceAvailable(deviceExist, Helper.getCurrentDate(), Helper.getFutureDate(Helper.getCurrentDate(), 14));
        System.out.println("Rented Device " + firstRent.getDevice(deviceExist).toString());
        	
        //5. reissue rent request and print
       	System.out.println("\n**Same rent request re-issued**");       	
        Lab secondRent=allLabs.rentDeviceAvailable(deviceExist, Helper.getCurrentDate(), Helper.getFutureDate(Helper.getCurrentDate(), 14));
        System.out.println("Rented Device " + secondRent.getDevice(deviceExist).toString());
        	
        //6. return device issue new rent request and print
        System.out.println("\n**Return device and issue new rent request**");        	
        secondRent.returnRentedDevice(deviceExist);
        
        Lab thirdRent=allLabs.rentDeviceAvailable(deviceExist, Helper.getFutureDate(Helper.getCurrentDate(), 20), Helper.getFutureDate(Helper.getCurrentDate(), 28));
        System.out.println("Rented Device " + thirdRent.getDevice(deviceExist).toString());
        
        
        
         /* TASK 4 - ask for the same device in all labs
          * if you can find a lab, rent the device from that lab
          */
        
        //TODO
        System.out.println("\n\n *" + " TASK 4 " + "*");  
        
        System.out.println("Searching labs for existing device " + deviceExist.deviceName() + "\nDevice found in inventory of the following labs:\n");
        
        for (int i = 0; i < allLabs.numberOfLabs; i++){
        	
        	if(allLabs.labs[i].isThereDevice(deviceExist)){//if device found
        		
        		MobileDevice tempDev = allLabs.labs[i].getDevice(deviceExist);
        		String devAvailDate = tempDev.availableDate(allLabs.labs[i]);
        				
        		System.out.println(Helper.printAvailable(tempDev, devAvailDate, allLabs.labs[i]));//print available date
        		allLabs.labs[i].rentRequest(tempDev, devAvailDate, Helper.getFutureDate(devAvailDate, 10));//device found, renting
        	}
        }

        
        /* TASK 5 - calculate maximum value tag for each lab */
        //TODO
        System.out.println("\n\n *" + " TASK 5 " + "*");
        for(int i=0; i<allLabs.numberOfLabs;i++){
        	System.out.println("Maximum value tag for lab " + allLabs.labs[i].labName + " is " + allLabs.labs[i].findMaximumValueTag());
        }
        
        /* TASK 6 - inquire about a device */
        //TODO
        System.out.println("\n\n *" + " TASK 6 " + "*");      
        MobileDevice inquire=new MobileDevice("Android", 25);
        
        for (int i = 0; i < allLabs.numberOfLabs; i++){
        	
        	if(allLabs.labs[i].isThereDevice(inquire)){
        	
        		MobileDevice temp=allLabs.labs[i].getDevice(inquire);
        		
        		System.out.println(Helper.printAvailable(temp, temp.availableDate(allLabs.labs[i]), allLabs.labs[i]));
        		System.out.println(temp.toString());
        		System.out.println( ( temp.isRented(allLabs.labs[i]) ? "Rented" : "Not Rented" ) + ( temp.isDeviceOverdue() ? ", Overdue" : "" ) +"\n");
        	}
        }
        
    }
}
