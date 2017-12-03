package com.example.dylan.soteria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;


public class Background2 extends AppCompatActivity {
    public static String sex;
    public static String hand;
    public static String hospital;
    public static String diagnosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void cont2(View view) {
        Intent intent = new Intent(this, Test1.class);
        startActivity(intent);

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {

            case R.id.radioButton1:
                if (checked) {
                    sex = "Male";
                }
            case R.id.radioButton2:
                if (checked) {
                    sex = "Female";
                }
            case R.id.radioButton3:
                if (checked) {
                    hand = "Left";
                }
            case R.id.radioButton4:
                if (checked) {
                    hand = "Neither";
                }
            case R.id.radioButton5:
                if (checked) {
                    hand = "Right";
                }
            case R.id.radioButton6:
                if (checked) {
                    hospital = "Yes";
                }
            case R.id.radioButton7:
                if (checked) {
                    hospital = "No";
                }
            case R.id.radioButton8:
                if (checked) {
                    diagnosed = "Yes";
                }
            case R.id.radioButton9:
                if (checked) {
                    diagnosed = "No";
                }
        }

    }
}

