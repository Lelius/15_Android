package com.leliuslogunov.a15chips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();

        Draw2D draw2D = new Draw2D(this);
        setContentView(draw2D);

        getSupportActionBar().setSubtitle(R.string.subtitle);
    }
}