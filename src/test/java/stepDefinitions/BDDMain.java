package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        plugin = {"pretty"},
        glue = "stepDefinitions",
        features = "D:/projects/musicplayer_backend/src/test/java/features",
        publish = true
        //plugin = "message:target/cucumber-report.ndjson"
)
public class BDDMain{// extends AbstractTestNGCucumberTests {

}
