package CAS_API.Home;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.RestUtilities;

public class Home_Valid_Auth_Info extends BaseTest {

    @Test(invocationCount = 1)
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

        //validate Response Schema and its Type
        APIBasicValidation.jsonResponseSchemaValidation(response,"GetHOMEValidResponseJSONSchema.json");

        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"program"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"title"));
//        APIBasicValidation.getKey(new JSONObject(response.asString()),"logo");
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"link"));
//        APIBasicValidation.getKey(new JSONObject(response.asString()),"url");
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"format"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"benefits"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"title"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"type"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"extra"));
//        APIBasicValidation.getKey(new JSONObject(response.asString()),"image");
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"item"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"quickLinks"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"progress"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"overall"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"health"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"contact"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"nok"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"lang"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"dismiss"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"program_enrollment"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"status"));
//        APIBasicValidation.getKey(new JSONObject(response.asString()),"reason");
//        APIBasicValidation.getKey(new JSONObject(response.asString()),"sections");
//        APIBasicValidation.getKey(new JSONObject(response.asString()),"cta");

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

    }
}
