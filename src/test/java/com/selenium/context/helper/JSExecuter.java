package com.selenium.context.helper;

import org.openqa.selenium.WebElement;

public interface JSExecuter
{
    <T> T executeScript(Class<T> clazz, String script, Object... args);

    Long getPageHeight();

    String randomGenerate(int digit);

    Number randomNumberGenerate(int digit);

    String randomStringGenerate(int digit);

    JSExecuter openNewTab(String links);

    JSExecuter doubleClick(WebElement element);

    JSExecuter navigateToUrl(String links);

    JSExecuter click(WebElement element);

    String getText(WebElement element);

    JSExecuter selectByIndex(WebElement element, Integer index);

    JSExecuter selectByValue(WebElement element, String value);

    JSExecuter sendKeys(WebElement element, String value);

    Boolean isDisplayed(WebElement element);

    JSExecuter clear(WebElement element);

    String getSelectedOptionText(WebElement element);

    Long getSelectedOptionIndex(WebElement element);

    String getSelectedOptionValue(WebElement element);

    JSExecuter scrollToElement(WebElement element);

    Boolean isAttributePresent(WebElement element, String attribute);

    String getAttribute(WebElement element, String attribute);

    Boolean checkBoxChecked(WebElement element);

    JSExecuter allCheckBoxChecked();

    JSExecuter pageZoom(Integer percentValue);

    JSExecuter pageRefresh();

    String getCurrentUrl();

    String getLocationPathName();

    Long getScreenYPageOffset();
}
