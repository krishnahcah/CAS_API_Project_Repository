
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

public class TC_VerifyOTP_Invalid_Empty_Mobile_Empty_OTP_Info extends BaseTest{
	Map<String, String> requestParams = new HashMap<String,String>();



	@Test
	public void CheckResponseWithEmptyMobile_EmptyOTP() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		test.log(LogStatus.INFO,"Sending Request with Empty Mobile and Empty OTP.");
		
		requestParams.put("mobile","");
		requestParams.put("otp","");
		response = RestAssured.given().
				contentType("application/json").
				body(requestParams).
				when().
				post(API_Endpoint.Endpoints.postVerifyOTP);
		response.then().log().all();

		//System.out.println(response.asPrettyString());
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Validations are missing.");
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.mobile[0]","The mobile field is required.");
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.otp[0]","The otp field is required.");
		
		APIBasicValidation.statusCodeValidation(response, 422);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		test.log(LogStatus.INFO, "Checking response Tags validations for Invalid payload...");
		APIBasicValidation.jsonResponseSchemaValidation(response,"422VerifyOTP_ValidationsMissingJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"validate");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"mobile");


		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}








	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_VerifyOTP_Invalid_Empty_Mobile_Info Ends.. ******");
	}


}
