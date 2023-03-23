package com.prestashop.demo.page.component;

import com.prestashop.demo.page.AbstractPageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

@Getter
public class LanguagesComponent extends AbstractPageObject {

    @FindBys({@FindBy(xpath = "//*[@aria-labelledby='language-selector-label']//a")})
    List<WebElement> languages;
}
