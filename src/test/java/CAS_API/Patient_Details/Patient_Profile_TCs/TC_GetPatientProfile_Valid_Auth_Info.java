package CAS_API.Patient_Details.Patient_Profile_TCs;



import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;

import baseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.FrameworkConstants;

public class TC_GetPatientProfile_Valid_Auth_Info extends BaseTest{

	//@BeforeTest
	public void getPatientProfile()
	{
		response = given().
				spec(httpRequest).header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getPatientProfile);
		System.out.println(response.asPrettyString());
		
	}
	
	@Test
	public void getPatientProfile_CheckResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getPatientProfile);
		
		Response splashScreenResponse = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getSplashScreen);
		
		//	String ExpectedPictureValue = splashScreenResponse.path("user_info.personal.picture");
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		APIBasicValidation.assertingSingleElementValue(response,"contact.email",splashScreenResponse.path("user_info.personal.email"));
		APIBasicValidation.assertingSingleElementValue(response,"lang.lang_id",1);
		APIBasicValidation.assertingSingleElementValue(response,"nok.first_name","k");
		APIBasicValidation.assertingSingleElementValue(response,"health.height",splashScreenResponse.path("user_info.health.height"));
		APIBasicValidation.assertingSingleElementValue(response,"health.weight",splashScreenResponse.path("user_info.health.weight"));
		APIBasicValidation.assertingSingleElementValue(response,"health.waist_size",splashScreenResponse.path("user_info.health.waist_size"));
		APIBasicValidation.assertingSingleElementValue(response,"health.daily_activity",splashScreenResponse.path("user_info.health.daily_activity"));
		APIBasicValidation.assertingSingleElementValue(response,"health.dob",splashScreenResponse.path("user_info.personal.dob"));
		
		 
		// checking response..
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"getPatientProfileJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"nok");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"first_name");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"last_name");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"relation_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program_nok");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"patient_nok");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"relation");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"mobile");
		
		APIBasicValidation.getKey(new JSONObject(response.asString()),"contact");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"alternate_number");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"state_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"state");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"city_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"email");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"city");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"address");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"locality");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"pincode");
		
		APIBasicValidation.getKey(new JSONObject(response.asString()),"lang");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"lang_id");

		APIBasicValidation.getKey(new JSONObject(response.asString()),"progress");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"overall");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"health");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"contact");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"nok");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"lang");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"dismiss");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	//@AfterClass
	public void afterClass()
	{

		test.log(LogStatus.INFO, "****** TC_GetPatientProfile_Valid_Auth_Info Ended.. ******");
	}

}

