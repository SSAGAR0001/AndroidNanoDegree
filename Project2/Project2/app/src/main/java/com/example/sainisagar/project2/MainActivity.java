package com.example.sainisagar.project2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int scoreA = 0;
    int scoreB = 0;

    /* increase the score of team A by 5 points*/
    public void plus_5A(View view) {
        scoreA += 5;
        displayForTeamA(scoreA);
    }

    /* increase the score of team A by 3 points*/
    public void plus_3A(View view) {
        scoreA += 3;
        displayForTeamA(scoreA);
    }

    /* increase the score of team A by 2 points*/
    public void plus_2A(View view) {
        scoreA += 2;
        displayForTeamA(scoreA);
    }

    /* display function for score text view of team A*/
    public void displayForTeamA(int scoreA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreA));
    }

    /* increase the score of team B by 5 points*/
    public void plus_5B(View view) {
        scoreB += 5;
        displayForTeamB(scoreB);
    }

    /* increase the score of team B by 3 points*/
    public void plus_3B(View view) {
        scoreB += 3;
        displayForTeamB(scoreB);
    }

    /* increase the score of team B by 2 points*/
    public void plus_2B(View view) {
        scoreB += 2;
        displayForTeamB(scoreB);
    }

    /* display function for score text view of team B*/
    public void displayForTeamB(int scoreB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreB));
    }

    /* resets the scores for both teams back to 0*/
    public void reset(View view) {
        scoreA = 0;
        scoreB = 0;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }
}