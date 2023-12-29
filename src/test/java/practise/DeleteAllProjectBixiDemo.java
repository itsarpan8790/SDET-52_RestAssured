package practise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteAllProjectBixiDemo {
	public static void main(String[] args) throws InterruptedException {
		HashMap<String, String> hmap = new LinkedHashMap<String, String>();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://rmgtestingserver/domain/Online_Banking_System/");

		driver.findElement(By.linkText("Staff Login")).click();
		driver.findElement(By.xpath("//input[@name='staff_id']")).sendKeys("210001");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		driver.findElement(By.xpath("//input[@name='staff_login-btn']")).click();

		driver.findElement(By.xpath("//input[@name='viewdet']")).click();

		List<WebElement> allTR = driver.findElements(By.xpath("//tbody/tr"));
		for (int i = 1; i < 50; i++) {

			String custId = driver.findElement(By.xpath("//tbody/tr/td[text()='" + i + "']/following-sibling::td[2]"))
					.getText();

			String acNo = driver.findElement(By.xpath("//tbody/tr/td[text()='" + i + "']/following-sibling::td[3]"))
					.getText();

			hmap.put(acNo, custId);

		}
		driver.findElement(By.name("home")).click();

		// Delete Customer
		driver.findElement(By.name("del_cust")).click();

		for (Entry<String, String> map : hmap.entrySet()) {

			driver.findElement(By.name("Cust_ac_no")).sendKeys(map.getKey());
			driver.findElement(By.name("Cust_ac_Id")).sendKeys(map.getValue());
			driver.findElement(By.name("reason")).sendKeys("xyz");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='delete']")).click();
			Thread.sleep(2000);
			try {
				//wait.until(ExpectedConditions.alertIsPresent());
				//driver.switchTo().alert().accept();
			} catch (Exception e) {

			}
			System.out.println(map.getKey() + "--->" + map.getValue() + " Deleted");

		}
		System.out.println("All customers flushed");
		driver.quit();

	}
}
