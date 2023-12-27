package practise;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import pojoClass.EmployeeWithObjectInsideObject_PojoSerialDeserial;
import pojoClass.Spouse;

public class II_LearnSeriallisationWithObjectWithinObject {

	@Test
	public void serialise() throws JsonGenerationException, JsonMappingException, IOException {
		File file = new File(".\\src\\test\\resources\\Serialisation\\3_SerialDeserial_ObjectWithinObject.json");
		
		long [] s_phn= {1234,5678};
		Spouse spouseObj = new Spouse("Sim", 25, s_phn);
		//spouseObj.setName("S.A.");	
		long [] phn= {8587851858l,9876543210l};
		EmployeeWithObjectInsideObject_PojoSerialDeserial emp1=new EmployeeWithObjectInsideObject_PojoSerialDeserial("Arpan", 30, phn, spouseObj);
		
		ObjectMapper obm = new ObjectMapper();
		obm.writerWithDefaultPrettyPrinter().writeValue(file, emp1);
	}
	
	
	
	
	@Test(dependsOnMethods = "serialise")
	public void deSerialise() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(".\\src\\test\\resources\\Serialisation\\3_SerialDeserial_ObjectWithinObject.json");
		
		ObjectMapper obm = new ObjectMapper();
		EmployeeWithObjectInsideObject_PojoSerialDeserial data = obm.readValue(file, EmployeeWithObjectInsideObject_PojoSerialDeserial.class);
		
		System.out.println("********Printing Employee1 Details**************");
		System.out.println(data.getEname());
		System.out.println(data.getAge());
		for(long mob:data.getMobNo()) {
			System.out.println(mob);
		}
		
		System.out.println("********Printing Spouse Details**************");
		System.out.println(data.getSpouse().getName());
		System.out.println(data.getSpouse().getAge());
		for(long mob:data.getSpouse().getMobNo()) {
			System.out.println(mob);
		}
		
	}
}
