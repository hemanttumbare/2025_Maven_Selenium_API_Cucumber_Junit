package com.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
                glue = {"com.cucumber.stepDefinitions","com.cucumber.hooks"},
                plugin = {"pretty","io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
                monochrome = true)
public class BddTest {

}
