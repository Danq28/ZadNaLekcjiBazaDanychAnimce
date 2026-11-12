package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import android.widget.AdapterView;
import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity {
    private AnimeDataBase animeDataBase;

    private EditText edittytul,editocena;
    private Spinner spinnergatunek;
    private CheckBox checkBox;
    private Button button,buttonAktualizacja;
    private ListView listView;
    private ArrayAdapter<Anime> arrayAdapter;
    private List<Anime> animelista;
    private Anime kliknieteAnime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        buttonAktualizacja=findViewById(R.id.buttonAktualizuj);
        edittytul=findViewById(R.id.edittytul);
        editocena=findViewById(R.id.editocena);
        spinnergatunek=findViewById(R.id.spinnergatunek);
        checkBox=findViewById(R.id.checkBox);


        animeDataBase = Room.databaseBuilder(
                        getApplicationContext(),
                        AnimeDataBase.class,
                        "anime_db"
                )
                .allowMainThreadQueries()
                .build();
//        animeDataBase.zwrocAnimeDao().wstawDoBazy(
//                new Anime("Clannad", "Rom-kom,slice of life",9,true)
//        );
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Anime anime = new Anime(
                                edittytul.getText().toString(),
                                spinnergatunek.getSelectedItem().toString(),
                                Integer.parseInt(editocena.getText().toString()),
                                checkBox.isChecked()
                        );
                        animeDataBase.zwrocAnimeDao().wstawDoBazy(anime);
                        anime.add(anime);
                        arrayAdapter.notifyDataSetChanged();

                    }
                }
        );
        listView=findViewById(R.id.animelist);
        animelista=animeDataBase.zwrocAnimeDao().zwrocWszystkieAnime();
        arrayAdapter=new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                animelista);
        listView.setAdapter(arrayAdapter);
        //


        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        animeDataBase.zwrocAnimeDao().usunZBazy(animelista.get(i));
                        animelista.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknieteAnime = animelista.get(i);
                        edittytul.setText(kliknieteAnime.tytul);
                        checkBox.setChecked(kliknieteAnime.czyZakonczone);

                        ArrayList<String> gatunki = new ArrayList<String>(
                                Arrays.asList("rom-kom", "shounen", "seinen", "isekai")
                        );
                        spinnergatunek.setSelection(gatunki.indexOf(kliknieteAnime.gatunek));
                        editocena.setText(String.valueOf(kliknieteAnime.sredniRating));


                    }
                }
        );
        buttonAktualizacja.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (kliknieteAnime != null && !editocena.getText().toString().isEmpty()) {
                            kliknieteAnime.tytul = edittytul.getText().toString();
                            kliknieteAnime.gatunek = spinnergatunek.getSelectedItem().toString();
                            kliknieteAnime.czyZakonczone = checkBox.isChecked();
                            kliknieteAnime.sredniRating = Float.parseFloat(editocena.getText().toString());

                            animeDataBase.zwrocAnimeDao().zaktualizujAnime(kliknieteAnime);
                            animelista.clear();
                            animelista.addAll(animeDataBase.zwrocAnimeDao().zwrocWszystkieAnime());
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

}
