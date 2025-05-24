package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"steps"},
        plugin = {
                "pretty",
                "html:reports.cucumber",
                "json:target/cucumber.json"
        },
        monochrome = true
)

public class TestRunner {
        
    @AfterClass
    public static void cleanDriver() {
        
    }
}
