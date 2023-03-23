package com.prestashop.demo.base;

import com.prestashop.demo.driver.DriverManager;
import com.prestashop.demo.driver.TargetFactory;
import com.prestashop.demo.report.AllureManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.prestashop.demo.config.ConfigurationManager.configuration;

@Listeners({TestListener.class})
public class BaseTest {

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        AllureManager.setAllureEnvironmentInformation();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser) {
        WebDriver driver = TargetFactory.createInstance(browser);
        DriverManager.setDriver(driver);

        DriverManager.getDriver().get(configuration().url());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        DriverManager.quit();
    }
}
