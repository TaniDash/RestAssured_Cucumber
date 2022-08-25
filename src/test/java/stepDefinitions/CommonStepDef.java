package stepDefinitions;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import testBase.Testbase;
import utils.XLUtility;

public class CommonStepDef extends Testbase {
	Testcontext testContext;
	XLUtility xLUtility;
	int rnNum;
	SoftAssert softAssert;
	
	public CommonStepDef(Testcontext testContext) {
		this.testContext = testContext;
	//	xLUtility = new XlUtility("src\\test\\java\\testData\\RestAPI_testData.xlsx");
		softAssert=new SoftAssert();
	}

	@Given("user provides baseUri with no authetication details")
	public void user_provides_base_uri_for_with_no_authetication_details() {
		RestAssured.baseURI = readConfig.getBaseUri();
		testContext.reqspec = given();
	}

	@Given("user provides baseUri with valid authetication details")
	public void user_provides_base_uri_with_valid_authetication_details() {
		RestAssured.baseURI = readConfig.getBaseUri();
		testContext.reqspec = given().auth().preemptive().basic(readConfig.getUsername(), readConfig.getPassword());

	}

	@And("user receives response content-type as {string}")
	public void user_receives_response_content_type_as(String contentType) {
		String expectedResponseContentType = testContext.resp.getHeader("Content-Type");
		Assert.assertEquals(expectedResponseContentType, "application/json");
	}

	@Then("user should receive response status code as {int}")
	public void user_should_receive_response_status_code_as(Integer statusCode) {

		switch (statusCode) {
		case 200:
			softAssert.assertEquals(testContext.resp.getStatusCode(), 200);
			break;
		case 201:
			softAssert.assertEquals(testContext.resp.getStatusCode(), 201);
			break;
		case 400:
			softAssert.assertEquals(testContext.resp.getStatusCode(), 400);
			break;
		case 401:
			softAssert.assertEquals(testContext.resp.getStatusCode(), 401);
			break;
		case 404:
			softAssert.assertEquals(testContext.resp.getStatusCode(), 404);
			break;
		}
	}

	@And("user should also receive the message {string}")
	public void user_should_also_receive_the_message(String message) {

		switch (message) {
		case "createNewUserForuserAPI":
			String expectedMessage1 = testContext.resp.jsonPath().get("message");
			softAssert.assertEquals(expectedMessage1,
					"Failed to create new User details as phone number already exists !!");
			System.out.println(expectedMessage1);
		case "createNewSkillDetailsForskillAPI":
			String expectedMessage2 = testContext.resp.jsonPath().get("message");
			softAssert.assertEquals(expectedMessage2, "Failed to create new Skill details as Skill already exists !!");
			System.out.println(expectedMessage2);
		case "successfullyCreated":
			String expectedMessage3 = testContext.resp.jsonPath().get("message_response");
			System.out.println(expectedMessage3);
			softAssert.assertEquals(expectedMessage3, "Successfully Created !!");
		case "successfullyUpdated":
			String expectedMessage4 = testContext.resp.jsonPath().get("message_response");
			System.out.println(expectedMessage4);
			softAssert.assertEquals(expectedMessage4, "Successfully Updated !!");
		case "notFoundMessage":
			String expectedMessage5 = testContext.resp.jsonPath().get("message");
			System.out.println(expectedMessage5);
			Assert.assertEquals(expectedMessage5, "User(id- null) Not Found !!");
			Assert.assertEquals(expectedMessage5, "Skill(id- 0) Not Found !!");
		case "deletedMessage":
			String expectedMessage = testContext.resp.jsonPath().get("message_response");
			System.out.println(expectedMessage);
			softAssert.assertEquals(expectedMessage, "The record has been deleted !!");

			break;
		}

	}

	
	
}
