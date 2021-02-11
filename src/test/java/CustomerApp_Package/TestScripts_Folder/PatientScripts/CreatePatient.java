package CustomerApp_Package.TestScripts_Folder.PatientScripts;
import DataProvider.DP;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;

import static io.restassured.RestAssured.given; //import this
import java.util.HashMap;

import utility.BaseTest;
import utility.Utility;
public class CreatePatient extends BaseTest{

	public static String newID = "";

	@Test(dataProvider="newPatientValidPayload", dataProviderClass=DP.class)
	public void createNewPatient(HashMap<String,Object> patientData) throws JsonProcessingException{
		// Converts ObjectArray to JSON String
		String patientDataInJsonString= Utility.convertObjectArraytoJSONString(patientData);
		//extentTest = extent.createTest("createNewPatient", "createNewPatient TC Started");
		extentTest.info("Input Payload Information : "+patientDataInJsonString);

		//Sending the GET request for a specific booking id and receiving the response
		extentTest.info("Process Started : Creation of New Patient");
		extentTest.info("Sending the POST request to create new Patient");

		response = given().
				spec(httpRequest).
				contentType("application/json").
				body(patientDataInJsonString).
				log().
				body().	
				when().
				post(readConfigurationFile("createPatientURI"));
		System.out.println(response.asPrettyString());
		//Verify the response code
		//AllureLogger.logToAllure("Asserting the response if the status code returned is 201");
		response.then().spec(responseSpec);		

		//To log the response to report
		extentTest.log(Status.INFO,response.toString());
		
		extentTest.log(Status.PASS, "Patient Creation is Succesfull.");  
		extentTest.info(response.toString());
		//To get the newly created Patient
		System.out.println("Account Number : "+response.then().extract().path("x.patient.account_no"));
		newID = response.then().extract().path("x.patient.account_no").toString();
	}	
	
	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is :" + responseBody);
		
	}
	
	@AfterTest
	public void endTest()
	{
		extent.flush();
	}


}
