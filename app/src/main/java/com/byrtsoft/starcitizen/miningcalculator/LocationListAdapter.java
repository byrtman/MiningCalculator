package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byrtsoft.starcitizen.db.MiningLocation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    private final LayoutInflater inflater;
    private List<MiningLocation> places; // cached copy of runs
    private PickLocationFragment fragment;

    public LocationListAdapter(Context context, Fragment fragment) {
        this.fragment = (PickLocationFragment) fragment;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.recyclerview_two_column, viewGroup, false);
        return new LocationListAdapter.LocationViewHolder(itemView, this);
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

    void setLocations(List<MiningLocation> places) {
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

    public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView runNameItemView;
        private final TextView runValueItemView;
        private final LocationListAdapter listAdapter;

        public LocationViewHolder(@NonNull View itemView, LocationListAdapter adapter) {
            super(itemView);
            runNameItemView = itemView.findViewById(R.id.columnOneTextView);
            runValueItemView = itemView.findViewById(R.id.columnTwoTextView);
            itemView.setOnClickListener(this);
            listAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            MiningLocation elem = places.get(position);
            Log.d("BYRT", "Create new run! => " + elem + " : " + LocalDateTime.now());
            fragment.onItemSelected(elem);
        }


    }
}
