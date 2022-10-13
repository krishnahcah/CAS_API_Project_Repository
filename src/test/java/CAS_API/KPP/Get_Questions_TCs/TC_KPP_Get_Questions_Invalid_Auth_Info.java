

package CAS_API.KPP.Get_Questions_TCs;


import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;

import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_KPP_Get_Questions_Invalid_Auth_Info extends BaseTest{

	
	@Test(priority = 1)
	public void CheckKPPQuestionsResponseWithInvalidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().
				contentType("application/json").header("Authorization",FrameworkConstants.Invalid_Authorization).
				pathParam("module_id", "3").
				when().
				get(API_Endpoint.Endpoints.KPPGetQuestions);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 401);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(priority = 2)
	public void CheckKPPQuestionsResponseWithMissingAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().
				contentType("application/json").
				pathParam("module_id", "3").
				when().
				get(API_Endpoint.Endpoints.KPPGetQuestions);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 401);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	@AfterClass
	public void afterClass()
	{

		test.log(LogStatus.INFO, "****** TC_KPP_Get_History_Invalid_Auth_Info Ended.. ******");
	}

}

