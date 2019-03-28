package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.byrtsoft.starcitizen.db.Chunk;
import com.byrtsoft.starcitizen.db.OreAlloc;

import java.util.List;

public class DefineChunkFragment extends Fragment {
    private static String TAG = "BYRT";

    private OnFragmentInteractionListener mListener;

    private NumberPicker mMassPicker;
    private TextView mSelectedMass;
    private TextView mTotalValueView;
    private Button mEditButton;

    private AppViewModel appViewModel;
    private Chunk mChunk;
    private OreAllocListAdapter oreAllocListAdapter;

    private double mSelectedMassAmount;
    private boolean mListObserverSet = false;
    private boolean mEditMode = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mChunk = createChunk();
        mListener.onChunkCreated(mChunk);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.chunk_entry, container, false);

        mListObserverSet = false;
        mSelectedMass = result.findViewById(R.id.textSetMassAmount);
        mSelectedMass.setVisibility(View.VISIBLE);
        mTotalValueView = result.findViewById(R.id.resultsTotalValue);

        // Setup mass picker
        mMassPicker = result.findViewById(R.id.massPicker);
        mMassPicker.setWrapSelectorWheel(true);
        mMassPicker.setDisplayedValues(getResources().getStringArray(R.array.chunk_sizes));
        mMassPicker.setMinValue(0);
        mMassPicker.setMaxValue(mMassPicker.getDisplayedValues().length-1);
        mMassPicker.setVisibility(View.INVISIBLE);

        // Setup Add/Edit mass
        mEditButton = result.findViewById(R.id.buttonSetMass);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the edit mass and done button
                if (mEditMode) {
                    mEditButton.setText(R.string.edit_mass);
                    mMassPicker.setVisibility(View.INVISIBLE);
                    String pickedMass = mMassPicker.getDisplayedValues()[mMassPicker.getValue()];
                    mSelectedMassAmount = Double.parseDouble(pickedMass);
                    mChunk.setMass(mSelectedMassAmount);
                    mListener.onMassSelected(mChunk);
                    oreAllocListAdapter.setMassToAllocate(mSelectedMassAmount);
                    mSelectedMass.setText(pickedMass+" "+getString(R.string.mass_unit));
                    mSelectedMass.setVisibility(View.VISIBLE);
                } else {
                    mEditButton.setText(R.string.commit);
                    oreAllocListAdapter.resetMass();
                    mMassPicker.setVisibility(View.VISIBLE);
                    mSelectedMass.setVisibility(View.INVISIBLE);
                }
                mEditMode = !mEditMode; // toggle mode
            }
        });

        // Setup floating add ore allocation button
        FloatingActionButton addNewOreButton = result.findViewById(R.id.addOreFAButton);
        addNewOreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefineOreFragment oreFragment = DefineOreFragment.newInstance(mChunk.getId(), null);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content, oreFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        // Setup the commit chunk button
        Button commitButton = result.findViewById(R.id.buttonChunkCommit);
        if (commitButton != null) {
            commitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectedMassAmount > 0) {
                        mListener.onChunkCommitted(mChunk);
                    }
                    oreAllocListAdapter.resetMass();
                    mChunk = null;
                    getFragmentManager().popBackStack();
                }
            });
        }

        // Setup the recyclerView that displays the ore allocations for the current chunk
        RecyclerView recyclerView = result.findViewById(R.id.alloc_recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        oreAllocListAdapter = new OreAllocListAdapter(getContext());
        recyclerView.setAdapter(oreAllocListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        appViewModel.getLastChunk().observe(this, new Observer<Chunk>() {
            @Override
            public void onChanged(@Nullable Chunk chunk) {
                Log.d(TAG, "DefineChunkFragment::getLastChunk("+chunk+")");
                mChunk = chunk;
                mListener.onChunkInserted(chunk);
                setupAllocListObserver();
            }
        });

        return result;
    }

    private void setupAllocListObserver() {
        if (!mListObserverSet) {
            appViewModel.getAllAllocs(mChunk.getId()).observe(this, new Observer<List<OreAlloc>>() {
                @Override
                public void onChanged(@Nullable List<OreAlloc> allocs) {
                    oreAllocListAdapter.setAllocs(allocs);
                    String text = new StringBuilder().append(getString(R.string.value_unit)).append(" ").append(String.valueOf(appViewModel.getAllocsValue(mChunk.getId()))).toString();
                    mTotalValueView.setText(text);
                }
            });
            mListObserverSet = true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DefineChunkFragment.OnFragmentInteractionListener) {
            mListener = (DefineChunkFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private Chunk createChunk() {
        Chunk chunk = new Chunk(mSelectedMassAmount);
        return chunk;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public interface OnFragmentInteractionListener {
        void onChunkCreated(Chunk chunk);
        void onChunkInserted(Chunk chunk);
        void onMassSelected(Chunk chunk);
        void onChunkCommitted(Chunk chunk);
    }
}
