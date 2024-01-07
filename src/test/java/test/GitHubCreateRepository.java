package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.GitHubGetRepoRootPOJO;

public class GitHubCreateRepository extends BaseClass{
	
	
	String url="orgs/{org}/repos";
	Random randomNum = new Random();
	
	@Test
	public void githubCreatePublicRepo()
	{
		System.out.println(token);
		String repoName1="repo_Jan_02_Public_"+randomNum.nextInt(1000);
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",token)
				.body("{\"name\":\""+repoName1+"\"}")
				.log().all()
			.when()
				.post(url)
			.then()
				.log().all()
				.statusCode(201)
				.body("name", Matchers.equalTo(repoName1));
	}

	@Test(enabled = true)
	public void githubCreatePrivateRepo() 
	{
		FileInputStream fis = null;
		try 
		{
				fis = new FileInputStream("src\\test\\resources\\Data\\createprivaterepo.json");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",token)
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.post(url)
			.then()
				.log().all()
				.statusCode(422);
//				.body("name", Matchers.equalTo("Dec_29_Private_Repo_01"))
//				.body("private", Matchers.equalTo(true));
	}
	
	@Test
	public void githubCreatePrivateRepoPojoTest() 
	{
		
		
		String repoName="repo_Jan_02_"+randomNum.nextInt(10000000);
		GitHubGetRepoRootPOJO obj=new GitHubGetRepoRootPOJO();
		obj.setName(repoName);
		obj.setDescription("private repo using POJO");
		obj.setPrivateVal(true);
		
		GitHubGetRepoRootPOJO res =RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",token)
				.body(obj)
				.contentType(ContentType.JSON)
			.when()
				.post(url)
			.then()
				.statusCode(201)
				.extract().as(GitHubGetRepoRootPOJO.class);
		
		MatcherAssert.assertThat(res.getName(), Matchers.equalTo(repoName));
		MatcherAssert.assertThat(res.getDescription(), Matchers.equalTo("private repo using POJO"));
		MatcherAssert.assertThat(res.isPrivateVal(), Matchers.equalTo(true));
	}
}
