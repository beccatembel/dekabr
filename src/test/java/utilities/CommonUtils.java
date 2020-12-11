package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {
    /***
     * This method read properties from files using FileInputStream
     * @param pathToPropertyFile
     * @return
     * @throws IOException
     */
    public static Properties readPropertyFile(String pathToPropertyFile) throws IOException {
        Properties properties = new Properties();
        File propFile = new File(pathToPropertyFile);
        FileInputStream fileInputStream = new FileInputStream(propFile);
        properties.load(fileInputStream);

        return properties;
    }

    /***
     * This method get Property Key from provided property file
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        try {
            Properties properties = readPropertyFile("src//test//resources//config.properties");
            String value = properties.getProperty(key);
            return value;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /***
     * This method takes screenshot
     * @param driver
     * @param testName
     * @throws IOException
     */
    public static void takeScreenshot(WebDriver driver, String testName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "src/test/java/screenshotFile/" + testName + System.currentTimeMillis() + ".png";
        File destScreenshot = new File(destination);
        FileUtils.copyFile(screenshot, destScreenshot);
    }
}
