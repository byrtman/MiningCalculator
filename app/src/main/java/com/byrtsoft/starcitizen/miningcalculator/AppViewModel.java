package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Chunk>> allChunks;
    private LiveData<List<OreAlloc>> currentAllocs;
    private LiveData<Chunk> currentChunk;

    public AppViewModel (Application application) {
        super(application);
        repository = new AppRepository(application);
        allChunks = repository.getAllChunks();
    }

    // Chunk data

    LiveData<List<Chunk>> getAllChunks() { return allChunks;}

    public int getAllChunksValue() {
        int result = 0;
        if (allChunks != null) {
            for (Chunk chunk : allChunks.getValue()) {
                result += chunk.getValue();
            }
        }
        return result;
    }

    public int getAllocsValue(int chunkId) {
        int result = 0;
        if (currentAllocs != null &&
                currentChunk != null &&
                currentChunk.getValue() != null &&
                currentChunk.getValue().getId() == chunkId) {
            for (OreAlloc alloc: currentAllocs.getValue()) {
                result += alloc.calculateValue(currentChunk.getValue().getMass());
            }
        }
        return result;
    }

    LiveData<Chunk> getLastChunk() {
        currentChunk = repository.getLastInsertedChunk();
        return currentChunk;
    }

    public void insertChunk(Chunk chunk) { repository.insertChunk(chunk);}
    public void updateChunk(Chunk chunk) { repository.updateChunk(chunk);}

    // OreAlloc data

    LiveData<List<OreAlloc>> getAllAllocs(int chunkId) {
        currentAllocs = repository.getAllAllocs(chunkId);
        return currentAllocs;
    }


    public void insertOreAlloc(OreAlloc alloc) { repository.insertOreAlloc(alloc);}

    // All data

    public void resetAllData() {
        repository.deleteAllOreAllocs();
        repository.deleteAllChunks();
    }

    public void resetChunkAllocation() {
        repository.deleteAllOreAllocs();
    }
}
