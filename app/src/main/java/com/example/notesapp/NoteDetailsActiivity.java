package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import io.grpc.okhttp.internal.Util;

public class NoteDetailsActiivity extends AppCompatActivity {
EditText notes_title_text,notes_content_text;
ImageView save_note_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details_actiivity);
        notes_content_text=findViewById(R.id.notes_content_text);
        notes_title_text=findViewById(R.id.notes_title_text);
        save_note_button=findViewById(R.id.save_note_button);
        
        save_note_button.setOnClickListener(v->saveNote());
    }

    private void saveNote() {
        String noteTitle=notes_title_text.getText().toString();
        String noteContent=notes_content_text.getText().toString();

        if(noteTitle==null||noteTitle.isEmpty())
        {
            notes_title_text.setError("Title is required!");
            return;
        }
        if(noteContent==null||noteContent.isEmpty())
        {
            notes_content_text.setError("Content is required!");
            return;
        }
        Note note=new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());
        SaveNoteToFirebase(note);


        
    }
    void SaveNoteToFirebase(Note note)
    {
        DocumentReference documentReference;
        documentReference=Utility.getCollectionReferenceForNotes().document();
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Utility.showToast(NoteDetailsActiivity.this,"Note added Successfully");
                    finish();
                }
                else
                {
                    Utility.showToast(NoteDetailsActiivity.this,"Failed while adding  Note");
                }
            }
        });
    }
}