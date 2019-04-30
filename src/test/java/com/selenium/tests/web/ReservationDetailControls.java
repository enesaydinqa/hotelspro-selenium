package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.web.CheckoutPage;
import com.selenium.pages.web.HotelDetailsPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;
import java.util.Map;

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

        Map<String, String> reservationDetailContainer = new HashMap<>();
        reservationDetailContainer.put("370px", "width");
        reservationDetailContainer.put("right", "float");
        reservationDetailContainer.put("baseline", "vertical-align");

        reservationDetailContainer.entrySet().parallelStream().forEach((entry) -> Assert.assertEquals(entry.getKey(), checkoutPage.reservationDetailContainer.getCssValue(entry.getValue())));

        Map<String, String> checkoutDetailHotelInfo = new HashMap<>();
        checkoutDetailHotelInfo.put("20px", "padding");
        checkoutDetailHotelInfo.put("1px solid rgb(214, 214, 214)", "border-bottom");
        checkoutDetailHotelInfo.put("baseline", "vertical-align");
        checkoutDetailHotelInfo.put("optimizelegibility", "text-rendering");
        checkoutDetailHotelInfo.put("antialiased", "-webkit-font-smoothing");
        checkoutDetailHotelInfo.put("border-box", "box-sizing");
        checkoutDetailHotelInfo.put("block", "display");

        checkoutDetailHotelInfo.entrySet().parallelStream().forEach((entry) -> Assert.assertEquals(entry.getKey(), checkoutPage.checkoutDetailHotelInfo.getCssValue(entry.getValue())));

        Map<String, String> checkoutDetailHotelInfoImage = new HashMap<>();
        checkoutDetailHotelInfoImage.put("100px", "width");
        checkoutDetailHotelInfoImage.put("100px", "height");
        checkoutDetailHotelInfoImage.put("left", "float");

        checkoutDetailHotelInfoImage.entrySet().parallelStream().forEach((entry) -> Assert.assertEquals(entry.getKey(), checkoutPage.checkoutDetailHotelInfoImage.getCssValue(entry.getValue())));

        Map<String, String> checkoutDetailHotelInfoText = new HashMap<>();
        checkoutDetailHotelInfoText.put("209px", "width");
        checkoutDetailHotelInfoText.put("left", "float");
        checkoutDetailHotelInfoText.put("18px", "margin-left");
        checkoutDetailHotelInfoText.put("14px", "font-size");
        checkoutDetailHotelInfoText.put("rgba(116, 116, 118, 1)", "color");

        checkoutDetailHotelInfoText.entrySet().parallelStream().forEach((entry) -> Assert.assertEquals(entry.getKey(), checkoutPage.checkoutDetailHotelInfoText.getCssValue(entry.getValue())));

        Map<String, String> checkoutDetailHotelInfoStartDay = new HashMap<>();
        checkoutDetailHotelInfoStartDay.put("115px", "width");
        checkoutDetailHotelInfoStartDay.put("left", "float");
        checkoutDetailHotelInfoStartDay.put("700", "font-weight");
        checkoutDetailHotelInfoStartDay.put("rgba(116, 116, 118, 1)", "color");
        checkoutDetailHotelInfoStartDay.put("baseline", "vertical-align");

        checkoutDetailHotelInfoStartDay.entrySet().parallelStream().forEach((entry) -> Assert.assertEquals(entry.getKey(), checkoutPage.checkoutDetailHotelInfoStartDay.getCssValue(entry.getValue())));

        Map<String, String> checkoutDetailHotelInfoEndDay = new HashMap<>();
        checkoutDetailHotelInfoEndDay.put("20px", "margin-left");
        checkoutDetailHotelInfoEndDay.put("115px", "width");
        checkoutDetailHotelInfoEndDay.put("left", "float");
        checkoutDetailHotelInfoEndDay.put("700", "font-weight");
        checkoutDetailHotelInfoEndDay.put("rgba(116, 116, 118, 1)", "color");
        checkoutDetailHotelInfoEndDay.put("baseline", "vertical-align");

        checkoutDetailHotelInfoEndDay.entrySet().parallelStream().forEach((entry) -> Assert.assertEquals(entry.getKey(), checkoutPage.checkoutDetailHotelInfoEndDay.getCssValue(entry.getValue())));

    }

}

