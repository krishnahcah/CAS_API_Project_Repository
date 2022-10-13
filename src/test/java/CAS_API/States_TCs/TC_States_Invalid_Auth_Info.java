
package CAS_API.States_TCs;


import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;

public class TC_States_Invalid_Auth_Info extends BaseTest{
	
	
	
	/*
	 * @BeforeClass public void beforeClass() { testClass =
	 * reports.startTest("TESTCASES -->>"+Thread.currentThread().getStackTrace()[1]
	 * .getClassName()); testClass.log(LogStatus.INFO,
	 * " >>> TC-"+Thread.currentThread().getStackTrace()[1]
	 * .getClassName()+" Started... <<< "); }
	 */
	@Test(priority = 1)
	public void CheckStatesResponseWithInvalidAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);
		
		response.then().log().all();
		
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
	
	@Test(priority = 2)
	public void CheckStatesResponseWithMissingAuth() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getStates);
		
		response.then().log().all();
		
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
	
	//@BeforeTest
		public void GetStates()
		{

			/*
			 * response = given(). spec(httpRequest).header("Authorization","").
			 * contentType("application/json"). when().
			 * get(readConfigurationFile("getStates"));
			 * System.out.println(response.asPrettyString());
			 */
		}

		//@Test
		void checkStatusCode()
		{
			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

			response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
					contentType("application/json").
					when().
					get(API_Endpoint.Endpoints.getStates);

			APIBasicValidation.statusCodeValidation(response, 401);
			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
		}


		//@Test
		void checkResponseHeaders()
		{
			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

			response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
					contentType("application/json").
					when().
					get(API_Endpoint.Endpoints.getStates);

			APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
			APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
			APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
		}

		//@Test
		void checkResponseTags() throws Exception
		{
			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

			response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
					contentType("application/json").
					when().
					get(API_Endpoint.Endpoints.getStates);
			APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

		}

		//@Test
		void checkReponseErrorMessage()
		{
			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

			response = RestAssured.given().header("Authorization",FrameworkConstants.Invalid_Authorization).
					contentType("application/json").
					when().
					get(API_Endpoint.Endpoints.getStates);

			APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");

			test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

		}


		//@AfterClass
		public void afterClass()
		{
			reports.endTest(testClass);	
			test.log(LogStatus.INFO, "****** TC_States_Invalid_NoAuth_Info Ended.. ******");
		}

}

