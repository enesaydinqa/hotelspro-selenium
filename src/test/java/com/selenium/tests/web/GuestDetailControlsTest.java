package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.web.CheckoutPage;
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

    private CheckoutPage checkoutPage;

    @Before
    public void before()
    {
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı check-out sayfasındaki konuk misafir isim-soyisim detaylarını başarılı bir şekilde girebilmelidir.")
    @PreconditionsToReproduce("Kullanıcının birden fazla kişi sayısı için rezervasyon yaptırması gerekir.")
    @Category(ParallelExecutable.class)
    public void testMultipleReservation()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());

        trySearchHotel(hotelName, country, "2", "3", "1");

        Assert.assertEquals(nameEmptyError, getText(checkoutPage.otherGuestInformationErrors.get(0)));
        Assert.assertEquals(lastNameEmptyError, getText(checkoutPage.otherGuestInformationErrors.get(1)));
    }

}

