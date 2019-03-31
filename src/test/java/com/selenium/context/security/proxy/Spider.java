package com.selenium.context.security.proxy;

import java.util.List;

public interface Spider
{
    void spider(String url);

    void spider(String url, boolean recurse, String contextName, Integer maxDepth);

    void spider(String url, Integer maxChildren, boolean recurse, String contextName);

    void spiderAsUser(String url, String contextId, String userId);

    void spiderAsUser(String url, String contextId, String userId, boolean recurse);

    void spiderAsUser(String url, String contextId, String userId, Integer maxChildren, boolean recurse);

    int getSpiderProgress(int scanId);

    int getLastSpiderScanId();

    List<String> getSpiderResults(int scanId);

    void excludeFromSpider(String regex);

    void setMaxDepth(int depth);

    void setPostForms(boolean post);

    void setThreadCount(int threads);

}
