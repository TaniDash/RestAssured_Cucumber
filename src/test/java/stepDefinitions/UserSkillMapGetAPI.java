package stepDefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import utils.ReadConfig;
import utils.XLUtility;

public class UserSkillMapGetAPI {
	RequestSpecification reqspec;
	Response response;
	static String first_User_Id;
	
	ReadConfig readConfig = new ReadConfig();
	XLUtility xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
	
	@Given("user provides baseUri for userSkillsMap with valid authetication details")
	public void user_provides_base_uri_for_user_skills_map_with_valid_authetication_details() {
		RestAssured.baseURI = readConfig.getBaseUri();
		reqspec = given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword());
	}

	@When("user sends GET request to get all users by providing valid endpoint")
	public void user_sends_get_request_to_get_all_users_by_providing_valid_endpoint() {
		response = reqspec.request(Method.GET, readConfig.getUserSkillsMapEndpoint());
//		System.out.println(response.getBody().asString());
	}
	
	@When("user sends GET request to get user with skill details by providing user id as path parameter")
	public void user_sends_get_request_to_get_user_with_skill_details_by_providing_user_id_as_path_parameter() {
		response = reqspec.request(Method.GET, readConfig.getUserSkillsMapEndpoint());
		
		JsonPath jp=response.jsonPath();
		first_User_Id=jp.get("users[2].id");
		
		if (first_User_Id != null) {
		} else {
			System.out.println("User id does not exist!");
		}

		String endpoint = readConfig.getUserSkillsMapEndpoint();
		String pathParam = endpoint + "/" + first_User_Id;
		response = reqspec.request(Method.GET, pathParam);
//		System.out.println(response.getBody().asString());
	}
	
	@When("user sends GET request to get all user details by providing skill id as path parameter")
	public void user_sends_get_request_to_get_all_user_details_by_providing_skill_id_as_path_parameter() {
		response = reqspec.request(Method.GET, readConfig.getUserSkillsMapEndpoint());
		JsonPath jsonpath = new JsonPath(response.asString());
		int size= jsonpath.getInt("skillmap.size()");
		System.out.println(size);
//		first_User_Id=jsonpath.getString("users[2].skillmap[0].id");
//		System.out.println(first_User_Id);
		
		if (first_User_Id != null) {
					System.out.println("first_UserId is: " + first_User_Id);
				} else {
					System.out.println("User id does not exist!");
				}

				String endpoint = readConfig.getUserSkillsMapEndpoint();
				String pathParam = endpoint + "/" + first_User_Id;
				response = reqspec.request(Method.GET, pathParam);
				System.out.println(response.getBody().asString());
	}
	
	@Then("user should be able to validate the specific user id in the response body")
	public void user_should_be_able_to_validate_the_specific_user_id_in_the_response_body() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(first_User_Id), true);
	}
	
	@Then("user should be able to validate the skill id in the response body")
	public void user_should_be_able_to_validate_the_skill_id_in_the_response_body() {
	  
	}

	
	@Then("user should receive valid status code as {int}")
	public void user_should_receive_valid_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Then("user should receive response content-type as {string}")
	public void user_should_receives_response_content_type_as(String string) {
		String expectedResponseContentType = response.getHeader("Content-Type");
		Assert.assertEquals(expectedResponseContentType, "application/json");
	}
}
