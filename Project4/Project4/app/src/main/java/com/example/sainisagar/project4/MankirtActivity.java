package com.example.sainisagar.project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MankirtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("Single Track", "GALLAN MITTHIYAAN"));
        songs.add(new Song("Single Track", "JAIL"));
        songs.add(new Song("Single Track", "GANGLAND"));
        songs.add(new Song("Single Track", "DAANG"));
        songs.add(new Song("Single Track", "JATT DI CLIP"));
        songs.add(new Song("Single Track", "JATT DA BLOOD"));
        songs.add(new Song("Single Track", "SHEESHA"));
        songs.add(new Song("Single Track", "KOTHI"));
        songs.add(new Song("Single Track", "BADNAAM"));
        songs.add(new Song("Single Track", "KUWARI"));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MankirtActivity.this , EnglishActivity.class);
                startActivity( intent );
            }
        });

    }


}