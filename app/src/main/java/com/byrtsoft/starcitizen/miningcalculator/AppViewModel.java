package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Chunk>> allChunks;

    public AppViewModel (Application application) {
        super(application);
        repository = new AppRepository(application);
        allChunks = repository.getAllChunks();
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

    LiveData<List<OreAlloc>> getAllAllocs(int chunkId) { return repository.getAllAllocs(chunkId);}


    public void insertChunk(Chunk chunk) { repository.insertChunk(chunk);}
    public void insertOreAlloc(OreAlloc alloc) { repository.insertOreAlloc(alloc);}

    public void resetAllData() {
        repository.deleteAllOreAllocs();
        repository.deleteAllChunks();
    }
}
