package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject
{
    public PageObject(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
}
