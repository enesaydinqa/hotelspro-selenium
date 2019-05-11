package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.context.utils.param.Constants;
import com.selenium.pages.web.SearchResultPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

@Category(ParallelExecutable.class)
public class SortingTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(SortingTest.class);

    private SearchResultPage searchResultPage;

    @Before
    public void init() throws Exception
    {
        super.init();

        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan sıralama işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testSortingForDistance()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        sortingAndAssert(searchResultPage.sortOptionsList.get(1), SortName.DISTANCE);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan sıralama işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testSortingForPrice()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        sortingAndAssert(searchResultPage.sortOptionsList.get(2), SortName.PRICE);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan sıralama işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testSortingForStarRating()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        sortingAndAssert(searchResultPage.sortOptionsList.get(3), SortName.STAR_RATING);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan sıralama işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testSortingForPopularity()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        sortingAndAssert(searchResultPage.sortOptionsList.get(4), SortName.POPULARITY);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan sıralama işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testSortingForTripAdvisor()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        sortingAndAssert(searchResultPage.sortOptionsList.get(5), SortName.TRIP_ADVISOR);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan sıralama işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testSortingForHotelName()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        searchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        sortingAndAssert(searchResultPage.sortOptionsList.get(5), SortName.HOTEL_NAME);
    }

    private void sortingAndAssert(WebElement sortingElement, SortName sortName)
    {
        validateElementExistence(sortingElement);
        waitAndClick(sortingElement);

        if (sortName == SortName.PRICE)
        {
            waitAndClick(searchResultPage.priceSortingOptions.get(1));
        }

        waitLoadIconNotView();

        logger.info("Check Sorting -> " + sortName.toString());
        Assert.assertEquals(sortName.toString() + " sorting not active", "active", getAttribute(sortingElement, "class"));
    }

    public enum SortName
    {
        DISTANCE,
        PRICE,
        STAR_RATING,
        POPULARITY,
        TRIP_ADVISOR,
        HOTEL_NAME
    }
}