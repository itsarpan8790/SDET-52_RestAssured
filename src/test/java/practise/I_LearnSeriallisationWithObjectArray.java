package practise;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojoClass.EmployeeWithObjectArray_PojoSerialDeserial;

public class I_LearnSeriallisationWithObjectArray {

	
	@Test
	public void practiseSerialisation() throws Throwable, JsonMappingException, IOException {
		File file = new File(".\\src\\test\\resources\\Serialisation\\2_SerialDeserial_ObjectArray.json");
		
		long [] phnNo= {8785851858l,9876543210l};
		EmployeeWithObjectArray_PojoSerialDeserial emp1 = new EmployeeWithObjectArray_PojoSerialDeserial("Arpan", 30, phnNo);

		ObjectMapper obm = new ObjectMapper();

		// obm.writeValue(file, emp1);
		obm.writerWithDefaultPrettyPrinter().writeValue(file, emp1);

	}
	@Test
	public void practiseDeSerialisation() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(".\\src\\test\\resources\\Serialisation\\2_SerialDeserial_ObjectArray.json");
		
		ObjectMapper obm = new ObjectMapper();
		EmployeeWithObjectArray_PojoSerialDeserial data = obm.readValue(file, EmployeeWithObjectArray_PojoSerialDeserial.class);
		System.out.println(data.getEname());
		System.out.println(data.getAge());
		System.out.println(data.getMobNo()[0]);
		
		System.out.println("**********ForLoopOutput***************");
		
		for(int i=0;i<data.getMobNo().length;i++) {
			System.out.println(data.getMobNo()[i]);
		}
		System.out.println("********ForEachOutput*****************");
		for(long phn:data.getMobNo()) {
			System.out.println(phn);
		}
	}
}
