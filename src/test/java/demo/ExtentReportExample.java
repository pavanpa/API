package demo;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportExample {
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	@BeforeClass
	public void beforeClass()
	{
		extent = new ExtentReports();
		spark =new ExtentSparkReporter("test-output/reports/report.html");
		extent.attachReporter(spark);
	}
	@Test
	public void testMethodOne()
	{
		System.out.println("------ Executing Method one -------------");
	test=extent.createTest("ExtentReportExampleTest1");
	Assert.assertEquals(2, 1);
	}
	
	@Test
	public void testMethodTwo()
	{
		System.out.println("------ Executing Method two -------------");
	test=extent.createTest("ExtentReportExampleTest2");
	
	}
	
	@AfterMethod
	public void afterMethod(ITestResult iTestResult) {
		System.out.println("-------- Executing after each test -------------");
		boolean result = iTestResult.isSuccess();
		if(result) {
			System.out.println("------- Test Passed -----------");
			test.log(Status.PASS, "This test Passed");
		}
		else {
			System.out.println("------- Test Failed -----------");
			test.log(Status.FAIL, "This test failed");
		}
		extent.flush();
	}

}
