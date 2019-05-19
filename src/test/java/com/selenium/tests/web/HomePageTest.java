package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.HomePage;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;

@Category(ParallelExecutable.class)
public class HomePageTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(HomePageTest.class);

    private String[] istanbulGeoLocation = {"41.026833", "28.886962"};
    private String[] dubaiGeoLocation = {"25.072595", "55.139985"};
    private String[] cancunGeoLocation = {"21.149015", "-86.821428"};
    private String[] londonGeoLocation = {"51.518210", "-0.077233"};
    private String[] orlandoGeoLocation = {"28.748410", "-81.364364"};
    private String[] shanghaiGeoLocation = {"31.186017", "121.441631"};

    private List<String> homePageText = Arrays.asList("caption", "caption.description", "explanation.title", "explanation.help.text", "explanation.li1", "explanation.li2", "explanation.li3", "explanation.li4", "explanation.li5", "business-help.text", "business-li1", "business-li2", "business-li3", "business-li4", "business-li5", "business-li6", "about.us", "about.us1", "about.us2", "about.us3", "item1", "item1.detail", "item2", "item2.detail", "item3", "item3.detail", "item4", "item4.detail", "item5", "item5.detail");

    private HomePage homePage;

    @Before
    public void init() throws Exception
    {
        super.init();
        homePage = new HomePage(driver);
    }

    @Test
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testHomePageHeaderVisibleMenu()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);

        List<WebElement> headerElements = Arrays.asList(homePage.logo, homePage.productsMenu, homePage.aboutMenu,
                homePage.officesMenu, homePage.languageMenu, homePage.signUpButton, homePage.loginMenu);

        headerElements.parallelStream().forEach(elem -> Assert.assertTrue(isDisplayed(elem)));

        mouseOver(homePage.languageMenu);
        Assert.assertTrue(isDisplayed(homePage.selectedLanguage));

        mouseOver(homePage.signUpButton);

        List<WebElement> otherHeaderElements = Arrays.asList(homePage.clientPartnerCustomerMenu, homePage.hotelPartnerCustomerMenu, homePage.localPartnerCustomerMenu);

        otherHeaderElements.parallelStream().forEach(elem -> Assert.assertTrue(isDisplayed(elem)));
    }

    @Test
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testHomePageMenuScroll()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);
        waitAndClick(homePage.aboutMenu);
        sleep(DEFAULT_MEDIUM_SLEEP);
        Long aboutUsContainerYPageOffset = browserJS.getScreenYPageOffset();

        Assert.assertTrue(aboutUsContainerYPageOffset == 1722);
        Assert.assertTrue(isDisplayed(homePage.headerSticky));


        waitAndClick(homePage.productsMenu);
        sleep(DEFAULT_MEDIUM_SLEEP);
        Long productsContainerYPageOffset = browserJS.getScreenYPageOffset();

        Assert.assertTrue(productsContainerYPageOffset == 4422);
        Assert.assertTrue(isDisplayed(homePage.headerSticky));


        waitAndClick(homePage.officesMenu);
        sleep(DEFAULT_MEDIUM_SLEEP);
        Long officesContainerYPageOffset = browserJS.getScreenYPageOffset();

        Assert.assertTrue(officesContainerYPageOffset == 5510);
        Assert.assertTrue(isDisplayed(homePage.headerSticky));
    }

    @Test
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testGoogleMapOffices()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);
        waitAndClick(homePage.officesMenu);
        sleep(DEFAULT_MEDIUM_SLEEP);

        List<String[]> offices = new ArrayList<>();
        offices.add(dubaiGeoLocation);
        offices.add(istanbulGeoLocation);
        offices.add(cancunGeoLocation);
        offices.add(londonGeoLocation);
        offices.add(orlandoGeoLocation);
        offices.add(shanghaiGeoLocation);

        IntStream.range(0, homePage.officesList.size()).forEach(each ->
        {
            browserJS.click(homePage.officesList.get(each));
            String googleMapSrc = getAttribute(homePage.googleMap, "src");
            sleep(DEFAULT_SLEEP);

            String latitude = offices.get(each)[0];
            String longitude = offices.get(each)[1];

            logger.info(String.format("Verify Google Map Latitude : %s", latitude));
            assertThat(googleMapSrc, CoreMatchers.containsString(latitude));

            logger.info(String.format("Verify Google Map Longitude : %s", longitude));
            assertThat(googleMapSrc, CoreMatchers.containsString(longitude));
        });
    }

    @Test
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testChangeLanguageCheckText() throws IOException
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);
        mouseOver(homePage.languageMenu);
        browserJS.click(homePage.englishOption);

        Properties config = loadConfigProperties("homepagetext.properties");

        homePageText.forEach(text -> {
            logger.info(String.format("searching text -> %s", config.getProperty(text)));
            Assert.assertTrue(isTextDisplayedOnPage(config.getProperty(text)));
        });

        navigateToURL(UrlFactory.MAIN_URL_FIRST);
        waitAndClick(homePage.officesMenu);
        sleep(DEFAULT_MEDIUM_SLEEP);
    }

}
