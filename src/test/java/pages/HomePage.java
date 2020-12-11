package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;
import utilities.JDBCUtils;
import utilities.PropertyUtils;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomePage {
    WebDriver driver = Driver.getDriver(PropertyUtils.getProperty("browser"));

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='container']//img")
    public List<WebElement> imageList;

    @FindBy(xpath = "//div[@class='container']//span[@class='gameDate']")
    public List<WebElement> gameDate;

    @FindBy(xpath = "//div[@class='container']//span[@class='gameLocation']")
    public List<WebElement> gameLocation;

    @FindBy(xpath = "//div[@class='container']//span[@class='teamName']")
    public List<WebElement> teamName;

    @FindBy(xpath = "//div[@class='container']/button[@type='sortByDate']")
    public WebElement sortByDate;

    @FindBy(xpath = "//div[@class='container']/button[@type='sortByTeamName']")
    public WebElement sortByTeamName;

    @FindBy(xpath = "//div[@class='container']/input[@type='sortByDatePicker']")
    public WebElement sortByDatePicker;

    @FindBy(xpath = "//div[@class='container']/select[@name='modelId']")
    public WebElement selectTeamName;

    @FindBy(xpath = "//div[@class='container']/button[@name='download']")
    public WebElement downloadCSV;


    /***
     * This method is verifying tags are displayed
     */
    public void tagsIsDisplayed() {
        for (int i = 0; i < gameDate.size(); i++) {
            Assert.assertTrue(gameDate.get(i).isDisplayed());
            Assert.assertTrue(gameLocation.get(i).isDisplayed());
            Assert.assertTrue(teamName.get(i).isDisplayed());
        }
    }

    /***
     * This method is verifying images are displayed
     */
    public void imageDisplayed() {
        for (int i = 0; i < imageList.size(); i++) {
            Assert.assertTrue(imageList.get(i).isDisplayed());
        }
    }

    /***
     * This method is verifying images path is matched from UI and DB
     */
    public void imageVerification() {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> selectImagesFromTables = null;

        try {
            selectImagesFromTables = JDBCUtils.runQuery("SELECT IMAGES FROM TABLE;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < imageList.size(); i++) {
            Assert.assertEquals(imageList.get(i).getAttribute("href"), selectImagesFromTables.get(i).get("IMAGES"));
        }
    }

    /***
     * This method is verifying tags are matched from UI and DB
     */
    public void tagsVerification() {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> selectTagsFromTables = null;

        try {
            selectTagsFromTables = JDBCUtils.runQuery("SELECT GAME_DATE, GAME_LOCATION, TEAM_NAME FROM TABLE;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < gameDate.size(); i++) {
            Assert.assertEquals(gameDate.get(i).toString(), selectTagsFromTables.get(i).get("GAME_DATE"));
            Assert.assertEquals(gameLocation.get(i).toString(), selectTagsFromTables.get(i).get("GAME_LOCATION"));
            Assert.assertEquals(teamName.get(i).toString(), selectTagsFromTables.get(i).get("TEAM_NAME"));
        }
    }

    /***
     * This method return list of gameDate ordered by descending order
     * @param dates
     * @return
     * @throws ParseException
     */
    public ArrayList<String> sortDatesDesc(List dates) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        Map<Date, String> dateFormatMap = new TreeMap<>(Collections.reverseOrder());
        for (Object date : dates)
            dateFormatMap.put(f.parse(String.valueOf(date)), String.valueOf(date));
        return new ArrayList<>(dateFormatMap.values());
    }

    /***
     * This method compare sorted date from UI with sorted date from DB
     * @throws ParseException
     */
    public void sortDatesVerification() throws ParseException {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> selectDataFromTables = null;

        try {
            selectDataFromTables = JDBCUtils.runQuery("SELECT GAME_DATE FROM TABLE ORDER BY GAME_DATE DESC");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < gameDate.size(); i++) {
            Assert.assertEquals(sortDatesDesc(Collections.singletonList(gameDate.get(i).getText())), selectDataFromTables.get(i).get("GAME_DATE").toString());
        }
    }

    /***
     * This method is sorting list of teamName
     * @param teamName
     * @return
     * @throws ParseException
     */
    public List<String> sortTeamNames(List teamName) throws ParseException {
        Collections.sort(teamName);
        return teamName;
    }

    /***
     * This method is comparing sorted teamName UI with sorted by date from DB
     * @throws ParseException
     */
    public void sortTeamNameVerification() throws ParseException {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> selectDataFromTables = null;

        try {
            selectDataFromTables = JDBCUtils.runQuery("SELECT TEAM_NAME FROM TABLE ORDER BY TEAM_NAME");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < gameDate.size(); i++) {
            Assert.assertEquals(sortTeamNames(Collections.singletonList((gameDate.get(i).getText()))), selectDataFromTables.get(i).get("TEAM_DATE").toString());
        }
    }

    /***
     * This method compare date from date picker and date from ui
     */
    public void verifySearchByDatePiker() {
        for (int i = 0; i < gameDate.size(); i++) {
            Assert.assertEquals(sortByDatePicker.getAttribute("value"), gameDate.get(i).toString());
        }
    }

    /***
     * This method will select option by value
     * @param teamName
     */
    public void selectTeamName(WebElement teamNameDropdown, String teamName) {
        Select selectTeamName = new Select(teamNameDropdown);
        selectTeamName.selectByValue(teamName);
    }

    /***
     * This method compare teamName in selected option and teamName from ui
     */
    public void verifySearchByTeamName() {
        for (int i = 0; i < teamName.size(); i++) {
            Assert.assertEquals(selectTeamName.getAttribute("value"), teamName.get(i).toString());
        }
    }

    /***
     * This method parse data from CSV file
     * @return List<List<String>>
     */
    public List<List<String>> csvReader(){
        List<List<String>> listOfLstLine = null;
        List<String> listLine = null;
        BufferedReader br = null;
        File file = new File("src/test/resources/data/exportedData.csv");
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] arrayLine = line.split(cvsSplitBy);
                listOfLstLine.addAll(Collections.singleton(listLine = Arrays.asList(arrayLine)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listOfLstLine;
    }

    /***
     * This method compare data from CSV and UI
     */
    public void verifyDataCsvAndUi() {
        for (int i = 0; i < imageList.size(); i++) {
            Assert.assertEquals(imageList.get(i).getAttribute("href"), csvReader().get(i).get(0));
            Assert.assertEquals(gameDate.get(i).toString(), csvReader().get(i).get(1));
            Assert.assertEquals(gameLocation.get(i).toString(), csvReader().get(i).get(2));
        }
    }
}
