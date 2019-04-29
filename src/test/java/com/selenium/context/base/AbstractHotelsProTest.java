package com.selenium.context.base;

import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.LoginPage;
import com.selenium.pages.web.SearchPage;
import com.selenium.pages.web.SearchResultPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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

        /*
         *TODO : Bazı durumlarda reCaptcha çıkıyor !!!
         */
    }

    protected void checkInCheckOutDateSelect()
    {
        SearchPage searchPage = new SearchPage(driver);

        waitAndClick(searchPage.checkinDate);

        int checkInDate = LocalDate.now().getDayOfMonth();

        WebElement checkinDateDay = driver.findElement(By.xpath("//div[contains(@class, 'CalendarMonthGrid')]/div[2]//td[text()='" + checkInDate + "']"));

        waitAndClick(checkinDateDay);
    }

    protected void searchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount)
    {
        SearchPage searchPage = new SearchPage(driver);

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
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        waitHotelSearchAnimate();

        int selectHotelCount = new Random().nextInt(searchResultPage.hotelList.size());

        logger.info("Search Result Select Hotel Count :" + selectHotelCount);
        waitAndClick(searchResultPage.hotelList.get(selectHotelCount));
    }

}
