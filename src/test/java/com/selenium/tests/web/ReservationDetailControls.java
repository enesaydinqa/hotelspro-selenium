package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.web.HotelDetailsPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ReservationDetailControls extends AbstractHotelsProTest
{
    private String hotelName = "Istanbul";
    private String country = "Turkey";

    private HotelDetailsPage hotelDetailsPage;
    private CheckoutPage checkoutPage;

    @Before
    public void before()
    {
        hotelDetailsPage = new HotelDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı check-out sayfasındaki rezervasyon bilgilerini görebilmelidir.")
    @Category(ParallelExecutable.class)
    public void testReservationDetail()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());

        searchHotel(hotelName, country, "2", "3", "1");
        randomHotelSelect();

        switchWindowTab(1);
        waitAndClick(hotelDetailsPage.bookNowButton);
        switchWindowTab(2);

        Assert.assertEquals("team leader areas do not appear properly", 3, checkoutPage.leadInformationInputs.size());

        waitAndClick(checkoutPage.checkoutFormSubmitButton);

        Assert.assertEquals("370px", checkoutPage.reservationDetailContainer.getCssValue("width"));
        Assert.assertEquals("right", checkoutPage.reservationDetailContainer.getCssValue("float"));
        Assert.assertEquals("baseline", checkoutPage.reservationDetailContainer.getCssValue("vertical-align"));
        Assert.assertEquals("20px", checkoutPage.checkoutDetailHotelInfo.getCssValue("padding"));
        Assert.assertEquals("1px solid rgb(214, 214, 214)", checkoutPage.checkoutDetailHotelInfo.getCssValue("border-bottom"));
        Assert.assertEquals("baseline", checkoutPage.checkoutDetailHotelInfo.getCssValue("vertical-align"));
        Assert.assertEquals("optimizelegibility", checkoutPage.checkoutDetailHotelInfo.getCssValue("text-rendering"));
        Assert.assertEquals("antialiased", checkoutPage.checkoutDetailHotelInfo.getCssValue("-webkit-font-smoothing"));
        Assert.assertEquals("border-box", checkoutPage.checkoutDetailHotelInfo.getCssValue("box-sizing"));
        Assert.assertEquals("block", checkoutPage.checkoutDetailHotelInfo.getCssValue("display"));
        Assert.assertEquals("100px", checkoutPage.checkoutDetailHotelInfoImage.getCssValue("width"));
        Assert.assertEquals("100px", checkoutPage.checkoutDetailHotelInfoImage.getCssValue("height"));
        Assert.assertEquals("left", checkoutPage.checkoutDetailHotelInfoImage.getCssValue("float"));
        Assert.assertEquals("209px", checkoutPage.checkoutDetailHotelInfoText.getCssValue("width"));
        Assert.assertEquals("left", checkoutPage.checkoutDetailHotelInfoText.getCssValue("float"));
        Assert.assertEquals("18px", checkoutPage.checkoutDetailHotelInfoText.getCssValue("margin-left"));
        Assert.assertEquals("14px", checkoutPage.checkoutDetailHotelInfoText.getCssValue("font-size"));
        Assert.assertEquals("rgba(116, 116, 118, 1)", checkoutPage.checkoutDetailHotelInfoText.getCssValue("color"));

    }

}

