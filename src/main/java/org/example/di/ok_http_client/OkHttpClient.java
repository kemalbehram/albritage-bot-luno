package org.example.di.ok_http_client;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpClient {
    public static okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();

                    String path = request.url().toString();

                    long startTs = System.currentTimeMillis();
                    Response response = chain.proceed(request);
                    return response;
                }
            }).build();
    //Response response = okHttpClient.newCall().execute();

}
