package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FooterPage extends PageObject
{
    public FooterPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".link-group-container > div > a")
    public List<WebElement> footerMenus;

    @FindBy(xpath = "//div[@class='social-media-container']/a/div[contains(@class,'linkedin-icon')]/parent::a")
    public WebElement linkedinIcon;

    @FindBy(xpath = "//div[@class='social-media-container']/a/div[contains(@class,'facebook-icon')]/parent::a")
    public WebElement facebookIcon;

    @FindBy(xpath = "//div[@class='social-media-container']/a/div[contains(@class,'instagram-icon')]/parent::a")
    public WebElement instagramIcon;

    @FindBy(xpath = "//div[@class='social-media-container']/a/div[contains(@class,'blog-icon')]/parent::a")
    public WebElement blogIcon;
}
