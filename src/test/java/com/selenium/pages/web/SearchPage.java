package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends PageObject
{
    public SearchPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "pac-input")
    public WebElement pacInput;

    @FindBy(css = "#ui-id-1 > li[class='ui-menu-item']")
    public List<WebElement> destinationOptions;

    @FindBy(id = "country-autocomplete")
    public WebElement countryInput;

    @FindBy(css = "#ui-id-2 > li[class='ui-menu-item']")
    public List<WebElement> countryOptions;

    @FindBy(css = ".DateRangePickerInput > div:nth-child(1)")
    public WebElement checkinDate;

    @FindBy(name = "rooms")
    public WebElement roomsSelect;

    @FindBy(name = "adults")
    public WebElement adultsSelect;

    @FindBy(name = "children")
    public WebElement childrenSelect;

    @FindBy(css = ".room-select-items + button")
    public WebElement searchHotelButton;

}
