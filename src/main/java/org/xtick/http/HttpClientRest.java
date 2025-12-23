package org.xtick.http;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.xtick.util.XTickUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */

@Getter
@Setter
public class HttpClientRest {
    public final static HttpClientRest intance = new HttpClientRest();
    private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    private HttpClientRest() {
    }

    public void reInit() {
        httpClient = HttpClientBuilder.create().build();
    }

    public static HttpClientRest getIntance() {
        return HttpClientRest.intance;
    }

    public String get(String url, Header[] headers) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).setExpectContinueEnabled(false).setCircularRedirectsAllowed(true).build();
        return get(url, headers, requestConfig);
    }

    public String get(String url, Header[] headers, RequestConfig requestConfig) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeaders(headers);
        httpGet.setConfig(requestConfig);
        httpGet.setConfig(RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).setExpectContinueEnabled(false).setCircularRedirectsAllowed(true).build());
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    public String get(String url, Map<String, Object> para) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).setExpectContinueEnabled(false).setCircularRedirectsAllowed(true).build();
        return get(url, para, requestConfig);
    }

    public String get(String url, Map<String, Object> para, RequestConfig requestConfig) throws IOException {
        HttpGet httpGet = new HttpGet(String.format("%s?%s", url, para.entrySet().stream().map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue())).collect(Collectors.joining("&"))));
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            Header header = response.getFirstHeader(HttpHeaders.CONTENT_ENCODING);
            if (Objects.nonNull(header)) {
                if ("zip".equals(header.getValue())) {
                    return XTickUtil.processData(response.getEntity().getContent());
                } else if ("gzip".equals(header.getValue())) {
                    return XTickUtil.processGZipData(response.getEntity().getContent());
                }
            }
            return EntityUtils.toString(response.getEntity());
        }
    }

    public String post(String url, Map<String, Object> para) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).setExpectContinueEnabled(false).setCircularRedirectsAllowed(true).build();
        return post(url, para, requestConfig);
    }

    public String post(String url, Map<String, Object> para, RequestConfig requestConfig) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
        httpPost.setEntity(new UrlEncodedFormEntity(para.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()))).collect(Collectors.toList())));
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            Header header = response.getFirstHeader(HttpHeaders.CONTENT_ENCODING);
            if (Objects.nonNull(header)) {
                if ("zip".equals(header.getValue())) {
                    return XTickUtil.processData(response.getEntity().getContent());
                } else if ("gzip".equals(header.getValue())) {
                    return XTickUtil.processGZipData(response.getEntity().getContent());
                }
            }
            return EntityUtils.toString(response.getEntity());
        }
    }
}
