package test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import core.BaseClass;
import io.restassured.RestAssured;

public class GitHubGetRepository extends BaseClass{
	
	String url="repos/{org}/{repo}";
	
	@Test
	public void gitHubGetPublicRepo()
	{
		//Rest Assured code to the scenario
		//test=extent.createTest("gitHubGetPublicRepo");
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.pathParam("repo", "repo_dec_27_05")
			.when()
				.get(url)
			.then()
				.statusCode(200)
				.body("name", Matchers.equalTo("repo_dec_27_05"));
	}
	
	@Test
	public void gitHubGetPrivateRepo()
	{
		//test=extent.createTest("gitHubGetPrivateRepo");
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.pathParam("repo", "repo_dec_27_07")
				.header("Authorization","Bearer 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")
			.when()
				.get(url)
			.then()
				.statusCode(200)
				.body("name", Matchers.equalTo("repo_dec_27_07"))
				.body("private",Matchers.equalTo(true));
	}
	
	@Test
	public void gitHubGetPrivateRepoNoAuth()
	{
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.pathParam("repo", "Dec_13_Auto_03")
			.when()
				.get(url)
			.then()
				.log().body()
				.statusCode(404)
				.body("message", Matchers.equalTo("Not Found"));
	}
	
	@Test
	public void gitHubGetPublicRepoInvalidOrg()
	{
		RestAssured
			.given()
				.pathParam("org", "orgpavanb901")
				.pathParam("repo", "Dec_11_Auto_03")
			.when()
				.get(url)
			.then()
			.log().body()
				.statusCode(404)
				.body("message", Matchers.equalTo("Not Found"));
	}
	
	@Test
	public void gitHubGetInvalidPublicRepo()
	{
		RestAssured
			.given()
				.pathParam("org", "orgpavanb90")
				.pathParam("repo", "Dec_11_Auto_03123")
			.when()
				.get(url)
			.then()
				.log().body()
				.statusCode(404)
				.body("message", Matchers.equalTo("Not Found"));
	}

}
