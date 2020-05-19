package pageClass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	
	
public static final Logger log = Logger.getLogger(DashboardPage.class.getName());
	
	
	
	@FindBy(how=How.ID, using = "welcome")
	WebElement welcome;
	
	
	public DashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getAdmin() { // page method
		
		
		String welcomemsg = welcome.getAttribute("innerHTML");
		
		return welcomemsg;
	}
	
}
