package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CollectionPage extends PageObject
{
    public CollectionPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(name = "collection-name")
    public WebElement collectionNameSelect;

    @FindBy(css = ".search-items article")
    public List<WebElement> favoriteItemList;
}
