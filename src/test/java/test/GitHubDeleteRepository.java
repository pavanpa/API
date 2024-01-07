package test;

import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GitHubDeleteRepository extends BaseClass
{
	String url="repos/{org}/{repos}";
	
	@Test
	public void deletePublicRepository()
	{
		RestAssured
		.given()
			.pathParam("org", "orgpavanb90")
			.pathParam("repos", "Dec_29_Private_Repo_01")
			.header("Authorization",deleteToken)
			.contentType(ContentType.JSON)
			.log().all()
		.when()
			.delete(url)
		.then()
		.log().all()
			.statusCode(204);
	}

}
