package CAS_API.SendOTP_TCs;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.RestUtilities;

import java.util.HashMap;
import java.util.Map;

public class SendOPT_Valid_Mobile_Info extends BaseTest {

    Map<String, String> requestParams = new HashMap<String,String>();

    @Test
    public void CheckResponseWithValidMobileNumber(){

        test.log(LogStatus.INFO, " >>> TC- "+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");

        test.log(LogStatus.INFO,"Sending Request with Valid Payload.");

        requestParams.put("mobile", FrameworkConstants.Valid_Mobile_Info);

        response= RestUtilities.PostRequest(requestParams, API_Endpoint.Endpoints.postSendOTPPath);
        System.out.println(response.asPrettyString());
        test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());

        // validate the response...
        Assert.assertEquals(response.asString(),"{\n" +"    \n"+"}");
        test.log(LogStatus.PASS,"Response is validated successfully!!");

        // validate the Status Code...
        APIBasicValidation.statusCodeValidation(response, 200);

        // validate response headers...
//        APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
//        APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
        APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

    }
}
