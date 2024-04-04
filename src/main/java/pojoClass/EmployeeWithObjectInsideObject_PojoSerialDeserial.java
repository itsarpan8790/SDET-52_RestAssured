	package pojoClass;

public class EmployeeWithObjectInsideObject_PojoSerialDeserial {
	
	// Step:1 Declare Variables Globally
			private String ename;
			private int age;
			private long [] mobNo;
			private Spouse spouse;
			
			// Step 2:Create Constructor to initialise Variables
			public EmployeeWithObjectInsideObject_PojoSerialDeserial(String ename, int age, long [] mobNo, Spouse spouse) {
				super();
				this.ename = ename;
				this.age = age;
				this.mobNo = mobNo;
				this.spouse = spouse;
			}
			
			// Step 3: Create Empty Constructor to trigger Seriallisation and Deserialisation
			public EmployeeWithObjectInsideObject_PojoSerialDeserial() {

			}
			
			// Step 4: Provide getters and Setters

			public String getEname() {
				return ename;
			}

			public void setEname(String ename) {
				this.ename = ename;
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

			public Spouse getSpouse() {
				return spouse;
			}

			public void setSpouse(Spouse spouse) {
				this.spouse = spouse;
			}
			
			
			
			
			
			

}
