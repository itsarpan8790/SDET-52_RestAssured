package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import pojoClass.RMGPojo;

import static io.restassured.RestAssured.*;

public class DDTusingDataProviderAndPojo {

	@DataProvider(name = "Excel")
	public Object[][] dataProviderMethodExcel() throws Throwable {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\DDTRestAssUred.xlsx");
		Sheet sheet = WorkbookFactory.create(fis).getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rowCount][cellCount];

		for (int i = 0; i < rowCount; i++) {

			for (int j = 0; j <= 2; j++) {
				data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}

		}
		
		for (int i = 0; i < rowCount; i++) {
			data[i][cellCount-1] = (int)(sheet.getRow(i).getCell(cellCount-1).getNumericCellValue());
            //here datatype of getNumericCellValue() is Double..thus typcasting to integer so that passed in constructor.
		}

		return data;

	}

	@DataProvider(name = "Hardcoded")
	public Object[][] dataProviderMethod() {
		Object[][] data = new Object[3][4];

		data[0][0] = "Amar_Akbar_Anthony";
		data[0][1] = "Movie";
		data[0][2] = "Ongoing";
		data[0][3] = 1990;

		data[1][0] = "Sachin_Kohli_Dhoni";
		data[1][1] = "Cricket";
		data[1][2] = "Completed";
		data[1][3] = 2000;

		data[2][0] = "Ronaldo_Revaldo_Ronaldinho";
		data[2][1] = "Football";
		data[2][2] = "Completed";
		data[2][3] = 2020;

		return data;

	}

	//@Test(dataProvider = "Excel") or
	@Test(dataProvider = "Hardcoded")
	public void dataDrivenRMG(String createdBy, String projectName, String status, int teamSize) {

		// pre requisites
		JavaUtility jutil = new JavaUtility();
		baseURI = "http://rmgtestingserver";
		port = 8084;
		RMGPojo rpj = new RMGPojo(createdBy, projectName + jutil.getRandomNumber(), status, teamSize);

		// PreConditions
		given().body(rpj).contentType(ContentType.JSON)
				// Actions
				.when().post("/addProject")
				// Validation
				.then().assertThat().statusCode(201).time(Matchers.lessThan(5000l), TimeUnit.MILLISECONDS)
				.contentType(ContentType.JSON);
	}

}
