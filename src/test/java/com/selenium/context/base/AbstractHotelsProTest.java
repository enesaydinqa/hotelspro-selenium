package com.selenium.context.base;

import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.CheckoutPage;
import com.selenium.pages.web.HotelDetailsPage;
import com.selenium.pages.web.LoginPage;
import com.selenium.pages.web.SearchPage;
import com.selenium.pages.web.SearchResultPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public abstract class AbstractHotelsProTest extends AbstractWebTest
{
    private Logger logger = Logger.getLogger(AbstractHotelsProTest.class);

    private SearchPage searchPage;
    private SearchResultPage searchResultPage;
    private HotelDetailsPage hotelDetailsPage;
    private CheckoutPage checkoutPage;

    protected void login(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);

        navigateToURL(UrlFactory.LOGIN);
        waitAndSendKeys(loginPage.usernameInput, username);
        waitAndSendKeys(loginPage.passwordInput, password);
        waitAndClick(loginPage.loginButton);

        /*
         *TODO : Bazı durumlarda reCaptcha çıkıyor !!!
         */
    }

    protected void checkInCheckOutDateSelect()
    {
        searchPage = new SearchPage(driver);

        waitAndClick(searchPage.checkinDate);

        int checkInDate = LocalDate.now().getDayOfMonth();

        WebElement checkinDateDay = driver.findElement(By.xpath("//div[contains(@class, 'CalendarMonthGrid')]/div[2]//td[text()='" + checkInDate + "']"));

        waitAndClick(checkinDateDay);
    }

    protected void searchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount)
    {
        searchPage = new SearchPage(driver);

        waitAndSendKeys(searchPage.pacInput, hotel);
        sleep(2);
        waitAndClick(searchPage.destinationOptions.get(0));
        waitAndSendKeys(searchPage.countryInput, passportCountry);
        sleep(2);
        waitAndClick(searchPage.countryOptions.get(0));

        checkInCheckOutDateSelect();

        selectOptionVisibleText(searchPage.roomsSelect, roomCount);
        selectOptionVisibleText(searchPage.adultsSelect, adultsCount);
        selectOptionVisibleText(searchPage.childrenSelect, childrenCount);
        waitAndClick(searchPage.searchHotelButton);

        waitHotelSearchAnimate();
    }

    private void waitHotelSearchAnimate()
    {
        int maxLoop = 2000;

        int counter = 0;

        while (true)
        {
            List<WebElement> animates = driver.findElements(By.cssSelector(".placeholder-animate"));

            if (animates.size() == 0 || counter == maxLoop)
            {
                break;
            }
        }
    }

    protected void randomHotelSelect()
    {
        searchResultPage = new SearchResultPage(driver);

        waitHotelSearchAnimate();
        sleep(3);

        int selectHotelCount = new Random().nextInt(searchResultPage.hotelList.size());

        logger.info("Search Result Select Hotel Count :" + selectHotelCount);
        waitAndClick(searchResultPage.hotelList.get(selectHotelCount));
    }

    protected void trySearchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount)
    {
        hotelDetailsPage = new HotelDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);

        searchHotel(hotel, passportCountry, roomCount, adultsCount, childrenCount);

        while (true)
        {
            randomHotelSelect();
            switchWindowTab(1);
            waitAndClick(hotelDetailsPage.bookNowButton);
            switchWindowTab(2);

            if (isDisplayed(hotelDetailsPage.productNotFound))
            {
                driver.close();
                switchWindowTab(1);
                driver.close();
                switchWindowTab(0);
            }

            if (!isDisplayed(hotelDetailsPage.productNotFound))
            {
                break;
            }
        }

        checkoutPage.leadInformationInputs.parallelStream().forEach(this::waitElementVisible);

        Assert.assertEquals("team leader areas do not appear properly", 3, checkoutPage.leadInformationInputs.size());

        waitAndClick(checkoutPage.checkoutFormSubmitButton);
    }

}
