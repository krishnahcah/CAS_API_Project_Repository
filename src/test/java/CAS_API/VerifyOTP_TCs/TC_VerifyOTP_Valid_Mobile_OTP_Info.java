package CAS_API.VerifyOTP_TCs;


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


public class TC_VerifyOTP_Valid_Mobile_OTP_Info extends BaseTest{
	String MobileNumber="";
	
	Map<String, String> requestParams=new HashMap<String,String>();
	
	@Test
	public void CheckResponseWithValidMobile_ValidOTP() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		test.log(LogStatus.INFO,"Sending Request with Valid Mobile and Valid OTP.");
		
		requestParams.put("mobile",FrameworkConstants.Valid_Mobile_Info);
		requestParams.put("otp","111111");
		response = RestAssured.given().
				contentType("application/json").
				body(requestParams).
				when().
				post(API_Endpoint.Endpoints.postVerifyOTP);
		response.then().log().all();

		//System.out.println(response.asPrettyString());
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		//APIBasicValidation.assertingSingleElementValue(response,"token","Valid OTP");
		//APIBasicValidation.assertingSingleElementValue(response,"new",0);
		
		APIBasicValidation.statusCodeValidation(response, 200);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"200VerifyOTP_with_Valid_OTP_JSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"token");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"new");
		
		//Set the Value of Valid Authorization
		validAuthorization="Bearer "+response.path("token");
		newPatient=response.path("new");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	/*
	 * @Test(priority=1) void setAccessTokenValue() {
	 * extentTest.info(" >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Started <<< ");
	 * 
	 * JsonPath jsonPath = new JsonPath(response.asString()); String accessToken =
	 * jsonPath.get("token");
	 * 
	 * assertTrue(accessToken.length()>0); validAccessToken=accessToken;
	 * extentTest.info("Access token value has been set.");
	 * 
	 * extentTest.pass("TC:"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Passed !!");
	 * extentTest.info(" >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Ended <<< ");
	 * 
	 * }
	 */
	
	
	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_VerifyOTP_Valid_Mobile_OTP_Info Ends.. ****** ");

	}
}
