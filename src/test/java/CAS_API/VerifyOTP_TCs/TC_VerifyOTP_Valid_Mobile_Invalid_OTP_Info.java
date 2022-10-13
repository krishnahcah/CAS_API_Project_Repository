
package CAS_API.VerifyOTP_TCs;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;


public class TC_VerifyOTP_Valid_Mobile_Invalid_OTP_Info extends BaseTest{
	Map<String, String> requestParams =new HashMap<String,String>();
	@BeforeClass
	public void beforeClass()
	{
		//extentTest.info("****** TC_VerifyOTP_Valid_Mobile_Info Starts.. ******");
	}

	@Test
	public void CheckResponseWithValidMobile_InvalidOTP() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		test.log(LogStatus.INFO,"Sending Request with Valid Mobile and Invalid OTP.");
		requestParams.put("mobile","8860005106");
		requestParams.put("otp","232323");
		response = RestAssured.given().
				contentType("application/json").
				body(requestParams).
				when().
				post(API_Endpoint.Endpoints.postVerifyOTP);
		response.then().log().all();

		//System.out.println(response.asPrettyString());
		
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Invalid OTP");
		APIBasicValidation.assertingSingleElementValue(response,"error.code",0);
		
		APIBasicValidation.statusCodeValidation(response, 401);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		test.log(LogStatus.INFO, "Checking response Tags validations for Invalid payload...");
		APIBasicValidation.jsonResponseSchemaValidation(response,"401VerifyOTP_with_WrongOTPJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");


		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	


	@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "TC_VerifyOTP_Valid_Mobile_Invalid_OTP_Info");

	}
}
