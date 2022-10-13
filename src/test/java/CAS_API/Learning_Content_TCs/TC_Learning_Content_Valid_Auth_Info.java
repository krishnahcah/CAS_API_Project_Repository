package CAS_API.Learning_Content_TCs;


import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import DataProvider.DP;
import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;

import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_Learning_Content_Valid_Auth_Info extends BaseTest{

	
	@Test(dataProvider = "learningContentValidPayload",dataProviderClass=DP.class)
	public void createLearningContent_CheckResponseWithValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String learningContentDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(learningContentDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postLearningContent);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		APIBasicValidation.statusCodeValidation(response, 200);


		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Vary",FrameworkConstants.Header_Vary_Info);
		
		test.log(LogStatus.INFO, " Response has No body, Hence no Keys and values to check");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	

	@Test(dataProvider = "learningContentEmptyPayload",dataProviderClass=DP.class)
	void createLearningContent_CheckResponseWithEmptyPayload(HashMap<String,Object> payload) throws Exception
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String learningContentDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(learningContentDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postLearningContent);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Sorry! Something went wrong.");
		APIBasicValidation.assertingSingleElementValue(response,"error.code",500);
		
		APIBasicValidation.statusCodeValidation(response, 500);


		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);
		APIBasicValidation.headerValidation(response,"Vary",FrameworkConstants.Header_Vary_Info);
		

		APIBasicValidation.jsonResponseSchemaValidation(response,"500InternalServerErrorJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for Invalid payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}




	//@AfterClass
	public void afterClass()
	{

		test.log(LogStatus.INFO, "****** TC_Learning_Content_Valid_Auth_Info Ended.. ******");
	}

}

