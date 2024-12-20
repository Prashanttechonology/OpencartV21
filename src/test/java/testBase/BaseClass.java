package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Logg4j 


public class BaseClass {

	
public Logger logger;	

public static	WebDriver driver;

public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	
	{      
		//loading config.properties file
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());//log4j2
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			     //os
			    
			   if(os.equalsIgnoreCase("windows"))
				   
				   
			   {
				   capabilities.setPlatform(Platform.WIN10) ;
				   
			   }
			   
			   else if (os.equalsIgnoreCase("mac"))
			   {
				   
				   capabilities.setPlatform(Platform.MAC);
			   }
			   else
			   {
				   System.out.println("No matching os");
			   }
			   
			   //browser
			   switch(br.toLowerCase())
			   {
			   case "chrome":capabilities.setBrowserName("chrome");break;			   
			   case "edge":capabilities.setBrowserName("edgechrome");break;
			    default: System.out.println("No matching browser");return;
			   }
			   
			   driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
				   
		}     
	
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		
		{
			switch(br.toLowerCase())
			{
			case"chrome":driver=new ChromeDriver();break;
			case"edge"  :driver=new EdgeDriver();break;
			default:System.out.println("Invalid broswer name");return;
			}
			
			
			
		}
		
		
		
		
	/*	switch(br.toLowerCase())
		{
		case"chrome":driver=new ChromeDriver();break;
		case"edge"  :driver=new EdgeDriver();break;
		default:System.out.println("Invalid broswer name");return;
		}*/
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));//reading url from properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	
	{
		
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException
	{
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesscreenshot=(TakesScreenshot) driver;
		File sourceFile=takesscreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_ "+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
	
	
}
