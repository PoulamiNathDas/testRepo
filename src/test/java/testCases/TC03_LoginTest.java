package testCases;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testBase.TestBase;
import excelReader.ExcelReader;
import pageClass.LoginPage2;
import screenShot.CaptureScreenshot;


@Listeners(listener.ListenerTest.class)
class TC03_LoginTest extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC03_LoginTest.class.getName());
	
	 int i;
	 ExcelReader excel;
	 CaptureScreenshot screen;
	 LoginPage2 login;
	 String filepath=System.getProperty("user.dir") + "\\Resources\\Data\\";
	 ArrayList<String> login_cred = new ArrayList<String>();
	 String email_id="";
	 String pass="";
	 
    
	@Parameters({"env", "browser"})
    @BeforeTest
	 void setUp(String env, String browser) {
    	
		 	init(env, browser);	  
	}
	
    @Test
	 void loginTest03() {
		
		   excel = new ExcelReader(); //Excel_Reader Class instantiation
		   screen = new CaptureScreenshot();  // ScreenShot class instantiation
		   login = new LoginPage2(driver); // login page class instantiation
		   
		   int row_num = 3;
		   login_cred=excel.getCellData(filepath,"loginData.xlsx", "login",row_num);
		
			email_id=login_cred.get(0);
			pass=login_cred.get(1);
			log.info("Email id for the TC " + row_num + " is: " + email_id);
			log.info("Password for the TC " + row_num + " is: " + pass); 
			
			String errormessage = login.loginInto(email_id, pass);
			
			Assert.assertEquals(errormessage, "Invalid credentials");
			
			screen.getScreenShot(driver, "Test_03_FailedLogin");
			
			excel.updateCellData(filepath,"loginData.xlsx", "login",row_num, errormessage);		
	}


	 
   @AfterTest
   public void endTest() {
   	
   	driver.close();
   	
   }

}