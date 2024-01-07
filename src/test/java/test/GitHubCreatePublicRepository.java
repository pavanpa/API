package test;

import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.RestAssured;

public class GitHubCreatePublicRepository extends BaseClass
{
	String url="orgs/{org}/repos";
	Random randomNum = new Random();
	@Test
	public void githubCreateValidPublicRepo()
	{
		
		String repoName1="repo_TC1_Public_"+randomNum.nextInt(1000);
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",token)
				.body("{\"name\":\""+repoName1+"\"}")
				
			.when()
				.post(url)
			.then()
				
				.statusCode(201)
				.body("name", Matchers.equalTo(repoName1));
	}
	@Test
	public void githubCreatePublicRepoNoAuth()
	{
		String repoName1="repo_TC2_Public_"+randomNum.nextInt(1000);
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.body("{\"name\":\""+repoName1+"\"}")
			.when()
				.post(url)
			.then()
				
				.statusCode(401);
	}
	
	@Test
	public void githubCreatePublicRepoInvalidOrg()
	{
		String repoName1="repo_TC3_Public_"+randomNum.nextInt(1000);
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90909")
				.header("Authorization",token)
				.body("{\"name\":\""+repoName1+"\"}")
			.when()
				.post(url)
			.then()
				
				.statusCode(404);
	}
	
	@Test
	public void githubCreateValidPublicRepoNoScopeAuth()
	{
		
		String repoName1="repo_TC4_Public_"+randomNum.nextInt(1000);
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",deleteToken)
				.body("{\"name\":\""+repoName1+"\"}")
				
			.when()
				.post(url)
			.then()
				
				.statusCode(401);
	}
	
	@Test
	public void githubCreateValidPublicDuplicateRepo()
	{
		
		
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",token)
				.body("{\"name\":\"repo_23\"}")
				
			.when()
				.post(url)
			.then()
				
				.statusCode(422);
	}

}
