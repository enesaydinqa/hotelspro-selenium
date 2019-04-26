package com.selenium.context.base;

import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.LoginPage;
import org.apache.log4j.Logger;

public abstract class AbstractHotelsProTest extends AbstractWebTest
{
    private Logger logger = Logger.getLogger(AbstractHotelsProTest.class);

    protected void login(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);

        navigateToURL(UrlFactory.LOGIN);
        waitAndSendKeys(loginPage.usernameInput, username);
        waitAndSendKeys(loginPage.passwordInput, password);
        waitAndClick(loginPage.loginButton);
    }

}
