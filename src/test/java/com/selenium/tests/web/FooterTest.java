package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.FooterPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class FooterTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(FooterTest.class);

    private FooterPage footerPage;

    @Before
    public void init() throws Exception
    {
        super.init();
        footerPage = new FooterPage(driver);
    }

    @Test
    @Category(ParallelExecutable.class)
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testFooterSocialMediaIcons()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);

        logger.info(String.format("Verify Linkedin Icon URL : %s", UrlFactory.HOTELSPRO_LINKEDIN.pageUrl));
        String linkedinUrl = getAttribute(footerPage.linkedinIcon, "href");

        Assert.assertEquals(String.format("Invalid Url : %s", linkedinUrl), UrlFactory.HOTELSPRO_LINKEDIN.pageUrl, linkedinUrl);

        logger.info(String.format("Verify Facebook Icon URL : %s", UrlFactory.HOTELSPRO_FACEBOOK.pageUrl));
        String facebookUrl = getAttribute(footerPage.facebookIcon, "href");

        Assert.assertEquals(String.format("Invalid Url : %s", facebookUrl), UrlFactory.HOTELSPRO_FACEBOOK.pageUrl, facebookUrl);

        logger.info(String.format("Verify Instagram Icon URL : %s", UrlFactory.HOTELSPRO_INSTAGRAM.pageUrl));
        String instagramUrl = getAttribute(footerPage.instagramIcon, "href");

        Assert.assertEquals(String.format("Invalid Url : %s", instagramUrl), UrlFactory.HOTELSPRO_INSTAGRAM.pageUrl, instagramUrl);

        logger.info(String.format("Verify Blog Icon URL : %s", UrlFactory.HOTELSPRO_BLOG.pageUrl));
        String blogUrl = getAttribute(footerPage.blogIcon, "href");

        Assert.assertEquals(String.format("Invalid Url : %s", blogUrl), UrlFactory.HOTELSPRO_BLOG.pageUrl, blogUrl);
    }

    @Test
    @Category(ParallelExecutable.class)
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testFooterRedirectMenus()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);

        String currentUrl;

        waitAndClick(footerPage.footerMenus.get(0));
        sleep(2);
        logger.info(String.format("Verify Redirect URL : %s", UrlFactory.HOTELSPRO_LINKEDIN.pageUrl));

        currentUrl = getCurrentURL();

        Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), UrlFactory.TERMS_CONDITIONS.pageUrl, currentUrl);
        driver.navigate().back();

        waitAndClick(footerPage.footerMenus.get(1));
        sleep(2);
        currentUrl = getCurrentURL();

        Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), UrlFactory.PRIVACY.pageUrl, currentUrl);
        driver.navigate().back();

        waitAndClick(footerPage.footerMenus.get(2));
        sleep(2);
        currentUrl = getCurrentURL();

        Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), UrlFactory.CONTACT_US.pageUrl, currentUrl);
        driver.navigate().back();

        waitAndClick(footerPage.footerMenus.get(3));
        sleep(2);
        currentUrl = getCurrentURL();

        Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), UrlFactory.TRADE_FAIRS.pageUrl, currentUrl);
        driver.navigate().back();

        waitAndClick(footerPage.footerMenus.get(4));
        sleep(2);
        currentUrl = getCurrentURL();

        Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), UrlFactory.FAQ.pageUrl, currentUrl);
        driver.navigate().back();

        waitAndClick(footerPage.footerMenus.get(5));
        switchWindowTab(1);
        sleep(2);
        currentUrl = getCurrentURL();

        Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), UrlFactory.BLOG.pageUrl, currentUrl);
    }
}