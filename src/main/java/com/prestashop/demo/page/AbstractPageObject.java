package com.prestashop.demo.page;

import com.prestashop.demo.config.ConfigurationManager;
import com.prestashop.demo.driver.DriverManager;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.prestashop.demo.config.ConfigurationManager.configuration;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {
    protected WebDriverWait wait;

    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), configuration().timeout()), this);
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigurationManager.configuration().timeout()));
    }
}
