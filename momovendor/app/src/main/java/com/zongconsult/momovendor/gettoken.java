package com.zongconsult.momovendor;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class gettoken extends MainActivity {

    public static String thetoken() throws JSONException, IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://sandbox.momodeveloper.mtn.com/collection/token/")
                .method("POST", body)
                .addHeader("Authorization", "Basic ZDM2YTI5OGQtODU3MS00MzdhLWEyYTQtOWI5MGZhYTM3YTI3OmU0YzcxMTU0MzdkMDQ3YzE5OWRhY2M0MDhlMmU2Y2Qy")
                .addHeader("Ocp-Apim-Subscription-Key", "a3d4691b14c349d69af2ba49a31b9dcc")
                .build();
        Response response = client.newCall(request).execute();

        String resp = response.body().string(); //get response body


        JSONObject object = new JSONObject(resp); //
        String token = object.getString("access_token");
        String token_type = object.getString( "token_type");
        String expires_in = object.getString("expires_in");


        String status = String.valueOf(response.code());

        System.out.println("___ Token is ______" + token + "______________");
        System.out.println("___ Token type is ______" + token_type + "______________");
        System.out.println("___ Token expires in ______" + expires_in + "______________");
        System.out.println("___ Token status is ______" + status + "______________");

        return token;

    }

}

