package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LocalPartnerSignupPage extends PageObject
{

    public LocalPartnerSignupPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "company_name_id")
    public WebElement companyNameInput;

    @FindBy(id = "contact_name_id")
    public WebElement contactNameInput;

    @FindBy(id = "phone_number_id")
    public WebElement phoneInput;

    @FindBy(id = "email_id")
    public WebElement emailInput;

    @FindBy(id = "country_id")
    public WebElement countrySelect;

    @FindBy(id = "address_id")
    public WebElement addressInput;

    @FindBy(id = "introduction_id")
    public WebElement introductionInput;

    @FindBy(id = "signup-agreement")
    public WebElement signupAgreement;

    @FindBy(xpath = "//button[contains(@class, 'user-connection__button')]")
    public WebElement saveButton;
}
