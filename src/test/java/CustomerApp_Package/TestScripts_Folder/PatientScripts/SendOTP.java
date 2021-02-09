package CustomerApp_Package.TestScripts_Folder.PatientScripts;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.AllureLogger;
import utility.BaseTest;


public class SendOTP extends BaseTest{
	protected static RequestSpecification requestSpec;
	
	@BeforeSuite
	public void setBaseURI() {
		
	   Header authHeader = new Header("Authorization",readConfigurationFile("authToken"));
		requestSpec = new RequestSpecBuilder().
	            		setBaseUri(readConfigurationFile("CAS_Base_URI")).addHeader("Authorization",readConfigurationFile("authToken"))
	            		.build();
	    
	}
	
	@Test
	public void sendOTP()
	{
		/*Response response = given().
				spec(requestSpec).
			when().
				get(readConfigurationFile("SendOTPPath"));
		
		response.then().spec(responseSpec);*/
		
		Header h1= new Header("Content-Type",ContentType.JSON.toString());
	    Header h2 = new Header("Accept", ContentType.JSON.toString());
	    Header h3 = new Header("Authorization","Token token=3Hu9orST5sKDHUPJBwjbogtt");
	    Header h4 = new Header("access_token","78e30244b424bd6559dcfa36bf4adbd9eef0180ddee828212efe0655af9410be");
	    List<Header> list = new ArrayList<Header>();
	    list.add(h1);
	    list.add(h2);
	    list.add(h3);
	    list.add(h4);

	    Headers header = new Headers(list);
		
		Response response1 =
			      given()
			          .headers(header)
			          .when()
			          .get("https://cas-app-qa.hcah.in/api/v1/patient/send-otp/8860005106")
			          .then()
			          .contentType(ContentType.JSON)
			          .extract().response();
		//logResponseAsString(response);
		logResponseAsString(response1);
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+response1.getBody());
	}
	
	
}
