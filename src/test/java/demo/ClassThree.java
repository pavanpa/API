package demo;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;

public class ClassThree {
	@Test
	public void createRepo()
	{
		Reporter.log("Create Repo",true);
		AssertJUnit.assertEquals(2, 1);
		
	}
	
	@Test(groups = "p1",priority = 1,dependsOnMethods = "createRepo")
	public void updateRepo()
	{
		Reporter.log("Update Repo",true);
		AssertJUnit.assertEquals(1, 1);
		
	}
	
	@Test(priority = 2,dependsOnMethods = "createRepo")
	public void deleteRepo()
	{
		Reporter.log("Delete Repo",true);
		
	}

}
