package com.selenium.context.base;

import com.selenium.pages.UrlFactory;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Commons
{
    String getCurrentURL();

    void navigateToURL(UrlFactory url);

    void waitAndClick(WebElement element);

    void mouseOver(WebElement element);

    void selectOptionIndex(WebElement element, int index);

    void selectOptionValue(WebElement element, String itemValue);

    void selectOptionVisibleText(WebElement element, String visibleText);

    void waitAndSendKeys(WebElement element, CharSequence text);

    boolean isDisplayed(WebElement element);

    void waitElementToBeClickable(WebElement element);

    void waitElementVisible(WebElement element);

    String getText(WebElement element);

    String getSelectedOptionText(WebElement element);

    String getAttribute(WebElement element, String attributeName);

    void sleep(int seconds) throws InterruptedException;

    void assertEquals(Object actual, Object expected);

    void switchWindowTab(int tab);

    void validateElementExistence(WebElement element);

    //-- Javascript

    void scrollToElement(WebElement element);

    boolean isTextDisplayedOnPage(String text);

}
