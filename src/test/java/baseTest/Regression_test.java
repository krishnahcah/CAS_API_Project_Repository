package baseTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import resources.SkippedTCs;
import utils.SkippedTCsReportGen;

public class Regression_test implements SkippedTCs
{

    @Test(priority = 0, groups = {"functest"})
    public void VerifyGetAPI() throws Exception {
        Demo demo = new Demo();
//        demo.GetAPI_validate();
        demo.getmail();
    }

    @AfterTest(groups = {"functest", "AdeRegression"})
    public void endTest() {
        new SkippedTCsReportGen().generateSkippedTCsReport(skippedTCs);
    }

}
