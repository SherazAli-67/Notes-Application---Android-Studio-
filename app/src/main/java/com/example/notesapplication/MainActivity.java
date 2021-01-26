package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView notes;
    FloatingActionButton add_note;
    ArrayList<Notes> arrayList;
    Notes_Database database;
    ImageView author;
    TextView hello;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        notes = findViewById(R.id.notes);
        add_note = findViewById(R.id.add_note);
        database = new Notes_Database(this);
        arrayList = database.getNotes();
        author =  findViewById(R.id.author);
        hello = findViewById(R.id.hello);
        sharedPreferences = getSharedPreferences("NotesSharedData",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        hello.setText("Hello "+sharedPreferences.getString("UserName",""));
        Listview_Adapter adapter = new Listview_Adapter(this, arrayList);
        notes.setAdapter(adapter);

        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = arrayList.get(position).getTitle();
                String descript = arrayList.get(position).getDescription();
                String date = arrayList.get(position).getDate();
                String time = arrayList.get(position).getTime();

                Intent moveToUpdate = new Intent(MainActivity.this, Update_Or_Delete_Note.class);
                moveToUpdate.putExtra("Title", title);
                moveToUpdate.putExtra("Descript", descript);
                moveToUpdate.putExtra("Date", date);
                moveToUpdate.putExtra("Time", time);
                startActivity(moveToUpdate);

            }
        });

    }

    public void onClickAddNote(View view) {
        Intent move_to_addNote = new Intent(MainActivity.this, Add_Note.class);
        startActivity(move_to_addNote);
    }

    public void onAuthorClick(View view)
    {
        startActivity(new Intent(MainActivity.this,AuthorActivity.class));

    }
}