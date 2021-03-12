package com.jen.flashcardapp;

import android.content.Intent;
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

        //When correct answer is selected, change correct answer font color to green
        correctAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAns.setTextColor(Color.parseColor("#00A000"));
            }
        });

        //When incorrect answer is selected
        wrong(choice2, correctAns);
        wrong(choice3, correctAns);
        wrong(choice4, correctAns);

        //When refresh icon is clicked, return all answer choices to original state (black font)
        ((ImageView)findViewById(R.id.refreshIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAns.setTextColor(Color.parseColor("#747474"));
                choice2.setTextColor(Color.parseColor("#747474"));
                choice3.setTextColor(Color.parseColor("#747474"));
                choice4.setTextColor(Color.parseColor("#747474"));
            }
        });

        //When visibility icon is clicked, show all answer choices
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

        //When invisibility icon is clicked, hide all answer choices
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

        ((ImageView)findViewById(R.id.addIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (!data.getExtras().getString("question").equals("cancel")) {
                String question = data.getExtras().getString("question");
                String correctAns = data.getExtras().getString("correctAns");
                String wrongAns1 = data.getExtras().getString("wrongAns1");
                String wrongAns2 = data.getExtras().getString("wrongAns2");
                String wrongAns3 = data.getExtras().getString("wrongAns3");

                ((TextView) (findViewById(R.id.flashcardQuestion))).setText(question);
                ((TextView) (findViewById(R.id.answer1))).setText(correctAns);
                ((TextView) (findViewById(R.id.answer2))).setText(wrongAns1);
                ((TextView) (findViewById(R.id.answer3))).setText(wrongAns2);
                ((TextView) (findViewById(R.id.answer4))).setText(wrongAns3);
            }
        }
    }


    /**
     * Helper method for when the incorrect answer is clicked.
     * Change font color of selected incorrect answer to red
     * and reveal correct answer by changing correct answer font to green.
     *
     * @param wrongChoice the incorrect choice that was selected
     * @param rightChoice the correct answer choice
     */
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