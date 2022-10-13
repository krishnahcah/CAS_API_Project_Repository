package CAS_API.CaretakerRelations_TCs;



import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasItems;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_CareTaker_Valid_Auth_Info extends BaseTest{

	//@BeforeTest
	public void GetCaretakerRelationList()
	{

		response = given().
				spec(httpRequest).header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCareTakerRelationList);
		System.out.println(response.asPrettyString());
	}

	@Test(priority = 1)
	public void CheckCareTakerResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getCareTakerRelationList);

		response.then().log().all();

		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		// checking response..
		response.then().body("relations.title",hasItems("Aunt","Brother","Cousin","Mother","Father"));
		test.log(LogStatus.PASS,"Titles in response has been validated successfully!!");

		response.then().body("relations.relation_id",hasItems(1,2,3,4,5,6));
		test.log(LogStatus.PASS,"Relation IDs in response has been validated successfully!!");

		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);

		test.log(LogStatus.INFO,"Checking Response Headers..");

		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"getCaretakerRelationsListJSONSchema.json");

		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"relations");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"relation_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"title");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}



	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_CareTaker_Valid_Auth_Info Ended.. ******");

	}

}

