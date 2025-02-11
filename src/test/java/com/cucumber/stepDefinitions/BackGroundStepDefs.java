package com.cucumber.stepDefinitions;

import com.selenium.driver.BaseDriver;
import com.utils.ContextShare;
import io.cucumber.java.en.Given;

public class BackGroundStepDefs {
    private final ContextShare context;

    public BackGroundStepDefs(ContextShare context){
        this.context = context;
    }
    @Given("Initialize the Browser")
    public void initialize_the_browser() {
        System.out.println("Initializing Browser...!!!");
        BaseDriver.setup();
    }
}
