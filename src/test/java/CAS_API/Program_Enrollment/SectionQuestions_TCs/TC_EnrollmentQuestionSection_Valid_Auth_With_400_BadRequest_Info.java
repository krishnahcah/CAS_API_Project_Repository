

package CAS_API.Program_Enrollment.SectionQuestions_TCs;



import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import io.restassured.RestAssured;
import baseTest.BaseTest;
import utils.FrameworkConstants;

public class TC_EnrollmentQuestionSection_Valid_Auth_With_400_BadRequest_Info extends BaseTest{

	

	@Test
	void checkStatusCode()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		response = RestAssured.given().
				contentType("application/json").pathParam("section_id", "1").
				when().
				get(API_Endpoint.Endpoints.getEnrollmentSectionQuestions);

		APIBasicValidation.statusCodeValidation(response, 401);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
	}


	@Test
	void checkResponseHeaders()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		response = RestAssured.given().pathParam("section_id", "1").
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getEnrollmentSectionQuestions);

		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
	}

	@Test
	void checkResponseTags() throws Exception
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		response = RestAssured.given().pathParam("section_id", "1").
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getEnrollmentSectionQuestions);
		APIBasicValidation.jsonResponseSchemaValidation(response,"unauthenticatedErrorJSONSchema.json");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}

	//@Test
	void checkReponseErrorMessage()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		response = RestAssured.given().
				contentType("application/json").pathParam("section_id", "1").
				when().
				get(API_Endpoint.Endpoints.getEnrollmentSectionQuestions);

		APIBasicValidation.assertingSingleElementValue(response,"error.message","Unauthenticated.");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}


	@AfterClass
	public void afterClass()
	{
		test.log(LogStatus.INFO, "****** TC_EnrollmentSection_NoAuth_Info Ended.. ******");
	}

}

