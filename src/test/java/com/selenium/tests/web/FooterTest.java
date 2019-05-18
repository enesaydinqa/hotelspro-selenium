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
import org.openqa.selenium.WebElement;

import java.util.stream.IntStream;

@Category(ParallelExecutable.class)
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
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testFooterSocialMediaIcons()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);

        assertSocialMediaUrl(UrlFactory.HOTELSPRO_LINKEDIN, footerPage.linkedinIcon);
        assertSocialMediaUrl(UrlFactory.HOTELSPRO_FACEBOOK, footerPage.facebookIcon);
        assertSocialMediaUrl(UrlFactory.HOTELSPRO_INSTAGRAM, footerPage.instagramIcon);
        assertSocialMediaUrl(UrlFactory.HOTELSPRO_BLOG, footerPage.blogIcon);
    }

    @Test
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testFooterRedirectMenus()
    {
        UrlFactory[] url = {UrlFactory.TERMS_CONDITIONS, UrlFactory.PRIVACY, UrlFactory.CONTACT_US, UrlFactory.TRADE_FAIRS, UrlFactory.FAQ, UrlFactory.HOTELSPRO_BLOG};

        navigateToURL(UrlFactory.MAIN_URL_FIRST);

        IntStream.range(0, 6).forEach(index -> {

            browserJS.click(footerPage.footerMenus.get(index));
            sleep(2);

            if (index == 5) switchWindowTab(1);

            String currentUrl = getCurrentURL();

            Assert.assertEquals(String.format("Verify Redirect URL : %s", currentUrl), url[index].pageUrl, currentUrl);

            driver.navigate().back();

        });
    }

    private void assertSocialMediaUrl(UrlFactory urlFactory, WebElement socialMediaIcon)
    {
        logger.info(String.format("Verify Social Media Icon URL : %s", urlFactory.pageUrl));
        String socialMediaUrl = getAttribute(socialMediaIcon, "href");

        Assert.assertEquals(String.format("Invalid Url : %s", socialMediaUrl), urlFactory.pageUrl, socialMediaUrl);
    }
}