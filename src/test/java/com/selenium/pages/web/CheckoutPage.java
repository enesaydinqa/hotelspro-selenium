package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends PageObject
{
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "(//div[@class='checkout-form__section'])[2]//input[@disabled]")
    public List<WebElement> leadInformationInputs;

    @FindBy(className = "checkout-form__submit ")
    public WebElement checkoutFormSubmitButton;

    @FindBy(xpath = "(//div[@class='checkout-form__section'])[2]//div[@class='errorsContainer']/span")
    public List<WebElement> otherGuestInformationErrors;

    @FindBy(className = "checkout-details")
    public WebElement reservationDetailContainer;

    @FindBy(className = "checkout-details__hotel-info")
    public WebElement checkoutDetailHotelInfo;

    @FindBy(css = ".checkout-details__hotel-info > img")
    public WebElement checkoutDetailHotelInfoImage;

    @FindBy(css = ".checkout-details__hotel-info > div.text")
    public WebElement checkoutDetailHotelInfoText;

    @FindBy(css = ".checkout-details__book-info > div.day:nth-child(1)")
    public WebElement checkoutDetailHotelInfoStartDay;

    @FindBy(css = ".checkout-details__book-info > div.day:nth-child(2)")
    public WebElement checkoutDetailHotelInfoEndDay;

    @FindBy(className = "icon-reservation-detail")
    public WebElement reservationDetailIcons;
}
