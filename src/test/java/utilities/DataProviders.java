package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];//creted for two dimesion aray
		
		for(int i=1;i<=totalrows;i++)//1   //read data from x1 storing in two dimesional array
			
		{
			
			  for(int j=0;j<totalcols;j++)//o is rows j is col
				  
			  {
				  
				  logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			  }
		}
		
		return logindata;//returning two dimesional array
		
	}
	
	//Data provider 2
	
	//Data provider 3
	
	
	
	
	
	
	
	
	
	
	
	
}
