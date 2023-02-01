package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
FloatingActionButton add_note_button;
RecyclerView recyclerView;
ImageView menu_button;
NoteAdapter noteAdapter;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_note_button=findViewById(R.id.add_note_button);
        recyclerView=findViewById(R.id.recycler_view);
        menu_button=findViewById(R.id.menu_button);

        add_note_button.setOnClickListener(v->startActivity(new Intent(MainActivity.this,NoteDetailsActiivity.class)));
        menu_button.setOnClickListener(v->showMenu());
        //setupRecyclerView();
    }

    private void setupRecyclerView() {
        Query query=Utility.getCollectionReferenceForNotes().orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options=new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class).build();
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //noteAdapter=new NoteAdapter(options,this);
        //recyclerView.setAdapter(noteAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }

    private void showMenu() {
    }
}