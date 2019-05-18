package com.selenium.context.base;

import com.selenium.context.utils.param.Constants;
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
import java.util.Properties;
import java.util.Random;

public abstract class AbstractHotelsProTest extends AbstractWebTest
{
    private Logger logger = Logger.getLogger(AbstractHotelsProTest.class);

    private SearchPage searchPage;
    private SearchResultPage searchResultPage;
    private HotelDetailsPage hotelDetailsPage;
    private CheckoutPage checkoutPage;
    private LoginPage loginPage;

    protected void login(String username, String password)
    {
        if (loginPage == null) loginPage = new LoginPage(driver);

        navigateToURL(UrlFactory.LOGIN);
        waitAndSendKeys(loginPage.usernameInput, username);
        waitAndSendKeys(loginPage.passwordInput, password);
        waitAndClick(loginPage.loginButton);
        waitLoadIconNotView();

        /**
         *
         *TODO : Bazı durumlarda reCaptcha çıkıyor !!!
         *
         **/
    }

    protected void checkInCheckOutDateSelect()
    {
        if (searchPage == null) searchPage = new SearchPage(driver);

        waitAndClick(searchPage.checkinDate);

        int checkInDate = LocalDate.now().getDayOfMonth();

        WebElement checkinDateDay = driver.findElement(By.xpath("//div[contains(@class, 'CalendarMonthGrid')]/div[2]//td[text()='" + checkInDate + "']"));

        waitAndClick(checkinDateDay);
    }

    protected void searchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount)
    {
        if (searchPage == null) searchPage = new SearchPage(driver);

        waitAndSendKeys(searchPage.pacInput, hotel);
        sleep(3);
        waitAndClick(searchPage.destinationOptions.get(0));
        waitAndSendKeys(searchPage.countryInput, passportCountry);
        sleep(3);
        waitAndClick(searchPage.countryOptions.get(0));

        checkInCheckOutDateSelect();

        selectOptionVisibleText(searchPage.roomsSelect, roomCount);
        selectOptionVisibleText(searchPage.adultsSelect, adultsCount);
        selectOptionVisibleText(searchPage.childrenSelect, childrenCount);
        waitAndClick(searchPage.searchHotelButton);

        waitHotelSearchAnimate();
    }

    protected void changeSearchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount)
    {
        if (searchPage == null) searchPage = new SearchPage(driver);

        waitAndSendKeys(searchPage.pacInput, hotel);
        sleep(3);
        waitAndClick(searchPage.destinationOptionsOther.get(0));
        waitAndSendKeys(searchPage.countryInput, passportCountry);
        sleep(3);
        waitAndClick(searchPage.countryOptionsOther.get(0));

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
        if (searchResultPage == null) searchResultPage = new SearchResultPage(driver);

        waitHotelSearchAnimate();
        sleep(3);

        int selectHotelCount = new Random().nextInt(searchResultPage.hotelList.size());

        logger.info("Search Result Select Hotel Count :" + selectHotelCount);
        waitAndClick(searchResultPage.hotelList.get(selectHotelCount));
    }

    protected void trySearchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount)
    {
        trySearchHotel(hotel, passportCountry, roomCount, adultsCount, childrenCount, false);
    }

    protected void trySearchHotel(String hotel, String passportCountry, String roomCount, String adultsCount, String childrenCount, Boolean addTransfer)
    {
        if (hotelDetailsPage == null) hotelDetailsPage = new HotelDetailsPage(driver);
        if (checkoutPage == null) checkoutPage = new CheckoutPage(driver);

        searchHotel(hotel, passportCountry, roomCount, adultsCount, childrenCount);

        if (addTransfer)
        {
            do
            {
                randomHotelSelect();
                switchWindowTab(1);

                if (!isDisplayed(hotelDetailsPage.addTransferText))
                {
                    driver.close();
                    switchWindowTab(0);
                }
            }
            while (!isDisplayed(hotelDetailsPage.addTransferText));

            waitAndClick(hotelDetailsPage.addTransferText);
            transferOwnerFormFilling();
        }

        if (!addTransfer)
        {
            do
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
            }
            while (isDisplayed(hotelDetailsPage.productNotFound));

            checkoutPage.leadInformationInputs.parallelStream().forEach(this::waitElementVisible);

            Assert.assertEquals("team leader areas do not appear properly", 3, checkoutPage.leadInformationInputs.size());

            waitAndClick(checkoutPage.checkoutFormSubmitButton);
        }

    }

    protected void transferOwnerFormFilling()
    {
        if (hotelDetailsPage == null) hotelDetailsPage = new HotelDetailsPage(driver);

        sleep(3);

        browserJS.click(hotelDetailsPage.directionOptions.get(1));
        selectOptionIndex(hotelDetailsPage.transportSelect, 1);
        selectOptionIndex(hotelDetailsPage.flyHour, 4);
        selectOptionIndex(hotelDetailsPage.flyMinute, 1);
        waitAndClick(hotelDetailsPage.searchButton);

        /**
         *
         * TODO: Transfer -> No results found. Bu yüzden işlem devam ettirilemiyor.
         *
         */
    }

    protected void enterCreditCardInformation()
    {
        if (checkoutPage == null) checkoutPage = new CheckoutPage(driver);

        selectOptionVisibleText(checkoutPage.paymentType, configuration.getPaymentType());
        waitAndSendKeys(checkoutPage.cardHolderName, configuration.getCardHolderName());
        waitAndSendKeys(checkoutPage.cardNumber, configuration.getCardNumber());
        selectOptionValue(checkoutPage.cardExpirationMonth, configuration.getCardExpirationMonth());
        selectOptionValue(checkoutPage.cardExpirationYear, configuration.getCardExpirationYear());
        waitAndSendKeys(checkoutPage.cardCvc, configuration.getCardCVC());
    }

    protected void loginAndSearchHotel()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");
    }

    protected void waitLoadIconNotView()
    {
        if (searchResultPage == null) searchResultPage = new SearchResultPage(driver);

        while (true)
        {
            if (isDisplayed(searchResultPage.loadingIcon))
            {
                logger.info("loading icon still appearing");
            }
            else
            {
                break;
            }
        }
    }

    protected Properties readNotifications() throws Exception
    {
        String fileSeparator = System.getProperty("file.separator");
        String fileName = fileSeparator.concat("notifications.properties");

        if (configuration.getLanguage().equals("tr"))
        {
            return loadConfigProperties("tr" + fileName);
        }
        else if (configuration.getLanguage().equals("en"))
        {
            return loadConfigProperties("en" + fileName);
        }

        throw new Exception("This language not set properties file");
    }

}
