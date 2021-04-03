package com.jen.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        TextView question = findViewById(R.id.flashcardQuestion);
        TextView answer = findViewById(R.id.flashcardAnswer);

        if (allFlashcards != null && allFlashcards.size() > 0) {
            question.setText(allFlashcards.get(0).getQuestion());
            answer.setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.nextIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() == 0) {
                    return;
                }

                currentCardDisplayedIndex++;

                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(question,"You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                }

                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                question.setText(flashcard.getAnswer());
                answer.setText(flashcard.getQuestion());

                answer.setVisibility(View.VISIBLE);
                question.setVisibility(View.GONE);
            }
        });

        //When answer is clicked, show question
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setVisibility(View.GONE);
                question.setVisibility(View.VISIBLE);
            }
        });

        //When question is clicked, show correct answer
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setVisibility(View.GONE);
                answer.setVisibility(View.VISIBLE);
            }
        });

        ((ImageView)findViewById(R.id.addIcon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
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

                ((TextView) findViewById(R.id.flashcardQuestion)).setText(question);
                ((TextView) findViewById(R.id.flashcardAnswer)).setText(correctAns);

                flashcardDatabase.insertCard(new Flashcard(question, correctAns));
                allFlashcards = flashcardDatabase.getAllCards();
            }
        }
    }
}