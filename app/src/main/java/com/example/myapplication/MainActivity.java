package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
public class MainActivity extends AppCompatActivity {
    private AnimeDataBase animeDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        animeDataBase = Room.databaseBuilder(
                        getApplicationContext(),
                        AnimeDataBase.class,
                        "anime_db"
                )
                .allowMainThreadQueries()
                .build();
        animeDataBase.zwrocAnimeDao().wstawDoBazy(
                new Anime("Clannad", "Rom-kom,slice of life",9,true)
        );
    }
}