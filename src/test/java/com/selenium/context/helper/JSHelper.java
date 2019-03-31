package com.selenium.context.helper;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSHelper implements JSExecuter
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
    public Long getPageHeight()
    {
        return executeScript(Long.class, "return $(document).height()");
    }

    @Override
    public String randomGenerate(int digit)
    {
        return executeScript(String.class, "var text = ''; var possible = 'abcdefghijklmnopqrstuvwxyz0123456789'; for (var i = 0; i < arguments[0]; i++) text += possible.charAt(Math.floor(Math.random() * possible.length)); return text;", digit);
    }

    @Override
    public Number randomNumberGenerate(int digit)
    {
        return executeScript(Number.class, "return Math.floor(Math.random() * Math.floor(Math.pow(10, arguments[0])))", digit);
    }

    @Override
    public String randomStringGenerate(int digit)
    {
        return executeScript(String.class, "return Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, arguments[0]);", digit);
    }


    @Override
    public JSExecuter openNewTab(String links)
    {
        return executeScript(JSExecuter.class, String.format("window.open(%s,'_blank');", links));
    }

    @Override
    public JSExecuter doubleClick(WebElement element)
    {
        return executeScript(JSExecuter.class, "for(var i=0; i < 2; i++) {arguments[0].click();}", element);
    }

    @Override
    public JSExecuter navigateToUrl(String links)
    {
        return executeScript(JSExecuter.class, String.format("window.location.href = '%s';", links));
    }

    @Override
    public JSExecuter click(WebElement element)
    {
        return executeScript(JSExecuter.class, "arguments[0].click();", element);
    }

    @Override
    public String getText(WebElement element)
    {
        return executeScript(String.class, "return arguments[0].textContent;", element);
    }

    @Override
    public JSExecuter selectByIndex(WebElement element, Integer index)
    {
        return executeScript(JSExecuter.class, "arguments[0].selectedIndex = arguments[1]", element, index);
    }

    @Override
    public JSExecuter selectByValue(WebElement element, String value)
    {
        return executeScript(JSExecuter.class, "arguments[0].value = arguments[1]", element, value);
    }

    @Override
    public JSExecuter sendKeys(WebElement element, String value)
    {
        return executeScript(JSExecuter.class, "arguments[0].value = arguments[1]", element, value);
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
    public JSExecuter clear(WebElement element)
    {
        return executeScript(JSExecuter.class, "arguments[0].value = ''", element);
    }

    @Override
    public String getSelectedOptionText(WebElement element)
    {
        return executeScript(String.class, "function getSelectedText(el) {\n" +
                "if (el.selectedIndex == -1) return null;\n" +
                "return el.options[el.selectedIndex].text;}\n" +
                "return getSelectedText(arguments[0]);", element);
    }

    @Override
    public Long getSelectedOptionIndex(WebElement element)
    {
        return executeScript(Long.class, "return arguments[0].selectedIndex", element);
    }

    @Override
    public String getSelectedOptionValue(WebElement element)
    {
        return executeScript(String.class, "return arguments[0].value", element);
    }

    @Override
    public JSExecuter scrollToElement(WebElement element)
    {
        return executeScript(JSExecuter.class, "arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'});", element);
    }

    @Override
    public Boolean isAttributePresent(WebElement element, String attribute)
    {
        return executeScript(Boolean.class, "var value = arguments[0].getAttribute(arguments[1]); if(value !== null) {return true} else return false;", element, attribute);
    }

    @Override
    public String getAttribute(WebElement element, String attribute)
    {
        return executeScript(String.class, "return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }

    @Override
    public Boolean checkBoxChecked(WebElement element)
    {
        return executeScript(Boolean.class, "return arguments[0].checked;", element);
    }

    @Override
    public JSExecuter allCheckBoxChecked()
    {
        return executeScript(JSExecuter.class, "var checkboxes = document.querySelectorAll(\"input[type='checkbox']\"); for(var i = 0; i < checkboxes.length; i++) {checkboxes[i].checked = true;}");
    }

    @Override
    public JSExecuter pageZoom(Integer percentValue)
    {
        return executeScript(JSExecuter.class, "document.body.style.zoom = arguments[0]", percentValue);
    }

    @Override
    public JSExecuter pageRefresh()
    {
        return executeScript(JSExecuter.class, "location.reload()");
    }

    @Override
    public String getCurrentUrl()
    {
        return executeScript(String.class, "return location.href");
    }

    @Override
    public String getLocationPathName()
    {
        return executeScript(String.class, "return location.pathname");
    }

}


