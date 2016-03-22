package com.winry.http;

import com.winry.form.Form;
import com.winry.util.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;


/**
 * Created by cong on 2016/3/22.
 */
public class HttpService {

    private OkHttpClient client = new OkHttpClient();

    public <T> T execute(Class<T> Clazz, String url) {
        Request  request = new Request.Builder().url(url).build();
        return call(Clazz, request);
    }

    public <T> T execute(Class<T> Clazz, String url, Form form) {
        RequestBody formBody = form.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        return call(Clazz, request);
    }

    private <T> T call(Class<T> Clazz, Request request) {
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            return JsonUtil.toObject(Clazz, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
