package context.security.proxy;


import edu.umass.cs.benchlab.har.HarEntry;
import edu.umass.cs.benchlab.har.HarRequest;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;
import java.util.List;

public interface LoggingProxy
{
    void clear() throws ProxyException;

    List<HarEntry> getHistory() throws ProxyException;

    List<HarEntry> getHistory(int start, int count) throws ProxyException;

    int getHistoryCount() throws ProxyException;

    List<HarEntry> findInRequestHistory(String regex) throws ProxyException;

    List<HarEntry> findInResponseHistory(String regex) throws ProxyException;

    List<HarEntry> findInResponseHistory(String regex, List<HarEntry> entries);

    List<HarEntry> makeRequest(HarRequest request, boolean followRedirect) throws ProxyException;

    Proxy getSeleniumProxy() throws UnknownHostException;

    void setAttackMode() throws ProxyException;

}
