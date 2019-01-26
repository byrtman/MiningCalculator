package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

        private OreAllocViewHolder(View itemView) {
            super(itemView);
            allocOreNameItemView = itemView.findViewById(R.id.oreNameTextView);
            allocPercentItemView = itemView.findViewById(R.id.percentTextView);
        }
    }

    private final LayoutInflater inflater;
    private List<OreAlloc> allocs; // cached copy of allocs

    OreAllocListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public OreAllocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_alloc_item, parent, false);
        return new OreAllocViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OreAllocViewHolder holder, int position) {
        if (allocs != null) {
            OreAlloc current = allocs.get(position);
            holder.allocOreNameItemView.setText(String.format(Locale.US, "%s", current.getOre().getName()));
            holder.allocPercentItemView.setText(String.format(Locale.US, "%.0f%s", "%", current.getAllocation()));
        } else {
            holder.allocOreNameItemView.setText(R.string.nothing);
        }
    }

    void setAllocs(List<OreAlloc> allocs) {
        this.allocs = allocs;
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
