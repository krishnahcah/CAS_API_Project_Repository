package CAS_API.Patient_Details.Patient_Program_TCs;


import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_PatientPrograms_Valid_Auth_Info extends BaseTest{

	@Test(priority = 1)
	public void GetPatientPrograms_CheckResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getPatientPrograms);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].program_id",2);
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].code","LIPAGLYN");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].linked",true);
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].logo","http://app.hcah.in/public/program_code/benefit/SG_1597995275.png");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].label","LIPAGLYN");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].parent_id",0);
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].parent_code","");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].sub_program_title","");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].sub_program_description","");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].active",true);
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].service","LIPAGLYN");
		APIBasicValidation.assertingSingleElementValue(response,"programs[0].organization","LIPAGLYN");
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);
		

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"getPatientProgramsJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"program_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"code");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"linked");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"logo");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"label");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"parent_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"parent_code");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"sub_program_title");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"sub_program_description");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"active");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"service");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"organization");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	

	
	
}

