package pageClass;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage2 {
	
	public static final Logger log = Logger.getLogger(LoginPage2.class.getName());
	
	WebDriver driver;
	
   // Approach 1: Page Object Model
	By email = By.id("txtUsername");
	
	By password = By.id("txtPassword");
	
	By btn = By.id("btnLogin");
	
	By msg = By.xpath("//span[text()='Invalid credentials']");

	
	public LoginPage2(WebDriver driver) {
		this.driver = driver;
	}
	
	public String loginInto(String emailId,String pass) { // page method
		
	
	    driver.findElement(email).sendKeys(emailId);
	    
	    driver.findElement(password).sendKeys(pass);
	    
	    driver.findElement(btn).click();
	    
	    String message = driver.findElement(msg).getText();
	    
	    return message;    
	}
}
