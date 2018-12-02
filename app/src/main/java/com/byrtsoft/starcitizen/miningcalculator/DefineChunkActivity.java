package com.byrtsoft.starcitizen.miningcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public class DefineChunkActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            DefineChunkFragment chunkFragment = new DefineChunkFragment();
            chunkFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, chunkFragment).commit();
        }
    }
}
