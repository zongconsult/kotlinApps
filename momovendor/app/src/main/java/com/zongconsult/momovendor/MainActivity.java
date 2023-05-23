package com.zongconsult.momovendor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//final gettoken = new thetoken(); //fr getting the token
        //final requesttopay rp = new requesttopay(); //or requesttopay/
          final checkrtp cktp = new checkrtp();
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    //rp.rtp();
                    //gt.thetoken(); //for getting the token
                     cktp.checkpayment();
                } catch (IOException e) {

                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
    }

}
