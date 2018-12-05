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

public class ChunkListAdapter extends RecyclerView.Adapter<ChunkListAdapter.ChunkViewHolder> {

    class ChunkViewHolder extends RecyclerView.ViewHolder {
        private final TextView chunkMassItemView;
        private final TextView chunkValueItemView;

        private ChunkViewHolder (View itemView) {
            super(itemView);
            chunkMassItemView = itemView.findViewById(R.id.massTextView);
            chunkValueItemView = itemView.findViewById(R.id.valueTextView);
        }
    }

    private final LayoutInflater inflater;
    private List<Chunk> chunks; // cahced copy of chunks

    ChunkListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public ChunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ChunkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChunkViewHolder holder, int position) {
        if (chunks != null) {
            Chunk current = chunks.get(position);
            holder.chunkMassItemView.setText(String.format(Locale.US, "%.2f", current.getMass()));
            holder.chunkValueItemView.setText(String.format(Locale.US, "%.2f", current.getValue()));
        } else {
            holder.chunkMassItemView.setText(R.string.nothing);
        }
    }

    void setChunks(List<Chunk> chunks) {
        this.chunks = chunks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (chunks != null) {
            return chunks.size();
        } else {
            return 0;
        }
    }
}
