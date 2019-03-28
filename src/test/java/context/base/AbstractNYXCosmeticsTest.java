package context.base;

import org.apache.log4j.Logger;
import selenium.pages.web.MainPageWebPage;

import java.util.NoSuchElementException;

public abstract class AbstractNYXCosmeticsTest extends AbstractWebTest
{
    private Logger logger = Logger.getLogger(AbstractNYXCosmeticsTest.class);

    MainPageWebPage mainPageWebPage;

    protected void closeCampaingPopup()
    {
        mainPageWebPage = new MainPageWebPage(driver);

        try
        {
            wait(5);
            if (isDisplayed(mainPageWebPage.getPopupIframe()))
            {
                switchFrame(mainPageWebPage.getPopupIframe());
                click(mainPageWebPage.getPopupCloseButton());
                wait(3);
            }
        }
        catch (NoSuchElementException ex)
        {
            logger.info("There is not showing campaing popup");
        }

    }

    protected void resetHar()
    {
        if (proxy != null)
        {
            proxy.endHar();
            proxy.newHar();
        }
        else
        {
            throw new UnsupportedOperationException("This test did not start with proxy");
        }
    }
}
