package CAS_API.Patient_Details.ProfilePicture_TCs;



import java.io.File;

import org.json.JSONObject;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.FrameworkConstants;

public class TC_ProfilePicture_Valid_Auth_Info extends BaseTest{
	File uploadPicture = new File(System.getProperty("user.dir")+"/src/test/java/Requests/testimg1.jpg");
	
	@Test(priority = 1)
	public void UploadProfilePicture_CheckResponseWithValidAuth_JPEGImage() throws JsonProcessingException
	{

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().header("Authorization",validAuthorization).contentType("multipart/form-data; boundary=<calculated when request is sent>").
				multiPart("picture",uploadPicture,"multipart/form-data").
				when().
				post(API_Endpoint.Endpoints.postUploadProfilePicture).thenReturn();
		
		response.then().log().all();
		Response splashScreenResponse = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getSplashScreen);
		
		String ExpectedPictureValue = splashScreenResponse.path("user_info.personal.picture");
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		if(response.asPrettyString().contains(ExpectedPictureValue))
		{
			test.log(LogStatus.PASS,"Profile Picture is successfully Uploaded!!");
		}
		else
		{
			test.log(LogStatus.FAIL,"Profile Picture is not able to Uploaded!!");
		}
		
		
		
	//	APIBasicValidation.assertingSingleElementValue(response, "picture", ExpectedPictureValue);
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"uploadProfilePictureJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"picture");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(priority = 2)
	public void UploadProfilePicture_CheckResponseWithValidAuth_PNGImage() throws JsonProcessingException
	{
		File uploadPicture = new File(System.getProperty("user.dir")+"/src/test/java/Requests/testpngimg.png");
		

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		
		response = RestAssured.given().header("Authorization",validAuthorization).contentType("multipart/form-data; boundary=<calculated when request is sent>").
				multiPart("picture",uploadPicture,"multipart/form-data").
				when().
				post(API_Endpoint.Endpoints.postUploadProfilePicture).thenReturn();
		
		response.then().log().all();
		Response splashScreenResponse = RestAssured.given().header("Authorization",validAuthorization).
				contentType("application/json").
				when().
				get(API_Endpoint.Endpoints.getSplashScreen);
		
		String ExpectedPictureValue = splashScreenResponse.path("user_info.personal.picture");
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		
		// checking response..
		if(response.asPrettyString().contains(ExpectedPictureValue))
		{
			test.log(LogStatus.PASS,"Profile Picture is successfully Uploaded!!");
		}
		else
		{
			test.log(LogStatus.FAIL,"Profile Picture is not able to Uploaded!!");
		}
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		APIBasicValidation.headerValidation(response,"Content-Encoding",FrameworkConstants.Header_Content_Encoding_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"uploadProfilePictureJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"picture");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	@Test(priority = 3)
	public void UploadProfilePicture_CheckResponseWithValidAuth_InvalidPDFFile() throws JsonProcessingException
	{
		File uploadPicture = new File(System.getProperty("user.dir")+"/src/test/java/Requests/test_sample_PDF_File.pdf");

		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

		response = RestAssured.given().header("Authorization",validAuthorization).contentType("multipart/form-data; boundary=<calculated when request is sent>").
				multiPart("picture",uploadPicture,"multipart/form-data").
				when().
				post(API_Endpoint.Endpoints.postUploadProfilePicture).thenReturn();
		
		response.then().log().all();
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

		// checking response..
		APIBasicValidation.assertingSingleElementValue(response,"error.validate.picture[0]","The picture must be an image.");
		APIBasicValidation.assertingSingleElementValue(response,"error.message","Validations are missing.");
		
		
		// Checking Status Code
		APIBasicValidation.statusCodeValidation(response, 422);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

		//Checking Response Schema and its Type
		APIBasicValidation.jsonResponseSchemaValidation(response,"uploadProfilePictureErrorJSONSchema.json");
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"validate");
		APIBasicValidation.getKey(new JSONObject(response.asString()),"picture");
		
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

	}
	
	


}

