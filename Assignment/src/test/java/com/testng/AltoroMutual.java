package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AltoroMutual {
	
static WebDriver driver;
	
	@Test
	public void beforeclass() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://testfire.net/index.jsp");

	}
	@Test
	private void openLoginPage() {
		WebElement clickLogin = driver.findElement(By.id("LoginLink"));
		clickLogin.click();
	}
	@Test
	private void tryLogIn() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("uid")).sendKeys("admin");
		driver.findElement(By.id("passw")).sendKeys("admin");
		driver.findElement(By.name("btnSubmit")).click();
}
	@Test
	public void viewSummary() {
		String amountToSend = "9876.0";
		Select accNumber = new Select(driver.findElement(By.id("listAccounts")));
		accNumber.selectByValue("800001");
		
driver.findElement(By.id("btnGetAccount")).click();
		
		driver.findElement(By.id("MenuHyperLink3")).click();
		
		Select fromAcc = new Select(driver.findElement(By.id("fromAccount")));
		fromAcc.selectByValue("800000");
		
		Select toAcc = new Select(driver.findElement(By.id("toAccount")));
		toAcc.selectByValue("800001");
		
		driver.findElement(By.id("transferAmount")).sendKeys(amountToSend);
		
		driver.findElement(By.id("transfer")).click();
		
		String getResult = driver.findElement(By.id("_ctl0__ctl0_Content_Main_postResp")).getText();
		
		boolean isValidateTransaction = getResult.indexOf(amountToSend) !=-1? true: false;
		
		if(isValidateTransaction) {
			driver.findElement(By.id("MenuHyperLink2")).click();
			
			String firstTransaction = driver.findElement(By.xpath("//table//tbody//tr[2]//td[5]")).getText();
			String secondTransaction = driver.findElement(By.xpath("//table//tbody//tr[3]//td[5]")).getText();
			
			System.out.println("First transaction = "+firstTransaction+"\nSecond Transaction = "+secondTransaction);
			
		}
		
		driver.findElement(By.id("HyperLink3")).click();
		driver.findElement(By.xpath("//a[@href='feedback.jsp']")).click();
		
		driver.findElement(By.name("email_addr")).sendKeys("admin@admin.in");
		driver.findElement(By.name("subject")).sendKeys("Testing subject");
		driver.findElement(By.name("comments")).sendKeys("Hi, my name is Aminoor Rasith, Comment testing.");
		driver.findElement(By.name("submit")).click();
		
		driver.findElement(By.id("LoginLink")).click();
	}
}
