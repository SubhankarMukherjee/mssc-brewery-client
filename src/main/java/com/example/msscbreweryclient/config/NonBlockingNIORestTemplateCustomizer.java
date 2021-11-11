package com.example.msscbreweryclient.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;

@Component
public class NonBlockingNIORestTemplateCustomizer implements RestTemplateCustomizer {
    public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
        DefaultConnectingIOReactor reactor= new DefaultConnectingIOReactor(
                IOReactorConfig.custom()
                        .setConnectTimeout(3000)
                        .setIoThreadCount(4)
                        .setSoTimeout(3000)
                        .build());
final PoolingNHttpClientConnectionManager connectionManager= new PoolingNHttpClientConnectionManager(reactor);
connectionManager.setMaxTotal(100);
connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpAsyncClient httpAsyncClient= HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);

    }

    @Override
    public void customize(RestTemplate restTemplate) {

    }
}
