package practise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteProjectsRMG {
	public static void main(String[] args) throws InterruptedException {
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
		
		List<WebElement> allDCB = driver.findElements(By.xpath("//a[@class='delete']"));
		for(WebElement cb:allDCB) {
			cb.click();
			driver.findElement(By.xpath("//input[@value='Delete']")).click();
		}
		
		driver.quit();
		
		
	}

}
