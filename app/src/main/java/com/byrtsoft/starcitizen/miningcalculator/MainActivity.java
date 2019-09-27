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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.byrtsoft.starcitizen.db.Chunk;
import com.byrtsoft.starcitizen.db.MiningRun;
import com.byrtsoft.starcitizen.db.Ore;
import com.byrtsoft.starcitizen.db.OreAlloc;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements DefineOreFragment.OnFragmentInteractionListener,
        DefineChunkFragment.OnFragmentInteractionListener {
    private static String TAG = "BYRT";
    private AppViewModel appViewModel;
    private Chunk mCurrentChunk;

    private DefineChunkFragment mChunkFragment;
    private PickLocationFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Floating button to add a new chunk
        FloatingActionButton addNewChunkButton = findViewById(R.id.addNewChunkFAButton);
        addNewChunkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the fragment, not the activity

//                mChunkFragment = new DefineChunkFragment();
//                mChunkFragment.setArguments(getIntent().getExtras());
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(android.R.id.content, mChunkFragment);

                mFragment = PickLocationFragment.newInstance();
                mFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(android.R.id.content, mFragment);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // RecyclerView to display all of the committed chunks in the database
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        final MiningRunListAdapter listAdapter = new MiningRunListAdapter(this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setting the observer for the chunks list when it is updated
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllMiningRuns().observe(this, new Observer<List<MiningRun>>() {
            @Override
            public void onChanged(@Nullable List<MiningRun> runs) {
                listAdapter.setMiningRuns(runs);

                // Set the total value of all chunks TextView
                final TextView view = findViewById(R.id.resultsTotalValue);
                String text = "TBD";//new StringBuilder().append(getString(R.string.value_unit)).append(" ").append(String.valueOf(appViewModel.getAllChunksValue())).toString();
                view.setText(text);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Item " + item + " selected");
        switch (item.getItemId()) {
            case R.id.actions_reset_all:
                appViewModel.resetAllData();
            case R.id.actions_reset_chunk:
                appViewModel.resetChunkAllocation();
                if (mCurrentChunk != null) {
                    mCurrentChunk.setValue(0.0);
                    appViewModel.updateChunk(mCurrentChunk);
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOreAllocated(Ore ore, double percent, int parentChunkId) {
        // This is called when ore is allocated to a chunk. This should be saved to the database
        // under the user's list of committed data.

        Log.d(TAG, "onOreAllocated("+ore.getName()+","+percent+"%) called for chunkId:" + mCurrentChunk.getId());
        OreAlloc alloc = new OreAlloc(ore, percent, mCurrentChunk.getId());
        updateChunkValue(parentChunkId, alloc);
        appViewModel.insertOreAlloc(alloc);
    }

    private void updateChunkValue(int chunkId, OreAlloc alloc) {
        if (mCurrentChunk.getId() != chunkId) {
            throw new AssertionError("mismatched ids");
        }
        if (mCurrentChunk != null && alloc != null) {
            double value = mCurrentChunk.getValue();
            double mass = mCurrentChunk.getMass();
            value += alloc.calculateValue(mass);
            mCurrentChunk.setValue(value);
            appViewModel.updateChunk(mCurrentChunk);
        }
    }

    @Override
    public void onChunkCreated(Chunk chunk) {
        appViewModel.insertChunk(chunk);
    }

    @Override
    public void onChunkInserted(Chunk chunk) {
        Log.d(TAG," Chunk " + chunk + " inserted");
        mCurrentChunk = chunk;
    }

    @Override
    public void onMassSelected(Chunk chunk) {
        appViewModel.updateChunk(chunk);
        Log.d(TAG," Chunk with id = " + chunk + " updated");
    }

    @Override
    public void onChunkCommitted(Chunk chunk) {
        // Updates the modified Chunk in the database - this updates the main list
        appViewModel.updateChunk(chunk);
        Log.d(TAG, "onChunkCommitted(" + chunk + ") called");
    }
}