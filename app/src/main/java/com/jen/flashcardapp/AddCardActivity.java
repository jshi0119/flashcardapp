package com.jen.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        //When cancel icon clicked, return to main activity
        ((ImageView)findViewById(R.id.cancelIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "cancel";
                Intent data = new Intent();
                data.putExtra("question", question);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        //When save icon clicked,  inputs of the two EditText fields
        // are retrieved and passed back to main activity to be displayed
        ((ImageView)findViewById(R.id.saveIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.questionInput)).getText().toString();
                String correctAns = ((EditText) findViewById(R.id.answerInput)).getText().toString();

                Intent data = new Intent();
                data.putExtra("question", question);
                data.putExtra("correctAns", correctAns);

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}