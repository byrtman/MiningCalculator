package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Currency;
import java.util.List;

public class ChunkListAdapter extends RecyclerView.Adapter<ChunkListAdapter.ChunkViewHolder> {

    class ChunkViewHolder extends RecyclerView.ViewHolder {
        private final TextView chunkItemView;

        private ChunkViewHolder (View itemView) {
            super(itemView);
            chunkItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private List<Chunk> chunks; // cahced copy of chunks

    ChunkListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @Override
    public ChunkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ChunkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChunkViewHolder holder, int position) {
        if (chunks != null) {
            Chunk current = chunks.get(position);
            holder.chunkItemView.setText(String.valueOf(current.getMass()));
        } else {
            holder.chunkItemView.setText("zippo!");
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
