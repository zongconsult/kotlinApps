package com.zongconsult.storeapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button next, previous;
    int i = 0;
    private int[] textureArrayWin = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image15, R.drawable.image16, R.drawable.image17, R.drawable.image18, R.drawable.image19};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);
        previous = findViewById(R.id.Previous);
        next = findViewById(R.id.Next);


        if (i == 0) {
            previous.setVisibility(View.GONE);

        }
       /*  if (i == 12) {
            next.setVisibility(View.GONE);
        }*/



        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView.setImageResource(textureArrayWin[i]);
                i--;
                if (i<=0){
                    next.setVisibility(View.VISIBLE);
                    i++;
                }
                if (i <= 0) {
                    previous.setVisibility(View.GONE);
                } else {
                    next.setVisibility(View.VISIBLE);
                }
                if (i == 19) {
                   // i = 0;

                    next.setVisibility(View.GONE);
                } else {
                    previous.setVisibility(View.VISIBLE);
                }

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(textureArrayWin[i]);
                i++;
                if (i == 0) {
                    previous.setVisibility(View.GONE);
                } else {
                    next.setVisibility(View.VISIBLE);
                }
                if (i == 19) {
                    //i = 0;
                    next.setVisibility(View.GONE);
i = 0;
                } else {

                    previous.setVisibility(View.VISIBLE);

                }
            }
        });

    }
}