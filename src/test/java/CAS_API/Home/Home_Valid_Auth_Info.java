package CAS_API.Home;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.RestUtilities;

public class Home_Valid_Auth_Info extends BaseTest {

    @Test
    public void GetHome_CheckResponseWithValidAuth() throws JsonProcessingException {

        test.log(LogStatus.INFO, " >>> TC-" + Thread.currentThread().getStackTrace()[1].getMethodName() + " Started... <<< ");

        response = RestUtilities.GetRequest(API_Endpoint.Endpoints.getHome, FrameworkConstants.Valid_Authorization);

        test.log(LogStatus.INFO, "Response Value :: " + response.asPrettyString());
        System.out.println(response.asPrettyString());

        // validate Status Code
        APIBasicValidation.statusCodeValidation(response, 200);

        test.log(LogStatus.INFO,"Checking Response Headers..");

        APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
        APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
        APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

        //Checking Response Schema and its Type
        APIBasicValidation.jsonResponseSchemaValidation(response,"GetHOMEValidResponseJSONSchema.json");

        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"program");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"title");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"logo");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"link");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"url");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"format");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"benefits");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"title");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"type");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"extra");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"image");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"item");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"quickLinks");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"progress");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"overall");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"health");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"contact");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"nok");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"lang");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"dismiss");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"program_enrollment");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"status");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"reason");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"sections");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"cta");

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

    }
}
