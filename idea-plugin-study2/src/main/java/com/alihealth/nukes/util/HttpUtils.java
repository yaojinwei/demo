package com.alihealth.nukes.util;

import com.intellij.openapi.diagnostic.Logger;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class HttpUtils {

    public static final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8");
    private static Logger logger = Logger.getInstance(HttpUtils.class);

    public static okhttp3.Response post(String url, String jsonBody) throws IOException {
        Map<String, String> headerMap = new HashMap<>();
        return post(url, jsonBody, headerMap);
    }

    public static okhttp3.Response post(String url, String jsonBody, Map<String, String> headerMap) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        return post(client, url, jsonBody, headerMap);
    }

    public static okhttp3.Response post(OkHttpClient client, String url, String jsonBody, Map<String, String> headerMap) throws IOException {
        okhttp3.RequestBody body = RequestBody.create(jsonBody, MEDIA_JSON);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .headers(Headers.of(headerMap))
                .removeHeader("host")
                .removeHeader("Accept-Encoding")
                .url(url)
                .post(body)
                .build();
        logger.debug("post reqest={}", request);
        logger.debug("post body={}", jsonBody);
        okhttp3.Response response = client.newCall(request).execute();
        logger.debug("post response={}", response);
        return response;
    }
}
