package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.CollectionPage;
import com.selenium.pages.web.HotelDetailsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class HotelDetailsTest extends AbstractHotelsProTest
{
    private Logger logger = Logger.getLogger(HotelDetailsTest.class);

    private HotelDetailsPage hotelDetailsPage;
    private CollectionPage collectionPage;

    @Before
    public void before()
    {
        hotelDetailsPage = new HotelDetailsPage(driver);
        collectionPage = new CollectionPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı hotel detail sayfasında 'Room Type' ve 'Meal Type' 'a göre filtreleme yapabilmelidir.")
    public void testFilteringOffers()
    {
        loginAndSearchHotel(true);

        filterSelect(hotelDetailsPage.roomTypeSelect, hotelDetailsPage.roomTypeOptionCheckboxes.get(0), "Room");
        filterSelect(hotelDetailsPage.mealTypeSelect, hotelDetailsPage.mealTypeOptionCheckboxes.get(0), "Meal");
    }

    @Test
    @ExpectedResult("Kullanıcı hotel detail sayfasında hotelin konumunu haritadan görebilmelidir.")
    @PreconditionsToReproduce("Kullanıcının hotel details sayfasında olması gerekir.")
    public void testDisplayingMapView()
    {
        loginAndSearchHotel(true);

        waitAndClick(hotelDetailsPage.showMapButton);
        sleep(DEFAULT_MEDIUM_SLEEP);

        Assert.assertTrue("detail map not appeared !!!", isDisplayed(hotelDetailsPage.hotelDetailMap));
    }

    @Test
    @ExpectedResult("Kullanıcı istediği otel için 'Create Offer' seçeneğini seçerek teklif yaratabilmelidir.")
    @PreconditionsToReproduce("Kullanıcının hotel details sayfasında olması gerekir.")
    public void testCreateOffer()
    {
        loginAndSearchHotel(true);
        waitAndClick(hotelDetailsPage.createOfferButton);
        sleep(DEFAULT_MEDIUM_SLEEP);

        for (int i = 0; i < 3; i++) waitAndClick(hotelDetailsPage.pickOfferImages.get(i));

        browserJS.click(hotelDetailsPage.roomList.get(0));
        waitAndClick(hotelDetailsPage.createOfferAndSavePDFButton);
        waitLoadIconNotView();

        Assert.assertEquals("url is wrong !!!", getCurrentURL(), UrlFactory.OFFER_PDF.pageUrl);
    }

    @Test
    @ExpectedResult("Kullanıcı hotel detail sayfasında önceden yapmış olduğu arama kriterlerini değiştirebilmelidir.")
    public void testChangeSearch()
    {
        loginAndSearchHotel(true);

        waitAndClick(hotelDetailsPage.changeSearchText);
        waitAndSendKeys(hotelDetailsPage.countrySelect, "Germany");
        sleep(DEFAULT_SLEEP * 3);
        waitAndClick(hotelDetailsPage.countryAutoCompleteOptions.get(0));
        selectOptionIndex(hotelDetailsPage.roomsSelect, 0);
        selectOptionIndex(hotelDetailsPage.adultsSelect, 2);
        selectOptionIndex(hotelDetailsPage.childrenSelect, 2);
        waitAndClick(hotelDetailsPage.modifyButton);
        sleep(DEFAULT_MEDIUM_SLEEP);

        String roomAvailabilitySummary = getText(hotelDetailsPage.roomAvailabilityText);

        logger.info("Room Availability Summary : " + roomAvailabilitySummary);

        assertThat(roomAvailabilitySummary, CoreMatchers.containsString("3 Yetişkin"));
        assertThat(roomAvailabilitySummary, CoreMatchers.containsString("2 Çocuk"));
    }


    @Test
    @ExpectedResult("Kullanıcı istediği hotel'i hotel detail sayfasından favorilerime başarılı bir şekilde eklemelidir.")
    @PreconditionsToReproduce("Kullanıcının hotel details sayfasında olması gerekir.")
    public void testAddToFavorite()
    {
        loginAndSearchHotel(true);
        sleep(DEFAULT_MEDIUM_SLEEP);
        mouseOver(hotelDetailsPage.addToFavoriteText);

        String favoriteListName = RandomStringUtils.randomAlphabetic(40);

        waitAndSendKeys(hotelDetailsPage.addCollectionInput, favoriteListName);
        waitAndClick(hotelDetailsPage.addFavoriteButton);
        sleep(DEFAULT_MEDIUM_SLEEP);
        mouseOver(hotelDetailsPage.addToFavoriteText);
        sleep(DEFAULT_MEDIUM_SLEEP);

        List<WebElement> favoriteListNames = hotelDetailsPage.addCollectionListCheckboxes;

        browserJS.click(favoriteListNames.get(favoriteListNames.size() - 1));
        sleep(DEFAULT_MEDIUM_SLEEP);

        navigateToURL(UrlFactory.COLLECTIONS);

        selectOptionVisibleText(collectionPage.collectionNameSelect, favoriteListName);
        sleep(DEFAULT_MEDIUM_SLEEP);

        Assert.assertEquals("otel not added favorite list", 1, collectionPage.favoriteItemList.size());
    }

    private void filterSelect(WebElement filter, WebElement filterOptions, String type)
    {
        waitAndClick(filter);
        sleep(DEFAULT_SLEEP);
        logger.info(String.format("%s Type Select", type));
        browserJS.click(filterOptions);
        sleep(DEFAULT_SLEEP);

        Assert.assertTrue(type + " type not checked", filterOptions.isSelected());
    }
}
