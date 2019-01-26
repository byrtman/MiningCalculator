package com.byrtsoft.starcitizen.miningcalculator;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements DefineOreFragment.OnFragmentInteractionListener,
        DefineChunkFragment.OnFragmentInteractionListener {

    private AppViewModel appViewModel;
    private Chunk mCurrentChunk;
    private ArrayList<OreAlloc> mAccumulatedAllocs = new ArrayList<>();
    private static int mCurrentChunkId;

    private DefineChunkFragment mChunkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNewChunkButton = findViewById(R.id.addNewChunkFAButton);
        addNewChunkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the fragment, not the activity
                mChunkFragment = new DefineChunkFragment();
                mChunkFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(android.R.id.content, mChunkFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        final ChunkListAdapter chunkListAdapter = new ChunkListAdapter(this);
        recyclerView.setAdapter(chunkListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllChunks().observe(this, new Observer<List<Chunk>>() {
            @Override
            public void onChanged(@Nullable List<Chunk> chunks) {
                chunkListAdapter.setChunks(chunks);
                final TextView view = findViewById(R.id.resultsTotalValue);
                view.setText(String.valueOf(appViewModel.getAllChunksValue())+" "+ getString(R.string.value_with_unit));
            }
        });




    }

    @Override
    public void onOreAllocated(Ore ore, double percent, int parentChunkId) {
        // This is called when ore is allocated to a chunk. This should be saved to the database
        // under the user's list of committed data.

        Log.d("BYRT", "onOreAllocated("+ore.getName()+","+percent+") called");
        OreAlloc alloc = new OreAlloc(ore, percent, mCurrentChunkId);
        mAccumulatedAllocs.add(alloc);
        appViewModel.insertOreAlloc(alloc);
    }

    private double calculateChunkValue() {
        double result = 0.0;
        double totalMass = mCurrentChunk.getMass();

        return result;
    }

    @Override
    public void onChunkCommitted(Chunk chunk) {
        mCurrentChunk = chunk;
        mCurrentChunk.setValue(calculateChunkValue());
        mCurrentChunkId = mCurrentChunk.getId();
        appViewModel.insertChunk(chunk);
        Log.d("BYRT", "onChunkCommitted("+chunk.getId()+":"+chunk.getMass()+") called");
    }
}