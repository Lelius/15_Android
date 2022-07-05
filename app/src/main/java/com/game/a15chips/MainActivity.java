package com.game.a15chips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Draw2D draw2D = new Draw2D(this);
        setContentView(draw2D);
    }
}