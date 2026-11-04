package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Anime.class}, version = 1)
public abstract class AnimeDataBase extends RoomDatabase {
    public abstract AnimeDAO zwrocAnimeDao();
}