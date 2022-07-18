package com.game.a15chips;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

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