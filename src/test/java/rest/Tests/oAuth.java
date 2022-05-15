package rest.Tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import restAssured.pojo.API;
import restAssured.pojo.WebAutomation;
import restAssured.pojo.getCourse;

public class oAuth {

	@Test
	public void test() {

		String[] WebAutomationCourseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		String accessTokenResponse = given().urlEncodingEnabled(false).log().all()
				.queryParam("code", "4%2F0AX4XfWiuQLIUoG6gOxBbOJMUWvXHrEltbsm3uemlYSaVzPGr3rL5NbyQoKGEXoduDUBEVg")
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		// Actual Request to hit rahul shetty academy

		getCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(getCourse.class);

		List<API> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}

		// get the course name of web automation
		List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		// To store dynamic value use ArrayList
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < webAutomationCourses.size(); i++) {
			al.add(webAutomationCourses.get(i).getCourseTitle());
		}
		System.out.println(al);

		// convert arrays to arrayList
		List<String> expectedList = Arrays.asList(WebAutomationCourseTitles);
		System.out.println(expectedList);

		// get the instructor and url
		System.out.println("instructor :" + gc.getInstructor());
		System.out.println("url :" + gc.getUrl());

		Assert.assertTrue(al.equals(expectedList));

	}

}
