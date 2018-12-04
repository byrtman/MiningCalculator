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

    public void insertChunk(Chunk chunk) { repository.insertChunk(chunk);}
    public void insertOre(Ore ore) { repository.insertOre(ore);}
}
