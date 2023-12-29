package com.rmg.testscripts;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtility.BaseClass_API;
import GenericUtility.EndPointsLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
/**
 * Front End-Selenium
 * Backend-API
 */

public class Scene2_CreateProject extends BaseClass_API {

	@Test
	public void createProject() throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8084/");
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		String projName = "Lollypop";
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projName + jUtil.getRandomNumber());
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Arpan");

		WebElement projStatus = driver
				.findElement(By.xpath("//label[text()='Project Status ']/following-sibling::select[@name='status']"));
		Select s = new Select(projStatus);
		s.selectByValue("Created");

		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		//Closing Browser
		Thread.sleep(2);
		driver.quit();
		
		// Extracting pid from Frontend
		String pid = driver.findElement(By.xpath("//tr/td[contains(.,'"+projName+"')]/preceding-sibling::td"))
				.getText();
		System.out.println("----->>"+pid);
		
		//Capturing ExpectedData from API
		Response resp = given().spec(request)
				.when().get(EndPointsLibrary.getSingleProjects+pid);
				
		resp.then().log().all();
		//Capturing
				String expData=rUtil.getJsonData(resp, "projectId");
				System.out.println(expData);
				
				//Validating in DataBase
				String query="select * from project;";
				String actData=dbUtil.readDataFromDBAndValidate(query, 1, expData);
				
				Assert.assertEquals(actData, expData);
				System.out.println("Verified");
				
				
		
		

	}

}
