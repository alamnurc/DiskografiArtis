package com.example.diskografiartis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> dataArtis = new ArrayList<>();
    private RecyclerView rvArtis;
    private ArtisAdapter artisAdapter;
    private DBController dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvArtis = findViewById(R.id.rv_tampil_artis);
        dbHandler = new DBController(this);
        tampilDiskografi();
    }

    private void tampilDiskografi() {
        dataArtis = dbHandler.getAllArtis();
        artisAdapter = new ArtisAdapter(this, dataArtis);
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(MainActivity.this);
        rvArtis.setLayoutManager(layManager);
        rvArtis.setAdapter(artisAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_add) {
            Intent openadd = new Intent(getApplicationContext(), InputArtis.class);
            openadd.putExtra("CRUD", "INSERT");
            startActivity(openadd);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilDiskografi();
    }
}