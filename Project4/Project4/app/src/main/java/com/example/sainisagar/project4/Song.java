package com.example.sainisagar.project4;

/**
 * Created by Saini Sagar on 2017-12-12.
 */

public class Song {
    private String mSongName;
    private String mArtistName;

    public Song(String ArtistName, String SongName) {
        mArtistName = ArtistName;
        mSongName = SongName;
    }
    public String getDefaultTranslation(){
        return mArtistName;
    }
    public String getMiwokTranslation() {
        return mSongName;
    }
}
