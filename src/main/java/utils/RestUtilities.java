package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

/**
 * Created by Ankit on 12-10-2022.
 **/
public class RestUtilities {

    public static String generatePayloadString(String fileName){
        String filePath="src/main/resources/payloads/"+fileName;
        try{
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Response GetRequest(String uRI){
        RequestSpecification requestSpecification=given();
        requestSpecification.contentType(ContentType.JSON);
        Response response=requestSpecification.get(uRI);
        return response;
    }

    public static Response PostRequest(String uRI,String payload){
        RequestSpecification requestSpecification=given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        Response response=requestSpecification.post(uRI);
        return response;
    }

    public static Response PostRequest(String uRI,String payload,String sessionID){
        RequestSpecification requestSpecification=given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        requestSpecification.header("cookie","JSESSIONID=" + sessionID+"");
        Response response=requestSpecification.post(uRI);
        return response;
    }


}
