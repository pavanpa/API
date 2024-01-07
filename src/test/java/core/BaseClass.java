package core;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import utils.GenericUtils;

public class BaseClass {
	public String token;
	public static ExtentReports extent;
	public ExtentTest test;
	public String deleteToken;
	public String expiredToken;
	public static String env;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		
		env = GenericUtils.getPropertyValue("global", "ENV");
		
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/reports/report.html");
		extent.attachReporter(spark);
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		token = GenericUtils.getPropertyValue(env, "TOKEN");
		token = "Bearer "+token;
		
		deleteToken = GenericUtils.getPropertyValue(env, "DELETE_TOKEN");
		deleteToken = "Bearer "+deleteToken;
		
		expiredToken = GenericUtils.getPropertyValue(env, "EXPIRED_TOKEN");
		expiredToken = "Bearer "+expiredToken;
		
		RestAssured.baseURI=GenericUtils.getPropertyValue(env, "BASE_URI");
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		ResponseSpecBuilder resSpec = new ResponseSpecBuilder();
		resSpec.expectHeader("server", "GitHub.com");
//		resSpec.expectHeader("Content-Type", "application/json; charset=utf-8");
		
		RestAssured.responseSpecification = resSpec.build();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		test = extent.createTest(method.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult iTestResult) {
		System.out.println("-------- Executing after each test -------------");
		boolean result = iTestResult.isSuccess();		
		if(result) {
			System.out.println("------- Test Passed -----------");
			test.log(Status.PASS, "This test Passed");
		}
		else {
			String failureMsg = iTestResult.getThrowable().getMessage();
			System.out.println("------- Test Failed -----------");
			test.log(Status.FAIL, failureMsg);
		}
		extent.flush();
	}
}
