package com.xuzimian.java.jenkins.plugin.scms.bitbucket;

import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import com.google.gson.Gson;
import com.xuzimian.java.jenkins.plugin.scms.bitbucket.response.CommitResponse;
import hudson.util.Secret;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * 目前使用的验证方式：
 * Authorization: <type> <credentials>
 * Proxy-Authorization: <type> <credentials>
 * <p>
 * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Authentication
 * <p>
 * Bitbucket 支持的Authentication method: https://developer.atlassian.com/bitbucket/api/2/reference/meta/authentication#app-pw
 */
@Slf4j
public class BitbucketProvider {
    private static final HttpHost API_HOST = HttpHost.create("https://api.bitbucket.org");
    private static final String V2_API_BASE_URL = "https://api.bitbucket.org/2.0/repositories/%s/%s/%s";
    private static final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    private static final int API_RATE_LIMIT_CODE = 429;

    private Gson gson;

    public BitbucketProvider() {
        this.gson = new Gson();
    }

    public CommitResponse searchCommits(String owner, String repositoryName, String branchName,
                                        StandardUsernamePasswordCredentials credentials) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(String.format(V2_API_BASE_URL, owner, repositoryName, "commits"));

        if (isNotEmpty(branchName)) {
            uriComponentsBuilder.queryParam("include", branchName);
        }

        URI uri = uriComponentsBuilder.build()
                .toUri();

        try {
            return getCommits(uri, credentials);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Get commits form bitbucket api error:", e);
        }
    }

    private CommitResponse getCommits(URI uri, StandardUsernamePasswordCredentials credentials) throws IOException, InterruptedException {
        CloseableHttpResponse response = executeQuery(uri, credentials);
        String responseStr = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
        return gson.fromJson(responseStr, CommitResponse.class);
    }

    private CloseableHttpResponse executeQuery(URI uri, StandardUsernamePasswordCredentials credentials) throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(uri);
        HttpHost host = HttpHost.create("" + uri.getScheme() + "://" + uri.getAuthority());
        return executeMethod(host, httpGet, credentials);
    }

    private CloseableHttpResponse executeMethod(HttpHost host, HttpRequestBase httpMethod, StandardUsernamePasswordCredentials credentials) throws InterruptedException, IOException {
        CloseableHttpClient client = getClient();
        HttpClientContext httpClientContext = HttpClientContext.create();
        configureContext(httpClientContext, API_HOST, convertToHttpCredentials(credentials));
        RequestConfig.Builder requestConfig = RequestConfig.custom();
        requestConfig.setConnectTimeout(10 * 1000);
        requestConfig.setConnectionRequestTimeout(60 * 1000);
        requestConfig.setSocketTimeout(60 * 1000);
        httpMethod.setConfig(requestConfig.build());

        CloseableHttpResponse response = client.execute(host, httpMethod, httpClientContext);
        while (response.getStatusLine().getStatusCode() == API_RATE_LIMIT_CODE) {
            release(httpMethod);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            log.debug("Bitbucket Cloud API rate limit reached, sleeping for 5 sec then retry...");
            Thread.sleep(5000);
            response = client.execute(host, httpMethod, httpClientContext);
        }
        return response;
    }

    private CloseableHttpClient getClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setKeepAliveStrategy((__, ___) -> MILLISECONDS.convert(5, SECONDS));
        httpClientBuilder.setConnectionManager(connectionManager);
        httpClientBuilder.setConnectionManagerShared(true);
        httpClientBuilder.setRetryHandler(new StandardHttpRequestRetryHandler());
        return httpClientBuilder.build();
    }

    private Credentials convertToHttpCredentials(StandardUsernamePasswordCredentials credentials) {
        return new UsernamePasswordCredentials(credentials.getUsername(),
                Secret.toString(credentials.getPassword()));
    }

    public void configureContext(HttpClientContext context, HttpHost host, Credentials credentials) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(host), credentials);
        AuthCache authCache = new BasicAuthCache();
        log.debug("Add host={0} to authCache.", host);
        authCache.put(host, new BasicScheme());
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);
    }

    private void release(HttpRequestBase method) {
        method.releaseConnection();
        connectionManager.closeExpiredConnections();
    }

    private void spikeRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();


    }
}
