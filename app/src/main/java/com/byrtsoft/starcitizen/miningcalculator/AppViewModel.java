package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Chunk>> allChunks;
    private LiveData<List<OreAlloc>> allAllocs;

    public AppViewModel (Application application) {
        super(application);
        repository = new AppRepository(application);
        allChunks = repository.getAllChunks();
//        allAllocs = repository.
    }

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

    public int getAllAllocsValue() {
        int result = 0;
        if (allChunks != null) {
            for (Chunk chunk : allChunks.getValue()) {
                result += chunk.getValue();
            }
        }
        return result;
    }

    public void insertChunk(Chunk chunk) { repository.insertChunk(chunk);}
    public void insertOreAlloc(OreAlloc alloc) { repository.insertOreAlloc(alloc);}
}
