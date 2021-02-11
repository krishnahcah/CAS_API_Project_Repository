package utility;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public abstract class BaseTest extends FrameworkUtility {

	protected static RequestSpecification httpRequest;
	protected static ResponseSpecification responseSpec;
	protected static Response response;
	
	
	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest extentTest;
	
	
	@BeforeSuite
	public void setBaseURI() {
	
		/*******************************************************
		Setup and Initialize Extent Reporting
	 ******************************************************/
		
		  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReportFolder/myExtentReport1.html");
		  htmlReporter.config().setDocumentTitle("CAS Automation Report"); // Tile of report
		   htmlReporter.config().setReportName("CAS-API Testing Report"); // name of the report 
		  
		  htmlReporter.config().setTheme(Theme.STANDARD);
		  
		  extent = new ExtentReports();// create ExtentReports and attach reporter(s)
		  extent.attachReporter(htmlReporter);
		  extent.setSystemInfo("Host Name",FrameworkUtility.readConfigurationFile(
		  "CAS_Base_URI"));
		  extent.setSystemInfo("Environment",FrameworkUtility.readConfigurationFile(
		  "Enviroment"));
		 
	/*******************************************************
			End of Extent Reporting Setup
	 ******************************************************/
		/*******************************************************
  	  						Setting up Base URI
		 ******************************************************/
		
		extentTest =extent.createTest("setBaseURI", "Setting Up Base URL for Customer App");
		extentTest.info("Setting Up Base URL for Customer App");
		extentTest.info("The base URI is : "+readConfigurationFile("CAS_Base_URI"));

		
		httpRequest = new RequestSpecBuilder().
				setBaseUri(readConfigurationFile("CAS_Base_URI")).
				build();

		/*******************************************************
    	  				Adding Headers to Base URI
		 ******************************************************/
		httpRequest.header("Authorization", readConfigurationFile("authToken"));
		httpRequest.header("access_token",readConfigurationFile("Access_Token"));
		httpRequest.header("Accept","application/json");


		
		
	}

	/*****************************************************************************************************************/
	//	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}

	/****************************************************************************************************************/
	//	@BeforeClass
	public void beforeClass() {
		/*******************************************************
  	  Configuration of Logger File for Log4j
		 ******************************************************/
		//logger=Logger.getLogger("Customer API-Automation");//added Logger
	//	PropertyConfigurator.configure("Log4j.properties"); //added logger
	//	logger.setLevel(Level.DEBUG);
	}

	/****************************************************************************************************************/	
	//	@AfterClass
	public void afterClass(){

	}

	/************************************************************************************************************************/
	@BeforeMethod
	public void beforeMethod() {
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();        
	}

	//	@AfterMethod
	public void afterMethod() {
		
	}

}
/*****************************************************************************************************************/