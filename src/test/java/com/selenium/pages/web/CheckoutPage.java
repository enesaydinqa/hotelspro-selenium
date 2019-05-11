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

    @FindBy(className = "checkout-form__title")
    public WebElement checkoutFormTitle;

    @FindBy(className = "price-container")
    public WebElement priceContainer;

    @FindBy(xpath = "(//div[@class='checkout-form__section'])[3]")
    public WebElement paymentContainer;

    @FindBy(css = ".checkout-card__form .payment-warning:nth-child(1)")
    public WebElement paymentWarningOne;

    @FindBy(css = ".checkout-card__form .payment-warning:nth-child(2)")
    public WebElement paymentWarningSecond;

    @FindBy(css = ".checkout-card__form > div:nth-child(5)")
    public WebElement paymentForm;

    @FindBy(className = "checkout-card__info")
    public WebElement cardInfo;

    @FindBy(name = "payment_type")
    public WebElement paymentType;

    @FindBy(name = "card_holder_name")
    public WebElement cardHolderName;

    @FindBy(name = "card_number")
    public WebElement cardNumber;

    @FindBy(name = "card_expiration_0")
    public WebElement cardExpirationMonth;

    @FindBy(name = "card_expiration_1")
    public WebElement cardExpirationYear;

    @FindBy(name = "card_cvc")
    public WebElement cardCvc;

}
