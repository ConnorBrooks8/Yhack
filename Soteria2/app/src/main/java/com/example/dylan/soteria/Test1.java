package com.example.dylan.soteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Test1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
    }
    public void cont3(View view) {
        Intent intent = new Intent(this, Test2.class);
        startActivity(intent);

    }
}
