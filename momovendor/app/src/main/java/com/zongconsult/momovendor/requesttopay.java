package com.zongconsult.momovendor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class requesttopay extends gettoken {
    public static String uniqueID = UUID.randomUUID().toString();
    private static gettoken gt;

    public static String rtp() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n\r\n  \"amount\": \"104000\",\r\n  \"currency\": \"EUR\",\r\n  \"externalId\": \"123456\",\r\n  \"payer\": {\r\n    \"partyIdType\": \"MSISDN\",\r\n    \"partyId\": \"46733123454\"\r\n  },\r\n  \"payerMessage\": \"Please complete payment for 100 BTC\",\r\n  \"payeeNote\": \"Thank you for doing business with us\"\r\n}");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + gt.thetoken())
                .addHeader("X-Reference-Id", uniqueID)
                .addHeader("X-Target-Environment", "sandbox")
                .addHeader("Content-Type", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", "a3d4691b14c349d69af2ba49a31b9dcc")
                .build();
        Response response = client.newCall(request).execute();

        String resp2 = response.body().string(); //get response body

       //System.out.println("___ Request to pay UUID is  ______" + uniqueID + "______________");

        JSONObject object = new JSONObject(resp2);

        String status2 = String.valueOf(response.code());

        System.out.println("___ Token status is ______" + status2 + "______________");
      //  System.out.println("___ UUID is ______" + uniqueID + "______________");

//return status2; //status of the request to pay

        return uniqueID;

    }


}