package com.example.dylan.soteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Background1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background1);
    }

    public void cont3(View view) {

        Intent intent = new Intent(this, Background2.class);
        startActivity(intent);

    }
}