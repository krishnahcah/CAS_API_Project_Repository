package utility;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utility.BaseTest;
public class Listeners extends TestListenerAdapter{


	public ExtentHtmlReporter htmlReporter; 

	public ExtentReports extent; 
	public ExtentTest test;


	public void onStart(ITestContext testContext)
	{
		/*******************************************************
			Setup and Initialize Extent Reporting
		 ******************************************************/

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReportFolder/myExtentReport.html");
		htmlReporter.config().setDocumentTitle("CAS Automation Report"); // Tile of report
		htmlReporter.config().setReportName("CAS-API Testing Report"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();// create ExtentReports and attach reporter(s)
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name",FrameworkUtility.readConfigurationFile("CAS_Base_URI"));
		extent.setSystemInfo("Environment",FrameworkUtility.readConfigurationFile("Enviroment"));
		/*******************************************************
				End of Extent Reporting Setup
		 ******************************************************/
	}

	public void onTestSuccess(ITestResult result)
	{

		test=extent.createTest(result.getName()); // create new entry in the report

		test.log(Status.PASS, "TEST CASE PASSED IS :" + result.getName());
	}

	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report

		test.log(Status.FAIL, "TEST CASE FAILED IS :" + result.getName()); // to add name in extent report
		test.log(Status.FAIL, "TEST CASE FAILED DUE TO :" + result.getThrowable()); // to add error/exception in extent report

	}

	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED IS :" + result.getName());
	}

	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
