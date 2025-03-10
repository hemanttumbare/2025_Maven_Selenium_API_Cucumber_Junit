package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseDriver {

    private static ThreadLocal<WebDriver> baseDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return baseDriver.get();
    }

    public static void removeDriver(){ baseDriver.remove(); }


    public static void setup(){
        WebDriver driver;
        String browser = System.getProperty("browser","Chrome");

        if(browser.equalsIgnoreCase("Firefox")){
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("Edge")){
            driver = new EdgeDriver();
        }else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
        baseDriver.set(driver);
    }
}
