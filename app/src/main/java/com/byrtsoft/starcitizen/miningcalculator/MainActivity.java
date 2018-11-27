package com.byrtsoft.starcitizen.miningcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPicker np1 = findViewById(R.id.numberPicker);
        np1.setMaxValue(1000);
        np1.setMinValue(0);
        np1.setWrapSelectorWheel(false);
    }
}
