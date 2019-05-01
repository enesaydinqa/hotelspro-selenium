package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.context.utils.param.Constants;
import com.selenium.pages.web.HotelDetailsPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class TransferTest extends AbstractHotelsProTest
{
    private HotelDetailsPage hotelDetailsPage;

    @Before
    public void before()
    {
        hotelDetailsPage = new HotelDetailsPage(driver);
    }

    @Test
    @ExpectedResult("Hotelspro'da bir bookinge eklenen transfer bilgilerinin görüntülenebilmesi gerekmektedir.")
    @Category(ParallelExecutable.class)
    public void testAddedTransferInformationView()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());

        trySearchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1", true);
    }

}
