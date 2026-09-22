package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature/career.feature",
    glue = {"stepdefinitions", "hooks"},
    plugin = {"pretty"},
    monochrome = true
)
public class testrunner {

}