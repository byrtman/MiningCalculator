package com.byrtsoft.starcitizen.miningcalculator;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private OreData[] mTableData;
    private int grandTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize app database
        mTableData = initData();
        AppDatabase db = Room.inMemoryDatabaseBuilder(this, AppDatabase.class).allowMainThreadQueries().build();
        OreDAO oreDb = db.getOreDAO();
        initializeDatabase(oreDb, mTableData);

        TextView view = findViewById(R.id.resultsTotalValue);
        view.setText(String.valueOf(grandTotal)+" "+getString(R.string.value));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), DefineChunkActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeDatabase(OreDAO oreDb, OreData[] data) {

        for (OreData currentData : data) {
            Ore ore = new Ore();
            ore.setName(currentData.name);
            ore.setPrice(currentData.price);
            ore.setInvDensity(currentData.invDensity);
            oreDb.insert(ore);
        }
    }

    private OreData[] initData() {
        return new OreData[]{
                new OreData("Quartz", 0.04, 1.4),
                new OreData("Bexalite", 79.09, 1.52),
                new OreData("Hepha..", 25.52, 1.72),
                new OreData("Agricium", 17.34, 0.68),
                new OreData("Laranite", 56.59, 2.0),
                new OreData("Aluminum", 1.76, 1.44),
                new OreData("Tungsten", 7.22, 1.88),

        };
    }


    private static class OreData {
        private String name;
        private double price;
        private double invDensity;

        private OreData(String name, double price, double invDensity) {
            this.name = name;
            this.price = price;
            this.invDensity = invDensity;
        }
    }

}