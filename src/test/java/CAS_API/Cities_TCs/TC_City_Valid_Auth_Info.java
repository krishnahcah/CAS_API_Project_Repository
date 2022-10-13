package CAS_API.Cities_TCs;



import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_City_Valid_Auth_Info extends BaseTest{

	//@BeforeTest
	public void GetCities()
	{

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCities);
		System.out.println(response.asPrettyString());
	}
	
	@Test
	public void CheckCityResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCities);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		APIBasicValidation.assertingSingleElementValue(response,"cities[0]",null);
		// checking response..
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"getCityJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"cities");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	
	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, " ****** TC_City_Valid_Info Ended.. ******");
	}

}

