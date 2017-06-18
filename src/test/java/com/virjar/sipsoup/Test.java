package com.virjar.sipsoup;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.virjar.dungproxy.client.httpclient.CrawlerHttpClient;
import com.virjar.dungproxy.client.httpclient.HttpInvoker;
import com.virjar.dungproxy.client.ippool.config.ProxyConstant;

/**
 * Created by virjar on 17/6/17.
 */
public class Test {
    public static void main(String[] args) {
        final CrawlerHttpClient httpClient = HttpInvoker.buildDefault();
        for (int i = 0; i < 20; i++) {
            new Thread() {
                @Override
                public void run() {

                    HttpGet httpGet = new HttpGet("https://passport.jd.com/new/login.aspx");
                    RequestConfig.Builder builder = RequestConfig.custom()
                            .setSocketTimeout(ProxyConstant.SOCKET_TIMEOUT)
                            .setConnectTimeout(ProxyConstant.CONNECT_TIMEOUT)
                            .setConnectionRequestTimeout(ProxyConstant.REQUEST_TIMEOUT).setRedirectsEnabled(true)
                            .setCircularRedirectsAllowed(true);

                    httpGet.setConfig(builder.build());
                    try {
                        CloseableHttpResponse execute = httpClient.execute(httpGet);
                        System.out.println(IOUtils.toString(execute.getEntity().getContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }
}
