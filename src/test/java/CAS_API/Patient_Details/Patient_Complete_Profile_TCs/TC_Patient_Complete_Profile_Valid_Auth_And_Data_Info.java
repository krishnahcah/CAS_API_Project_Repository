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

public class TC_Patient_Complete_Profile_Valid_Auth_And_Data_Info extends BaseTest{

	
	@Test(dataProvider = "patientCompleteProfile_Valid_Auth_Valid_Fields",dataProviderClass=DP.class)
	public void postPatientCompleteProfile_CheckResponseWithValidAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientCompleteProfileDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(patientCompleteProfileDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientCompleteProfile);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		APIBasicValidation.statusCodeValidation(response, 200);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"patientCompleteProfile_Valid_Auth_Valid_FieldsJSONSchema.json");


		test.log(LogStatus.INFO,"Checking response KEYS validations for valid payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"personal");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"first_name");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"last_name");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"gender");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"email");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"mobile");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"picture");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"dob");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"created_at");
		
		
		APIBasicValidation.getKey(new JSONObject(response.asString()),"health");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"height");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"weight");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"waist_size");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"daily_activity");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"stride");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"run");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"walk");
		
		APIBasicValidation.getKey(new JSONObject(response.asString()),"ideal_weight");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"patient_diet_plan");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"total_calories");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"patient_activity_target_cal");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"patient_type_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"score_logic");
		
		
		APIBasicValidation.getKey(new JSONObject(response.asString()),"progress");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"overall");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"health");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"contact");
		
		
		APIBasicValidation.getKey(new JSONObject(response.asString()),"nok");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"lang");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"dismiss");

		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");


	}

	@Test(dataProvider = "patientCompleteProfile_Valid_Auth_Invalid_Fields",dataProviderClass=DP.class)
	public void postPatientCompleteProfile_CheckResponseWithValidAuth_InValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientCompleteProfileDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(patientCompleteProfileDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientCompleteProfile);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		// checking response..
				APIBasicValidation.assertingSingleElementValue(response,"error.message","Sorry! Something went wrong.");
				APIBasicValidation.assertingSingleElementValue(response,"error.code",500);
		APIBasicValidation.statusCodeValidation(response, 500);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);

		
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

		test.log(LogStatus.INFO, "****** TC_Patient_Complete_Profile_Valid_Auth_And_Data_Info Ended.. ******");
	}

}

