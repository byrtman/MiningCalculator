package com.byrtsoft.starcitizen.miningcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byrtsoft.starcitizen.db.Chunk;

import java.util.List;
import java.util.Locale;

public class ChunkListAdapter extends RecyclerView.Adapter<ChunkListAdapter.ChunkViewHolder> {

    class ChunkViewHolder extends RecyclerView.ViewHolder {
        private final TextView chunkMassItemView;
        private final TextView chunkValueItemView;

        private ChunkViewHolder (View itemView) {
            super(itemView);
            chunkMassItemView = itemView.findViewById(R.id.columnOneTextView);
            chunkValueItemView = itemView.findViewById(R.id.columnThreeTextView);
        }
    }

    private final LayoutInflater inflater;
    private List<Chunk> chunks; // cached copy of chunks

    ChunkListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public ChunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_two_column, parent, false);
        return new ChunkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChunkViewHolder holder, int position) {
        if (chunks != null) {
            Chunk current = chunks.get(position);
//            holder.chunkMassItemView.setText(String.format(Locale.US, "%.2f %s", current.getMass(), "Kg"));
            holder.chunkMassItemView.setText(String.format(Locale.US, "%s", current.getName()));
            holder.chunkValueItemView.setText(String.format(Locale.US, "%s %.0f", "$", current.getValue()));
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
