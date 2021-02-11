package CustomerApp_Package.TestScripts_Folder.PatientScripts;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import utility.BaseTest;

public class GetPatient extends BaseTest{

	@Test
	public void GetPatientDetails()
	{
		
		response = given().
				spec(httpRequest).header("mobile_access_token",readConfigurationFile("Mobile_Access_Token")).
				contentType("application/json").
				log().
				body().	
				when().
				get(readConfigurationFile("patientDetails"));
		System.out.println(response.asPrettyString());
	}
	@Test
	void checkPatientAccountNumber()
	{
		String responseBody = response.asPrettyString();
		//System.out.println("responseBody is :" + responseBody);
		Assert.assertEquals(responseBody.contains(readConfigurationFile("account_no")), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // get the  status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}
	
}
