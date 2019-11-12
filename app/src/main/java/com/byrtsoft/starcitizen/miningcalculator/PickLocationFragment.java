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

import com.byrtsoft.starcitizen.db.MiningLocation;
import com.byrtsoft.starcitizen.db.Ore;

import java.util.List;

public class PickLocationFragment extends Fragment {

    private static String TAG = "BYRT";
    private AppViewModel appViewModel;
    private LocationListAdapter locationListAdapter;

    public static PickLocationFragment newInstance() {
        return new PickLocationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.pick_location_fragment, container, false);

        // Setup the recyclerView that displays the ore allocations for the current chunk
        RecyclerView recyclerView = result.findViewById(R.id.location_recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        locationListAdapter = new LocationListAdapter(getContext(), this);
        recyclerView.setAdapter(locationListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        appViewModel.getAllMiningLocations().observe(this, new Observer<List<MiningLocation>>() {
            @Override
            public void onChanged(@Nullable List<MiningLocation> miningLocations) {
                Log.d(TAG, "PickLocation: getting list of locations");
                locationListAdapter.setLocations(miningLocations);
            }
        });

        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onItemSelected(MiningLocation location) {

        Log.d(TAG, "Number of Ores available at " + location.getName() + " = " + location.getOreIds().length);
        String msg = "";
        for (int i=0; i < location.getOreIds().length; i++) {
            msg +=  Ore.ORES()[location.getOreIds()[i]].getName() + "\n\t";
        }
        Log.d(TAG, "\n\n" + msg);
        getFragmentManager().popBackStack();
    }

}
