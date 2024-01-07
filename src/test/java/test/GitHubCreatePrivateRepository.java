package test;

import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.GitHubGetRepoRootPOJO;

public class GitHubCreatePrivateRepository extends BaseClass
{
	String url="orgs/{org}/repos";
	Random randomNum = new Random();
	@Test
	public void githubCreatePrivateRepo() 
	{
		
		
		String repoName="repo_TC1_Private_"+randomNum.nextInt(10000000);
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
	@Test
	public void githubCreatePrivateRepoExpiredToken() 
	{
		
		
		String repoName="repo_TC2_Private_"+randomNum.nextInt(10000000);
		GitHubGetRepoRootPOJO obj=new GitHubGetRepoRootPOJO();
		obj.setName(repoName);
		obj.setDescription("private repo using POJO");
		obj.setPrivateVal(true);
		
		GitHubGetRepoRootPOJO res =RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.header("Authorization",expiredToken)
				.body(obj)
				.contentType(ContentType.JSON)
			.when()
				.post(url)
			.then()
				.statusCode(401)
				.log().all()
				.extract().as(GitHubGetRepoRootPOJO.class);
		
//		MatcherAssert.assertThat(res.getName(), Matchers.equalTo(repoName));
//		MatcherAssert.assertThat(res.getDescription(), Matchers.equalTo("private repo using POJO"));
//		MatcherAssert.assertThat(res.isPrivateVal(), Matchers.equalTo(true));
	}
	@Test
	public void githubCreatePrivateRepoInvalidOrg() 
	{
		
		
		String repoName="repo_TC3_Private_"+randomNum.nextInt(10000000);
		GitHubGetRepoRootPOJO obj=new GitHubGetRepoRootPOJO();
		obj.setName(repoName);
		obj.setDescription("private repo using POJO");
		obj.setPrivateVal(true);
		
		GitHubGetRepoRootPOJO res =RestAssured
			.given()
				.pathParam("org", "orgpavanb9090")
				.header("Authorization",token)
				.body(obj)
				.contentType(ContentType.JSON)
			.when()
				.post(url)
			.then()
				.statusCode(404)
				.log().all()
				.extract().as(GitHubGetRepoRootPOJO.class);
		
//		MatcherAssert.assertThat(res.getName(), Matchers.equalTo(repoName));
//		MatcherAssert.assertThat(res.getDescription(), Matchers.equalTo("private repo using POJO"));
//		MatcherAssert.assertThat(res.isPrivateVal(), Matchers.equalTo(true));
	}
	@Test
	public void githubCreatePrivateRepoNoRepoName() 
	{
		
		
		//String repoName="repo_TC1_Private_"+randomNum.nextInt(10000000);
		GitHubGetRepoRootPOJO obj=new GitHubGetRepoRootPOJO();
	//	obj.setName(repoName);
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
				.statusCode(422)
				.extract().as(GitHubGetRepoRootPOJO.class);
		
//		MatcherAssert.assertThat(res.getName(), Matchers.equalTo(repoName));
//		MatcherAssert.assertThat(res.getDescription(), Matchers.equalTo("private repo using POJO"));
//		MatcherAssert.assertThat(res.isPrivateVal(), Matchers.equalTo(true));
	}
	@Test
	public void githubCreatePrivateRepoDuplicateRepoName() 
	{
		
		
		//String repoName="repo_TC1_Private_"+randomNum.nextInt(10000000);
		GitHubGetRepoRootPOJO obj=new GitHubGetRepoRootPOJO();
		obj.setName("repo_23");
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
				.statusCode(422)
				.extract().as(GitHubGetRepoRootPOJO.class);
		
//		MatcherAssert.assertThat(res.getName(), Matchers.equalTo(repoName));
//		MatcherAssert.assertThat(res.getDescription(), Matchers.equalTo("private repo using POJO"));
//		MatcherAssert.assertThat(res.isPrivateVal(), Matchers.equalTo(true));
	}
}
