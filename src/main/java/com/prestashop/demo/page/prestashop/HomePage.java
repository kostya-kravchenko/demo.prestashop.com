package com.prestashop.demo.page.prestashop;

import com.prestashop.demo.enums.SortOrder;
import com.prestashop.demo.page.AbstractPageObject;
import com.prestashop.demo.page.component.SelectSortOrderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Getter
public class HomePage extends AbstractPageObject {

    @FindBy(xpath = "//*[@id='js-product-list-top']//button")
    WebElement sortOrderBtn;

    @FindBys({@FindBy(tagName = "article")})
    List<WebElement> allProducts;

    public HomePage sortBy(SortOrder sortOrder) {
        sortOrderBtn.click();
        new SelectSortOrderComponent()
                .getOptions()
                .stream()
                .filter(webElement -> webElement.getText().equals(sortOrder.getOptionText()))
                .findFirst()
                .get()
                .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));
        return new HomePage();
    }
}
