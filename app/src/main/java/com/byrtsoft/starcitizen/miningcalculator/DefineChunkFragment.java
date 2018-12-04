package com.byrtsoft.starcitizen.miningcalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class DefineChunkFragment extends Fragment {

    private NumberPicker mMassPicker;
    private TextView mSelectedMass;
    private Button mEditButton;
    private boolean mEditMode = false;

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
                    mEditButton.setText("Edit Mass");
                    mMassPicker.setVisibility(View.INVISIBLE);
                    mSelectedMass.setText(String.valueOf(mMassPicker.getDisplayedValues()[mMassPicker.getValue()])+" "+getString(R.string.chunk_mass));
                    mSelectedMass.setVisibility(View.VISIBLE);
                } else {
                    mEditButton.setText("commit");
                    mMassPicker.setVisibility(View.VISIBLE);
                    mSelectedMass.setVisibility(View.INVISIBLE);
                }
                mEditMode = !mEditMode; // toggle mode
            }
        });

        return result;
    }
}
