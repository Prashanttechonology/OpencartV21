package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountsRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTEst extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	
	{
		logger.info("****Starting TC001_AccountRegistrationTest****");//not working
		
		   try
		   {	
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("****Clicked on MyAccount Link****");
		
		hp.clickRegister();
		logger.info("****Clicked on Register Link****");
		
		
		AccountsRegistrationPage repage=new AccountsRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		
		repage.setFirstName(randomString().toUpperCase());
		repage.setLastName(randomString().toUpperCase());
		repage.setEmail(randomString()+"@gmail.com");//Randomly genrrated mail
		repage.setTelephone(randomNumber());
		
		String password =randomAlphaNumeric();
		
		repage.setPassword(password);
		repage.setConfirmPassword(password);
		
		repage.setPrivacyPolicy();
		repage.clickContinue();
		
		
	logger.info("Validating Expeced message");	
	String confmsg=repage.getConfirmationMsg();
	Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	      catch(Exception e)	
		   {
		      logger.error("Test failed");
		      logger.debug("Debug logs");
		      Assert.fail();
		   }
		   
		   logger.info("****Finished TC001_AccountRegistrationTest****");
	}
     
	public String randomString()
	{
		
	String	generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	
	

	public String randomNumber()
	{
		
	String	generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	
	public String randomAlphaNumeric()
	{
		
	String	generatedstring=RandomStringUtils.randomAlphabetic(3);
	String	generatednumber=RandomStringUtils.randomNumeric(3);
		return generatedstring+"@"+ generatednumber;
	}
	
}
