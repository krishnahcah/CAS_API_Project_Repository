

package CAS_API.Program_Enrollment.EnrollmentSection_TCs;



import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;

public class TC_EnrollmentSection_Valid_Auth_Info extends BaseTest{

	
	@Test
	public void EnrollmentSection_CheckResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getEnrollmentSection);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Enrollment is either done or processed");
		APIBasicValidation.assertingSingleElementValue(response,"error.code",0);
		// checking response..
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"enrollmentSectionsValidResponseJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"sections");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"section_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"name");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"component");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"progress");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"reason");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"description");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program_enrollment");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"progress");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"status");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test
	public void EnrollmentSection_CheckResponseWithValidAuth_With_400_BadRequest() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getEnrollmentSection);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Enrollment is either done or processed");
		APIBasicValidation.assertingSingleElementValue(response,"error.code",0);
		// checking response..
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 400);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Close_Connection_Info);
		APIBasicValidation.headerValidation(response,"Vary",FrameworkConstants.Header_Vary_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"400BadRequestDismissCardJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	


}

