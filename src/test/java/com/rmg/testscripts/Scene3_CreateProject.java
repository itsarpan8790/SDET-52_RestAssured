package com.rmg.testscripts;
/**
 * Create Project-Using API
 * Get the Project-Selenium
 */

import GenericUtility.BaseClass_API;
import GenericUtility.EndPointsLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pojoClass.RMGPojo;

public class Scene3_CreateProject extends BaseClass_API {
	@Test
	public void createProject() throws InterruptedException {
		RMGPojo rp=new RMGPojo("ABG", "Lollypop"+jUtil.getRandomNumber(), "ongoing", 5);
		
		Response resp = given().spec(request).body(rp)
		.post(EndPointsLibrary.createProject);
		resp.then().log().all();
		
		//Capturing pid From Front End
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

		
		
		
	}
	

}
