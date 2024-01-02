package demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		System.out.println("====== Executing before method ========");
	}
	
	@BeforeMethod(groups = "special")
	public void beforeMethod1() {
		System.out.println("====== Executing before method 1========");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("====== Executing after method ========");
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("****** Executing Before Class ******");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("****** Executing After Class ******");
	}

}
