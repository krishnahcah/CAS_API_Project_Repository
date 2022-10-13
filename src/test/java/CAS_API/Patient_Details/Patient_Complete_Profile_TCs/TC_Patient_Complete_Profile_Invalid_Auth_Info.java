package CAS_API.Patient_Details.Patient_Complete_Profile_TCs;

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

public class TC_Patient_Complete_Profile_Invalid_Auth_Info extends BaseTest{
	@Test(dataProvider = "patientCompleteProfile_Valid_Auth_Valid_Fields",dataProviderClass=DP.class)
	public void PostPatientCompleteProfile_CheckResponseWithNoAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientCompleteProfileDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").body(patientCompleteProfileDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientCompleteProfile);

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

	
	@Test(dataProvider = "patientCompleteProfile_Valid_Auth_Valid_Fields",dataProviderClass=DP.class)
	public void PostPatientCompleteProfile_CheckResponseWithInvalidAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientCompleteProfileDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").body(patientCompleteProfileDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientCompleteProfile);

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

	@Test(dataProvider = "patientCompleteProfile_Valid_Auth_Invalid_Fields",dataProviderClass=DP.class)
	public void PostPatientCompleteProfile_CheckResponseWithInvalidAuth_InvalidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientCompleteProfileDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").body(patientCompleteProfileDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientCompleteProfile);

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

		test.log(LogStatus.INFO, "****** TC_Patient_Complete_Profile_Valid_Auth_And_Data_Info Ended.. ******");
	}

}


