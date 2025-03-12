package com.cucumber.hooks;

import com.selenium.driver.BaseDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class CustomHooks {

    @After
    public void tearDown(Scenario scenario) {
        try {
            WebDriver driver = BaseDriver.getDriver();
            System.out.println("Closing the browser...!!!");
            if (driver != null) {
                if (scenario.isFailed()) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment(sanitizeScenarioName(scenario.getName()), new ByteArrayInputStream(screenshot));
                }
                driver.quit();
                BaseDriver.removeDriver();
            }else{
                System.out.println("Base Driver is null...!!!");
            }
        }catch(Exception e){
            System.err.println("Issue while closing the driver...!!!\n" + e.getMessage());
            e.printStackTrace();
        }
    }
    private String sanitizeScenarioName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_-]", "_");
    }
}
