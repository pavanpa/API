package demo;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class ClassTwo {
	
	//method names are camel case
	
		@Test(priority = 0)
		public void createRepo() {
			System.out.println("Create Repo");
			AssertJUnit.assertEquals(1, 2);
		}
		
		@Test(priority = 1,groups = "p1")
		public void updateRepo() {
			System.out.println("Update Repo");
		}
		
		@Test(priority = 2,groups = "p1")
		public void deleteRepo() {
			System.out.println("Delete Repo");
		}

}
