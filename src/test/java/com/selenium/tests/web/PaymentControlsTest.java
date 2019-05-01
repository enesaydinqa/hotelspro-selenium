package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.context.utils.param.Constants;
import com.selenium.pages.web.CheckoutPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;


public class PaymentControlsTest extends AbstractHotelsProTest
{
    private CheckoutPage checkoutPage;

    @Before
    public void before()
    {
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı check-out sayfasındaki ödeme bilgilerini görebilmelidir.")
    @Category(ParallelExecutable.class)
    public void testPayment()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());

        trySearchHotel(Constants.HOTEL_NAME, Constants.COUNTRY, "2", "3", "1");

        List<WebElement> paymentElements = Arrays.asList(
                checkoutPage.paymentContainer,
                checkoutPage.paymentWarningOne,
                checkoutPage.paymentWarningSecond,
                checkoutPage.paymentForm,
                checkoutPage.cardInfo
        );

        paymentElements.parallelStream().forEach(this::isDisplayed);

        /**
         *
         * ÖDEME YAPILACAK
         *
         */

    }
}
