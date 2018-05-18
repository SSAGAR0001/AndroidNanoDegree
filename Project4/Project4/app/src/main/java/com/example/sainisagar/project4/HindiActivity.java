package com.example.sainisagar.project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.ArrayList;

public class HindiActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("Singer: Arijit Singh", "PHIR BHI TUMKO CHAHUNGA"));
        songs.add(new Song("Singer: Shreya Ghoshal", "THODI DER"));
        songs.add(new Song("Singer: Sunidhi Chauhan", "KAMLI"));
        songs.add(new Song("Singer: Atif Aslam", "TERE SANG YAARA"));
        songs.add(new Song("Singer: Babbu Maan", "KINARA"));
        songs.add(new Song("Singer: Arijit Singh", "TUM HI HO"));
        songs.add(new Song("Singer: Diljit Dosanjh", "MAIN TAAN AIDAAN HI NACHNA"));
        songs.add(new Song("Singer: Ankit Tiwari", "TAY HAI"));
        songs.add(new Song("Singer: Atif Aslam", "JEENA JEENA"));
        songs.add(new Song("Singer: Ankit Tiwari", "SUN RAHA HAI NA TU"));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(HindiActivity.this , MankirtActivity.class);
            startActivity( intent );
        }
    });
    }
}
