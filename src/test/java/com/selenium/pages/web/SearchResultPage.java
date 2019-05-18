package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends PageObject
{
    public SearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".search-items article .continue-button")
    public List<WebElement> hotelList;

    @FindBy(name = "search_button")
    public WebElement searchButton;

    @FindBy(id = "hotel-name__filter")
    public WebElement hotelNameFilterInput;

    @FindBy(xpath = "//div[@class='pricesearch_loading']/div/div")
    public WebElement loadingIcon;

    @FindBy(css = ".search-hotel-count span")
    public WebElement searchHotelCount;

    @FindBy(name = "hotel_class")
    public List<WebElement> startRatings;

    @FindBy(css = ".search-item .stars")
    public List<WebElement> searchResultStarRatings;

    @FindBy(id = "minPrice")
    public WebElement minPriceInput;

    @FindBy(id = "maxPrice")
    public WebElement maxPriceInput;

    @FindBy(className = "new-price")
    public List<WebElement> hotelPriceTitle;

    @FindBy(css = ".filter-container > div:nth-child(7)")
    public WebElement districtMenuTitle;

    @FindBy(className = "capitalize")
    public WebElement capitalizeTitle;

    @FindBy(css = ".filter-container > div:nth-child(8)")
    public WebElement facilityMenuTitle;

    @FindBy(css = ".filter-container > div:nth-child(9)")
    public WebElement roomTypeMenuTitle;

    @FindBy(css = ".filter-container > div:nth-child(6)")
    public WebElement mealTypeMenuTitle;

    @FindBy(css = ".filter-container > div:nth-child(10)")
    public WebElement roomCategoryMenuTitle;

    @FindBy(css = ".filter-container > div:nth-child(11)")
    public WebElement promotionAndDealsMenuTitle;

    @FindBy(id = "status")
    public WebElement landmarkStatusSelect;

    @FindBy(className = "button-show-on-map")
    public WebElement showOnMapButton;

    @FindBy(className = "map-container")
    public WebElement mapContainer;

    @FindBy(css = ".sort-options > ul > li")
    public List<WebElement> sortOptionsList;

    @FindBy(css = ".price-sorting-options li span")
    public List<WebElement> priceSortingOptions;

}
