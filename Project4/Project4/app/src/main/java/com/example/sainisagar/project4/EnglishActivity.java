package com.example.sainisagar.project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EnglishActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Singer: Eminem", "SUPERMAN"));
        songs.add(new Song("Singer: Alan Walker", "SING ME TO SLEEP"));
        songs.add(new Song("Singer: Ed Sheeran", "SHAPE OF YOU"));
        songs.add(new Song("Singer: Alan Walker", "FADED"));
        songs.add(new Song("Singer: Charlie Puth", "SEE YOU AGAIN"));
        songs.add(new Song("Singer: Akon", "BEAUTIFUL"));
        songs.add(new Song("Singer: Alan Walker", "ALONE"));
        songs.add(new Song("Singer: Eminem", "WTP"));
        songs.add(new Song("Singer: Akon", "DON'T MATTER"));
        songs.add(new Song("Singer: Maroon 5", "ANIMALS"));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(EnglishActivity.this , SharryActivity.class);
            startActivity( intent );
        }
    });
    }
}
