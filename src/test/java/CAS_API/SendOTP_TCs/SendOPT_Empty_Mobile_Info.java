package CAS_API.SendOTP_TCs;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.RestUtilities;

import java.util.HashMap;
import java.util.Map;

public class SendOPT_Empty_Mobile_Info extends BaseTest {

    Map<String, String> requestParams = new HashMap<String,String>();

    @Test(invocationCount = 1)
    public void CheckResponseWithEmptyMobileNumber()
    {

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

        test.log(LogStatus.INFO,"Sending Request with InValid Payload.");

        requestParams.put("mobile", "");

        response= RestUtilities.PostRequest(requestParams, API_Endpoint.Endpoints.postSendOTPPath);
        System.out.println(response.asPrettyString());
        test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

        // validate response..
        APIBasicValidation.assertingSingleElementValue(response, "error.message","Validations are missing.");
        APIBasicValidation.assertingSingleElementValue(response,"error.validate.mobile[0]", "The mobile field is required.");
        test.log(LogStatus.PASS,"All Responses is validated successfully!!");

        // validate Status Code
        APIBasicValidation.statusCodeValidation(response, 422);

        // validate Response Headers
        test.log(LogStatus.INFO,"Checking Response Headers..");

        APIBasicValidation.headerValidation(response,"Content-Type", FrameworkConstants.Header_ContentType_JSON_Info);
        APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
        APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

        //Checking Response Schema and its Type
        APIBasicValidation.jsonResponseSchemaValidation(response,"TC_SendOTP_Invalid_Empty_Mobile_Info_missingMobileNumberErrorJSONSchema.json");


        test.log(LogStatus.INFO, "Checking response Keys for validation...");
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"error"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"message"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"validate"));
        Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()),"mobile"));

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");


    }
}
