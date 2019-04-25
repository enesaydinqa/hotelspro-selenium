package com.selenium.tests.web;

import com.selenium.context.annotations.Description;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.context.objects.SignUp;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.SignupPage;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Arrays;
import java.util.List;

@NotThreadSafe
public class ClientPartnerSignUpTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(ClientPartnerSignUpTest.class);

    private String country = "United States";
    private String state = "Alabama";
    private String reCAPTCHAErrorMessage = "Lütfen reCAPTCHA’yı kontrol edin.";
    private List<String> inputTitle = Arrays.asList("Fatura Ülkesi", "Ad", "Telefon Numarası", "E-posta adresi :", "Şehir", "İsim", "Soyisim", "Ünvan");


    private SignupPage signupPage;

    @Before
    public void before()
    {
        signupPage = new SignupPage(driver);
    }

    @Test
    @Description("Kullanıcı başarılı bir şekilde Client Partner Sign Up Formunu doldurabilmeli ve gönderebilmelidir.")
    @Category(ParallelExecutable.class)
    public void testClientPartnerSignUp()
    {
        SignUp signUp = signUpFormSet();

        navigateToURL(UrlFactory.SIGNUP);
        mouseOver(signupPage.signUpButton);
        waitAndClick(signupPage.partnerCustomerMenu);
        waitAndSendKeys(signupPage.agencyCountry, signUp.getAgencyCountry());
        waitAndClick(signupPage.listFirstOption);

        jsHelper.clear(signupPage.agencyCountry); // Clear

        waitAndClick(signupPage.saveButton);

        inputTitle.forEach(title -> {
            logger.info("Check Required Input : " + title);

            Assert.assertTrue("Required Message Not Found", isTextDisplayedOnPage(title));
        });

        waitAndSendKeys(signupPage.agencyCountry, signUp.getAgencyCountry());
        waitAndClick(signupPage.listFirstOption);

        waitAndSendKeys(signupPage.agencyName, signUp.getAgencyName());
        waitAndSendKeys(signupPage.phone, signUp.getPhone());
        waitAndSendKeys(signupPage.managerEmail, signUp.getManagerEmail());
        waitAndSendKeys(signupPage.state, signUp.getState());
        waitAndClick(signupPage.listFirstOption);
        waitAndSendKeys(signupPage.city, signUp.getCity());
        waitAndSendKeys(signupPage.address, signUp.getAddress());
        waitAndSendKeys(signupPage.firstName, signUp.getFirstName());
        waitAndSendKeys(signupPage.lastName, signUp.getLastName());
        waitAndSendKeys(signupPage.jobTitle, signUp.getJobTitle());
        waitAndSendKeys(signupPage.companyEmail, signUp.getCompanyEmail());

        jsHelper.click(signupPage.signupAgreement);

        waitAndClick(signupPage.saveButton);
        sleep(3);
        Assert.assertTrue(isTextDisplayedOnPage(reCAPTCHAErrorMessage));
    }

    private SignUp signUpFormSet()
    {
        SignUp signUp = new SignUp();

        signUp.setAgencyCountry(country);
        signUp.setAgencyName(RandomStringUtils.randomAlphabetic(5));
        signUp.setPhone("1".concat(RandomStringUtils.randomNumeric(10)));
        signUp.setManagerEmail(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));
        signUp.setState(state);
        signUp.setCity(RandomStringUtils.randomAlphabetic(5));
        signUp.setAddress(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));
        signUp.setFirstName(RandomStringUtils.randomAlphabetic(5));
        signUp.setLastName(RandomStringUtils.randomAlphabetic(5));
        signUp.setJobTitle(RandomStringUtils.randomAlphabetic(5));
        signUp.setCompanyEmail(RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4).concat(".com")));

        return signUp;
    }


}
