package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends PageObject
{

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='slick-track'])[1]/div")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "(//a[@itemprop='item'])[1]")
    private WebElement breadCrumb;


}
