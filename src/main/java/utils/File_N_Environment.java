package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class File_N_Environment {

	public static Map<String,String> file_and_Environment = new HashMap<String,String>();
	public static Properties propMain = new Properties();
	public static Properties propPreSet = new Properties();
	
	public static Map<String,String> readConfigurationFile(){
		String environment = "qa";//System.getProperty("env");
		try {
			
			if(environment.equalsIgnoreCase("qa"))
			{
				FileInputStream fisQA=new FileInputStream(System.getProperty("user.dir")+"/Inputs/qa.properties");
				propMain.load(fisQA);
				file_and_Environment.put("CAS_Base_URI", propMain.getProperty("CAS_Base_URI"));
			}
			else if(environment.equalsIgnoreCase("dev"))
			{
				FileInputStream fisDEV=new FileInputStream(System.getProperty("user.dir")+"/Inputs/dev.properties");
				propMain.load(fisDEV);
				file_and_Environment.put("CAS_Base_URI", propMain.getProperty("CAS_Base_URI"));
			}
			else if(environment.equalsIgnoreCase("staging"))
			{
				FileInputStream fisStaging=new FileInputStream(System.getProperty("user.dir")+"/Inputs/staging.properties");
				propMain.load(fisStaging);
				file_and_Environment.put("CAS_Base_URI", propMain.getProperty("CAS_Base_URI"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return file_and_Environment;
	}
	
	public static Map<String,String> getConfigReader()
	{
		if(file_and_Environment==null)
		{
			file_and_Environment=readConfigurationFile();
		}
		return file_and_Environment;
	}
}
