package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.HomePage;
import com.selenium.pages.web.LoginPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(ParallelExecutable.class)
public class ForgotPasswordTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(ForgotPasswordTest.class);

    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void before()
    {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı login sayfasında başarılı bir şekilde Forgot Password butonu ile, şifre sıfırlama işlemini tamamlayabilmelidir")
    @PreconditionsToReproduce("Kullanıcı Hotelsprp'ya login olmamalıdır.")
    public void testRedirectToForgotPasswordPage()
    {
        navigateToURL(UrlFactory.MAIN_URL_SECOND);
        waitAndClick(homePage.loginMenu);
        waitAndClick(loginPage.forgotPasswordText);

        Assert.assertEquals("not the same url", getCurrentURL(), UrlFactory.PASSWORD_RESET.pageUrl);
    }
}
