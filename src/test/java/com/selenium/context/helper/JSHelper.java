package com.selenium.context.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSHelper implements JSExecutor
{
    private static final Logger logger = LoggerFactory.getLogger(JSHelper.class);

    private JavascriptExecutor jExecutor;

    public JSHelper(WebDriver driver)
    {
        this.jExecutor = (JavascriptExecutor) driver;
    }

    public <T> T executeScript(Class<T> clazz, String script, Object... args)
    {
        logger.info("JavaScript Execute Info : { " + script + " }", "Argument Info : { " + args + " }");

        return clazz.cast(jExecutor.executeScript(script, args));
    }

    @Override
    public JSExecutor click(WebElement element)
    {
        return executeScript(JSExecutor.class, "arguments[0].click();", element);
    }

    @Override
    public Boolean isDisplayed(WebElement element)
    {
        return executeScript(Boolean.class, "function isVisible(elem) {\n" +
                "    if (!(elem instanceof Element)) throw Error('DomUtil: elem is not an element.');\n" +
                "    const style = getComputedStyle(elem);\n" +
                "    if (style.display === 'none') return false;\n" +
                "    if (style.visibility !== 'visible') return false;\n" +
                "    if (style.opacity < 0.1) return false;\n" +
                "    if (elem.offsetWidth + elem.offsetHeight + elem.getBoundingClientRect().height +\n" +
                "        elem.getBoundingClientRect().width === 0) {\n" +
                "        return false;\n" +
                "    }\n" +
                "    const elemCenter   = {\n" +
                "        x: elem.getBoundingClientRect().left + elem.offsetWidth / 2,\n" +
                "        y: elem.getBoundingClientRect().top + elem.offsetHeight / 2\n" +
                "    };\n" +
                "    if (elemCenter.x < 0) return false;\n" +
                "    if (elemCenter.x > (document.documentElement.clientWidth || window.innerWidth)) return false;\n" +
                "    if (elemCenter.y < 0) return false;\n" +
                "    if (elemCenter.y > (document.documentElement.clientHeight || window.innerHeight)) return false;\n" +
                "    let pointContainer = document.elementFromPoint(elemCenter.x, elemCenter.y);\n" +
                "    do {\n" +
                "        if (pointContainer === elem) return true;\n" +
                "    } while (pointContainer = pointContainer.parentNode);\n" +
                "    return false;\n" +
                "}\n" +
                "return isVisible(arguments[0])", element);
    }

    @Override
    public JSExecutor clear(WebElement element)
    {
        return executeScript(JSExecutor.class, "arguments[0].value = ''", element);
    }

    @Override
    public JSExecutor scrollToElement(WebElement element)
    {
        return executeScript(JSExecutor.class, "arguments[0].scrollIntoView(true);", element);
    }

    @Override
    public Long getScreenYPageOffset()
    {
        return executeScript(Long.class, "return window.pageYOffset;");
    }

}


