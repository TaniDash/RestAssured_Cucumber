package stepDefinitions;

import org.json.simple.JSONObject;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import testBase.Testbase;
import utils.XLUtility;

public class SkillAPISteps extends Testbase {
	Testcontext testContext;
	XLUtility xLUtility;
	int rnNum;
	CommonStepDef commonStepDef;


	public SkillAPISteps(Testcontext testContext) {
		this.testContext = testContext;
		xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
		commonStepDef=new CommonStepDef(testContext);
	}

	@When("user sends GET request with valid endpoint for skillAPI")
	public void user_sends_get_request_with_valid_endpoint_for_skill_api() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getSkillAPIEndpoint()).then().extract().response();
	}

	@When("user sends GET request with valid endpoint and specific skill id")
	public void user_sends_get_request_with_valid_endpoint_and_specific_skill_id() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getSkillAPIEndpoint()).then().extract().response();
		String body=testContext.resp.getBody().asString();	
		JsonPath jsonpath = testContext.resp.jsonPath();
		testContext.Skill_ID = jsonpath.getInt("skill_id[0]");
		System.out.println(testContext.Skill_ID);
		
		if (testContext.Skill_ID != 0) {
			System.out.println("Skill_ID is: " + testContext.Skill_ID);
		} else {
			System.out.println("Skill_ID does not exist!");
		}		
		
		String endpoint = readConfig.getSkillAPIEndpoint();
		String pathParam = endpoint + "/" +testContext.Skill_ID;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
		System.out.println(testContext.resp.body().asString());
	}

	@When("user sends POST request for skillAPI with valid Json body and header")
	public void user_sends_post_request_for_skill_api_with_valid_json_body_and_header() throws InterruptedException {
		JSONObject payload = new JSONObject();
		String skillName = xLUtility.getCellData("SkillAPI", 1, 0);
		rnNum = getRandom();
		String skillNameRandom = skillName + rnNum;
		payload.put("skill_name", skillNameRandom);

		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getSkillAPIEndpoint()).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}

	@When("user sends POST request for skillAPI with valid Json body and header already existing")
	public void user_sends_post_request_for_skill_api_with_valid_json_body_and_header_already_existing()
			throws InterruptedException {
		JSONObject payload = new JSONObject();
		String skillName = xLUtility.getCellData("SkillAPI", 1, 0);
		payload.put("skill_name", skillName);

		testContext.reqspec.headers("Content-Type", "application/json").body(payload.toJSONString());
		testContext.resp = testContext.reqspec.when().post(readConfig.getSkillAPIEndpoint()).then().extract().response();
		testContext.resp = testContext.reqspec.when().post(readConfig.getSkillAPIEndpoint()).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}

	@When("user sends PUT request with updated valid Json body and specific skill id")
	public void user_sends_put_request_with_updated_valid_json_body_and_specific_skill_id()throws InterruptedException {
		JSONObject updatedPayload = new JSONObject();
		String skillName = xLUtility.getCellData("SkillAPI", 1, 0);
		rnNum = getRandom();
		String skillNameRandom = skillName + rnNum;
		updatedPayload.put("skill_name", skillNameRandom);

		testContext.reqspec.headers("Content-Type", "application/json").body(updatedPayload.toJSONString());
		testContext.newSkill_ID=getNewSkillID();
		testContext.resp = testContext.reqspec.when().put(readConfig.getSkillAPIEndpoint() + "/" + testContext.newSkill_ID).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
		Thread.sleep(3000);
	}
	
	@When("user sends PUT request with updated valid Json body and invalid skill id")
	public void user_sends_put_request_with_updated_valid_json_body_and_invalid_skill_id() {
		JSONObject updatedPayload = new JSONObject();
		String skillName = xLUtility.getCellData("SkillAPI", 1, 0);
		rnNum = getRandom();
		String skillNameRandom = skillName + rnNum;
		updatedPayload.put("skill_name", skillNameRandom);

		testContext.reqspec.headers("Content-Type", "application/json").body(updatedPayload.toJSONString());
		int invalidSkill_ID=0;
		testContext.resp = testContext.reqspec.when().put(readConfig.getSkillAPIEndpoint() + "/" + invalidSkill_ID).then().extract().response();
		System.out.println(testContext.resp.getBody().asString());
	}
	

	@When("user sends DELETE request for specific skill id")
	public void user_sends_delete_request_for_specific_skill_id() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().delete(readConfig.getSkillAPIEndpoint() + "/" + testContext.newSkill_ID).then().extract().response();
		Thread.sleep(3000);
	}

	@When("user sends GET request for already deleted or non-existing skill id")
	public void user_sends_get_request_for_already_deleted_or_non_existing_skill_id() throws InterruptedException {
		testContext.resp = testContext.reqspec.when().delete(readConfig.getSkillAPIEndpoint() + "/" + testContext.newSkill_ID).then().extract().response();
	}

	@And("user should be able to find the new skill detail when he gets the new skill id")
	public void user_should_be_able_to_find_the_new_skill_detail_when_he_gets_the_new_skill_id() {
		int id = testContext.resp.jsonPath().getInt("skill_id");
		String endpoint = readConfig.getSkillAPIEndpoint();
		String pathParam = endpoint + "/" +id;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
		//System.out.println(testContext.resp.getBody().asString());
		Assert.assertEquals(testContext.resp.getStatusCode(), 200);
	}

	@And("user should be able to validate the skill id in the response body")
	public void user_should_be_able_to_validate_the_skill_id_in_the_response_body() {
		System.out.println(testContext.Skill_ID);
		Assert.assertEquals(testContext.Skill_ID, 3966);
		}

	@And("user should be able to validate json schema for skillAPI")
	public void user_should_be_able_to_validate_json_schema_for_skill_api() {
		testContext.resp.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema\\skillAPI.json"));
	}
	
	@And("get request to the skill id should not return the skill id")
	public void get_request_to_the_skill_id_should_not_return_the_skill_id() {
		testContext.resp = testContext.reqspec.when().get(readConfig.getSkillAPIEndpoint() + "/" + testContext.newSkill_ID)
				.then().extract().response();
	}

	
}
