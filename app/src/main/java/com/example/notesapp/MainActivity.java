package com.example.notesapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> notes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewNotes = findViewById(R.id.listViewNotes);
        Button addNoteBtn = findViewById(R.id.addNoteBtn);

        notes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listViewNotes.setAdapter(adapter);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNoteDialog();
            }
        });

        listViewNotes.setOnItemClickListener((parent, view, position, id)->{
            notes.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Notes Deleted", Toast.LENGTH_SHORT).show();
        });
    }

    private void showAddNoteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_note, null);
        EditText editTextNote = view.findViewById(R.id.editNote);

        builder.setView(view)
                .setTitle("Add Your Note")
                .setPositiveButton("Add", (dialog, which) -> {
                    String note = editTextNote.getText().toString();
                    if (!note.isEmpty()){
                        notes.add(note);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else{
                        Toast.makeText(MainActivity.this,   "Note Can not be Empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}

/*

git remote add origin https://github.com/ycvinay/NotesApp.git
git branch -M main
git push -u origin main

 */
