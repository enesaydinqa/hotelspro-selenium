package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;


public class HomePage extends PageObject
{

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='slick-track'])[1]/div")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "(//a[@itemprop='item'])[1]")
    private WebElement breadCrumb;


}
