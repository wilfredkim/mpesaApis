package com.mpesaapp.mpesaapp.endpoints;

import com.google.gson.Gson;
import com.mpesaapp.mpesaapp.configs.MpesaConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class BaseController {

    protected String encode(String customerKey, String customerSecret) {
        String appKeySecret = customerKey + ":" + customerSecret;
        byte[] bytes = appKeySecret.getBytes(StandardCharsets.ISO_8859_1);
        return Base64.getEncoder().encodeToString(bytes);
    }

    protected Gson gson = new Gson();


    protected String authenticateApis() throws Exception {
        MpesaConfig mpesaConfig = new MpesaConfig();//todo to fetch from db

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .method("GET", null)
                .addHeader("Authorization", "Basic " + encode(mpesaConfig.getCustomerKey(), mpesaConfig.getCustomerSecret()))
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String s = response.body().string();
        System.out.println(s);
        JSONObject jsonObject = new JSONObject(s);
        return jsonObject.getString("access_token");

    }
}
