package context.security.proxy;

import context.security.proxy.model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface Authentication
{
    List<String> getSupportedAuthenticationMethods() throws ProxyException;

    String getLoggedInIndicator(String contextId) throws ProxyException;

    String getLoggedOutIndicator(String contextId) throws ProxyException;

    void setLoggedInIndicator(String contextId, String loggedInIndicatorRegex) throws ProxyException;

    void setLoggedOutIndicator(String contextId, String loggedOutIndicatorRegex) throws ProxyException;

    Map<String, String> getAuthenticationMethodInfo(String contextId) throws ProxyException;

    List<Map<String, String>> getAuthMethodConfigParameters(String authMethod) throws ProxyException;

    void setAuthenticationMethod(String contextId, String authMethodName, String authMethodConfigParams) throws ProxyException;

    void setFormBasedAuthentication(String contextId, String loginUrl, String loginRequestData) throws ProxyException, UnsupportedEncodingException;

    void setHttpAuthentication(String contextId, String hostname, String realm, String portNumber) throws ProxyException, UnsupportedEncodingException;

    void setHttpAuthentication(String contextId, String hostname, String realm) throws ProxyException, UnsupportedEncodingException;

    void setManualAuthentication(String contextId) throws ProxyException;

    void setScriptBasedAuthentication(String contextId, String scriptName, String scriptConfigParams) throws ProxyException, UnsupportedEncodingException;

    List<User> getUsersList(String contextId) throws ProxyException, IOException;

    User getUserById(String contextId, String userId) throws ProxyException, IOException;

    List<Map<String, String>> getAuthenticationCredentialsConfigParams(String contextId) throws ProxyException;

    Map<String, String> getAuthenticationCredentials(String contextId, String userId) throws ProxyException;

    String newUser(String contextId, String name) throws ProxyException;

    void removeUser(String contextId, String userId) throws ProxyException;

    void setAuthenticationCredentials(String contextId, String userId, String authCredentialsConfigParams) throws ProxyException;

    void setUserEnabled(String contextId, String userId, boolean enabled) throws ProxyException;

    void setUserName(String contextId, String userId, String name) throws ProxyException;

    String getForcedUserId(String contextId) throws ProxyException;

    boolean isForcedUserModeEnabled() throws ProxyException;

    void setForcedUserModeEnabled(boolean forcedUserModeEnabled) throws ProxyException;

    void setForcedUser(String contextId, String userId) throws ProxyException;

    List<String> getSupportedSessionManagementMethods() throws ProxyException;

    String getSessionManagementMethod(String contextId) throws ProxyException;

    void setSessionManagementMethod(String contextId, String sessionManagementMethodName, String methodConfigParams) throws ProxyException;
}
