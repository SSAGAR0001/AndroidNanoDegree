package com.example.sainisagar.project4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.AdapterView;

public class SharryActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Album: Aate Di Chiri", "AATE DI CHIRI"));
        songs.add(new Song("Album: Aate Di Chiri", "BHULL JAYIN NA"));
        songs.add(new Song("Album: Aate Di Chiri", "YAAR ANMULLE"));
        songs.add(new Song("Album: Aate Di Chiri", "KUDIYAAN TE BUSAAN"));
        songs.add(new Song("Album: Aate Di Chiri", "YENKNE"));
        songs.add(new Song("Album: Meri Bebe", "MERI BEBE"));
        songs.add(new Song("Album: Meri Bebe", "TERE GATE CH JATTI NE"));
        songs.add(new Song("Album: Meri Bebe", "VISA"));
        songs.add(new Song("Album: Meri Bebe", "1100 MOBILE"));
        songs.add(new Song("Album: Meri Bebe", "IKK BOTTLE"));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SharryActivity.this , HindiActivity.class);
                startActivity( intent );
            }
        });
    }
}
