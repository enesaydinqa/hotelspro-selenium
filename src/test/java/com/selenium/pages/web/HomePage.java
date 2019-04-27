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

    @FindBy(css = "[class='sub-nav-overlay signup']  [href='/agency/signup/']")
    public WebElement clientPartnerCustomerMenu;

    @FindBy(css = "[class='sub-nav-overlay signup']  [href='/signup/hotel_partner/']")
    public WebElement hotelPartnerCustomerMenu;

    @FindBy(css = "[class='sub-nav-overlay signup']  [href='/signup/local_partner/'")
    public WebElement localPartnerCustomerMenu;

    @FindBy(css = "[href='/account/login/']")
    public WebElement loginMenu;

    @FindBy(xpath = "//a[contains(@class, 'header-button signup-button')]")
    public WebElement signUpButton;

    @FindBy(className = "top-logo")
    public WebElement logo;

    @FindBy(className = "about")
    public WebElement aboutMenu;

    @FindBy(className = "products")
    public WebElement productsMenu;

    @FindBy(className = "offices")
    public WebElement officesMenu;

    @FindBy(className = "language")
    public WebElement languageMenu;

    @FindBy(xpath = "//li//a[text()='English']")
    public WebElement englishOption;

    @FindBy(className = "selected-language")
    public WebElement selectedLanguage;

    @FindBy(css = "[class='before-login-header-top  sticky ']")
    public WebElement headerSticky;

    @FindBy(className = "city")
    public List<WebElement> officesList;

    @FindBy(id = "google-map")
    public WebElement googleMap;
}
