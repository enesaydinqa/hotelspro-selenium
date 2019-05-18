package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends PageObject
{
    public HeaderPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "myBookings")
    public WebElement myBookingMenu;
}
