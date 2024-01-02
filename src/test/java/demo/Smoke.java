package demo;

import org.testng.annotations.Test;

public class Smoke {
	//method names are camel case
	
		@Test
		public void testMethodOne() {
			System.out.println("Method one");
		}
		
		@Test
		public void testMethodTwo() {
			System.out.println("Method Two");
		}
		
		@Test
		public void testMethodThree() {
			System.out.println("Method Three");
		}

}
