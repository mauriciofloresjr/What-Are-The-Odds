package com.mauriciofloresjr.whataretheodds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Half;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private static final String ODDS_GUESS = "odds_guess";
    private static final String ODDS_CHANCE = "odds_chance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        showResults();
    }

    public void showResults(){
        //Setting vars for storage and taking numbers from user input
        int oddsChanceNum = getIntent().getIntExtra(ODDS_CHANCE, 0);
        int oddsGuessNum = getIntent().getIntExtra(ODDS_GUESS, 0);

        //Finding different TextViews
        TextView resultsTitleTV2 = findViewById(R.id.resultsTitleTV2);
        TextView resultsTitleTV4 = findViewById(R.id.resultsTitleTV4);
        TextView resultsTV = findViewById(R.id.resultsTV);
        TextView statView = findViewById(R.id.statView1);

        //Creating a decimal format for formatting percentage number
        DecimalFormat precision = new DecimalFormat("###.###");

        //Creating sounds
        MediaPlayer victorySnd = MediaPlayer.create(this, R.raw.success_sound);
        MediaPlayer failureSnd = MediaPlayer.create(this, R.raw.failure_sound);

        //Creating the random number generator
        Random random = new Random();
        int randomNum;
        randomNum = random.nextInt(oddsChanceNum) + 1;
        String randomNumStr = String.valueOf(randomNum);

        //Updating TextViews with results and user inputs
        resultsTitleTV2.setText(getString(R.string.resultsText2, oddsChanceNum));
        resultsTitleTV4.setText(getString(R.string.resultsText4,oddsGuessNum));
        resultsTV.setText(randomNumStr);

        //Creating a percentage calculator
        double percentage = (1.000f / oddsChanceNum) * 100;

        //Updating stats view
        statView.setText(getString(R.string.percentOdds,precision.format(percentage)));

        //Creating victory case
        if (oddsGuessNum == randomNum) {
            victorySnd.start();
        } else {
            failureSnd.start();
        }
    }

    //Code for the back button
    public void backBtn(View v){
        finish();
    }
}
