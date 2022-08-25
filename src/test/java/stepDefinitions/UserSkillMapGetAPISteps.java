package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import testBase.Testbase;


public class UserSkillMapGetAPISteps extends Testbase{
//	static String users_id;
//	static String skill_id;
	Testcontext testContext;
	
	public UserSkillMapGetAPISteps(Testcontext testContext) {
		this.testContext=testContext;
	}
	 String user_id;
	 String skill_id;
	@When("user sends GET request to get all users with all skill details with valid endpoint")
	public void user_sends_get_request_to_get_all_users_with_all_skill_details_with_valid_endpoint() {
	 testContext.resp=testContext.reqspec.when().get(readConfig.getUserSkillsMapEndpointForUser()).then().extract().response();
	 //System.out.println(testContext.resp.getBody().asString());
//	 JsonPath jsonpath = testContext.resp.jsonPath();
//	 int size=jsonpath.getInt("users.size()");
//	 System.out.println(size);
//	 for (int i=0; i<size; i++) {
//		 int len=jsonpath.getInt("users["+ i + "].skillmap.size()");
//		 if (len>0) {
//			 user_id=jsonpath.getString("users[" + i + "].id");
//			 skill_id=jsonpath.getString("users[" + i + "].skillmap[0].id");
//			 break;
//		 }
//	 }
//	 System.out.println(user_id);
//	 System.out.println(skill_id);
//	 
	}
	

	@When("user sends GET request to get single user with skill details with valid endpoint and invalid user id")
	public void user_sends_get_request_to_get_single_user_with_skill_details_with_valid_endpoint_and_invalid_user_id() {
		String invalidUserId = null;
		String endpoint = readConfig.getUserSkillsMapEndpointForUser();
		String pathParam = endpoint + "/" + invalidUserId;
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
	}
	
	@When("user sends GET request to get single user with skill details with valid endpoint and specific user id")
	public void user_sends_get_request_to_get_single_user_with_skill_details_with_valid_endpoint_and_specific_user_id() {	
		 testContext.resp=testContext.reqspec.when().get(readConfig.getUserSkillsMapEndpointForUser()).then().extract().response();
		 String body=testContext.resp.getBody().asString();	
			JsonPath jsonpath = testContext.resp.jsonPath();
			testContext.users_id = jsonpath.getString("users[0].id");
			System.out.println(testContext.users_id);
			
			if (testContext.users_id != null) {
				System.out.println("User ID is: " + testContext.users_id);
			} else {
				System.out.println("User ID does not exist!");
			}		
			
		String endpoint = readConfig.getUserSkillsMapEndpointForUser();
		String pathParam = endpoint + "/" + testContext.users_id;
		System.out.println(pathParam);
		testContext.resp = testContext.reqspec.when().get(pathParam).then().extract().response();
	}
	
	
	@When("user sends GET request to get all user details with valid endpoint and invalid skill id")
	public void user_sends_get_request_to_get_all_user_details_with_valid_endpoint_and_invalid_skill_id() {
	 
	}
	
	@When("user sends GET request to get all user details with valid endpoint and valid skill id")
	public void user_sends_get_request_to_get_all_user_details_with_valid_endpoint_and_valid_skill_id() {
	   
	}
			
//		JsonPath js=new JsonPath(testContext.resp.asString());
//		//skill_id = jsonpath.getString("users[0].skillmap[0].id");
//		
//		int size=js.getInt("users.size()");
//		//System.out.println(size);
//		
//		for(int i=1; i<size; i++) {
////		if (skill_id==null) {
////			System.out.println("skillmap["+i+"].id");
////		}
//			
//			if("skillmap.size()"!=null) {
//				System.out.println("users:["+i+"]"+"skillmap[0]"+".id");
//			}
//			break;
//		}
//	}

	@And("user should be able to validate json schema for usersSkillsMapGetAPI")
	public void user_should_be_able_to_validate_json_schema_for_users_skills_map_get_api() {
		testContext.resp.then().assertThat()
	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema\\UserSkillMapGetAPI.json"));

	}
	

}
