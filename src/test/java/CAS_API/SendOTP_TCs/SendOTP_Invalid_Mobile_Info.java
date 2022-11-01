package CAS_API.SendOTP_TCs;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.RestUtilities;

import java.util.HashMap;
import java.util.Map;

public class SendOTP_Invalid_Mobile_Info extends BaseTest {

    Map<String, String> requestParams = new HashMap<String,String>();

    @Test
    public void CheckResponseWithInvalidMobileNumber() throws JsonProcessingException
    {

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

        test.log(LogStatus.INFO,"Sending Request with InValid Payload.");

        requestParams.put("mobile", FrameworkConstants.Invalid_Mobile_Info);

        response= RestUtilities.PostRequest(requestParams, API_Endpoint.Endpoints.postSendOTPPath);
        System.out.println(response.asPrettyString());
        test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

        // validate response..
        APIBasicValidation.assertingSingleElementValue(response, "error.message","Sorry! Something went wrong.");
        APIBasicValidation.assertingSingleElementValue(response,"error.code", 500);
        test.log(LogStatus.PASS,"All Responses is validated successfully!!");

        // validate Status Code
        APIBasicValidation.statusCodeValidation(response, 500);

        // validate response Keys for validation
        test.log(LogStatus.INFO, "Checking response Keys for validation...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"error");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"message");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"code");

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

    }
}
