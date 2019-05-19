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

    @FindBy(css = "[class='dropdownLink room-type']")
    public WebElement roomTypeSelect;

    @FindBy(css = "[class='dropdownLink meal-type']")
    public WebElement mealTypeSelect;

    @FindBy(css = "#dropdownRoomTypes input")
    public List<WebElement> roomTypeOptionCheckboxes;

    @FindBy(css = "#dropdownMealTypes input")
    public List<WebElement> mealTypeOptionCheckboxes;

    @FindBy(className = "show-map-button")
    public WebElement showMapButton;

    @FindBy(className = "hotel-detail-map")
    public WebElement hotelDetailMap;

    @FindBy(className = "create-offer")
    public WebElement createOfferButton;

    @FindBy(css = ".image-list > div.image-container")
    public List<WebElement> pickOfferImages;

    @FindBy(css = "[class='room-list body'] input")
    public List<WebElement> roomList;

    @FindBy(className = "checkout-form__submit")
    public WebElement createOfferAndSavePDFButton;

    @FindBy(className = "change-search")
    public WebElement changeSearchText;

    @FindBy(id = "country-autocomplete")
    public WebElement countrySelect;

    @FindBy(css = ".ui-autocomplete li a")
    public List<WebElement> countryAutoCompleteOptions;

    @FindBy(className = "modifybutton--availability")
    public WebElement modifyButton;

    @FindBy(name = "rooms")
    public WebElement roomsSelect;

    @FindBy(name = "adults")
    public WebElement adultsSelect;

    @FindBy(name = "children")
    public WebElement childrenSelect;

    @FindBy(className = "room-availability__summary")
    public WebElement roomAvailabilityText;

    @FindBy(className = "add-to-favorites-text")
    public WebElement addToFavoriteText;

    @FindBy(css = ".collection-add-new input")
    public WebElement addCollectionInput;

    @FindBy(css = ".collection-list input")
    public List<WebElement> addCollectionListCheckboxes;

    @FindBy(css = ".collection-add-new button")
    public WebElement addFavoriteButton;
}
