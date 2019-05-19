package com.selenium.pages.web;

import com.selenium.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyBookingPage extends PageObject
{
    public MyBookingPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = "ul.body li.actions")
    public List<WebElement> actionsMenu;

    @FindBy(xpath = "//a[contains(@href,'/account/tickets/new/?booking_code')]")
    public List<WebElement> sendMessageOption;

    @FindBy(id = "status")
    public WebElement reservationStatusSelect;

    @FindBy(name = "registration_submit")
    public WebElement applyFilterButton;

    @FindBy(name = "filter_reset")
    public WebElement resetFilterButton;

    @FindBy(css = "ul.body [class='cell status'] p")
    public List<WebElement> bookingsStatusList;

    @FindBy(className = "inbox")
    public WebElement inboxButton;

    @FindBy(className = "add-ticket")
    public WebElement addTicketButton;

    @FindBy(css = ".autocomplete-select input")
    public WebElement reservationNumber;

    @FindBy(id = "category")
    public WebElement categorySelect;

    @FindBy(id = "subject")
    public WebElement subjectInput;

    @FindBy(id = "message")
    public WebElement messageInput;

    @FindBy(name = "attachment")
    public WebElement attachment;

    @FindBy(css = ".add-user")
    public WebElement createNewRequestButton;

    @FindBy(css = ".message--success p")
    public WebElement successNewRequestMessage;

    @FindBy(css = "[class='icon fa fa-arrow-circle-o-down']")
    public WebElement moreFilterIcon;

    @FindBy(id = "currency")
    public WebElement currencySelect;

    @FindBy(css = "ul.body .cost p")
    public List<WebElement> bookingCostList;

    @FindBy(css = "ul.body .id p")
    public List<WebElement> bookingIdList;

    @FindBy(css = "section.booking-detail")
    public List<WebElement> bookingDetails;

    @FindBy(css = ".booking-detail-actions div.col a")
    public List<WebElement> reservationDetailOperations;

    @FindBy(id = "add_note")
    public WebElement addNoteInput;

    @FindBy(className = "user-connection__button")
    public WebElement addNoteButton;

    @FindBy(className = "ajax-response")
    public WebElement successfullyMessageArea;

    @FindBy(css = ".body .ticket a")
    public List<WebElement> ticketList;

    @FindBy(className = "js-ticket-detail")
    public WebElement ticketDetailContainer;

    @FindBy(id = "reply")
    public WebElement replyInput;

    @FindBy(className = "loader-button")
    public WebElement postReplyButton;

    @FindBy(className = "pending")
    public WebElement postReplyPending;

    @FindBy(css = "div.export-to-csv")
    public WebElement exportToExcelButton;

    @FindBy(className = "ticketNumberSelect")
    public WebElement ticketNumberSelect;

    @FindBy(name = "filter")
    public WebElement otherApplyFilterButton;
}
