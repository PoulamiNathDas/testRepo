package testBase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import configReader.ConfigReader;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public WebDriver driver;
	
	public void init(String env, String browser) {
		selectBrowser(browser);
		getUrl(env);
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}


	public void selectBrowser(String browser) {
	if (browser.equalsIgnoreCase("Chrome")){
		String browser_path= ConfigReader.getValueFromPropertyFile("Chrome_Path");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+browser_path);
		driver=new ChromeDriver();
	}
	
	else if (browser.equalsIgnoreCase("Firefox")){
		String browser_path= ConfigReader.getValueFromPropertyFile("Firefox_Path");
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+browser_path);
		driver=new FirefoxDriver();
	}
	
	else {
		log.info("There is no match for the browser type");
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
 }
	public void getUrl(String env) {
		
		if(env.equalsIgnoreCase("dev")) {
		
		}
		
		else if(env.equalsIgnoreCase("qa")) {
			String url= ConfigReader.getValueFromPropertyFile("QAUrl");
			log.info("Navigating to:"+url);
			driver.get(url);
		}
		
		else if(env.equalsIgnoreCase("prod")) {
			
		}
		
		}
}