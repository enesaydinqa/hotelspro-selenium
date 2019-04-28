package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends PageObject
{
    public SearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".search-items article .continue-button")
    public List<WebElement> hotelList;
}
