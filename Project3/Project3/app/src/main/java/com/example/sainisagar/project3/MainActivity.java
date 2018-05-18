package com.example.sainisagar.project3;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private int calculateScore1(int score, boolean first, boolean second, boolean third, boolean fourth) {
        if(first == true) score++;
        if(second == true) score++;
        if(third == true) score++;
        if(fourth == true) score++;
        return score;
    }
    private int calculateScore2(int score, boolean fifth, boolean sixth1, boolean sixth2, boolean sixth3) {
        if(fifth == true) score++;
        if(sixth1 == true && sixth2 == true && sixth3 == true) score += 2;
        else score += 0;
        return score;
    }
    int checkAnswerOne()
    {
        EditText editText = (EditText) findViewById( R.id.name_view );
        if( editText.getText().toString().length() == 0 )
            return 0;

        if( Integer.valueOf( editText.getText().toString() )== 1947 )
            return 1;
        return 0;
    }

    public void submit(View view) {
        int score = 0;
        score = checkAnswerOne();



        RadioButton first_question = (RadioButton) findViewById(R.id.image1);
        boolean first = first_question.isChecked();

        RadioButton second_question = (RadioButton) findViewById(R.id.fatherc);
        boolean second = second_question.isChecked();

        RadioButton third_question = (RadioButton) findViewById(R.id.fatherg);
        boolean third = third_question.isChecked();

        RadioButton fourth_question = (RadioButton) findViewById(R.id.fatherh);
        boolean fourth = fourth_question.isChecked();
        score = calculateScore1(score, first, second, third, fourth);

        RadioButton fifth_question = (RadioButton) findViewById(R.id.dairyf);
        boolean fifth = fifth_question.isChecked();

        CheckBox sixth1_question = (CheckBox) findViewById(R.id.demo1);
        boolean sixth1 = sixth1_question.isChecked();
        CheckBox sixth2_question = (CheckBox) findViewById(R.id.demo2);
        boolean sixth2 = sixth2_question.isChecked();
        CheckBox sixth3_question = (CheckBox) findViewById(R.id.demo3);
        boolean sixth3 = sixth3_question.isChecked();
        score = calculateScore2(score, fifth, sixth1, sixth2, sixth3);

        String message = createOrderSummary(score);
        displayMessage(message);

    }
    private String createOrderSummary(int score) {
        String priceMessage = "YOUR TEST RESULTS ARE READY!";
        priceMessage += "\nTOTAL SCORE: " + score;
        priceMessage += "\nTHANK YOU FOR TAKING THE TEST!";
        priceMessage += "\nKEEP IMPROVING!";
        return priceMessage;
    }

    private void displayMessage(String message) {
        Toast.makeText(this, message  , Toast.LENGTH_SHORT ).show();
    }
}
