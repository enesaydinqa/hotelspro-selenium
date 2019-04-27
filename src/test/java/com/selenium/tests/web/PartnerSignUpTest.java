package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.context.objects.ClientPartnerSignUp;
import com.selenium.context.objects.HotelPartnerSignUp;
import com.selenium.context.objects.LocalPartnerSignUp;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.ClientPartnerSignupPage;
import com.selenium.pages.web.FooterPage;
import com.selenium.pages.web.HomePage;
import com.selenium.pages.web.HotelPartnerSignupPage;
import com.selenium.pages.web.LocalPartnerSignupPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Arrays;
import java.util.List;

@NotThreadSafe
public class PartnerSignUpTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(PartnerSignUpTest.class);

    private String country = "United States";
    private String state = "Alabama";
    private String reCAPTCHAErrorMessage = "Lütfen reCAPTCHA’yı kontrol edin.";
    private String isNotAllowedToBeEmpty = "\"%s\" is not allowed to be empty";
    private String mustBeaNumber = "\"%s\" must be a number";

    private List<String> clientPartnerRequiredInputTitles = Arrays.asList("Fatura Ülkesi", "Ad", "Telefon Numarası", "E-posta adresi :", "Şehir", "İsim", "Soyisim", "Ünvan");
    private List<String> hotelPartnerRequiredInputTitles = Arrays.asList("İletişim Kurulacak Kişinin Adı:", "Telefon Numarası", "E-posta adresi :", "Fatura Ülkesi", "Ad");
    private List<String> localPartnerRequiredInputTitles = Arrays.asList("Şirket Adı", "İletişim Kurulacak Kişinin Adı:", "Telefon Numarası", "E-posta adresi :", "Fatura Ülkesi", "Tanıtım");

    private HomePage homePage;
    private FooterPage footerPage;
    private ClientPartnerSignupPage clientPartnerSignupPage;
    private HotelPartnerSignupPage hotelPartnerSignupPage;
    private LocalPartnerSignupPage localPartnerSignupPage;

    @Before
    public void before()
    {
        homePage = new HomePage(driver);
        footerPage = new FooterPage(driver);
        clientPartnerSignupPage = new ClientPartnerSignupPage(driver);
        hotelPartnerSignupPage = new HotelPartnerSignupPage(driver);
        localPartnerSignupPage = new LocalPartnerSignupPage(driver);
    }

    @Test
    @ExpectedResult("Kullanıcı başarılı bir şekilde Client Partner Sign Up Formunu doldurabilmeli ve gönderebilmelidir.")
    @PreconditionsToReproduce("Kullanıcının siteye önceden kayıtlı olmayan bir email adresi ile sign up olması gereklidir.")
    @Category(ParallelExecutable.class)
    public void testClientPartnerSignUp()
    {
        ClientPartnerSignUp clientPartnerSignUp = clientPartnerSignUpFormSet();

        navigateToURL(UrlFactory.SIGN_UP);
        mouseOver(homePage.signUpButton);
        waitAndClick(homePage.clientPartnerCustomerMenu);
        waitAndSendKeys(clientPartnerSignupPage.agencyCountry, clientPartnerSignUp.getAgencyCountry());
        waitAndClick(clientPartnerSignupPage.listFirstOption);

        jsHelper.clear(clientPartnerSignupPage.agencyCountry); // Clear

        waitAndClick(clientPartnerSignupPage.saveButton);

        clientPartnerRequiredInputTitles.forEach(title -> {

            if (title.equals("Telefon Numarası"))
            {
                logger.info("Check Required Input : " + String.format(mustBeaNumber, title));
                Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(String.format(mustBeaNumber, title)));
            }
            else
            {
                logger.info("Check Required Input : " + String.format(isNotAllowedToBeEmpty, title));
                Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(String.format(isNotAllowedToBeEmpty, title)));
            }

        });

        waitAndSendKeys(clientPartnerSignupPage.agencyCountry, clientPartnerSignUp.getAgencyCountry());
        waitAndClick(clientPartnerSignupPage.listFirstOption);

        waitAndSendKeys(clientPartnerSignupPage.agencyName, clientPartnerSignUp.getAgencyName());
        waitAndSendKeys(clientPartnerSignupPage.phone, clientPartnerSignUp.getPhone());
        waitAndSendKeys(clientPartnerSignupPage.managerEmail, clientPartnerSignUp.getManagerEmail());
        waitAndSendKeys(clientPartnerSignupPage.state, clientPartnerSignUp.getState());
        waitAndClick(clientPartnerSignupPage.listFirstOption);
        waitAndSendKeys(clientPartnerSignupPage.city, clientPartnerSignUp.getCity());
        waitAndSendKeys(clientPartnerSignupPage.address, clientPartnerSignUp.getAddress());
        waitAndSendKeys(clientPartnerSignupPage.firstName, clientPartnerSignUp.getFirstName());
        waitAndSendKeys(clientPartnerSignupPage.lastName, clientPartnerSignUp.getLastName());
        waitAndSendKeys(clientPartnerSignupPage.jobTitle, clientPartnerSignUp.getJobTitle());
        waitAndSendKeys(clientPartnerSignupPage.companyEmail, clientPartnerSignUp.getCompanyEmail());

        jsHelper.click(clientPartnerSignupPage.signupAgreement);

        waitAndClick(clientPartnerSignupPage.saveButton);
        sleep(3);
        Assert.assertTrue(isTextDisplayedOnPage(reCAPTCHAErrorMessage));
        footerPage.footerMenus.forEach(this::isDisplayed);

        /*
         *TODO : ReCaptcha olduğu için form save edilemiyor.
         */
    }

    @Test
    @ExpectedResult("Hotelspro'da Sign up'tan yeni otel partner kaydı oluşturulabilmesi gerekmektedir.")
    @Category(ParallelExecutable.class)
    public void testHotelPartnerSignUp()
    {
        HotelPartnerSignUp hotelPartnerSignUp = hotelPartnerSignUpFormSet();

        navigateToURL(UrlFactory.SIGN_UP);
        mouseOver(homePage.signUpButton);
        waitAndClick(homePage.hotelPartnerCustomerMenu);
        waitAndClick(hotelPartnerSignupPage.saveButton);

        hotelPartnerRequiredInputTitles.forEach(title -> {

            if (title.equals("Telefon Numarası"))
            {
                logger.info("Check Required Input : " + String.format(mustBeaNumber, title));
                Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(String.format(mustBeaNumber, title)));
            }
            else
            {
                logger.info("Check Required Input : " + String.format(isNotAllowedToBeEmpty, title));
                Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(String.format(isNotAllowedToBeEmpty, title)));
            }

        });

        waitAndSendKeys(hotelPartnerSignupPage.contactNameInput, hotelPartnerSignUp.getContactName());
        waitAndSendKeys(hotelPartnerSignupPage.positionInput, hotelPartnerSignUp.getPosition());
        waitAndSendKeys(hotelPartnerSignupPage.phoneInput, hotelPartnerSignUp.getPhone());
        waitAndSendKeys(hotelPartnerSignupPage.faxInput, hotelPartnerSignUp.getFax());
        waitAndSendKeys(hotelPartnerSignupPage.emailInput, hotelPartnerSignUp.getEmail());
        waitAndSendKeys(hotelPartnerSignupPage.webPageInput, hotelPartnerSignUp.getWepPage());
        selectOptionValue(hotelPartnerSignupPage.countrySelect, hotelPartnerSignUp.getCountry());
        waitAndSendKeys(hotelPartnerSignupPage.cityInput, hotelPartnerSignUp.getCity());
        waitAndSendKeys(hotelPartnerSignupPage.addressInput, hotelPartnerSignUp.getAddress());
        waitAndSendKeys(hotelPartnerSignupPage.zipcodeInput, hotelPartnerSignUp.getZippcode());
        waitAndSendKeys(hotelPartnerSignupPage.hotelNameInput, hotelPartnerSignUp.getHotelName());
        waitAndSendKeys(hotelPartnerSignupPage.typePropertyInput, hotelPartnerSignUp.getTypeProperties());
        waitAndSendKeys(hotelPartnerSignupPage.numberRoomInput, hotelPartnerSignUp.getNumberRoom());
        waitAndSendKeys(hotelPartnerSignupPage.totalSuitesInput, hotelPartnerSignUp.getTotalSuites());
        waitAndSendKeys(hotelPartnerSignupPage.informationInput, hotelPartnerSignUp.getInformation());

        jsHelper.click(hotelPartnerSignupPage.signupAgreement);

        waitAndClick(hotelPartnerSignupPage.saveButton);
        sleep(3);
        Assert.assertTrue(isTextDisplayedOnPage(reCAPTCHAErrorMessage));
        footerPage.footerMenus.forEach(this::isDisplayed);

        /*
         *TODO : ReCaptcha olduğu için form save edilemiyor.
         */
    }

    @Test
    @ExpectedResult("Hotelspro'da Sign up'tan yeni local partner kaydı oluşturulabilmesi gerekmektedir.")
    @Category(ParallelExecutable.class)
    public void testLocalPartnerSignUp()
    {
        LocalPartnerSignUp localPartnerSignUp = localPartnerSignUpFormSet();

        navigateToURL(UrlFactory.SIGN_UP);
        mouseOver(homePage.signUpButton);
        waitAndClick(homePage.localPartnerCustomerMenu);
        waitAndClick(localPartnerSignupPage.saveButton);

        localPartnerRequiredInputTitles.forEach(title -> {

            if (title.equals("Telefon Numarası"))
            {
                logger.info("Check Required Input : " + String.format(mustBeaNumber, title));
                Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(String.format(mustBeaNumber, title)));
            }
            else
            {
                logger.info("Check Required Input : " + String.format(isNotAllowedToBeEmpty, title));
                Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(String.format(isNotAllowedToBeEmpty, title)));
            }

        });

        waitAndSendKeys(localPartnerSignupPage.companyNameInput, localPartnerSignUp.getCompanyName());
        waitAndSendKeys(localPartnerSignupPage.contactNameInput, localPartnerSignUp.getContactName());
        waitAndSendKeys(localPartnerSignupPage.phoneInput, localPartnerSignUp.getPhone());
        waitAndSendKeys(localPartnerSignupPage.emailInput, localPartnerSignUp.getEmail());
        selectOptionValue(localPartnerSignupPage.countrySelect, localPartnerSignUp.getCountry());
        waitAndSendKeys(localPartnerSignupPage.addressInput, localPartnerSignUp.getAddress());
        waitAndSendKeys(localPartnerSignupPage.introductionInput, localPartnerSignUp.getCompanyForIntroduction());

        jsHelper.click(localPartnerSignupPage.signupAgreement);

        waitAndClick(localPartnerSignupPage.saveButton);
        sleep(3);
        Assert.assertTrue(isTextDisplayedOnPage(reCAPTCHAErrorMessage));
        footerPage.footerMenus.forEach(this::isDisplayed);

        /*
         *TODO : ReCaptcha olduğu için form save edilemiyor.
         */
    }

    private ClientPartnerSignUp clientPartnerSignUpFormSet()
    {
        ClientPartnerSignUp clientPartnerSignUp = new ClientPartnerSignUp();

        clientPartnerSignUp.setAgencyCountry(country);
        clientPartnerSignUp.setAgencyName(RandomStringUtils.randomAlphabetic(5));
        clientPartnerSignUp.setPhone("1".concat(RandomStringUtils.randomNumeric(10)));
        clientPartnerSignUp.setManagerEmail(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));
        clientPartnerSignUp.setState(state);
        clientPartnerSignUp.setCity(RandomStringUtils.randomAlphabetic(5));
        clientPartnerSignUp.setAddress(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));
        clientPartnerSignUp.setFirstName(RandomStringUtils.randomAlphabetic(5));
        clientPartnerSignUp.setLastName(RandomStringUtils.randomAlphabetic(5));
        clientPartnerSignUp.setJobTitle(RandomStringUtils.randomAlphabetic(5));
        clientPartnerSignUp.setCompanyEmail(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));

        return clientPartnerSignUp;
    }

    private HotelPartnerSignUp hotelPartnerSignUpFormSet()
    {
        HotelPartnerSignUp hotelPartnerSignUp = new HotelPartnerSignUp();

        hotelPartnerSignUp.setContactName(RandomStringUtils.randomAlphabetic(5));
        hotelPartnerSignUp.setPosition(RandomStringUtils.randomAlphabetic(5));
        hotelPartnerSignUp.setPhone("1".concat(RandomStringUtils.randomNumeric(10)));
        hotelPartnerSignUp.setFax(RandomStringUtils.randomNumeric(7));
        hotelPartnerSignUp.setEmail(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));
        hotelPartnerSignUp.setWepPage("www.".concat(RandomStringUtils.randomAlphabetic(5).concat(".com")));
        hotelPartnerSignUp.setCountry("American Samoa");
        hotelPartnerSignUp.setCity(RandomStringUtils.randomAlphabetic(5));
        hotelPartnerSignUp.setAddress(RandomStringUtils.randomAlphanumeric(20));
        hotelPartnerSignUp.setZippcode(RandomStringUtils.randomNumeric(5));
        hotelPartnerSignUp.setHotelName(RandomStringUtils.randomAlphabetic(5));
        hotelPartnerSignUp.setTypeProperties(RandomStringUtils.randomAlphabetic(5));
        hotelPartnerSignUp.setOpenHotelDate("01/01/2014");
        hotelPartnerSignUp.setNumberRoom(RandomStringUtils.randomNumeric(3));
        hotelPartnerSignUp.setTotalSuites(RandomStringUtils.randomNumeric(2));
        hotelPartnerSignUp.setInformation(RandomStringUtils.randomAlphabetic(30));

        return hotelPartnerSignUp;
    }

    private LocalPartnerSignUp localPartnerSignUpFormSet()
    {
        LocalPartnerSignUp localPartnerSignUp = new LocalPartnerSignUp();

        localPartnerSignUp.setCompanyName(RandomStringUtils.randomAlphabetic(5));
        localPartnerSignUp.setContactName(RandomStringUtils.randomAlphabetic(5));
        localPartnerSignUp.setPhone("1".concat(RandomStringUtils.randomNumeric(10)));
        localPartnerSignUp.setEmail(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));
        localPartnerSignUp.setCountry("American Samoa");
        localPartnerSignUp.setAddress(RandomStringUtils.randomNumeric(30));
        localPartnerSignUp.setCompanyForIntroduction(RandomStringUtils.randomAlphabetic(40));

        return localPartnerSignUp;
    }


}
