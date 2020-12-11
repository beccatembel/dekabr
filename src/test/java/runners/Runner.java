package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml",
                "rerun:target/rerun.txt"},
        features = "src/test/resources",
        glue = "steps",
        tags = "@ui",
        dryRun = false // If dryRun is false = it will  show us unimplemented steps.
)

/***
 * Run all test suites
 */
public class Runner {

}
