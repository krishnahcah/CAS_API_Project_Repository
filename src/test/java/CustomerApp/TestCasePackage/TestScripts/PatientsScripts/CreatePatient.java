package CustomerApp.TestCasePackage.TestScripts.PatientsScripts;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given; //import this

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import utility.AllureLogger;
import utility.BaseTest;
import utility.FrameworkConstants;
public class CreatePatient extends BaseTest{

	public static String newID = "";
	
	public static String readTxt(String filePath){
        StringBuffer sb = new StringBuffer();

        try {
            FileReader reader=new FileReader(new File(filePath));
            char[] byt=new char[1024];
            int len = 0;
              while( (len = reader.read(byt)) != -1){
                    sb.append(new String(byt,0,len));
                }               
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }catch (IOException e) {
              e.printStackTrace();
          }
           return sb.toString();     
           
    }
	 public static String[] parseStr(String str,String c){
         return str.split(c);      
      }
	 @DataProvider(name="testData")
		       public Object[][] getData(){
		          String[] data = parseStr(FrameworkConstants.Test_DATA_FILE_PATH,";");
		          Object[][] testData = new Object[data.length][];
		             for(int i=0;i<data.length;i++){
		                 
		                 ObjectMapper mapper=new ObjectMapper();
		                 try {
		                    Map map = mapper.readValue(data[i], Map.class);
		                    testData[i]=new Object[]{(Map)map.get("request")}; 
		                    
		                    
		                } catch (JsonParseException e) {
		                    e.printStackTrace();
		                } catch (JsonMappingException e) {
		                    e.printStackTrace();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }     
		             }     
		             return testData;
		           
		       } 
	
	 @Test(dataProvider="testData")
     public void test(Map param) throws JsonProcessingException{
         ObjectMapper mapper=new ObjectMapper();
         System.out.println("My Output" + param.toString());
         String result = null;
       result="jatin";
	   // parameter passing because the interface is json format, themap converted into json 
	   //result = OkHttpUtil.postJson(Constance.test_host+param.get("url").toString(),mapper.writeValueAsString(param.get("body")));
	       //You can add assertion 
         
     }
	
	 @DataProvider(name = "payload")
	    public JSONArray payload() throws IOException, ParseException{
		 JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader(FrameworkConstants.Patient_DATA_FILE_PATH);
		Object obj= jsonparser.parse(reader);
		JSONObject jsonOjbect =(JSONObject) obj;
		JSONArray JsonArray=(JSONArray) jsonOjbect.get("patient");
		return  JsonArray;
	    }
	 
	 @Test
	 public void test1() throws IOException, ParseException
	 {
		 JSONParser jsonparser = new JSONParser();
			FileReader reader = new FileReader(FrameworkConstants.Patient_DATA_FILE_PATH);
			Object obj= jsonparser.parse(reader);
			JSONObject jsonOjbect =(JSONObject) obj;
			JSONArray JsonArray=(JSONArray) jsonOjbect.get("patient");
			System.out.println("Payload, we are sending is : "+JsonArray);
			
	 }
	 
	
	@Test(dataProvider="payload",description="Create New Patient in DB") 
	public void createPatient(String patientData){
		
		AllureLogger.logToAllure("Starting the test to create new details");
		/*******************************************************
		 * Send a POST request to createPatientURI 
		 * and check that the response has HTTP status code 201
		 ******************************************************/
		AllureLogger.logToAllure("Payload, we are sending is : "+patientData);
		System.out.println("Payload, we are sending is : "+patientData);
		
		//Sending the GET request for a specific booking id and receiving the response
		AllureLogger.logToAllure("Process Started : Creation of New Patient");
		AllureLogger.logToAllure("Sending the POST request to create new Patient");
		
		
		 Header h1 = new Header("Authorization",readConfigurationFile("authToken"));
		 Header h2 = new Header("access_token",readConfigurationFile("Access_Token"));
		 
		 List<Header> list = new ArrayList<Header>();
		    list.add(h1);
		    list.add(h2);
		Headers headers = new Headers(list);
		Response response = given().
								spec(requestSpec).headers(headers).
								contentType("application/json").
					            body(patientData).log().body().
					        when().
					        	post(readConfigurationFile("createPatientURI"));
		
		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 201");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
		//To get the newly created Patient
		System.out.println("Account Number : "+response.then().extract().path("x.patient.account_no"));
		newID = response.then().extract().path("x.patient.account_no").toString();
		AllureLogger.logToAllure("Retrieved booking id : "+response.then().extract().path("x.patient.account_no"));
		
	}	
}
