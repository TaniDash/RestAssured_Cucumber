package testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= "classpath:features",
		glue= "stepDefinitions",
		//tags= "@userAPI",
		monochrome= true,
		dryRun= false ,
		plugin= {
				"pretty",
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json: target/cucumber.json"		
		} 
		)

public class TestRestAPIRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider (parallel=true)
	public Object [][]scenarios(){
		return super.scenarios();
	}
	
	
	
}
