package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byrtsoft.starcitizen.db.Chunk;

public class PickLocationFragment extends Fragment {

    private AppViewModel appViewModel;

    public static PickLocationFragment newInstance() {
        return new PickLocationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.pick_location_fragment, container, false);

        // Setup the recyclerView that displays the ore allocations for the current chunk
//        RecyclerView recyclerView = result.findViewById(R.id.chunk_entry_recyclerview);
//        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
//        oreAllocListAdapter = new OreAllocListAdapter(getContext());
//        recyclerView.setAdapter(oreAllocListAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
//
//        appViewModel.getLastChunk().observe(this, new Observer<Chunk>() {
//            @Override
//            public void onChanged(@Nullable Chunk chunk) {
//                Log.d(TAG, "DefineChunkFragment::getLastChunk("+chunk+")");
//                mChunk = chunk;
//                mListener.onChunkInserted(chunk);
//                setupAllocListObserver();
//            }
//        });

        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        // TODO: Use the ViewModel
    }

}
