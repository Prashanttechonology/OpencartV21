package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


/*
 Data is valid-login success-test pass-logoout
               login failed-test fail
               
               
Data is invalid-login success-test fail-logout
                 login failed-test pass               
 */

public class TC003_LoginDDT extends BaseClass {

	
	       @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")//getting data provider from other class
	       public void verify_loginDDT(String email,String pwd,String exp)
	
	       {
	    	   
	    	   logger.info("****starting TC_003_LoginDDT****");
	    	   
	    	   try
	    	   {
	    	   //Homepage
	 	      HomePage hp=new HomePage(driver);
	 	      
	 	      hp.clickMyAccount();
	 	      hp.clickLogin();
	 	      
	 	      //LoginPage
	 	      LoginPage lp=new LoginPage(driver);
	 	      
	 	      lp.setEmail(email);
	 	      lp.setPassword(pwd);
	 	      lp.clickLogin();
	 	      
	 	      
	 	      //MyAccountPage
	 	      
	 	      MyAccountPage macc=new MyAccountPage(driver);
	 	       boolean targetPage=macc.isMyAccountPageExists();
	 	     
	 	     	 	     
	 	    /*
	 	    Data is valid-login success-test pass-logoout
	 	                  login failed-test fail
	 	                  
	 	                  
	 	   Data is invalid-login success-test fail-logout
	 	                    login failed-test pass               
	 	    */
	    	   
	    	   
	    	       if(exp.equalsIgnoreCase("Valid"))
	 	     
	    	       {
	    	    	     if(targetPage==true)
	    	    	     {
	    	    	    	 
	    	    	    	 Assert.assertTrue(true);
	    	    	    	 macc.clickLogout();
	    	    	     }
	    	    	     
	    	    	     else
	    	    	     
	    	    	     {
	    	    	    	 
	    	    	    	 Assert.assertTrue(false);
	    	    	     }
	    	    	     	    	    	     
	    	    	     
	    	       }
	 	     
	 	     
	 	     
	    	    if(exp.equalsIgnoreCase("Invalid"))
	    	   
	    	    {
	    	    	if(targetPage==true)
	    	    	{
	    	    		 macc.clickLogout();
		    		     Assert.assertTrue(false);
	    	    	}
	    	    	else
	    	    	{
	    	    		
	    	    		Assert.assertTrue(true);
	    	    	}
	    	    }
	    	   
	    	   
	    	   }catch(Exception e)
	    	   
	    	   {
	    		   Assert.fail();
	    	   }
	    	    logger.info("****Finished TC_003_LoginDDT****");
	    	   
	       }	
	
	       
}
