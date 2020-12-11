package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;

import java.text.ParseException;

public class DataManipulation {
    HomePage homePage = new HomePage();

    @Given("User is able to sort by date")
    public void UserIsAbleToSortByDate() {
        homePage.sortByDate.click();
        try {
            homePage.sortDatesDesc(homePage.gameDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Then("Verifying sorted date is matching with data base")
    public void verifyingSortedDateIsMatchingWithDataBase() {
        try {
            homePage.sortDatesVerification();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Given("User is able to sort by team")
    public void userIsAbleToSortByTeam() {
        homePage.sortByTeamName.click();
        try {
            homePage.sortTeamNames(homePage.gameDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Then("Verifying sorted data is matching with data base")
    public void verifyingSortedDataIsMatchingWithDataBase() {
        try {
            homePage.sortTeamNameVerification();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Given("User is able to search by date picker")
    public void userIsAbleToSearchByDatePicker() {
        homePage.sortByDatePicker.click();
    }

    @Then("Verifying searched data by date picker show on ui")
    public void verifyingSearchedDataByDatePickerShowOnUi() {
        homePage.verifySearchByDatePiker();
    }

    @Given("User is able to search by team name")
    public void userIsAbleToSearchByTeamName() {
        homePage.selectTeamName(homePage.selectTeamName, "Team 1");
    }

    @Then("Verifying searched data by team name show on ui")
    public void verifyingSearchedDataByTeamNameShowOnUi() {
        homePage.verifySearchByTeamName();
    }

    @Given("User is able to download csv file")
    public void userIsAbleToDownloadCsvFile() {
        homePage.downloadCSV.click();
    }

    @Then("Verifying downloaded data equals to data on ui")
    public void verifyingDownloadedDataEqualsToDataOnUi() {
        homePage.verifyDataCsvAndUi();
    }
}
