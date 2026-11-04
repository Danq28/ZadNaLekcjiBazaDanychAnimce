package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimeDAO {

    @Insert
    public void wstawDoBazy(Anime anime);

    @Insert
    public void wstawWiecejAnime(Anime... animeList);

    @Delete
    public void usunZBazy(Anime anime);

    @Update
    public void zaktualizujAnime(Anime anime);

    @Query("SELECT * FROM anime")
    public List<Anime> zwrocWszystkieAnime();

    @Query("SELECT tytul FROM anime WHERE sredni_rating > :rating")
    public List<String> zwrocTytulyZRatingiemWiekszymNiz(float rating);
}