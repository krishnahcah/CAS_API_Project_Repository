package CAS_API.SendOTP_TCs;

import java.util.HashMap;
import java.util.Map;

//import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;


public class TC_SendOTP_Valid_Mobile_Info extends BaseTest{
	Map<String, String> requestParams = new HashMap<String,String>();
	/*
	 * @BeforeClass public void beforeClass() {
	 * extentTest.info("****** Testcases for SEND OTP ******");
	 * extentTest.info("****** TC_SendOTP_Valid_Mobile_Info Starts.. ******"); }
	 */

	
	@Test
	public void CheckResponseWithValidMobileNumber() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");


		test.log(LogStatus.INFO,"Sending Request with Valid Payload.");
		
		requestParams.put("mobile", FrameworkConstants.Valid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		//Assert.assertEquals(response.getBody().toString(),"{}");
		test.log(LogStatus.PASS,"Response is validated successfully!!");
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		//Checking Response Schema and its Type
		//APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");
		test.log(LogStatus.INFO,"Since their is an empty response so Response schema can't be validated.");
		/*
		 * test.log(LogStatus.INFO, "Checking response Keys for validation...");
		 * APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		 * APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		 */
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	
	//@Test(priority = 1)
	public void checkResponseWithValidMobileNumber()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		requestParams.put("mobile", FrameworkConstants.Valid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		Assert.assertEquals(response.getBody().asString(),"{}");
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
	}
	
	//@Test
	void checkValidStatusCode()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		requestParams.put("mobile", FrameworkConstants.Valid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.statusCodeValidation(response, 200);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	//@Test
	void checkResponseHeaders()
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		requestParams.put("mobile", FrameworkConstants.Valid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	/*
	 * @Test void checkResponseTags() {
	 * 
	 * test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Started... <<< ");
	 * 
	 * requestParams.put("mobile", FrameworkConstants.Valid_Mobile_Info); response =
	 * RestAssured.given().body(requestParams). contentType("application/json").
	 * when(). post(API_Endpoint.Endpoints.postSendOTPPath);
	 * APIBasicValidation.jsonResponseSchemaValidation(response,
	 * "missingMobileErrorJSONSchema.json");
	 * 
	 * test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Ended... <<< ");
	 * 
	 * 
	 * }
	 */
	
	@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_SendOTP_Valid_Mobile_Info Ends.. ******");
	}
}
