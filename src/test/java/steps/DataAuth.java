package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utilities.Driver;

public class DataAuth {
    WebDriver driver = Driver.getDriver("browser");
    HomePage homePage = new HomePage();

    @Given("User is able to see images and tags")
    public void UserIsAbleToSeeImagesAndTags() {
        homePage.imageDisplayed();
        homePage.tagsIsDisplayed();
    }

    @Then("Verifying ui data is matching with data base")
    public void verifyingUiDataIsMatchingWithDataBase() {
        homePage.imageVerification();
        homePage.tagsVerification();
    }
}
