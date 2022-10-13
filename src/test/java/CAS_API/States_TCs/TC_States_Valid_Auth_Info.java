package CAS_API.States_TCs;




import utils.FrameworkConstants;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
//import DataProvider.DP;
import baseTest.BaseTest;
import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
public class TC_States_Valid_Auth_Info extends BaseTest{

	
	@Test
	public void CheckStatesResponseWithValidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		APIBasicValidation.assertingSingleElementValue(response, "states[0].state_id", 26);
		APIBasicValidation.assertingSingleElementValue(response, "states[0].name", "ANDAMAN & NICOBAR ISLANDS");
		// checking response..
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Vary","Authorization,Accept-Encoding");

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"getStatesJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"states");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"state_id");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"name");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	//@BeforeTest
	public void GetStates()
	{

		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);
		System.out.println(response.asPrettyString());
	}

	//@Test
	void checkStatusCode()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);
		
		APIBasicValidation.statusCodeValidation(response, 200);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
	}


	//@Test
	void checkResponseHeaders()
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);

		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Vary","Authorization,Accept-Encoding");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	//@Test
	void checkResponseTagsValidation() throws Exception
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		response = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);
		APIBasicValidation.jsonResponseSchemaValidation(response,"getStatesJSONSchema.json");
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
	}

	/*
	 * 
	 * @Test(dataProvider =
	 * "get_All_States_Valid_Payload",dataProviderClass=DP.class) void
	 * checkStateValue(HashMap<String,Object> payload) throws
	 * JsonProcessingException {
	 * extentTest.info(" >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Started <<< ");
	 * 
	 * String patientRegistrationDataInJsonString=
	 * Utility.convertObjectArraytoJSONString(payload); JsonPath jp= new
	 * JsonPath(patientRegistrationDataInJsonString);
	 * 
	 * List<Integer> state_IDs =
	 * response.path("states.findAll { it.state_id >0 }.state_id"); List<String>
	 * state_Names = response.path("states.findAll { it.state_id >0 }.name");
	 * 
	 * for(int i=0;i<state_IDs.size();i++) { System.out.println("Count--"+i); Object
	 * obj=jp.get("states["+i+"]");
	 * //System.out.println("jj-"+obj.+"+++"+jp.get("states["+i+"]"));
	 * assertingItemValueUsingHasItem(response,"states",obj.toString()); }
	 * System.out.println(" State IDs : "+state_IDs);
	 * 
	 * 
	 * //assertTrue(patientProgram.get("program_id").equals(8));
	 * 
	 * 
	 * extentTest.pass("TC:"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Passed !!");
	 * extentTest.info(" >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getMethodName()+" Ended <<< ");
	 * 
	 * }
	 * 
	 * 
	 * 
	 */
	//@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_Patient_Programs_With_Valid_Info Ended.. ******");
		
	}

}

