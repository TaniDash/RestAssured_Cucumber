package stepDefinitions;

import static io.restassured.RestAssured.given;
import java.util.Random;
import org.json.simple.JSONObject;
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

public class UserAPIStepDef {
	RequestSpecification reqspec;
	Response response;
	static String firstUserId;
	static String name;
	static String phoneNum;

	ReadConfig readConfig = new ReadConfig();
	XLUtility xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");

	@Given("user provides baseUri with valid authetication details")
	public void user_provides_base_uri_with_valid_authetication_details() {
		RestAssured.baseURI = readConfig.getBaseUri();
		reqspec = given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword());

	}

	@When("user sends GET request with valid endpoint")
	public void user_sends_get_request_with_endpoint_as() {
		response = reqspec.request(Method.GET, readConfig.getEndpoint());
	}

	@When("user sends GET request with valid path parameter")
	public void user_sends_get_request_with_valid_path_parameter() {
		response = reqspec.request(Method.GET, readConfig.getEndpoint());

		JsonPath jsonpath = response.jsonPath();
//		firstUserId = jsonpath.getString("user_id[0]");

		if (firstUserId != null) {
			System.out.println("firstUserId is: " + firstUserId);
		} else {
			System.out.println("User id does not exist!");
		}

		String endpoint = readConfig.getEndpoint();
		String pathParam = endpoint + "/" + firstUserId;
		response = reqspec.request(Method.GET, pathParam);

	}

	@When("user sends POST request with valid Json body")
	public void user_sends_post_request_with_valid_json_body() throws InterruptedException {
		JSONObject payload = new JSONObject();

		String comments = xLUtility.getCellData("UserAPI", 1, 0);
		String educationPg = xLUtility.getCellData("UserAPI", 1, 1);
		String educationUg = xLUtility.getCellData("UserAPI", 1, 2);
		String linkedin = xLUtility.getCellData("UserAPI", 1, 3);
		String location = xLUtility.getCellData("UserAPI", 1, 4);
		name = xLUtility.getCellData("UserAPI", 1, 5);
		String phone = xLUtility.getCellData("UserAPI", 1, 6);
		String timeZone = xLUtility.getCellData("UserAPI", 1, 7);
		String visa = xLUtility.getCellData("UserAPI", 1, 8);

		int randomNum = new Random().nextInt(999);
		phoneNum = phone + randomNum;

		payload.put("comments", comments);
		payload.put("education_pg", educationPg);
		payload.put("education_ug", educationUg);
		payload.put("linkedin_url", linkedin);
		payload.put("location", location);
		payload.put("name", name);
		payload.put("phone_number", phoneNum);
		payload.put("time_zone", timeZone);
		payload.put("visa_status", visa);

		reqspec.header("Content-Type", "application/json").body(payload.toJSONString());
		response = reqspec.request(Method.POST, readConfig.getEndpoint());

	}

	@When("user sends PUT request with updated valid Json body providing specific userId as path parameter")
	public void user_sends_put_request_with_updated_valid_json_body_providing_specific_user_id_as_path_parameter() {
		JSONObject Updatedpayload = new JSONObject();

		String comments = xLUtility.getCellData("UserAPI", 1, 0);
		String educationPg = xLUtility.getCellData("UserAPI", 1, 1);
		String educationUg = xLUtility.getCellData("UserAPI", 1, 2);
		String linkedin = xLUtility.getCellData("UserAPI", 1, 3);
		String location = xLUtility.getCellData("UserAPI", 1, 4);
		name = xLUtility.getCellData("UserAPI", 1, 5);
		String phone = xLUtility.getCellData("UserAPI", 1, 6);
		String timeZone = xLUtility.getCellData("UserAPI", 1, 7);
		String visa = xLUtility.getCellData("UserAPI", 1, 8);

		int randomNum = new Random().nextInt(999);
		phoneNum = phone + randomNum;

		Updatedpayload.put("comments", comments);
		Updatedpayload.put("education_pg", educationPg);
		Updatedpayload.put("education_ug", educationUg);
		Updatedpayload.put("linkedin_url", linkedin);
		Updatedpayload.put("location", location);
		Updatedpayload.put("name", name);
		Updatedpayload.put("phone_number", phoneNum);
		Updatedpayload.put("time_zone", timeZone);
		Updatedpayload.put("visa_status", visa);

		reqspec.header("Content-Type", "application/json").body(Updatedpayload.toJSONString());
		response = reqspec.request(Method.PUT, readConfig.getEndpoint() + "/" + firstUserId);
	}

	@When("user sends DELETE request for specific userId")
	public void user_sends_delete_request_for_specific_user_id() throws InterruptedException {
		response = reqspec.request(Method.DELETE, readConfig.getEndpoint()+"/"+firstUserId);
		System.out.println(response.getBody().asString());
		Thread.sleep(6000);
	}

	@Then("user receives valid status code as {int}")
	public void user_receives_valid_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("user gets valid status code as {int}")
	public void user_gets_valid_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 201);
	}

	@Then("user receives response content-type as {string}")
	public void user_receives_response_content_type_as(String contentType) {
		String expectedResponseContentType = response.getHeader("Content-Type");
		Assert.assertEquals(expectedResponseContentType, "application/json");
	}

	@Then("user should be able to validate the user id in the response body")
	public void user_should_be_able_to_validate_the_user_id_in_the_response_body() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(firstUserId), true);
	}

	@Then("user should be able to validate the response body")
	public void user_should_be_able_to_validate_the_response_body() {
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(phoneNum), true);
	}

	@Then("user should be able to validate the updated response body")
	public void user_should_be_able_to_validate_the_updated_response_body() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(firstUserId), true);
		Assert.assertEquals(responseBody.contains(phoneNum), true);
	}

	@Then("user should be able to validate the message {string} in the response body")
	public void user_should_be_able_to_validate_the_message_in_the_response_body(String string) {
		String expectedMessage = response.jsonPath().get("message_response");
		System.out.println(expectedMessage);
		Assert.assertEquals(expectedMessage, "Successfully Created !!");
	}

	@Then("user should be able to validate the updated message {string} in the response body")
	public void user_should_be_able_to_validate_updated_the_message_in_the_response_body(String string) {
		String expectedMessage = response.jsonPath().get("message_response");
		System.out.println(expectedMessage);
		Assert.assertEquals(expectedMessage, "Successfully Updated !!");
	}

	@Then("user should be able to validate the deleted message {string} in the response body")
	public void user_should_be_able_to_validate_the_deleted_message_in_the_response_body(String string) {
		String expectedMessage = response.jsonPath().get("message_response");
		System.out.println(expectedMessage);
		Assert.assertEquals(expectedMessage, "The record has been deleted !!");
	}

}
