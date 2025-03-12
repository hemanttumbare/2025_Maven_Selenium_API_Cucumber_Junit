package com.cucumber.stepDefinitions;

import com.selenium.driver.BaseDriver;
import com.selenium.pages.HomePage;
import com.utils.ContextShare;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class HomePageTestsSepDef {

    private final ContextShare context;

    public HomePageTestsSepDef(ContextShare context){
        this.context=context;
    }

    @When("User Navigates to HomePage")
    public void user_navigates_to_home_page() {
        BaseDriver.getDriver().get("https://magento.softwaretestingboard.com/");
    }
    @Then("Verify HomePage Logo And URL are displayed")
    public void verify_home_page_logo_and_url_are_displayed() {
        HomePage homePage = new HomePage(BaseDriver.getDriver());
        Assert.assertTrue("homePage logo not displayed",homePage.checkHomePageLogo());
        Assert.assertEquals("Base URL does not match","https://magento.softwaretestingboard.com/",BaseDriver.getDriver().getCurrentUrl());
    }

    @Then("Verify HomePage Logo And URL are displayed Correctly")
    public void verify_home_page_logo_and_url_are_displayed_correctly() {
        HomePage homePage = new HomePage(BaseDriver.getDriver());
        Assert.assertEquals("Base URL does not match","https://magento.softwaretestingboard.com/123",BaseDriver.getDriver().getCurrentUrl());
    }
}
