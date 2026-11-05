    package com.example.myapplication;

    import androidx.room.ColumnInfo;
    import androidx.room.Entity;
    import androidx.room.PrimaryKey;

    @Entity(tableName = "anime")
    public class Anime {
        @PrimaryKey(autoGenerate = true)
        public int id;

        public String tytul;

        public String gatunek;

        @ColumnInfo(name = "sredni_rating")
        public float sredniRating;

        public boolean czyZakonczone;


        public Anime(String tytul, String gatunek, float sredniRating, boolean czyZakonczone) {
            this.tytul = tytul;
            this.gatunek = gatunek;
            this.sredniRating = sredniRating;
            this.czyZakonczone = czyZakonczone;
        }

        public Anime() {
        }

        @Override
        public String toString() {
            return
                    "gatunek: " + gatunek +", "  +"\n"+
                    "tytul: " + tytul  +", "+"\n"+
                    "sredniRating: " + sredniRating +", "+"\n"+
                    "czyZakonczone: " + czyZakonczone;
                    };
        }

