package selenium.tests.adaptive;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsResponsiveTest;
import context.flag.PaymentExecutable;
import context.flag.SerialExecutable;
import context.objects.CreditCard;
import context.objects.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.NewAddressRegistrationPage;
import selenium.pages.mobile.PaymentPage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.stream.IntStream;

@NotThreadSafe
public class PaymentTest extends AbstractNYXCosmeticsResponsiveTest
{
    private NewAddressRegistrationPage newAddressRegistrationPage;
    private PaymentPage paymentPage;
    private CreditCard creditCard;

    @Before
    public void init() throws Exception
    {
        super.init();
        creditCard = new CreditCard();
        newAddressRegistrationPage = new NewAddressRegistrationPage(driver);
        paymentPage = new PaymentPage(driver);

        login();
        allBasketProductRemove();
    }

    @Test
    @Description("Giriş yapılan kullanıcı ile alışveriş tamamlama senaryosu.")
    @Category({PaymentExecutable.class, SerialExecutable.class})
    public void testUserLoggedAgreementVisible()
    {
        randomProductSelectAndAddBasket();
        wait(5);
        navigateToURL(UrlFactory.CHECKOUT_PAYMENT);
        wait(7);
        enterCreditCard(creditCard);
        Long pageHeight = jsHelper.getPageHeight();
        secureScrollPage(0, pageHeight.intValue());

        Assert.assertEquals("Agreement Smooth or Ever Not Showing !", 8, paymentPage.agreements.size());

        IntStream.range(0, 8).forEach(i -> isDisplayed(paymentPage.agreements.get(i)));
    }


    private void newAddressRegistrationFormFilling(User user)
    {
        wait(10);
        sendKeys(newAddressRegistrationPage.emailInput, user.getEmail());
        sendKeys(newAddressRegistrationPage.firstNameInput, user.getFirstName());
        sendKeys(newAddressRegistrationPage.lastNameInput, user.getLastName());
        sendKeys(newAddressRegistrationPage.phoneInput, user.getPhone());
        click(newAddressRegistrationPage.citySelectBox);
        wait(3);
        click(newAddressRegistrationPage.okButton);
        wait(5);
        click(newAddressRegistrationPage.townshipSelectBox);
        wait(3);
        clickViaJs(newAddressRegistrationPage.okButton);
        wait(5);
        click(newAddressRegistrationPage.addressTextArea);
        sendKeys(newAddressRegistrationPage.addressTextArea, user.getAddress());
        wait(3);
        scrollToElement(newAddressRegistrationPage.saveButton);
        click(newAddressRegistrationPage.saveButton);
        wait(5);
    }

    private void enterCreditCard(CreditCard creditCard)
    {
        wait(10);
        sendKeys(paymentPage.nameInput, creditCard.getFirstName() + " " + creditCard.getLastName());
        sendKeys(paymentPage.ccNumber, String.valueOf(creditCard.getCardNumber()));
        click(paymentPage.nameInput);
        wait(5);
    }
}
