

package CAS_API.KPP.Post_Tracking_TCs;


import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;

import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_KPP_Save_Tracking_Valid_Auth_Info extends BaseTest{

	
	@Test(priority = 1)
	public void CheckKPPSaveTrackingResponseWithValidAuth_ValidBody() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().
				contentType("application/json").header("Authorization",validAuthorization).
				pathParam("track_id", "3").body("{ \"answer[]\" : 3}").
				when().
				post(API_Endpoint.Endpoints.postSaveTracking);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		//APIBasicValidation.assertingSingleElementValue(response,"",null);
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);

		//Checking Response Schema and its Type
		//APIBasicValidation.jsonResponseSchemaValidation(response,"500InternalServerErrorJSONSchema.json");
		
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(priority = 2)
	public void CheckKPPSaveTrackingResponseWithValidAuth_NoBody() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().
				contentType("application/json").header("Authorization",validAuthorization).
				pathParam("track_id", "3").
				when().
				post(API_Endpoint.Endpoints.postSaveTracking);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Sorry! Something went wrong.");
		APIBasicValidation.assertingSingleElementValue(response,"error.code",500);
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 500);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);
		APIBasicValidation.headerValidation(response,"Vary",FrameworkConstants.Header_Vary_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"500InternalServerErrorJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	

	//@AfterClass
	public void afterClass()
	{

		test.log(LogStatus.INFO, "****** TC_KPP_Get_History_Invalid_Auth_Info Ended.. ******");
	}

}

