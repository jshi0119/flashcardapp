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
                finish();
            }
        });

        //When save icon clicked,  inputs of the two EditText fields
        // are retrieved and passed back to main activity to be displayed
        ((ImageView)findViewById(R.id.saveIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.questionInput)).getText().toString();
                String correctAns = ((EditText) findViewById(R.id.answerInput1)).getText().toString();
                String wrongAns1 = ((EditText) findViewById(R.id.answerInput2)).getText().toString();
                String wrongAns2 = ((EditText) findViewById(R.id.answerInput3)).getText().toString();
                String wrongAns3 = ((EditText) findViewById(R.id.answerInput4)).getText().toString();

                Intent data = new Intent();
                data.putExtra("question", question);
                data.putExtra("correctAns", correctAns);
                data.putExtra("wrongAns1", wrongAns1);
                data.putExtra("wrongAns2", wrongAns2);
                data.putExtra("wrongAns3", wrongAns3);

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}