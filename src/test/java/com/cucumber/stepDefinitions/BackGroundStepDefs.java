package com.cucumber.stepDefinitions;

import com.selenium.driver.BaseDriver;
import io.cucumber.java.en.Given;

public class BackGroundStepDefs {
    @Given("Initialize the Browser")
    public void initialize_the_browser() {
        System.out.println("Initializing Browser...!!!");
        BaseDriver.setup();
    }
}
