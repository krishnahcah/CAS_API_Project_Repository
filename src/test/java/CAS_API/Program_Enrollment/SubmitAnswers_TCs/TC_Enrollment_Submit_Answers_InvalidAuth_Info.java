

package CAS_API.Program_Enrollment.SubmitAnswers_TCs;



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

public class TC_Enrollment_Submit_Answers_InvalidAuth_Info extends BaseTest{

	@Test(dataProvider = "EnrollmentSubmitAnswersWithValidPayload",dataProviderClass=DP.class)
	public void Enrollment_Submit_Answers_With_Invalid_Auth_Valid_Payload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").
				pathParam("section_id", "1").
				body(patientloginDataInJsonString).
				header("Authorization",FrameworkConstants.Invalid_Authorization).
				when().
				post(API_Endpoint.Endpoints.postEnrollmentSectionSubmitAnswer);
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
		APIBasicValidation.statusCodeValidation(response, 401);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for Valid payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(dataProvider = "EnrollmentSubmitAnswersWithValidPayload",dataProviderClass=DP.class)
	public void Enrollment_Submit_Answers_With_No_Auth_Valid_Payload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").
				pathParam("section_id", "1").
				body(patientloginDataInJsonString).

				when().
				post(API_Endpoint.Endpoints.postEnrollmentSectionSubmitAnswer);
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
		APIBasicValidation.statusCodeValidation(response, 401);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for Valid payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(dataProvider = "EnrollmentSubmitAnswersWithEmptyPayload",dataProviderClass=DP.class)
	public void Enrollment_Submit_Answers_With_Invalid_Auth_Invalid_Payload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").
				pathParam("section_id", "1").
				body(patientloginDataInJsonString).
				header("Authorization",FrameworkConstants.Invalid_Authorization).
				when().
				post(API_Endpoint.Endpoints.postEnrollmentSectionSubmitAnswer);
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");
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
	
	

}

