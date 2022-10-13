package CAS_API.Home;



import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_Home_Valid_Auth_Info extends BaseTest{

	//@BeforeTest
	public void GetCities()
	{

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getHome);
		System.out.println(response.asPrettyString());
	}
	
	@Test
	public void GetHome_CheckResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCities);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		//APIBasicValidation.assertingSingleElementValue(response,"cities[0]",null);
		// checking response..
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"GetHOMEValidResponseJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"title");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"logo");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"link");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"url");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"format");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"benefits");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"title");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"type");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"extra");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"image");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"item");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"quickLinks");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"progress");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"overall");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"health");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"contact");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"nok");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"lang");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"dismiss");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program_enrollment");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"status");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"reason");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"sections");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"cta");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	

}

