package com.jen.flashcardapp;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class FlashcardDatabase {
    private final com.jen.flashcardapp.AppDatabase db;

    FlashcardDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                com.jen.flashcardapp.AppDatabase.class, "flashcard-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public void initFirstCard() {
        if (db.flashcardDao().getAll().isEmpty()) {
            insertCard(new com.jen.flashcardapp.Flashcard("Who is the 44th President of the United States", "Barack Obama"));
        }
    }

    public List<com.jen.flashcardapp.Flashcard> getAllCards() {
        return db.flashcardDao().getAll();
    }

    public void insertCard(com.jen.flashcardapp.Flashcard flashcard) {
        db.flashcardDao().insertAll(flashcard);
    }

    public void deleteCard(String flashcardQuestion) {
        List<com.jen.flashcardapp.Flashcard> allCards = db.flashcardDao().getAll();
        for (com.jen.flashcardapp.Flashcard f : allCards) {
            if (f.getQuestion().equals(flashcardQuestion)) {
                db.flashcardDao().delete(f);
            }
        }
    }

    public void updateCard(com.jen.flashcardapp.Flashcard flashcard) {
        db.flashcardDao().update(flashcard);
    }

    public void deleteAll() {
        for (com.jen.flashcardapp.Flashcard f : db.flashcardDao().getAll()) {
            db.flashcardDao().delete(f);
        }
    }
}
