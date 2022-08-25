package testBase;
import java.util.HashMap;
import java.util.Random;
import static io.restassured.RestAssured.given;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ReadConfig;
import utils.XLUtility;

public class Testbase {
	
	public ReadConfig readConfig= new ReadConfig();
	
	public int getRandom() {
		int randomNum = new Random().nextInt(999);	
		return randomNum;
	}

	public String getNewUserIDForUserAPI() {
		XLUtility xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
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
		
		RestAssured.baseURI=readConfig.getBaseUri();
		RequestSpecification request=RestAssured.given();
		
		Response resp=request.given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword())
				.headers("Content-Type", "application/json")
				.body(payload.toJSONString())
				.when().post(readConfig.getUserAPIEndpoint()).then().extract().response();
		
		JsonPath jsonpath = resp.jsonPath();
		String newUserIDForUserAPI = jsonpath.get("user_id");
		System.out.println(newUserIDForUserAPI);
		return newUserIDForUserAPI;
	}
	
	
	public int getNewSkillID() {
		XLUtility xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
		JSONObject payload = new JSONObject();
		String skillName = xLUtility.getCellData("SkillAPI", 1, 0);
		 int rnNum = getRandom();
		String skillNameRandom = skillName + rnNum;
		payload.put("skill_name", skillNameRandom);
		
		RestAssured.baseURI=readConfig.getBaseUri();
		RequestSpecification request=RestAssured.given();
		
		Response resp=request.given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword())
				.headers("Content-Type", "application/json")
				.body(payload.toJSONString())
				.when().post(readConfig.getSkillAPIEndpoint()).then().extract().response();
		
	//	System.out.println(resp.getBody().asString());
		JsonPath jsonpath = resp.jsonPath();
		int newId = jsonpath.get("skill_id");
		System.out.println(newId);
		return newId;
		}

	public String getNewUserIDForUserSkillMapAPI() {
		XLUtility xLUtility = new XLUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
		
		RestAssured.baseURI=readConfig.getBaseUri();
		RequestSpecification request=RestAssured.given();
		Response resp=request.given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword())
				.when().get(readConfig.getUserSkillMapAPIEnd()).then().extract().response();
		
		JsonPath jsonpath = resp.jsonPath();
		String user_id_for_userSkillMap = jsonpath.getString("user_id[0]");
		String skill_id_for_userSkillMap = jsonpath.getString("skill_id[0]");

		JSONObject payload = new JSONObject();
		String months_of_exp = xLUtility.getCellData("UserSkillMapAPI", 1, 2);

		payload.put("user_id", user_id_for_userSkillMap);
		payload.put("skill_id", skill_id_for_userSkillMap);
		payload.put("months_of_exp", months_of_exp);
	
		String location=request.given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword())
				.headers("Content-Type", "application/json")
				.body(payload.toJSONString())
				.when().post(readConfig.getUserSkillMapAPIEnd()).then().extract().header("Location");
		
		String newUserIdForUserSkillMapAPI = StringUtils.substringAfter(location, "/UserSkills/");
		System.out.println(newUserIdForUserSkillMapAPI);
				
		return newUserIdForUserSkillMapAPI;
				
	}
	
	
}