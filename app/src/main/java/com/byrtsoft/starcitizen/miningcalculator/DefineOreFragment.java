package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DefineOreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DefineOreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefineOreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CHUNK_ID = "param_chunkId";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int mChunkId;

    private NumberPicker mOrePicker;
    private NumberPicker mAllocPicker;
    private Button mCommitButton;

    private OnFragmentInteractionListener mListener;

    public DefineOreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DefineOreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DefineOreFragment newInstance(int param1, String param2) {
        DefineOreFragment fragment = new DefineOreFragment();
        Bundle args = new Bundle();
        args.putInt(CHUNK_ID, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChunkId = getArguments().getInt(CHUNK_ID);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_define_ore, container, false);
        mOrePicker = result.findViewById(R.id.orePicker);
        mOrePicker.setDisplayedValues(getResources().getStringArray(R.array.ore_types));
        mOrePicker.setMinValue(0);
        mOrePicker.setMaxValue(mOrePicker.getDisplayedValues().length-1);
        mAllocPicker = result.findViewById(R.id.allocPicker);
        mAllocPicker.setMinValue(0);
        mAllocPicker.setMaxValue(100);
        mCommitButton = result.findViewById(R.id.buttonOreCommit);
        mCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ore selectedOre = new Ore("poop", 1, 1);
                selectedOre.setName(mOrePicker.getDisplayedValues()[mOrePicker.getValue()]);
                double selectedAllocation = (double) mAllocPicker.getValue();
                onCommitPressed(selectedOre, selectedAllocation, mChunkId);
            }
        });
        return result;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onCommitPressed(Ore ore, double percent, int chunkId) {
        if (mListener != null) {
            mListener.onOreAllocated(ore, percent, chunkId);
        }
        getFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DefineOreFragment.OnFragmentInteractionListener) {
            mListener = (DefineOreFragment.OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onOreAllocated(Ore ore, double percent, int parentChunkId);
    }
}
