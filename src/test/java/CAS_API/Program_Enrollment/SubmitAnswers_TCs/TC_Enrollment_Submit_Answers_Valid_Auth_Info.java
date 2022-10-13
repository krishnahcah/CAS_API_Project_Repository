

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

public class TC_Enrollment_Submit_Answers_Valid_Auth_Info extends BaseTest{

	@Test(dataProvider = "EnrollmentSubmitAnswersWithValidPayload",dataProviderClass=DP.class)
	public void Enrollment_Submit_Answers_With_Valid_Auth_Valid_Payload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);
		test.log(LogStatus.INFO,"Sending Request with Valid Payload.");
		response = RestAssured.given().
				contentType("application/json").
				pathParam("section_id", "1").
				body(patientloginDataInJsonString).
				header("Authorization",validAuthorization).
				when().
				post(API_Endpoint.Endpoints.postEnrollmentSectionSubmitAnswer);
		response.then().log().all();
		
//		System.out.println(response.asPrettyString());
		test.log(LogStatus.INFO,response.asPrettyString());
		// checking Status code..
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

		 test.log(LogStatus.INFO, "Checking response Keys for validation...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(dataProvider = "EnrollmentSubmitAnswersWithEmptyPayload",dataProviderClass=DP.class)
	public void Enrollment_Submit_Answers_With_Valid_Auth_Empty_Payload(HashMap<String,Object> payload) throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		String patientloginDataInJsonString= utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestAssured.given().
				contentType("application/json").
				pathParam("section_id", "1").
				body(patientloginDataInJsonString).
				header("Authorization",validAuthorization).
				when().
				post(API_Endpoint.Endpoints.postEnrollmentSectionSubmitAnswer);
		response.then().log().all();
		
		//System.out.println(response.asPrettyString());
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Validations are missing.");
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.question_id[0]","The question id field is required.");
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.answer[0]","The answer field is required.");
		
		APIBasicValidation.statusCodeValidation(response, 422);

		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		APIBasicValidation.jsonResponseSchemaValidation(response,"enrollmentSubmitAnswersWithEmptyResponseJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for Invalid payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"validate");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"question_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"answer");
		
	
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	



}

