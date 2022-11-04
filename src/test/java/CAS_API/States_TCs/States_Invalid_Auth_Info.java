package CAS_API.States_TCs;

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

public class States_Invalid_Auth_Info extends BaseTest {

    @Test(priority = 1)
    public void CheckStatesResponseWithInvalidAuth() throws JsonProcessingException {

        test.log(LogStatus.INFO, " >>> TC-" + Thread.currentThread().getStackTrace()[1].getMethodName() + " Started... <<< ");
        String[] Invalid_Authorization = {FrameworkConstants.Invalid_Authorization, ""};

        for (int i = 0; i < 2; i++) {

            response = RestUtilities.GetRequest(API_Endpoint.Endpoints.getHome, Invalid_Authorization[i]);

            test.log(LogStatus.INFO, "Response Value :: " + response.asPrettyString());
            System.out.println("Response Value :: " + response.asPrettyString());

            // validate response..
            APIBasicValidation.assertingSingleElementValue(response, "error.message", "Unauthenticated.");

            // validate Status Code
            APIBasicValidation.statusCodeValidation(response, 401);

            test.log(LogStatus.INFO, "Checking Response Headers..");

            APIBasicValidation.headerValidation(response, "Content-Type", FrameworkConstants.Header_ContentType_JSON_Info);
            APIBasicValidation.headerValidation(response, "Server", FrameworkConstants.Header_Server_Apache_Info);
            APIBasicValidation.headerValidation(response, "Connection", FrameworkConstants.Header_Connection_Info);

            test.log(LogStatus.INFO, "Checking response Keys for payload...");
            Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()), "error"));
            Assert.assertNotNull(APIBasicValidation.getKey(new JSONObject(response.asString()), "message"));

            test.log(LogStatus.INFO, " >>> TC-" + Thread.currentThread().getStackTrace()[1].getMethodName() + " Ended... <<< ");

        }
    }
}
