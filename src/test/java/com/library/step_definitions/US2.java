package com.library.step_definitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtil;
import com.library.utilities.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2 {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBooksNumber;
    String expectedBorrowedBooksNumber;

    @Given("I am in the homepage of library app")
    public void i_am_in_the_homepage_of_library_app() {
        loginPage.login();
    }

    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {
        BrowserUtil.waitFor(4);
        actualBorrowedBooksNumber = dashBoardPage.borrowedBooksNumber.getText();

    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "SELECT COUNT(*)\n" +
                "FROM book_borrow\n" +
                "WHERE is_returned = 0";
        DB_Util.runQuery(query);
        expectedBorrowedBooksNumber = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBorrowedBooksNumber, actualBorrowedBooksNumber);

    }


}
