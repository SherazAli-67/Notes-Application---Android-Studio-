package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Update_Or_Delete_Note extends AppCompatActivity {

    TextView title;
    TextView description, date, time;
    String getTitle, getDescript,getDate,getTime ="";
    Bundle bundle;
    Notes_Database database = new Notes_Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__or__delete__note);
        title = findViewById(R.id.update_note_title);
        description = findViewById(R.id.update_note_description);
        date =  findViewById(R.id.update_note_date);
        time = findViewById(R.id.update_note_time);

        bundle = getIntent().getExtras();

        getTitle = bundle.getString("Title");
        getDescript = bundle.getString("Descript");
        getDate = bundle.getString("Date");
        getTime = bundle.getString("Time") ;

        title.setText(getTitle);
        description.setText(getDescript);
        date.setText(getDate);
        time.setText(getTime);
    }

    public void onUpdateNoteClick(View view)
    {
        long result = database.updateNotes(title.getText().toString(),description.getText().toString(),
                date.getText().toString(),time.getText().toString());
        if(result > 0)
        {
            Toast.makeText(this, "Updated Successfully !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Update_Or_Delete_Note.this,MainActivity.class));
        }
        else
            Toast.makeText(this, "Error in update: "+result, Toast.LENGTH_SHORT).show();
    }

    public void onDeleteNoteClick(View view)
    {
        long result = database.deleteNotes(getTitle);
        if(result > -1)
        {
            Toast.makeText(this, "Deleted Successfully !"+result, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Update_Or_Delete_Note.this,MainActivity.class));
        }
        else
            Toast.makeText(this, "Error in Delete: "+result, Toast.LENGTH_SHORT).show();
    }
}