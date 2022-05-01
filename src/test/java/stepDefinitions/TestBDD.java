package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBDD {
    @Given("user searches with id {int}")
    public void userSearchesWithId(int arg0) {
        assertEquals (arg0,1);
    }

    @Then("{string} has to be returned")
    public void hasToBeReturned(String arg0) {
        System.out.println(arg0);
        assertEquals(arg0,"Test User" );
    }
}
