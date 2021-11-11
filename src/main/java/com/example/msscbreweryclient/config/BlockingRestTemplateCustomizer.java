package com.example.msscbreweryclient.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

//@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    public ClientHttpRequestFactory clientHttpRequestFactory()
    {
        // Set total number of connection and route
        PoolingHttpClientConnectionManager connectionManager= new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        // set request Config with timeout

        //set 3 sec timeout
        RequestConfig requestConfig= RequestConfig.custom().setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000).build();
        CloseableHttpClient httpClient= HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
    return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(clientHttpRequestFactory());
    }
}
