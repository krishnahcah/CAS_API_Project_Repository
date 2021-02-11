package CustomerApp_Package.TestScripts_Folder.PatientScripts;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.BaseTest;


public class SendOTP extends BaseTest{
	//protected static RequestSpecification requestSpec;

	@Test
	public void sendOTP()
	{
		
		response = given().
				spec(httpRequest).
				contentType("application/json").
				log().
				body().	
				when().
				post(readConfigurationFile("SendOTPPath"));
		//System.out.println(response.asPrettyString());
	}
	@Test
	void checkResposeBody()
	{
		String responseBody = response.asPrettyString();
		System.out.println("responseBody is :" + responseBody);
		Assert.assertEquals(responseBody.contains("Welcome to the HCAH Consumer App!"), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
	
	
}
