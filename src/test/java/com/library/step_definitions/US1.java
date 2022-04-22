package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US1 {
    List<String> actualIds;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        System.out.println("Database Connection is don inside the Hooks");
    }


    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query = "SELECT id\n" +
                "FROM users";
        DB_Util.runQuery(query);
        actualIds = DB_Util.getColumnDataAsList(1);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "select DISTINCT  id\n" +
                "from users";
        DB_Util.runQuery(query);
        List<String> expectedID = DB_Util.getColumnDataAsList(1);
        Assert.assertEquals(expectedID, actualIds);
    }


    List<String> actualColumns;

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query = "SELECT * FROM users";
        DB_Util.runQuery(query) ;
        actualColumns = DB_Util.getAllColumnNamesAsList();
        System.out.println(actualColumns);
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnsName) {
        System.out.println(expectedColumnsName);
        Assert.assertEquals(expectedColumnsName, actualColumns);
    }
}
