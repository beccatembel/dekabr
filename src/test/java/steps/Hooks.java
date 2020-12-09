package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;
import utilities.PropertyUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks {
    WebDriver driver = Driver.getDriver(PropertyUtils.getProperty("browser"));

    @Before
    public void setUp(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed part");
            //CommonUtils.takeScreenshot(driver, "Test Scenario");
        }
        //driver.quit();
    }
}
