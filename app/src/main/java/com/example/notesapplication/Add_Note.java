package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Add_Note extends AppCompatActivity {

    EditText add_note_title, add_note_description,add_note_date,add_note_time;
    Notes_Database database;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__note);

        add_note_title = findViewById(R.id.add_note_title);
        add_note_description = findViewById(R.id.add_note_description);
        add_note_date = findViewById(R.id.add_note_date);
        add_note_time = findViewById(R.id.add_note_time);
        database = new Notes_Database(this);

    }

    public void onCreateNoteClick(View view)
    {
        long result = database.insert(add_note_title.getText().toString(),add_note_description.getText().toString(),
                add_note_date.getText().toString(),add_note_time.getText().toString());

        if(result == -1)
            Toast.makeText(this, "Data is missing", Toast.LENGTH_SHORT).show();

        else if(result > 0)
        {
            startActivity(new Intent(Add_Note.this,MainActivity.class));
        }
        else
            Toast.makeText(this, "Error: "+result, Toast.LENGTH_SHORT).show();
    }

    public void onCancelClick(View view)
    {
        add_note_title.setText("");
        add_note_description.setText("");
        startActivity(new Intent(Add_Note.this,MainActivity.class));
    }

    public void onTimeClickListener(View view)
    {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(Add_Note.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                add_note_time.setText(hour+":"+minute);
            }
        },hour,minute,false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void onDateClick(View view)
    {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                add_note_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}