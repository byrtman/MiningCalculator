package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class OreAllocListAdapter extends RecyclerView.Adapter<OreAllocListAdapter.OreAllocViewHolder> {

    class OreAllocViewHolder extends RecyclerView.ViewHolder {
        private final TextView allocOreNameItemView;
        private final TextView allocPercentItemView;
        private final TextView allocValueItemView;

        private OreAllocViewHolder(View itemView) {
            super(itemView);
            allocOreNameItemView = itemView.findViewById(R.id.oreNameTextView);
            allocPercentItemView = itemView.findViewById(R.id.percentTextView);
            allocValueItemView = itemView.findViewById(R.id.valueTextView);
        }
    }

    private final LayoutInflater inflater;
    private List<OreAlloc> allocs; // cached copy of allocs
    private double mChunkMass; // the chunk mass we are allocating

    OreAllocListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public OreAllocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_alloc_item, parent, false);
        return new OreAllocViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OreAllocViewHolder holder,  int position) {
        if (allocs != null) {
            OreAlloc alloc = allocs.get(position);
            holder.allocOreNameItemView.setText(String.format(Locale.US, "%s", alloc.getOre().getName()));
            holder.allocPercentItemView.setText(String.format(Locale.US, "%.0f%s",alloc.getAllocation(),inflater.getContext().getString(R.string.alloc_unit)));
            holder.allocValueItemView.setText(String.format(Locale.US, "%s%.0f", inflater.getContext().getString(R.string.value_unit), alloc.calculateValue(mChunkMass)));
        } else {
            holder.allocOreNameItemView.setText(R.string.nothing);
        }
    }

    public void setMassToAllocate(double mass) {
        if (mChunkMass > 0.0) {
            throw new AssertionError("Attempting to reset chunk mass when already set!");
        }
        mChunkMass = mass;
    }

    public void resetMass() {
        mChunkMass = 0.0;
    }

    void setAllocs(List<OreAlloc> allocs) {
        this.allocs = allocs;
        Log.d("BYRT", "number of oreAllocs = " + allocs.size());
        for (OreAlloc o : allocs
             ) {
            Log.d("BYRT", o.toString());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (allocs != null) {
            return allocs.size();
        } else {
            return 0;
        }
    }
}
