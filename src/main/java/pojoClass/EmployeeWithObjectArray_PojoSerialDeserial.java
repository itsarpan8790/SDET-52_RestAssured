package pojoClass;

public class EmployeeWithObjectArray_PojoSerialDeserial {
	
	  // Step:1 Declare Variables Globally
		private String ename;
		private int age;
		private long [] mobNo;
		
		// Step 2:Create Constructor to initialise Variables
		public EmployeeWithObjectArray_PojoSerialDeserial(String ename, int age, long[] mobNo) {
			super();
			this.ename = ename;
			this.age = age;
			this.mobNo = mobNo;
		}
		
		// Step 3: Create Empty Constructor to trigger  Deserialisation
		public EmployeeWithObjectArray_PojoSerialDeserial() {

		}
		
		//Step 4: Provide getters and Setters

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
		
		

}
