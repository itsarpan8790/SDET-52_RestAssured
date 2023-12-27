package pojoClass;

public class Spouse {

	// Step:1 Declare Variables Globally
	String name;
	int age;
	long[] mobNo;

	// Step 2:Create Constructor to initialise Variables
	public Spouse(String name, int age, long[] mobNo) {
		super();
		this.name = name;
		this.age = age;
		this.mobNo = mobNo;
	}

	// Step 3: Create Empty Constructor to trigger Seriallisation and Deserialisation
	public Spouse() {

	}

	// Step 4: Provide getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long[] getMobNo() {
		return mobNo;
	}

	public void setMobNo(long[] mobNo) {
		this.mobNo = mobNo;
	}

}
