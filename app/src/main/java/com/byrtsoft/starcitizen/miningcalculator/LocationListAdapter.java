package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byrtsoft.starcitizen.db.MiningLocation;

import java.util.List;
import java.util.Locale;

class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    private final LayoutInflater inflater;
    private List<MiningLocation> places; // cached copy of runs

    public LocationListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.recyclerview_two_column, viewGroup, false);
        return new LocationListAdapter.LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int i) {
        if (places != null) {
            MiningLocation current = places.get(i);
            holder.runNameItemView.setText(String.format(Locale.US, "%s", current.getName()));
            holder.runValueItemView.setText("");
        } else {
            holder.runNameItemView.setText(R.string.nothing);
        }
    }

    void setLocatons(List<MiningLocation> places) {
        this.places = places;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (places != null) {
            return places.size();
        } else {
            return 0;
        }
    }


    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView runNameItemView;
        private final TextView runValueItemView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            runNameItemView = itemView.findViewById(R.id.columnOneTextView);
            runValueItemView = itemView.findViewById(R.id.columnTwoTextView);
        }
    }
}
