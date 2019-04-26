package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject
{
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(className = "forgot-password")
    public WebElement forgotPasswordText;

    @FindBy(id = "id_username")
    public WebElement usernameInput;

    @FindBy(id = "id_password")
    public WebElement passwordInput;

    @FindBy(name = "login_submit")
    public WebElement loginButton;
}
