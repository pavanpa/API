package demo;

import org.testng.annotations.Test;

public class ClassOne {
	@Test(groups = "p1",priority = 1)
	public void testMethodOne() {
		System.out.println("Method one");
	}
	
	@Test(groups = {"special","p1"})
	public void testMethodTwo() {
		System.out.println("Method Two");
	}
	
	@Test
	public void testMethodThree() {
		System.out.println("Method Three");
	}
	
}
