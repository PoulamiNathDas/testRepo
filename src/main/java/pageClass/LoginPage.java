package pageClass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	
	// Approach 2: Page
	
	@FindBy(how=How.ID, using = "txtUsername")
	WebElement email;
	
	
	@FindBy(id="txtPassword")
	WebElement password;
	
	@FindBy(how = How.ID, using = "btnLogin")
	WebElement btn;
	
	@FindBy(xpath="//span[text()='Invalid credentials']")
	@CacheLookup
	WebElement msgWithCache;
	
	@FindBy(xpath="//span[text()='Invalid credentials']")
	WebElement msgWithoutCache;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String loginInto(String emailId,String pass) {
		
		
		String message = null;
	    email.sendKeys(emailId); 
	    
	    password.sendKeys(pass);
	    
	    btn.click();
	    
	    long withoutCacheStartTime = System.currentTimeMillis();
	    for(int i = 0; i < 1000; i ++)
	    {
	      message= msgWithoutCache.getText();
	    }
	    long withoutCacheEndTime = System.currentTimeMillis();
	    log.info("Time take in seconds Without cache " + ((withoutCacheEndTime - withoutCacheStartTime)/ 1000));
	    
	    long withCacheStartTime = System.currentTimeMillis();
	    for(int i = 0; i < 1000; i ++)
	    {
	      message = msgWithCache.getText();
	    }
	    long withCacheEndTime = System.currentTimeMillis();
	    log.info("Time take in seconds With cache " + ((withCacheEndTime - withCacheStartTime)/ 1000));
	    
	    return message;    
	}
	
	public void loginSuccess(String emailId,String pass) {
		
		email.sendKeys(emailId); 
	    
	    password.sendKeys(pass);
	    
	    btn.click();
	}
}
