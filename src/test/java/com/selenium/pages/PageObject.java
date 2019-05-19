package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class PageObject
{
    public PageObject(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#list li")
    public List<WebElement> selectOptionList;
}
