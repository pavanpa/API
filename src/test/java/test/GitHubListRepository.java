package test;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.RestAssured;
import pojo.GitHubGetRepoRootPOJO;

public class GitHubListRepository extends BaseClass
{
	String url="orgs/{org}/repos";
	@Test
	public void listPublicRepository()
	{
		GitHubGetRepoRootPOJO[] res=RestAssured
		.given()
			.pathParam("org", "orgpavanb90")
			.queryParam("per_page", 10)
			.queryParam("type", "public")
			.log().all()
		.when()
			.get(url)
		.then()
		.log().all()
			.statusCode(200)
			.extract().as(GitHubGetRepoRootPOJO[].class);
		
		String loginVal =res[0].getOwner().getLogin();
		MatcherAssert.assertThat(loginVal, Matchers.equalTo("orgpavanb90"));
		
		String typeVal =res[0].getOwner().getType();
		MatcherAssert.assertThat(typeVal, Matchers.equalTo("Organization"));
		
		boolean site_adminVal =res[0].getOwner().isSite_admin();
		MatcherAssert.assertThat(site_adminVal, Matchers.equalTo(false));
	}
	
	@Test
	public void listPrivateRepository()
	{
		GitHubGetRepoRootPOJO res=RestAssured
		.given()
			.pathParam("org", "orgpavanb90")
			.header("Authorization",token)
			.queryParam("per_page", 10)
			.queryParam("type", "private")
			.log().all()
		.when()
			.get(url)
		.then()
		.log().all()
			.statusCode(200)
			.extract().as(GitHubGetRepoRootPOJO.class);
		
		String loginVal =res.getOwner().getLogin();
		MatcherAssert.assertThat(loginVal, Matchers.equalTo("orgpavanb90"));
		
		String typeVal =res.getOwner().getType();
		MatcherAssert.assertThat(typeVal, Matchers.equalTo("Organization"));
		
		boolean site_adminVal =res.getOrganization().isSite_admin();
		MatcherAssert.assertThat(site_adminVal, Matchers.equalTo(false));
	}
}
