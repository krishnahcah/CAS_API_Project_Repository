import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utility.BaseTest;

public class Extentreportclasses extends BaseTest {

	@Test
	public void test1() throws IOException
	{

		extentTest = extent.createTest("MyFirstTest", "Sample description");
		Assert.assertTrue(true);
		extentTest.log(Status.PASS, "Test has been passed.");  
	}

	@AfterTest
	public void teardown()
	{

		extent.flush();
	}	
}
