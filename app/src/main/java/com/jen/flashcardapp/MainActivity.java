package com.jen.flashcardapp;

import android.content.Intent;
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

        TextView question = findViewById(R.id.flashcardQuestion);
        TextView answer = findViewById(R.id.flashcardAnswer);

        //When question is clicked, show correct answer
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setVisibility(View.GONE);
                answer.setVisibility((View.VISIBLE));
            }
        });

        //When answer is clicked, show question
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setVisibility(View.GONE);
                question.setVisibility((View.VISIBLE));
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
                ((TextView) (findViewById(R.id.flashcardAnswer))).setText(correctAns);
            }
        }
    }
}