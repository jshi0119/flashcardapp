package com.jen.flashcardapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView correctAns = findViewById(R.id.answer1);
        TextView choice2 = findViewById(R.id.answer2);
        TextView choice3 = findViewById(R.id.answer3);
        TextView choice4 = findViewById(R.id.answer4);

        correctAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAns.setTextColor(Color.parseColor("#00A000"));
            }
        });

        wrong(choice2, correctAns);
        wrong(choice3, correctAns);
        wrong(choice4, correctAns);

        ((ImageView)findViewById(R.id.refreshIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAns.setTextColor(Color.parseColor("#747474"));
                choice2.setTextColor(Color.parseColor("#747474"));
                choice3.setTextColor(Color.parseColor("#747474"));
                choice4.setTextColor(Color.parseColor("#747474"));
            }
        });

        ((ImageView)findViewById(R.id.visibleIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAns.setVisibility(View.INVISIBLE);
                choice2.setVisibility(View.INVISIBLE);
                choice3.setVisibility(View.INVISIBLE);
                choice4.setVisibility(View.INVISIBLE);

                ((ImageView)findViewById(R.id.visibleIcon)).setVisibility(View.INVISIBLE);
                ((ImageView)findViewById(R.id.invisibleIcon)).setVisibility(View.VISIBLE);
            }
        });

        ((ImageView)findViewById(R.id.invisibleIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAns.setVisibility(View.VISIBLE);
                choice2.setVisibility(View.VISIBLE);
                choice3.setVisibility(View.VISIBLE);
                choice4.setVisibility(View.VISIBLE);

                ((ImageView)findViewById(R.id.visibleIcon)).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.invisibleIcon)).setVisibility(View.INVISIBLE);
            }
        });
    }

    private static void wrong(TextView wrongChoice, TextView rightChoice) {
        wrongChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightChoice.setTextColor(Color.parseColor("#00A000"));
                wrongChoice.setTextColor(Color.parseColor("#FF0000"));
            }
        });
    }
}