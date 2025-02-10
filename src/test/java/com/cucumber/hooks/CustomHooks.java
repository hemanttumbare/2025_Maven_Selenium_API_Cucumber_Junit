package com.cucumber.hooks;

import com.selenium.driver.BaseDriver;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class CustomHooks {

    @After
    public void tearDown() {
        try {
            WebDriver driver = BaseDriver.getDriver();
            System.out.println("Closing the browser...!!!");
            if (driver != null) {
                driver.quit();
            }else{
                System.out.println("Base Driver is null...!!!");
            }
        }catch(Exception e){
            System.out.println("Issue while closing the driver...!!!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

}
