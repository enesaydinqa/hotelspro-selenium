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

public class GuestDetailControlsTest extends AbstractHotelsProTest
{
    private String hotelName = "Istanbul";
    private String country = "Turkey";
    private String nameEmptyError = "\"İsim\" is not allowed to be empty";
    private String lastNameEmptyError = "\"Soyisim\" is not allowed to be empty";

    private HotelDetailsPage hotelDetailsPage;
    private CheckoutPage checkoutPage;

    @Before
    public void before()
    {
        hotelDetailsPage = new HotelDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı check-out sayfasındaki konuk misafir isim-soyisim detaylarını başarılı bir şekilde girebilmelidir.")
    @PreconditionsToReproduce("Kullanıcının birden fazla kişi sayısı için rezervasyon yaptırması gerekir.")
    @Category(ParallelExecutable.class)
    public void testMultipleReservation()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());

        searchHotel(hotelName, country, "2", "3", "1");
        randomHotelSelect();

        switchWindowTab(1);
        waitAndClick(hotelDetailsPage.bookNowButton);
        switchWindowTab(2);

        Assert.assertEquals("team leader areas do not appear properly", 3, checkoutPage.leadInformationInputs.size());

        waitAndClick(checkoutPage.checkoutFormSubmitButton);

        Assert.assertEquals(nameEmptyError, getText(checkoutPage.otherGuestInformationErrors.get(0)));
        Assert.assertEquals(lastNameEmptyError, getText(checkoutPage.otherGuestInformationErrors.get(1)));
    }

}

