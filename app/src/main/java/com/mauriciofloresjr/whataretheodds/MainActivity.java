package com.mauriciofloresjr.whataretheodds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String ODDS_GUESS = "odds_guess";
    private static final String ODDS_CHANCE = "odds_chance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnFunc(View view) {

        //Creating toasts for error handling
        Toast numError = Toast.makeText(this, "Numbers cannot be lower than 1!", Toast.LENGTH_SHORT);
        Toast guessOutOfRange = Toast.makeText(this,"Guess cannot exceed odds!", Toast.LENGTH_SHORT);
        Toast numOutOfRange = Toast.makeText(this,"Your inputs cannot go over 100,000!", Toast.LENGTH_SHORT);
        Toast inputMissing = Toast.makeText(this,"You must put a number in every field!", Toast.LENGTH_SHORT);

        //Finding all text views for user input
        EditText oddsChance = findViewById(R.id.oddsChanceTV);
        EditText oddsGuess = findViewById(R.id.oddsGuessTV);

        //Creating the intent to switch activities
        Intent results = new Intent(this, ResultActivity.class);

        //Transfers user input strings to integers to prepare for data transfer to results activity
        String oddsChanceStr = oddsChance.getText().toString();
        int oddsChanceNum;
        if(oddsChanceStr.isEmpty()) {
            inputMissing.show();
        } else {
            oddsChanceNum = Integer.parseInt(oddsChanceStr);
            String oddsGuessStr = oddsGuess.getText().toString();
            int oddsGuessNum;
            if (oddsGuessStr.isEmpty()) {
                inputMissing.show();
            } else {
                oddsGuessNum = Integer.parseInt(oddsGuessStr);

                //Setting if statement to check if user has numbers greater than 1
                if (oddsGuessNum < 1 || oddsChanceNum < 1) {
                    numError.show();
                 } else if (oddsGuessNum > oddsChanceNum) {
                    guessOutOfRange.show();
                } else if (oddsGuessNum > 100000 || oddsChanceNum > 100000) {
                    numOutOfRange.show();
                } else {
                    results.putExtra(ODDS_CHANCE, oddsChanceNum);
                    results.putExtra(ODDS_GUESS, oddsGuessNum);
                    startActivity(results);
                }
            }
        }
    }
}
