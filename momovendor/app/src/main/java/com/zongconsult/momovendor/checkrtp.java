package com.zongconsult.momovendor;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class checkrtp extends requesttopay {

    private static gettoken gt;
    private static requesttopay rtp;

    public static JSONObject checkpayment() throws JSONException, IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/" + rtp.rtp())
                .method("GET", null)
                .addHeader("Ocp-Apim-Subscription-Key", "a3d4691b14c349d69af2ba49a31b9dcc")
                .addHeader("Authorization", "Bearer " + gt.thetoken())
                .addHeader("X-Target-Environment", "sandbox")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        String resp3 = response.body().string(); //get response body

        JSONObject object3 = new JSONObject(resp3);

        //String status2 = String.valueOf(response.code());

        //System.out.println("___ Token status is ______" + status2 + "______________");
        System.out.println("Data: " + object3 + "______________");

        return object3;

    }

}
