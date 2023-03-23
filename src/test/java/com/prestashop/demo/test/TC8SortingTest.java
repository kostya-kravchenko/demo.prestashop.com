package com.prestashop.demo.test;

import com.prestashop.demo.base.BaseTest;
import com.prestashop.demo.enums.SortOrder;
import com.prestashop.demo.page.prestashop.HomePage;
import com.prestashop.demo.page.prestashop.MainPage;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TC8SortingTest extends BaseTest {
  SoftAssertions softAssertions = new SoftAssertions();

  @Test
  public void checkSortingTest() {
    List<String> titlesAZ = new MainPage()
            .waitUntilPageLoaded()
            .clickAllProductButton()
            .sortBy(SortOrder.NAME_A_TO_Z)
            .getAllProducts()
            .stream()
            .map(webElement -> webElement.findElement(By.cssSelector("h2.product-title")))
            .map(WebElement::getText)
            .toList();

    softAssertions
            .assertThat(titlesAZ)
            .isEqualTo(new ArrayList<>(titlesAZ).stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));

    List<String> titlesZA = new HomePage()
            .sortBy(SortOrder.NAME_Z_TO_A)
            .getAllProducts()
            .stream()
            .map(webElement -> webElement.findElement(By.cssSelector("h2.product-title")))
            .map(WebElement::getText)
            .toList();

    softAssertions
            .assertThat(titlesZA)
            .isEqualTo(new ArrayList<>(titlesZA).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

    List<Double> pricesLowToHigh = new HomePage()
            .sortBy(SortOrder.PRICE_LOW_TO_HIGH)
            .getAllProducts()
            .stream()
            .map(webElement -> webElement.findElement(By.cssSelector("div.product-price-and-shipping span:first-of-type")))
            .map(WebElement::getText)
            .map(s -> Double.parseDouble(s.strip().replaceAll("[€]", "")))
            .toList();

    softAssertions
            .assertThat(pricesLowToHigh)
            .isEqualTo(new ArrayList<>(pricesLowToHigh).stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));

    List<Double> pricesHighToLow = new HomePage()
            .sortBy(SortOrder.PRICE_HIGH_TO_LOW)
            .getAllProducts()
            .stream()
            .map(webElement -> webElement.findElement(By.cssSelector("div.product-price-and-shipping span:first-of-type")))
            .map(WebElement::getText)
            .map(s -> Double.parseDouble(s.strip().replaceAll("[€]", "")))
            .toList();

    softAssertions
            .assertThat(pricesHighToLow)
            .isEqualTo(new ArrayList<>(pricesHighToLow).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

    softAssertions.assertAll();
  }

}
