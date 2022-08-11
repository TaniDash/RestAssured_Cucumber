
package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features= "classpath:features",
		glue= "stepDefinitions",
		tags="@UserSkillMapGet",
		monochrome= true,
		dryRun= false,
		plugin= {
				"pretty",
				"html: target/cucumber",
				"json: target/cucumber.json"		
		}
		)

public class RestAPIRunner extends AbstractTestNGCucumberTests{

	
}
