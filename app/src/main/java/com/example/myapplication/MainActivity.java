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
public class MainActivity extends AppCompatActivity {
    private AnimeDataBase animeDataBase;

    private EditText edittytul,editocena;
    private Spinner spinnergatunek;
    private CheckBox checkBox;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
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
                        AnimeDataBase.
                    }
                }
    }
}