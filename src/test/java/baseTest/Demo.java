package baseTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import resources.SkippedTCs;
import utils.RestUtilities;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

public class Demo implements SkippedTCs
{

    @Test
    public void GetAPI_validate() throws IOException {

        System.out.println(" >>> TC- " + Thread.currentThread().getStackTrace()[1].getMethodName() + " Started... <<< ");
        String baseurl="https://catfact.ninja/fact";

        Properties prop=new Properties();
        prop.load(Demo.class.getClassLoader().getResourceAsStream("Environment.properties"));
        System.out.println("Environment is  "+prop.getProperty("Environment"));

        Response response = RestUtilities.GetRequest(baseurl);
//        System.out.println("\n Time : "+response.then().extract().time());
        System.out.println("\n Response : "+response.asString());


        JSONObject jsonObject = new JSONObject(response.asString());
        String fact=jsonObject.getString("fact");
        System.out.println("\n Fact : "+fact);
        int length=jsonObject.getInt("length");
        System.out.println("\n len : "+length);



    }

    @Test
    public void getmail(){

        skippedTCs.put("Demo class method getmail","Skipped due to timeout");


    }
}
