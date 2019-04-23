package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byrtsoft.starcitizen.db.MiningRun;

import java.util.List;
import java.util.Locale;

public class MiningRunListAdapter extends RecyclerView.Adapter<MiningRunListAdapter.MiningRunViewHolder> {

    class MiningRunViewHolder extends RecyclerView.ViewHolder {
        private final TextView runNameItemView;
        private final TextView runValueItemView;

        private MiningRunViewHolder (View itemView) {
            super(itemView);
            runNameItemView = itemView.findViewById(R.id.columnOneTextView);
            runValueItemView = itemView.findViewById(R.id.columnTwoTextView);
        }
    }

    private final LayoutInflater inflater;
    private List<MiningRun> runs; // cached copy of runs

    MiningRunListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public MiningRunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_two_column, parent, false);
        return new MiningRunViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MiningRunViewHolder holder, int position) {
        if (runs != null) {
            MiningRun current = runs.get(position);
            holder.runNameItemView.setText(String.format(Locale.US, "%s", current.getName()));
            holder.runValueItemView.setText(String.format(Locale.US, "%s %.0f", "$", current.getValue()));
        } else {
            holder.runNameItemView.setText(R.string.nothing);
        }
    }

    void setMiningRuns(List<MiningRun> runs) {
        this.runs = runs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (runs != null) {
            return runs.size();
        } else {
            return 0;
        }
    }
}
