package CAS_API.Patient_Details.Patient_Login_TCs;


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

public class TC_PatientLogin_Valid_Auth_And_Data_Info extends BaseTest{


	@Test(dataProvider = "patientLoginWithValidPayload",dataProviderClass=DP.class,priority = 1)
	public void patientLogin_CheckResponseWithValidAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientLogin);


		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		APIBasicValidation.statusCodeValidation(response, 200);


		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"patientLoginValidResponseJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"user_info");
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


		APIBasicValidation.getKey(new JSONObject(response.asString()),"jabber");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"host");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"password");


		APIBasicValidation.getKey(new JSONObject(response.asString()),"app");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"access_token");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"version");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"lang_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"currency");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"cust_app_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"hcah");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"account");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"token");




		APIBasicValidation.getKey(new JSONObject(response.asString()),"coins");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"available");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"text");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"earn");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"redeem");


		APIBasicValidation.getKey(new JSONObject(response.asString()),"programs");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"service");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"organization");
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");


	}


	@Test(dataProvider = "patientLoginWithInvalidPayload_Missing_ProgramID",dataProviderClass=DP.class, priority = 2)
	public void patientLogin_CheckResponseWithValidAuth_InvalidPayload_Missing_ProgramID(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientLogin);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Validations are missing.");
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.program_id[0]","The program id field is required.");
		APIBasicValidation.statusCodeValidation(response, 422);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"patientLoginWithInvalidPayload_Missing_ProgramIDJSONSchema.json");


		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"validate");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program_id");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	@Test(dataProvider = "patientLoginWithInvalidPayload_Missing_DeviceInfo",dataProviderClass=DP.class, priority = 2)
	public void patientLogin_CheckResponseWithValidAuth_WithInvalidPayload_Missing_DeviceInfo(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientLogin);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Validations are missing.");
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.device_info[0]","The device info field is required.");
		APIBasicValidation.statusCodeValidation(response, 422);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"patientLoginWithInvalidPayload_Missing_DeviceInfo_JSONSchema.json");


		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"validate");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"device_info");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}


	@Test(dataProvider = "patientLoginWithInvalidPayload_Invalid_ProgramID",dataProviderClass=DP.class)
	public void patientLogin_CheckResponseWithValidAuth_WithInvalidPayload_Invalid_ProgramID(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postPatientLogin);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.code",500);
		
		APIBasicValidation.statusCodeValidation(response, 500);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);

		test.log(LogStatus.INFO, "Checking response Tags validations for Invalid payload...");
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

		test.log(LogStatus.INFO, "****** TC_PatientLogin_Valid_Auth_And_Data_Info Ended.. ******");
	}

}

