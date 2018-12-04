package com.byrtsoft.starcitizen.miningcalculator;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppViewModel appViewModel;
    private ChunkListAdapter chunkListAdapter;
    private int grandTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView view = findViewById(R.id.resultsTotalValue);
        view.setText(String.valueOf(grandTotal)+" "+getString(R.string.value));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), DefineChunkActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ChunkListAdapter adapter = new ChunkListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllChunks().observe(this, new Observer<List<Chunk>>() {
            @Override
            public void onChanged(@Nullable List<Chunk> chunks) {
                adapter.setChunks(chunks);
            }
        });

    }

}