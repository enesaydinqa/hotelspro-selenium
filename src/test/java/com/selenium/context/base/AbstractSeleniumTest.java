package com.selenium.context.base;

import com.selenium.context.driver.DriverManager;
import com.selenium.pages.UrlFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class AbstractSeleniumTest extends DriverManager implements Commons
{
    protected static final int DEFAULT_SLEEP = 1;
    protected static final int DEFAULT_MEDIUM_SLEEP = 5;
    protected static final int DEFAULT_LARGE_SLEEP = 10;

    @Override
    public String getCurrentURL()
    {
        return driver.getCurrentUrl();
    }

    @Override
    public void navigateToURL(UrlFactory url)
    {
        driver.manage().timeouts().pageLoadTimeout(configuration.getPageLoadTimeout(), TimeUnit.MINUTES);
        driver.navigate().to(url.pageUrl);
    }

    @Override
    public void waitAndClick(WebElement element)
    {
        validateElementExistence(element);
        waitElementVisible(element);
        scrollToElement(element);
        waitElementToBeClickable(element);
        scrollToElement(element);
        sleep(DEFAULT_SLEEP);
        element.click();
    }

    @Override
    public void mouseOver(WebElement element)
    {
        Actions actions = new Actions(driver);

        waitElementVisible(element);
        actions.moveToElement(element).perform();
    }

    @Override
    public void selectOptionIndex(WebElement element, int index)
    {
        new Select(element).selectByIndex(index);
    }

    @Override
    public void selectOptionValue(WebElement element, String itemValue)
    {
        new Select(element).selectByValue(itemValue);
    }

    @Override
    public void selectOptionVisibleText(WebElement element, String visibleText)
    {
        new Select(element).selectByVisibleText(visibleText);
    }

    @Override
    public void waitAndSendKeys(WebElement element, CharSequence text)
    {
        waitElementVisible(element);
        scrollToElement(element);
        element.sendKeys(text);
    }

    @Override
    public boolean isDisplayed(WebElement element)
    {
        boolean isDisplayed = false;

        try
        {
            sleep(DEFAULT_MEDIUM_SLEEP);
            isDisplayed = element.isDisplayed();
        }
        catch (Exception ex)
        {

        }

        return isDisplayed;
    }

    @Override
    public void waitElementToBeClickable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, configuration.getWaitLoadTimeout());
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void waitElementVisible(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, configuration.getWaitLoadTimeout());
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public String getText(WebElement element)
    {
        return element.getText();
    }

    @Override
    public String getSelectedOptionText(WebElement element)
    {
        Select dropdown = new Select(element);
        return dropdown.getFirstSelectedOption().getText();
    }

    @Override
    public String getAttribute(WebElement element, String attributeName)
    {
        return element.getAttribute(attributeName);
    }

    @Override
    public void sleep(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000);
        }
        catch (Exception ex)
        {
        }

    }

    @Override
    public void assertEquals(Object actual, Object expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Override
    public void switchWindowTab(int tab)
    {
        ArrayList<String> TabSwitch = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(TabSwitch.get(tab));
    }

    @Override
    public boolean isTextDisplayedOnPage(String text)
    {
        List<WebElement> foundElements = driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
        return foundElements.size() > 0;
    }


    @Override
    public void validateElementExistence(WebElement element)
    {
        await("waiting for element --> " + element).atMost(configuration.getWaitLoadTimeout(), TimeUnit.SECONDS)
                .pollInterval(200, TimeUnit.MILLISECONDS)
                .ignoreExceptions()
                .until(element::isEnabled);
    }


    /**
     * Actions JavaScript
     **/

    @Override
    public void scrollToElement(WebElement element)
    {
        JavascriptExecutor scroll = driver;
        scroll.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'});", element);
    }
}
