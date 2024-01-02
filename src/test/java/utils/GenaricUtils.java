package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GenaricUtils 
{
public String getPropertyValue(String env, String key) 
{
		
		String returnVal = null;
	try {
		FileInputStream fis =new FileInputStream("src/test/resources/"+env+".properties");
		Properties prop=new Properties();
		prop.load(fis);
		returnVal = prop.getProperty(key);
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return returnVal;
}

}
