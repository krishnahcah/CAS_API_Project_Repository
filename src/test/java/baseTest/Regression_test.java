package baseTest;

import CAS_API.SendOTP_TCs.SendOPT_Valid_Mobile_Info;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.SkippedTCs;
import utils.SkippedTCsReportGen;

public class Regression_test extends BaseTest implements SkippedTCs
{
//    @BeforeClass(groups = {"functest", "AdeRegression"})
//    public void startTest() {
//        testClass =reports.startTest("TESTCASES -->>"+Thread.currentThread().getStackTrace()[1].getClassName());
//        testClass.log(LogStatus.INFO," >>> TC-"+Thread.currentThread().getStackTrace()[1].getClassName()+" Started... <<< ");
//    }

    @Test(priority = 0, groups = {"functest", "AdeRegression"})
    public void CheckResponseWithValidMobileNumber() throws Exception {

        SendOPT_Valid_Mobile_Info sendOPT_valid_mobile_info = new SendOPT_Valid_Mobile_Info();
        sendOPT_valid_mobile_info.CheckResponseWithValidMobileNumber();
    }

    @AfterTest(groups = {"functest", "AdeRegression"})
    public void endTest() {
        new SkippedTCsReportGen().generateSkippedTCsReport(skippedTCs);
    }

}
