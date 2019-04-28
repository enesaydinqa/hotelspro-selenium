package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelDetailsPage extends PageObject
{
    public HotelDetailsPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".single-hotel-about__prices .button-book-now")
    public WebElement bookNowButton;


}
