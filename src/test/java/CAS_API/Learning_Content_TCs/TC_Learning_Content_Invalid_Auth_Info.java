

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

public class TC_Learning_Content_Invalid_Auth_Info extends BaseTest{

	
	@Test(dataProvider = "learningContentValidPayload",dataProviderClass=DP.class)
	public void CreateLearningContentCheckResponseWithNoAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String learningContentDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").body(learningContentDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postLearningContent);

		// checking response..
				APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
				test.log(LogStatus.INFO,"Checking Status Code..");
				APIBasicValidation.statusCodeValidation(response, 401);

				test.log(LogStatus.INFO,"Checking Response Headers..");
				APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
				APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
				APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

				APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

				test.log(LogStatus.INFO, "Checking response Keys for Invalid payload...");
				APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
				APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
				
				test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(dataProvider = "learningContentValidPayload",dataProviderClass=DP.class)
	public void CreateLearningContentCheckResponseWithInvalidAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String learningContentDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").body(learningContentDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postLearningContent);

		// checking response..
				APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
				test.log(LogStatus.INFO,"Checking Status Code..");
				APIBasicValidation.statusCodeValidation(response, 401);

				test.log(LogStatus.INFO,"Checking Response Headers..");
				APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
				APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
				APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

				APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

				test.log(LogStatus.INFO, "Checking response Keys for Invalid payload...");
				APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
				APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
				
				test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(dataProvider = "learningContentEmptyPayload",dataProviderClass=DP.class)
	public void CreateLearningContentCheckResponseWithInvalidAuth_EmptyPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String learningContentDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").body(learningContentDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postLearningContent);

		// checking response..
				APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
				test.log(LogStatus.INFO,"Checking Status Code..");
				APIBasicValidation.statusCodeValidation(response, 401);

				test.log(LogStatus.INFO,"Checking Response Headers..");
				APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
				APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
				APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

				APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

				test.log(LogStatus.INFO, "Checking response Keys for Invalid payload...");
				APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
				APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
				
				test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}


	
//	@AfterClass
	public void afterClass()
	{

		test.log(LogStatus.INFO, "****** TC_Learning_Content_Invalid_Auth_Info Ended.. ******");
	}

}

