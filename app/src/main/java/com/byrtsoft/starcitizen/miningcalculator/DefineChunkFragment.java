package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class DefineChunkFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private NumberPicker mMassPicker;
    private TextView mSelectedMass;
    private Button mEditButton;
    private boolean mEditMode = false;

    private double mSelectedMassValue;
    private double mAccumulatedValue;
    private int mChunkId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.chunk_entry, container, false);

        mSelectedMass = result.findViewById(R.id.textSetMassAmount);
        mSelectedMass.setVisibility(View.VISIBLE);

        // Setup mass picker
        mMassPicker = result.findViewById(R.id.massPicker);
        mMassPicker.setWrapSelectorWheel(true);
        mMassPicker.setDisplayedValues(getResources().getStringArray(R.array.chunk_sizes));
        mMassPicker.setMinValue(0);
        mMassPicker.setMaxValue(mMassPicker.getDisplayedValues().length-1);
        mMassPicker.setVisibility(View.INVISIBLE);

        mEditButton = result.findViewById(R.id.buttonSetMass);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditMode) {
                    mEditButton.setText(R.string.edit_mass);
                    mMassPicker.setVisibility(View.INVISIBLE);
                    String pickedMass = mMassPicker.getDisplayedValues()[mMassPicker.getValue()];
                    mSelectedMassValue = Double.parseDouble(pickedMass);
                    mSelectedMass.setText(pickedMass+" "+getString(R.string.mass_with_unit));
                    mSelectedMass.setVisibility(View.VISIBLE);
                } else {
                    mEditButton.setText(R.string.commit);
                    mMassPicker.setVisibility(View.VISIBLE);
                    mSelectedMass.setVisibility(View.INVISIBLE);
                }
                mEditMode = !mEditMode; // toggle mode
            }
        });

        FloatingActionButton addNewOreButton = result.findViewById(R.id.addOreFAButton);
        addNewOreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefineOreFragment oreFragment = new DefineOreFragment();
                oreFragment.setArguments(getActivity().getIntent().getExtras());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content, oreFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        Button commitButton = result.findViewById(R.id.buttonChunkCommit);
        if (commitButton != null) {
            commitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addChunkToDatabase();
                    getFragmentManager().popBackStack();
                }
            });
        }


        return result;
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

    private void addChunkToDatabase() {
        Toast.makeText(getContext(), "addChunkToDatabase()", Toast.LENGTH_SHORT).show();
        mChunkId++;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public interface OnFragmentInteractionListener {
        void onChunkComitted(Chunk chunk);
    }
}
