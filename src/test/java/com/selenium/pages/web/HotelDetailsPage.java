package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HotelDetailsPage extends PageObject
{
    public HotelDetailsPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".single-hotel-about__prices .button-book-now")
    public WebElement bookNowButton;

    @FindAll({
            @FindBy(xpath = "//p[text()='Bu ürün artık mevcut değildir.']"),
            @FindBy(xpath = "//p[text()='İşlem yapılırken ürün fiyatında değişiklik olmuştur. Lütfen tekrar deneyiniz.']")
    })
    public WebElement productNotFound;

    @FindBy(css = ".add-transfer-container .add-transfer")
    public WebElement addTransferText;

    @FindBy(css = ".direction-option > input")
    public List<WebElement> directionOptions;

    @FindBy(xpath = "(//select)[1]")
    public WebElement transportSelect;

    @FindBy(xpath = "(//select)[2]")
    public WebElement flyHour;

    @FindBy(xpath = "(//select)[3]")
    public WebElement flyMinute;

    @FindBy(className = "search-button")
    public WebElement searchButton;

}
