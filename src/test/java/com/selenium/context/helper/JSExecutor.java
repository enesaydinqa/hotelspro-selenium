package com.selenium.context.helper;

import org.openqa.selenium.WebElement;

public interface JSExecutor
{
    <T> T executeScript(Class<T> clazz, String script, Object... args);

    JSExecutor click(WebElement element);

    Boolean isDisplayed(WebElement element);

    JSExecutor clear(WebElement element);

    JSExecutor scrollToElement(WebElement element);

    Long getScreenYPageOffset();
}
