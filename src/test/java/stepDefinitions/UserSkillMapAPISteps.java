package stepDefinitions;

import org.json.simple.JSONObject;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import testBase.Testbase;
import utils.XLUtility;

public class UserSkillMapAPISteps extends Testbase{
	Testcontext testContext;
	XLUtility xLUtility;
	int rnNum;

	//static String user_skill_id;	
	static String user_id;	
	static String skill_id;	
	
	public UserSkillMapAPISteps(Testcontext testContext) {
		this.testContext = testContext;
		xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
	}
	
	@When("user sends GET request with valid endpoint for userSKillMapAPI")
	public void user_sends_get_request_with_valid_endpoint_for_user_s_kill_map_api() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserSkillMapAPIEnd()).then().extract().response();
	}
	
	@When("user sends GET request with valid user skill id as path parameter")
	public void user_sends_get_request_with_valid_user_skill_is_as_path_parameter() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserSkillMapAPIEnd()).then().extract().response();
		JsonPath jsonpath = testContext.resp.jsonPath();
		String user_skill_id = jsonpath.getString("user_skill_id[0]");

		if (user_skill_id != null) {
			System.out.println("user_skill_id is: " + user_skill_id);
		} else {
			System.out.println("user_skill_id does not exist!");
		}
		
		String endpoint = readConfig.getUserSkillMapAPIEnd();
		String pathParam = endpoint + "/" + user_skill_id;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
		System.out.println(testContext.resp.body().asString());
	}
	
	@When("user sends GET request with invalid user skill id as path parameter")
	public void user_sends_get_request_with_invalid_user_skill_id_as_path_parameter() {
		String endpoint = readConfig.getUserSkillMapAPIEnd();
		int rn=getRandom();
		String pathParam = endpoint + "/" +rn;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
		System.out.println(testContext.resp.body().asString());
	}
	
	
	@When("user sends POST request with valid Json body and header for userSkillMapAPI")
	public void user_sends_post_request_with_valid_json_body_and_header_for_user_skill_map_api() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserSkillMapAPIEnd()).then().extract().response();
		JsonPath jsonpath = testContext.resp.jsonPath();
		String user_id_for_userSkillMap = jsonpath.getString("user_id[0]");
		String skill_id_for_userSkillMap = jsonpath.getString("skill_id[0]");

		JSONObject payload = new JSONObject();
		String months_of_exp = xLUtility.getCellData("UserSkillMapAPI", 1, 2);

		payload.put("user_id", user_id_for_userSkillMap);
		payload.put("skill_id", skill_id_for_userSkillMap);
		payload.put("months_of_exp", months_of_exp);

		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getUserSkillMapAPIEnd()).then().extract()
				.response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}
	
	@When("user sends POST request with invalid Json body and header for userSkillMapAPI")
	public void user_sends_post_request_with_invalid_json_body_and_header_for_user_skill_map_api() throws InterruptedException {
		JSONObject payload = new JSONObject();
		String months_of_exp = xLUtility.getCellData("UserSkillMapAPI", 1, 2);
		payload.put("user_id", 0);
		payload.put("skill_id", 0);
		payload.put("months_of_exp", months_of_exp);

		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getUserSkillMapAPIEnd()).then().extract()
				.response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}
	
	
	@When("user sends PUT request with valid users skill id as path parameter")
	public void user_sends_put_request_with_valid_users_skill_id_as_path_parameter() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().get(readConfig.getUserSkillMapAPIEnd()).then().extract().response();
		JsonPath jsonpath = testContext.resp.jsonPath();
		String user_id_for_userSkillMap = jsonpath.getString("user_id[0]");
		String skill_id_for_userSkillMap = jsonpath.getString("skill_id[0]");

		JSONObject updatePayload = new JSONObject();
		String months_of_exp = xLUtility.getCellData("UserSkillMapAPI", 1, 2);

		updatePayload.put("user_id", user_id_for_userSkillMap);
		updatePayload.put("skill_id", skill_id_for_userSkillMap);
		updatePayload.put("months_of_exp", months_of_exp);
		
		testContext.newUserIdForUserSkillMapAPI=getNewUserIDForUserSkillMapAPI();
		testContext.reqspec.headers("Content-Type", "application/json").body(updatePayload.toJSONString());
		testContext.resp = testContext.reqspec.when().put(readConfig.getUserSkillMapAPIEnd() + "/" +testContext.newUserIdForUserSkillMapAPI).then()
				.extract().response();
		System.out.println(testContext.resp.asString());
		Thread.sleep(3000);
	}
	
	@When("user sends PUT request with invalid path parameter")
	public void user_sends_put_request_with_invalid_users_skill_id_as_path_parameter() throws InterruptedException {
		JSONObject updatedPayload = new JSONObject();
		String user_id = xLUtility.getCellData("UserSkillMapAPI", 1, 0);
		String skill_id = xLUtility.getCellData("UserSkillMapAPI", 1, 1);
		String months_of_exp = xLUtility.getCellData("UserSkillMapAPI", 1, 2);		
		updatedPayload.put("user_id", user_id);
		updatedPayload.put("skill_id", skill_id);
		updatedPayload.put("months_of_exp", months_of_exp);

		testContext.reqspec.headers("Content-Type", "application/json").body(updatedPayload.toJSONString());
		testContext.resp = testContext.reqspec.when().put(readConfig.getUserSkillMapAPIEnd() + "/" + null).then()
				.extract().response();
		System.out.println(testContext.resp.asString());
		Thread.sleep(3000);
	}
	
	@When("user sends DELETE request for specific users skill id")
	public void user_sends_delete_request_for_specific_users_skill_id() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().delete(readConfig.getUserSkillMapAPIEnd()+ "/" + testContext.newUserIdForUserSkillMapAPI).then()
				.extract().response();
		Thread.sleep(3000);
	}
	
	@When("user sends DELETE request for already deleted or non-existing users skill id")
	public void user_sends_get_request_for_already_deleted_or_non_existing_users_skill_id() throws InterruptedException {
		int rn=getRandom();
		testContext.resp = testContext.reqspec.when().delete(readConfig.getUserAPIEndpoint() + "/" + testContext.newUserIdForUserSkillMapAPI).then()
				.extract().response();
		Thread.sleep(3000);
	}

	@Then("user should be able to validate the response body for userSkillMapAPI")
	public void user_should_be_able_to_validate_the_response_body_for_user_skill_map_api() {
		String body=testContext.resp.getBody().asString();
		Assert.assertEquals(body.isEmpty(), false);
		}

	
	@And("user should be able to validate json schema for userSkillMapAPI")
	public void user_should_be_able_to_validate_json_schema_for_user_skill_map_api() {
		testContext.resp.then().assertThat()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema\\UseSkillMapaPI.json"));
	}
	

	
	

	
	

	

}
