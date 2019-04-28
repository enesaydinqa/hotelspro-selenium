package com.selenium.tests.web;

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
}
