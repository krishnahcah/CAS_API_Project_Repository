package baseTest;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ExtentReportListener;
import utils.File_N_Environment;

@Listeners(ExtentReportListener.class)
public class BaseTest extends ExtentReportListener{

	protected static RequestSpecification httpRequest;
	protected static ResponseSpecification responseSpec;
	protected static Response response;
	protected static String validMobileNumber="8860005106";
	protected static ExtentTest testClass;
	
	protected static Boolean newPatient;
	protected static String validAuthorization;
	@BeforeSuite
	public void baseTest() {
		//httpRequest = new RequestSpecBuilder().setBaseUri(File_N_Environment.readConfigurationFile().get("CAS_Base_URI")).build();
		//httpRequest.header("Accept","application/json");
		//httpRequest.contentType("application/json");
		RestAssured.registerParser("text/html", Parser.JSON);
		RestAssured.baseURI = File_N_Environment.readConfigurationFile().get("CAS_Base_URI");
		
	}

}