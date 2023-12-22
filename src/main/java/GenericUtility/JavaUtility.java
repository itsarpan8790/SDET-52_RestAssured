package GenericUtility;

import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random ran = new Random();
		int random = ran.nextInt(100);
		return random;

	}
}
