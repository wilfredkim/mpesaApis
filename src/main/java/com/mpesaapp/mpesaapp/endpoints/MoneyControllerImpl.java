package com.mpesaapp.mpesaapp.endpoints;

import com.google.gson.Gson;
import com.mpesaapp.mpesaapp.model.C2bRequest;
import com.mpesaapp.mpesaapp.model.C2bResponse;
import com.mpesaapp.mpesaapp.model.MpesaExpressRequest;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MoneyControllerImpl extends BaseController implements MoneyController {

    @Override
    public ResponseEntity<Object> mpesaExpress(MpesaExpressRequest mpesaExpressRequest) {
        try {

            String token = authenticateApis();
            if (token == null) {
                return new ResponseEntity<>("Failed to authenticate!!", HttpStatus.FORBIDDEN);
            }
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, gson.toJson(mpesaExpressRequest));
            Request request = new Request.Builder().url(APICALL.LIPA_NA_MPESA)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            Response response = client.newCall(request).execute();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<C2bResponse> c2b(C2bRequest c2bRequest) {
        try {
            String token = authenticateApis();
            if (token == null) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            }

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, gson.toJson(c2bRequest));
            Request request = new Request.Builder().url(APICALL.C2B)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();;
            C2bResponse c2bResponse = gson.fromJson(responseString, C2bResponse.class);
            c2bResponse.setSuccess(true);
            return new ResponseEntity<>(c2bResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
