package com.prestashop.demo.page.prestashop;

import com.prestashop.demo.page.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends AbstractPageObject {
    @FindBy(css = ".no-account a")
    WebElement signUpBnt;

    public SignUpPage clickSignUpButton() {
        signUpBnt.click();
        return new SignUpPage();
    }
}
