package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

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
import utils.GenaricUtils;

public class BaseClass {
	
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	public static String token;
	public String deleteToken;
	public String env;
	public GenaricUtils gUtils;
	
	@BeforeSuite
	public void beforeSuite()
	{
		gUtils = new GenaricUtils();
		env=gUtils.getPropertyValue("global", "ENV");
		token = gUtils.getPropertyValue("global", "P_TOK");
		token="Bearer "+token;
		System.out.println(token);
		deleteToken = gUtils.getPropertyValue(env, "DELETE_TOKEN");
//		deleteToken = "Bearer "+deleteToken;
		RestAssured.baseURI=gUtils.getPropertyValue(env, "P_BASE_URI");
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		ResponseSpecBuilder resSpec = new ResponseSpecBuilder();
		resSpec.expectHeader("server", "GitHub.com");
		resSpec.expectHeader("Content-Type", "application/json; charset=utf-8");
		
		RestAssured.responseSpecification=resSpec.build();
		extent = new ExtentReports();
		spark =new ExtentSparkReporter("test-output/reports/report.html");
		extent.attachReporter(spark);
	}
	@BeforeClass(alwaysRun = true)
	public void beforeClass()
	{
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method)
	{
		test=extent.createTest(method.getName());
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult iTestResult) 
	{
		System.out.println("-------- Executing after each test -------------");
		boolean result = iTestResult.isSuccess();
		if(result) 
		{
			System.out.println("------- Test Passed -----------");
			test.log(Status.PASS, "This test Passed");
		}
		else 
		{
			System.out.println("------- Test Failed -----------");
			test.log(Status.FAIL, "This test failed");
		}
		extent.flush();
	}

}
