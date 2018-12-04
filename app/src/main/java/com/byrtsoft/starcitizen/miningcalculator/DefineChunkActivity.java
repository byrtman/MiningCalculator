package com.byrtsoft.starcitizen.miningcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class DefineChunkActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            DefineChunkFragment chunkFragment = new DefineChunkFragment();
            chunkFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, chunkFragment).commit();

            Button commitButton = findViewById(R.id.buttonChunkCommit);
            if (commitButton != null) {
                commitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
    }
}
