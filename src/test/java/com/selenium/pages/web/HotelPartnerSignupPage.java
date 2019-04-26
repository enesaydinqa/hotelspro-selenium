package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HotelPartnerSignupPage extends PageObject
{

    public HotelPartnerSignupPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "contact_name_id")
    public WebElement contactNameInput;

    @FindBy(id = "position_id")
    public WebElement positionInput;

    @FindBy(id = "phone_id")
    public WebElement phoneInput;

    @FindBy(id = "fax_id")
    public WebElement faxInput;

    @FindBy(id = "email_id")
    public WebElement emailInput;

    @FindBy(id = "web_page_id")
    public WebElement webPageInput;

    @FindBy(id = "country_id")
    public WebElement countrySelect;

    @FindBy(id = "city_id")
    public WebElement cityInput;

    @FindBy(id = "address_id")
    public WebElement addressInput;

    @FindBy(id = "zipcode_id")
    public WebElement zipcodeInput;

    @FindBy(id = "hotel_name_id")
    public WebElement hotelNameInput;

    @FindBy(id = "type_property_id")
    public WebElement typePropertyInput;

    @FindBy(id = "hotel_opening_date_id")
    public WebElement hotelOpeningDate;

    @FindBy(id = "number_room_id")
    public WebElement numberRoomInput;

    @FindBy(id = "total_suites_id")
    public WebElement totalSuitesInput;

    @FindBy(id = "information_id")
    public WebElement informationInput;

    @FindBy(id = "signup-agreement")
    public WebElement signupAgreement;

    @FindBy(xpath = "//button[contains(@class, 'user-connection__button')]")
    public WebElement saveButton;
}
