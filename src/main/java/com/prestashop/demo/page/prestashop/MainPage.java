package com.prestashop.demo.page.prestashop;

import com.prestashop.demo.page.AbstractPageObject;
import com.prestashop.demo.page.component.LanguagesComponent;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.prestashop.demo.driver.DriverManager.getDriver;

@Getter
public class MainPage extends AbstractPageObject {

    @FindBy(xpath = "//div[@id='loadingMessage']")
    WebElement loadingMessage;

    @FindBy(xpath = "//*[@id='framelive']")
    WebElement iframe;

    @FindBy(xpath = "//div[@id='carousel']/following-sibling::section/a")
    WebElement allProductsButton;

    @FindBy(xpath = "//button[@data-toggle='dropdown']")
    WebElement languagesBtn;

    @FindBy(css = ".user-info a")
    WebElement signInBtn;

    public MainPage waitUntilPageLoaded() {
        wait.until(ExpectedConditions.invisibilityOf(loadingMessage));
        getDriver().switchTo().frame(iframe);
        wait.until(ExpectedConditions.elementToBeClickable(allProductsButton));
        return this;
    }

    public HomePage clickAllProductButton() {
        allProductsButton.click();
        return new HomePage();
    }

    public LanguagesComponent clickLanguagesButton() {
        languagesBtn.click();
        return new LanguagesComponent();
    }

    public SignInPage clickSigninButton() {
        signInBtn.click();
        return new SignInPage();
    }
}
