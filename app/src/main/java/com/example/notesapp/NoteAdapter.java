package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note,NoteAdapter.NoteViewHolder> {

Context context;
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.notes_title_text_view.setText(note.title);
        holder.notes_content_text_view.setText(note.content);
        holder.notes_time_text_view.setText(Utility.timestampToString(note.timestamp));

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView notes_title_text_view,notes_content_text_view,notes_time_text_view;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            notes_title_text_view=itemView.findViewById(R.id.notes_time_text_view);
            notes_content_text_view=itemView.findViewById(R.id.notes_content_text_view);
            notes_time_text_view=itemView.findViewById(R.id.notes_time_text_view);


        }
    }
}
