package com.selenium.context.security.proxy;

import com.selenium.context.security.proxy.model.Context;
import com.selenium.context.security.proxy.model.Script;
import org.zaproxy.clientapi.core.Alert;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public interface ScanningProxy extends LoggingProxy
{
    List<Alert> getAlerts() throws ProxyException;

    List<Alert> getAlerts(int start, int count) throws ProxyException;

    int getAlertsCount() throws ProxyException;

    void deleteAlerts() throws ProxyException;

    void scan(String url, Integer progressCount) throws ProxyException;

    int getScanProgress(int scanId) throws ProxyException;

    int getLastScannerScanId() throws ProxyException;

    byte[] getXmlReport() throws ProxyException;

    byte[] getHtmlReport() throws ProxyException;

    void setScannerAttackStrength(String scannerId, String strength) throws ProxyException;

    void setScannerAlertThreshold(String scannerId, String threshold) throws ProxyException;

    void setEnableScanners(String ids, boolean enabled) throws ProxyException;

    void disableAllScanners() throws ProxyException;

    void enableAllScanners() throws ProxyException;

    void setEnablePassiveScan(boolean enabled) throws ProxyException;

    void excludeFromScanner(String regex) throws ProxyException;

    void shutdown() throws ProxyException;

    void setOptionHandleAntiCSRFTokens(boolean enabled) throws ProxyException;

    void createContext(String contextName, boolean inScope) throws ProxyException;

    void includeRegexInContext(String contextName, Pattern regex) throws ProxyException;

    void includeUrlTreeInContext(String contextName, String parentUrl) throws ProxyException;

    void excludeRegexFromContext(String contextName, Pattern regex) throws ProxyException;

    void excludeParentUrlFromContext(String contextName, String parentUrl) throws ProxyException;

    Context getContextInfo(String contextName) throws ProxyException, IOException;

    List<String> getContexts() throws ProxyException;

    void setContextInScope(String contextName, boolean inScope) throws ProxyException;

    List<String> getIncludedRegexs(String contextName) throws ProxyException;

    List<String> getExcludedRegexs(String contextName) throws ProxyException;

    List<String> getAntiCsrfTokenNames() throws ProxyException;

    void addAntiCsrfToken(String tokenName) throws ProxyException;

    void removeAntiCsrfToken(String tokenName) throws ProxyException;

    List<String> listEngines() throws ProxyException;

    List<Script> listScripts() throws ProxyException;

    void disableScript(String scriptName) throws ProxyException;

    void enableScript(String scriptName) throws ProxyException;

    void loadScript(String scriptName, String scriptType, String scriptEngine, String fileName) throws ProxyException;

    void loadScript(String scriptName, String scriptType, String scriptEngine, String fileName, String scriptDescription) throws ProxyException;

    void removeScript(String scriptName) throws ProxyException;

    void runStandAloneScript(String scriptName) throws ProxyException;

    void scanAsUser(String url, String contextId, String userId, boolean recurse) throws ProxyException;
}
