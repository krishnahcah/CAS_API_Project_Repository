
package CAS_API.CaretakerRelations_TCs;



import static io.restassured.RestAssured.given;


import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_CareTaker_Invalid_Auth_Info extends BaseTest{

	//@BeforeClass
	public void beforeClass()
	{
		test.log(LogStatus.INFO, "****** TC_CareTaker_Invalid_Auth_Info Started.. ******");

	}
	//@BeforeTest
	public void GetCaretakerRelationList()
	{

		response = given().
				spec(httpRequest).header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCareTakerRelationList);
		System.out.println(response.asPrettyString());
	}

	@Test(priority = 1)
	public void CheckCareTakerResponseWithInvalidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCareTakerRelationList);
		
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
	public void CheckCareTakerResponseWithMissingAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCareTakerRelationList);
		
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

	


	//@AfterClass
	public void afterClass()
	{
		//test.log(LogStatus.INFO, "****** TC_CareTaker_Invalid_Auth_Info Ended.. ******");

	}

}

