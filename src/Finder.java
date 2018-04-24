/**
 * Last Name: Arsenieva
 * First Name: Olga
 * Student ID: 067871137
 * codeboard UserName: arsenievaolga
 */


/**
 * Class Finder defines methods that can be used elsewhere in the project to
 * find maximum value tags, and to simplify operations
 */
public class Finder {

	/**
	 * Finds the biggest value tag in an array of
	 * 
	 * @param input - array of value tags to search
	 *            
	 * @return biggest value tag
	 */
	public static int findMaximumValueTag(int[] input) {

		int maxElement = -100;
		// TODO
		for (int i = 0; i < input.length; i++) {
			if (input[i] > maxElement) {
				maxElement = input[i];
			}
		}
		return maxElement;
	}

}
