package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.context.utils.param.Constants;
import com.selenium.pages.web.SearchPage;
import com.selenium.pages.web.SearchResultPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Category(ParallelExecutable.class)
public class SearchTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(SearchTest.class);

    private SearchPage searchPage;
    private SearchResultPage searchResultPage;

    @Before
    public void init() throws Exception
    {
        super.init();

        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan search sonrası gelinen Hotel results sayfasındaki Change search butonu ile arama kriterlerinin değiştirilmesi " +
            "ve tekrar arama yapılabilmesi gerekmektedir.")
    public void testChangeSearch()
    {
        loginAndSearchHotel();
        waitAndClick(searchPage.changeSearch);
        changeSearchHotel("Ankara", "Turkey", "4", "2", "2");

        Assert.assertTrue(searchResultPage.hotelList.size() > 0);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForHotelName()
    {
        loginAndSearchHotel();
        validateElementExistence(searchResultPage.searchHotelCount);

        int preFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));
        logger.info("Search Result Hotel Count : " + preFilterHotelCount);

        waitAndSendKeys(searchResultPage.hotelNameFilterInput, Constants.Filter.HOTEL_NAME);
        waitAndClick(searchResultPage.searchButton);
        waitLoadIconNotView();

        int postFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));

        if (searchResultPage.hotelList.size() > 0)
        {
            logger.info("Check Hotel Name : " + Constants.Filter.HOTEL_NAME);
            Assert.assertTrue(isTextDisplayedOnPage(Constants.Filter.HOTEL_NAME));
        }
        else
        {
            logger.info("Check No Result Message: " + Constants.SearchResult.FILTER_RESULT_NO_MESSAGE);
            Assert.assertTrue(isTextDisplayedOnPage(Constants.SearchResult.FILTER_RESULT_NO_MESSAGE));
        }

        logger.info("Filter Search Result Hotel Count : " + postFilterHotelCount);
        Assert.assertTrue(preFilterHotelCount > postFilterHotelCount);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForStarRating()
    {
        loginAndSearchHotel();
        validateElementExistence(searchResultPage.searchHotelCount);

        int preFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));
        logger.info("Search Result Hotel Count : " + preFilterHotelCount);

        sleep(DEFAULT_MEDIUM_SLEEP);
        browserJS.click(searchResultPage.startRatings.get(0));
        waitAndClick(searchResultPage.searchButton);
        waitLoadIconNotView();

        int postFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));

        logger.info("Filter Search Result Hotel Count : " + postFilterHotelCount);
        Assert.assertTrue(preFilterHotelCount > postFilterHotelCount);

        searchResultPage.searchResultStarRatings.forEach(each -> {
            int starRating = each.findElements(By.cssSelector("i")).size();

            logger.info(String.format("Star Rating : %s", starRating));
            Assert.assertEquals(5, starRating);
        });
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForPrice()
    {
        loginAndSearchHotel();
        validateElementExistence(searchResultPage.searchHotelCount);

        int preFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));
        logger.info("Search Result Hotel Count : " + preFilterHotelCount);

        waitAndSendKeys(searchResultPage.minPriceInput, String.valueOf(Constants.Filter.MIN_PRICE));
        waitAndSendKeys(searchResultPage.maxPriceInput, String.valueOf(Constants.Filter.MAX_PRICE));
        waitAndClick(searchResultPage.searchButton);
        waitLoadIconNotView();

        int postFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));

        logger.info("Filter Search Result Hotel Count : " + postFilterHotelCount);
        Assert.assertTrue(preFilterHotelCount > postFilterHotelCount);

        searchResultPage.hotelPriceTitle.forEach(each -> {
            int price = Integer.valueOf(getText(each).split("€ ")[1]);

            logger.info("Hotel Price -> " + price);
            Assert.assertTrue("price range is wrong !!!", price >= Constants.Filter.MIN_PRICE & price <= Constants.Filter.MAX_PRICE);
        });

    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForDistrict()
    {
        loginAndSearchFilter(searchResultPage.districtMenuTitle, Constants.Filter.DISTRICT);

        waitElementVisible(searchResultPage.capitalizeTitle);
        Assert.assertEquals("capitalize is wrong !!!", Constants.Filter.DISTRICT.toLowerCase(), getText(searchResultPage.capitalizeTitle).toLowerCase());
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForFacility()
    {
        loginAndSearchFilter(searchResultPage.facilityMenuTitle, Constants.Filter.FACILITY_NAME);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForLandmark()
    {
        loginAndSearchHotel();
        validateElementExistence(searchResultPage.searchHotelCount);

        int preFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));
        logger.info("Search Result Hotel Count : " + preFilterHotelCount);

        selectOptionVisibleText(searchResultPage.landmarkStatusSelect, Constants.Filter.LANDMARK);
        waitAndClick(searchResultPage.searchButton);
        waitLoadIconNotView();

        int postFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));

        logger.info("Filter Search Result Hotel Count : " + postFilterHotelCount);
        Assert.assertTrue(preFilterHotelCount >= postFilterHotelCount);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForRoomType()
    {
        loginAndSearchFilter(searchResultPage.roomTypeMenuTitle, Constants.Filter.ROOM_TYPE);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForMealType()
    {
        loginAndSearchFilter(searchResultPage.mealTypeMenuTitle, Constants.Filter.MEAL_TYPE);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForRoomCategory()
    {
        loginAndSearchFilter(searchResultPage.roomCategoryMenuTitle, Constants.Filter.ROOM_CATEGORY);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan filtreleme işlemlerinin düzgün bir şekilde çalışması gerekmektedir.")
    public void testFilterForPromotionAndDeals()
    {
        loginAndSearchFilter(searchResultPage.promotionAndDealsMenuTitle, Constants.Filter.PROMOTION_AND_DEALS);
    }

    @Test
    @ExpectedResult("Hotelspro'da yapılan search sonrası gelinen Hotel results sayfasında Show on Map butonu ile otellerin haritadaki konumlarını gördükten sonra " +
            "Hide Map butonu ile Hotel Results sayfasına geri dönülebilmesi gerekmektedir.")
    public void testHideMap()
    {
        loginAndSearchHotel();
        validateElementExistence(searchResultPage.showOnMapButton);

        int maxLoop = 5000;
        int loop = 0;

        while (true)
        {
            loop++;

            logger.info("check show on map button do not disable counter -> " + loop);
            String buttonDisabled = getAttribute(searchResultPage.showOnMapButton, "class");

            if (buttonDisabled.equals("button-show-on-map") & loop < maxLoop)
            {
                break;
            }
        }

        waitAndClick(searchResultPage.showOnMapButton);
        Assert.assertTrue("show on map button not appeared !!!", isDisplayed(searchResultPage.mapContainer));

        waitAndClick(searchResultPage.showOnMapButton);
        Assert.assertFalse("show on map button appeared !!!", isDisplayed(searchResultPage.mapContainer));
    }

    private void loginAndSearchFilter(WebElement menu, String filterKeyword)
    {
        loginAndSearchHotel();
        validateElementExistence(searchResultPage.searchHotelCount);

        int preFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));
        logger.info("Search Result Hotel Count : " + preFilterHotelCount);

        waitElementToBeClickable(menu);
        browserJS.scrollToElement(menu);
        menu.click();

        WebElement filterOption = driver.findElement(By.xpath("//p[text()='" + filterKeyword + "']//parent::label/input"));

        browserJS.scrollToElement(filterOption);
        sleep(DEFAULT_MEDIUM_SLEEP);
        browserJS.click(filterOption);
        waitAndClick(searchResultPage.searchButton);
        waitLoadIconNotView();

        int postFilterHotelCount = Integer.valueOf(getText(searchResultPage.searchHotelCount));

        logger.info("Filter Search Result Hotel Count : " + postFilterHotelCount);
        Assert.assertTrue(preFilterHotelCount >= postFilterHotelCount);
    }


}