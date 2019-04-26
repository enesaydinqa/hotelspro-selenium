package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject
{
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = "[class='sub-nav-overlay signup']  [href='/agency/signup/']")
    public WebElement clientPartnerCustomerMenu;

    @FindBy(css = "[class='sub-nav-overlay signup']  [href='/signup/hotel_partner/']")
    public WebElement hotelPartnerCustomerMenu;

    @FindBy(css = "[class='sub-nav-overlay signup']  [href='/signup/local_partner/'")
    public WebElement localPartnerCustomerMenu;

    @FindBy(css = "[href='/account/login/']")
    public WebElement loginMenu;

    @FindBy(xpath = "//span[text()='Kaydol']")
    public WebElement signUpButton;

}
