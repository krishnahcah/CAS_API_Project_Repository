

package CAS_API.SendOTP_TCs;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;

public class TC_SendOTP_Invalid_Mobile_Info extends BaseTest{
	
	Map<String, String> requestParams = new HashMap<String,String>();
	
	
	@Test
	public void CheckResponseWithInvalidMobileNumber() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");


		test.log(LogStatus.INFO,"Sending Request with InValid Payload.");
		
		requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response, "error.message","Sorry! Something went wrong.");
		APIBasicValidation.assertingSingleElementValue(response,"error.code", 500);
		test.log(LogStatus.PASS,"All Responses is validated successfully!!");
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 500);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"500InternalServerErrorJSONSchema.json");

		
		  test.log(LogStatus.INFO, "Checking response Keys for validation...");
		  APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		  APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		  APIBasicValidation.getKey(new JSONObject(response.asString()),"code");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
//	@Test(priority = 1)
	public void checkResponseWithInvalidMobileNumber()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.assertingSingleElementValue(response, "error.message","Sorry! Something went wrong.");
		APIBasicValidation.assertingSingleElementValue(response,"error.code", 500);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
	}
	
	
	//@Test
	void checkInvalidStatusCode()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.statusCodeValidation(response, 500);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");


	}

	//@Test
	void checkResponseHeaders()
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
		
	}

	//@Test
	void checkResponseValidation() throws Exception
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.jsonResponseSchemaValidation(response,"500InternalServerErrorJSONSchema.json");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	//@Test
	void checkResponseKeys() throws Exception
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);
		response = RestAssured.given().body(requestParams).
				contentType("application/json").
				when().
				post(API_Endpoint.Endpoints.postSendOTPPath);
		APIBasicValidation.responseKeyValidationFromJsonObject(response,"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}


	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_SendOTP_Invalid_Mobile_Info Ends.. ******");
	}


}

