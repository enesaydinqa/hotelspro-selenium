package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.HomePage;
import com.selenium.pages.web.LoginPage;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTest extends AbstractHotelsProTest
{
    private String invalidLoginMessage = "Lütfen geçerli kullanıcı adı ve şifre ile giriş yapın. Kullanıcı adı ve şifrenin büyük-küçük harfe duyarlı olduğunu unutmayın.";

    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void before()
    {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcının yanlış login email veya yanlış şifre ile login olmaması gereklidir. Bu durumda sistem uyarı göstermelidir.")
    @Category(ParallelExecutable.class)
    public void testInvalidMailAddressTryLogin()
    {
        String randomEmail = RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4)).concat(".com");

        invalidTryLogin(randomEmail, configuration.getTestEmailPassword());
    }

    @Test
    @ExpectedResult("Kullanıcının yanlış login email veya yanlış şifre ile login olmaması gereklidir. Bu durumda sistem uyarı göstermelidir.")
    @Category(ParallelExecutable.class)
    public void testInvalidUserIdTryLogin()
    {
        String randomUserId = RandomStringUtils.randomNumeric(5);

        invalidTryLogin(randomUserId, configuration.getTestEmailPassword());

    }

    @Test
    @ExpectedResult("Kullanıcının yanlış login email veya yanlış şifre ile login olmaması gereklidir. Bu durumda sistem uyarı göstermelidir.")
    @Category(ParallelExecutable.class)
    public void testInvalidPasswordTryLogin()
    {
        String randomPassword = RandomStringUtils.randomAlphabetic(10);

        invalidTryLogin(configuration.getTestEmailPassword(), randomPassword);

    }

    private void invalidTryLogin(String username, String password)
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);
        waitAndClick(homePage.loginMenu);
        waitAndSendKeys(loginPage.usernameInput, username);
        waitAndSendKeys(loginPage.passwordInput, password);
        waitAndClick(loginPage.loginButton);

        sleep(5);

        Assert.assertTrue("not visible or bad invalid message", isTextDisplayedOnPage(invalidLoginMessage));
    }
}
