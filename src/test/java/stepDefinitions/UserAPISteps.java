package stepDefinitions;

import org.json.simple.JSONObject;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import testBase.Testbase;
import utils.XLUtility;


public class UserAPISteps extends Testbase {
	Testcontext testContext;
	XLUtility xLUtility;

	public UserAPISteps(Testcontext testContext) {
		this.testContext = testContext;
		xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
	}


	@When("user sends GET request with valid endpoint")
	public void user_sends_get_request_with_endpoint_as() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserAPIEndpoint()).then().extract().response();
	}

	@When("user sends GET request with valid path parameter")
	public void user_sends_get_request_with_valid_path_parameter() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserAPIEndpoint()).then().extract().response();
		String responseBody = testContext.resp.getBody().asString();
	
		JsonPath jsonpath = testContext.resp.jsonPath();
		String firstUserId = jsonpath.getString("user_id[0]");

		if (firstUserId != null) {
			System.out.println("firstUserId is: " +firstUserId);
		} else {
			System.out.println("User id does not exist!");
		}
		String endpoint = readConfig.getUserAPIEndpoint();
		String pathParam = endpoint + "/" + firstUserId;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
	}

	@When("user sends GET request with invalid user id as path parameter")
	public void user_sends_get_request_with_invalid_user_id_as_path_parameter() {
		int rnNum=getRandom();
		String invalidUserId = rnNum+"";
		String endpoint = readConfig.getUserAPIEndpoint();
		String pathParam = endpoint + "/" + invalidUserId;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();

	}

	@When("user sends POST request with valid Json body and header")
	public void user_sends_post_request_with_valid_json_body() throws InterruptedException {
		JSONObject payload = new JSONObject();
		String comments = xLUtility.getCellData("UserAPI", 1, 0);
		String educationPg = xLUtility.getCellData("UserAPI", 1, 1);
		String educationUg = xLUtility.getCellData("UserAPI", 1, 2);
		String linkedin = xLUtility.getCellData("UserAPI", 1, 3);
		String location = xLUtility.getCellData("UserAPI", 1, 4);
		String name = xLUtility.getCellData("UserAPI", 1, 5);
		String phone = xLUtility.getCellData("UserAPI", 1, 6);
		String timeZone = xLUtility.getCellData("UserAPI", 1, 7);
		String visa = xLUtility.getCellData("UserAPI", 1, 8);
		
		int rnNum = getRandom();
		String phoneNum = phone + rnNum;
		payload.put("comments", comments);
		payload.put("education_pg", educationPg);
		payload.put("education_ug", educationUg);
		payload.put("linkedin_url", linkedin);
		payload.put("location", location);
		payload.put("name", name);
		payload.put("phone_number", phoneNum);
		payload.put("time_zone", timeZone);
		payload.put("visa_status", visa);

		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getUserAPIEndpoint()).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}

	@When("user sends POST request with existing phone number")
	public void user_sends_post_request_with_existing_phone_number() throws InterruptedException {
		JSONObject payload = new JSONObject();
		String comments = xLUtility.getCellData("UserAPI", 1, 0);
		String educationPg = xLUtility.getCellData("UserAPI", 1, 1);
		String educationUg = xLUtility.getCellData("UserAPI", 1, 2);
		String linkedin = xLUtility.getCellData("UserAPI", 1, 3);
		String location = xLUtility.getCellData("UserAPI", 1, 4);
		String name = xLUtility.getCellData("UserAPI", 1, 5);
		String phone = xLUtility.getCellData("UserAPI", 1, 6);
		String timeZone = xLUtility.getCellData("UserAPI", 1, 7);
		String visa = xLUtility.getCellData("UserAPI", 1, 8);
		
		payload.put("comments", comments);
		payload.put("education_pg", educationPg);
		payload.put("education_ug", educationUg);
		payload.put("linkedin_url", linkedin);
		payload.put("location", location);
		payload.put("name", name);
		payload.put("time_zone", timeZone);
		payload.put("visa_status", visa);

		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getUserAPIEndpoint()).then().extract().response();
		
		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getUserAPIEndpoint()).then().extract().response();
	}

	@When("user sends PUT request with updated valid Json body providing specific userId as path parameter")
	public void user_sends_put_request_with_updated_valid_json_body_providing_specific_user_id_as_path_parameter()throws InterruptedException {
		JSONObject Updatedpayload = new JSONObject();
		String comments = xLUtility.getCellData("UserAPI", 1, 0);
		String educationPg = xLUtility.getCellData("UserAPI", 1, 1);
		String educationUg = xLUtility.getCellData("UserAPI", 1, 2);
		String linkedin = xLUtility.getCellData("UserAPI", 1, 3);
		String location = xLUtility.getCellData("UserAPI", 1, 4);
		String name = xLUtility.getCellData("UserAPI", 1, 5);
		String phone = xLUtility.getCellData("UserAPI", 1, 6);
		String timeZone = xLUtility.getCellData("UserAPI", 1, 7);
		String visa = xLUtility.getCellData("UserAPI", 1, 8);
		
		int rnNum=getRandom();
		String phoneNum=phone+rnNum;
		System.out.println(phoneNum);
		
		Updatedpayload.put("comments", comments);
		Updatedpayload.put("education_pg", educationPg);
		Updatedpayload.put("education_ug", educationUg);
		Updatedpayload.put("linkedin_url", linkedin);
		Updatedpayload.put("location", location);
		Updatedpayload.put("name", name);
		Updatedpayload.put("phone_number", phoneNum);
		Updatedpayload.put("time_zone", timeZone);
		Updatedpayload.put("visa_status", visa);

		testContext.newUserIDForUserAPI=getNewUserIDForUserAPI();
		testContext.reqspec.headers("Content-Type", "application/json").body(Updatedpayload.toJSONString());
		testContext.resp = testContext.reqspec.when().put(readConfig.getUserAPIEndpoint() + "/" +testContext.newUserIDForUserAPI).then()
				.extract().response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);

	}

	@When("user sends DELETE request for specific userId")
	public void user_sends_delete_request_for_specific_user_id() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().delete(readConfig.getUserAPIEndpoint() + "/" +testContext.newUserIDForUserAPI).then()
				.extract().response();
		Thread.sleep(3000);
	}

	@When("user sends GET request for non-existing userId")
	public void user_sends_delete_request_for_already_deleted_or_non_existing_user_id() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserAPIEndpoint()+"/"+testContext.newUserIDForUserAPI).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}


	@And("user should be able to validate the the response body for userAPI")
	public void user_should_be_able_to_validate_the_user_id_in_the_response_body() {
		String responseBody = testContext.resp.getBody().asString();
		Assert.assertEquals(responseBody.isEmpty(), false);
	
	}

	@And("user should be able to validate json schema for userAPI")
	public void user_should_be_able_to_validate_json_schema_for_user_api() {
		testContext.resp.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema\\userAPI.json"));
	}


}
