package CAS_API.States_TCs;

import apiConfigs.API_Endpoint;
import apiValidations.APIBasicValidation;
import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.RestUtilities;

public class States_Valid_Auth_Info extends BaseTest {

    @Test
    public void CheckStatesResponseWithValidAuth() throws JsonProcessingException {

        test.log(LogStatus.INFO, " >>> TC-" + Thread.currentThread().getStackTrace()[1].getMethodName() + " Started... <<< ");

        response = RestUtilities.GetRequest(API_Endpoint.Endpoints.getStates, FrameworkConstants.Valid_Authorization);

        test.log(LogStatus.INFO, "Response Value :: " + response.asPrettyString());
        System.out.println(response.asPrettyString());

        JSONObject jsonObject = new JSONObject(response.asString());

        for (int i = 0; i < 10; i++) {

            String name ="";
            int state_id = jsonObject.getJSONArray("states").getJSONObject(i).getInt("state_id");

            if (state_id == 18) {
                name = jsonObject.getJSONArray("states").getJSONObject(i).getString("name");
                System.out.println("State Name :: " + name+" , with respect to State Id :: 18");
            }
            if (state_id == 35) {
                name = jsonObject.getJSONArray("states").getJSONObject(i).getString("name");
                System.out.println("State Name :: " + name+" , with respect to State Id :: 35");
            }
            if (state_id == 28) {
                name = jsonObject.getJSONArray("states").getJSONObject(i).getString("name");
                System.out.println("State Name :: " + name+" , with respect to State Id :: 28");
            }
            if (state_id == 30) {
                name = jsonObject.getJSONArray("states").getJSONObject(i).getString("name");
                System.out.println("State Name :: " + name+" , with respect to State Id :: 30");
            }
            if (state_id == 12) {
                name = jsonObject.getJSONArray("states").getJSONObject(i).getString("name");
                System.out.println("State Name :: " + name+" , with respect to State Id :: 12");
            }
        }

        // validate Status Code
        APIBasicValidation.statusCodeValidation(response, 200);

        test.log(LogStatus.INFO,"Checking Response Headers..");

        APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
        APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
        APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
        APIBasicValidation.headerValidation(response,"Vary","Origin,Authorization,Accept-Encoding");

        // validate Response Schema and its Type
        APIBasicValidation.jsonResponseSchemaValidation(response,"getStatesJSONSchema.json");

        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"states");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"state_id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"name");

        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

    }
}
