

package CAS_API.Care_TCs;



import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import DataProvider.DP;
import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;

public class TC_Care_Lead_Invalid_Auth_Info extends BaseTest{


	@Test(dataProvider = "Care_Lead_WithValidPayload",dataProviderClass=DP.class)
	public void checkLeadResponseWithNoAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{
		System.err.println("IS NEW Patient : "+newPatient);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
	
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postCareLead);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

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

	}
	
	@Test(dataProvider = "Care_Lead_WithValidPayload",dataProviderClass=DP.class)
	public void checkLeadResponseWithInvalidAuth_ValidPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
	
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postCareLead);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

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

	}
	
	@Test(dataProvider = "Care_Lead_WithEmptyPayload",dataProviderClass=DP.class)
	public void checkLeadResponseWithInvalidAuth_EmptyPayload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
	
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").body(patientloginDataInJsonString).
				when().
				post(API_Endpoint.Endpoints.postCareLead);

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

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

	}


	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_EnrollmentSection_NoAuth_Info Ended.. ******");
	}

}

