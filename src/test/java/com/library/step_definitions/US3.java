package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US3 {

    String actualBookGenre;


    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        String query = "SELECT book_categories.name, COUNT(*) AS countofbookcategories\n" +
                "FROM book_borrow\n" +
                "         INNER JOIN books\n" +
                "             ON book_borrow.book_id = books.id\n" +
                "         INNER JOIN book_categories\n" +
                "             ON books.book_category_id = book_categories.id\n" +
                "GROUP BY book_categories.name\n" +
                "ORDER BY countofbookcategories DESC";
        DB_Util.runQuery(query);
        actualBookGenre = DB_Util.getCellValue(1, 1);
        System.out.println("actualBookGenre = " + actualBookGenre);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedBookGenre) {
        System.out.println("expectedBookGenre = " + expectedBookGenre);
        Assert.assertEquals(expectedBookGenre, actualBookGenre);

    }


}
