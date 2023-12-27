package GenericUtility;

import java.util.Random;



public class JavaUtility {
	/**
	 * @author arpan
	 * This method will generate random number(100)
	 */
	public int getRandomNumber() {
		Random ran = new Random();
		int random = ran.nextInt(100);
		return random;

	}
}
