package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.pages.UrlFactory;
import com.selenium.pages.web.HeaderPage;
import com.selenium.pages.web.MyBookingPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class MyBookingTest extends AbstractHotelsProTest
{
    private Logger logger = Logger.getLogger(MyBookingTest.class);

    private static final String EXPECTED_TICKET_PAGE_URL = "/account/tickets/new/?booking_code=";

    private HeaderPage headerPage;
    private MyBookingPage myBookingPage;

    @Before
    public void init() throws Exception
    {
        super.init();

        headerPage = new HeaderPage(driver);
        myBookingPage = new MyBookingPage(driver);
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasında yer alan bookingler için bookinglerin en sağında yer alan actionların düzgün bir şekildeki çalışması gerekmektedir.")
    public void testOpenTicketFromTheActionField()
    {
        loginAndGoMyBookings();
        waitAndClick(myBookingPage.actionsMenu.get(0));
        waitAndClick(myBookingPage.sendMessageOption.get(0));
        sleep(3);

        logger.info("Check Url :" + EXPECTED_TICKET_PAGE_URL);
        assertThat("expected url not found !!!", getCurrentURL(), CoreMatchers.containsString(EXPECTED_TICKET_PAGE_URL));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasındaki filtrelerin düzgün bir biçimde çalışması gerekmektedir.")
    public void testFilter()
    {
        loginAndGoMyBookings();

        selectOptionValue(myBookingPage.reservationStatusSelect, "cancelled");
        waitAndClick(myBookingPage.applyFilterButton);

        myBookingPage.bookingsStatusList.forEach(status -> Assert.assertEquals("status is wrong !!!", "Cancelled", getText(status)));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasındaki filtrelerin düzgün bir biçimde çalışması gerekmektedir.")
    public void testMoreFilter()
    {
        loginAndGoMyBookings();

        String currencyType = "EUR";

        waitAndClick(myBookingPage.moreFilterIcon);
        selectOptionValue(myBookingPage.currencySelect, currencyType);
        waitAndClick(myBookingPage.applyFilterButton);
        waitLoadIconNotView();

        myBookingPage.bookingCostList.forEach(status -> assertThat("currency type is wrong !!!", getText(status), CoreMatchers.containsString(currencyType)));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasında filtreleme yapıldıktan sonra filtrelerin temizlendiği Reset Filter butonunun düzgün bir şekilde çalışması gerekmektedir.\n")
    public void testResetFilter()
    {
        loginAndGoMyBookings();

        selectOptionValue(myBookingPage.reservationStatusSelect, "cancelled");
        waitAndClick(myBookingPage.applyFilterButton);
        waitLoadIconNotView();
        waitAndClick(myBookingPage.resetFilterButton);
        waitLoadIconNotView();

        Assert.assertEquals("reset filter not working !!!", "Hepsi", getSelectedOptionText(myBookingPage.reservationStatusSelect));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasından Inbox alanına giderek yeni bir ticket oluşturulabilmesi gerekmektedir.")
    public void testCreateNewRequest() throws Exception
    {
        loginAndGoMyBookings();

        waitAndClick(myBookingPage.inboxButton);
        waitAndClick(myBookingPage.addTicketButton);
        waitLoadIconNotView();

        createNewAMessageFormFill(0, "Complaint", RandomStringUtils.randomAlphabetic(15), RandomStringUtils.randomAlphabetic(50));

        Assert.assertEquals(getText(myBookingPage.successNewRequestMessage), readNotifications().getProperty("request.successfully.message"));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasındaki bookinglerin detayına gidilmesi ve booking detayının düzgün bir şekilde görüntülenebilmesi, booking detail sayfasındaki actionların çalışması gerekmektedir.")
    public void testRedirectBookingDetail()
    {
        loginAndGoMyBookings();

        waitAndClick(myBookingPage.bookingIdList.get(0));
        sleep(5);

        Assert.assertTrue(myBookingPage.bookingDetails.size() == 3);
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasındaki bookinglerin detayına gidilmesi ve booking detayının düzgün bir şekilde görüntülenebilmesi, booking detail sayfasındaki actionların çalışması gerekmektedir.")
    public void testBookingAddNote() throws Exception
    {
        loginAndGoMyBookings();

        waitAndClick(myBookingPage.bookingIdList.get(0));
        sleep(3);
        waitAndClick(myBookingPage.reservationDetailOperations.get(0));

        String noteText = RandomStringUtils.randomAlphabetic(30);

        waitAndSendKeys(myBookingPage.addNoteInput, noteText);
        waitAndClick(myBookingPage.addNoteButton);
        sleep(1);

        assertThat("successfully message is wrong !!!", getText(myBookingPage.successfullyMessageArea), CoreMatchers.containsString(readNotifications().getProperty("booking.note.added.message")));
        Assert.assertTrue("note not appeared !!!", isTextDisplayedOnPage(noteText));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasından Inbox alanına giderek yeni bir ticket oluşturulabilmesi gerekmektedir.")
    public void testInboxTicketAppear() throws Exception
    {
        testCreateNewRequest();
        navigateToURL(UrlFactory.TICKETS);
        sleep(5);
        waitAndClick(myBookingPage.ticketList.get(0));

        Assert.assertTrue(isDisplayed(myBookingPage.ticketDetailContainer));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasından Inbox alanına giderek görüntülenen ticketların filtrelenebilmesi gerekmektedir.")
    public void testInboxTicketFilter() throws Exception
    {
        testCreateNewRequest();
        navigateToURL(UrlFactory.TICKETS);

        waitAndClick(myBookingPage.ticketNumberSelect);
        sleep(5);
        waitAndClick(myBookingPage.selectOptionList.get(0));
        waitAndClick(myBookingPage.otherApplyFilterButton);
        sleep(5);

        Assert.assertEquals("ticket filter not working !!!", 1, myBookingPage.ticketList.size());
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasından Inbox alanına giderek yeni bir ticket oluşturulabilmesi gerekmektedir.")
    public void testInboxTicketAddNote() throws Exception
    {
        testInboxTicketAppear();

        waitAndSendKeys(myBookingPage.replyInput, RandomStringUtils.randomAlphabetic(50));

        myBookingPage.attachment.sendKeys(getImage().getAbsolutePath());
        waitAndClick(myBookingPage.postReplyButton);
        waitPostReplyAnimation();

        assertThat("successfully message is wrong !!!", getText(myBookingPage.successfullyMessageArea), CoreMatchers.containsString(readNotifications().getProperty("ticket.note.added.message")));
    }

    @Test
    @ExpectedResult("Hotelspro'da My Bookings sayfasındaki bookinglerin excel olarak indirilebilmesi gerekmektedir.")
    public void testExportToExcel()
    {
        loginAndGoMyBookings();

        waitAndClick(myBookingPage.exportToExcelButton);
        sleep(5);

        String dir = System.getProperty("user.dir").concat(System.getProperty("file.separator")).concat("downloadFiles");
        File downloadFile = new File(dir);

        Assert.assertEquals("excel not downloaded", 1, Objects.requireNonNull(downloadFile.listFiles()).length);
    }

    private void loginAndGoMyBookings()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
        waitAndClick(headerPage.myBookingMenu);
    }

    private void createNewAMessageFormFill(int reservationNumberIndex, String categoryValue, String subject, String message)
    {
        waitAndClick(myBookingPage.reservationNumber);
        waitAndClick(myBookingPage.selectOptionList.get(reservationNumberIndex));
        selectOptionValue(myBookingPage.categorySelect, categoryValue);
        waitAndSendKeys(myBookingPage.subjectInput, subject);
        waitAndSendKeys(myBookingPage.messageInput, message);

        myBookingPage.attachment.sendKeys(getImage().getAbsolutePath());
        waitAndClick(myBookingPage.createNewRequestButton);
        sleep(5);
    }

    private File getImage()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File image = new File(classLoader.getResource(("images".concat(System.getProperty("file.separator").concat("create-new-message-test-image.jpg")))).getFile());

        return image;
    }

    private void waitPostReplyAnimation()
    {
        while (true)
        {
            if (isDisplayed(myBookingPage.postReplyPending))
            {
                logger.info("load post reply still appear");
            }
            else
            {
                break;
            }
        }
    }
}
