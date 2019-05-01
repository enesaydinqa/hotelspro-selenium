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

        trySearchHotel(hotelName, country, "2", "3", "1");

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

        checkoutDetailHotelInfo.entrySet().parallelStream().forEach((css) -> Assert.assertEquals(css.getKey(), checkoutPage.checkoutDetailHotelInfo.getCssValue(css.getValue())));

        Map<String, String> checkoutDetailHotelInfoImage = new HashMap<>();
        checkoutDetailHotelInfoImage.put("100px", "width");
        checkoutDetailHotelInfoImage.put("100px", "height");
        checkoutDetailHotelInfoImage.put("left", "float");

        checkoutDetailHotelInfoImage.entrySet().parallelStream().forEach((css) -> Assert.assertEquals(css.getKey(), checkoutPage.checkoutDetailHotelInfoImage.getCssValue(css.getValue())));

        Map<String, String> checkoutDetailHotelInfoText = new HashMap<>();
        checkoutDetailHotelInfoText.put("209px", "width");
        checkoutDetailHotelInfoText.put("left", "float");
        checkoutDetailHotelInfoText.put("18px", "margin-left");
        checkoutDetailHotelInfoText.put("14px", "font-size");
        checkoutDetailHotelInfoText.put("rgba(116, 116, 118, 1)", "color");

        checkoutDetailHotelInfoText.entrySet().parallelStream().forEach((css) -> Assert.assertEquals(css.getKey(), checkoutPage.checkoutDetailHotelInfoText.getCssValue(css.getValue())));

        Map<String, String> checkoutDetailHotelInfoStartDay = new HashMap<>();
        checkoutDetailHotelInfoStartDay.put("115px", "width");
        checkoutDetailHotelInfoStartDay.put("left", "float");
        checkoutDetailHotelInfoStartDay.put("700", "font-weight");
        checkoutDetailHotelInfoStartDay.put("rgba(116, 116, 118, 1)", "color");
        checkoutDetailHotelInfoStartDay.put("baseline", "vertical-align");

        checkoutDetailHotelInfoStartDay.entrySet().parallelStream().forEach((css) -> Assert.assertEquals(css.getKey(), checkoutPage.checkoutDetailHotelInfoStartDay.getCssValue(css.getValue())));

        Map<String, String> checkoutDetailHotelInfoEndDay = new HashMap<>();
        checkoutDetailHotelInfoEndDay.put("20px", "margin-left");
        checkoutDetailHotelInfoEndDay.put("115px", "width");
        checkoutDetailHotelInfoEndDay.put("left", "float");
        checkoutDetailHotelInfoEndDay.put("700", "font-weight");
        checkoutDetailHotelInfoEndDay.put("rgba(116, 116, 118, 1)", "color");
        checkoutDetailHotelInfoEndDay.put("baseline", "vertical-align");

        checkoutDetailHotelInfoEndDay.entrySet().parallelStream().forEach((css) -> Assert.assertEquals(css.getKey(), checkoutPage.checkoutDetailHotelInfoEndDay.getCssValue(css.getValue())));

        Map<String, String> reservationDetailIcons = new HashMap<>();
        reservationDetailIcons.put("hidden", "overflow");
        reservationDetailIcons.put("15px 0px", "margin");
        reservationDetailIcons.put("328px", "width");
        reservationDetailIcons.put("baseline", "vertical-align");
        reservationDetailIcons.put("rgba(0, 0, 0, 0) none repeat scroll 0% 0% / auto padding-box border-box", "background");
        reservationDetailIcons.put("optimizelegibility", "text-rendering");
        reservationDetailIcons.put("antialiased", "-webkit-font-smoothing");
        reservationDetailIcons.put("border-box", "box-sizing");

        reservationDetailIcons.entrySet().parallelStream().forEach((css) -> Assert.assertEquals(css.getKey(), checkoutPage.reservationDetailIcons.getCssValue(css.getValue())));

    }

}

