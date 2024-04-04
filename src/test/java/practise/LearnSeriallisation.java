package practise;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojoClass.Employee_PojoSerialDeserial;


public class LearnSeriallisation {

	@Test
	public void practiseSerialisation() throws Throwable, JsonMappingException, IOException {
		File file = new File(".\\src\\test\\resources\\Serialisation\\1_SerialDeserial.json");
		Employee_PojoSerialDeserial emp1 = new Employee_PojoSerialDeserial("Arpan", 33, 858785185);

		ObjectMapper obm = new ObjectMapper();

		 //obm.writeValue(file, emp1);-prints in single line
		obm.writerWithDefaultPrettyPrinter().writeValue(file, emp1);

	}
	
	@Test
	public void practiseDeSerialisation() throws Throwable, JsonMappingException, IOException {
		File file = new File(".\\src\\test\\resources\\Serialisation\\1_SerialDeserial.json");
		
		ObjectMapper obm = new ObjectMapper();
		Employee_PojoSerialDeserial data = obm.readValue(file, Employee_PojoSerialDeserial.class);
		
		System.out.println(data.getEname());
		System.out.println(data.getAge());
		System.out.println(data.getMobNo());
		
		
	}
	

}
