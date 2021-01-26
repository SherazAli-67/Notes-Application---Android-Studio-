package com.example.notesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AuthorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView deleteAllNotes, update, rate,logout;
    Notes_Database database =  new Notes_Database(this);
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        deleteAllNotes = findViewById(R.id.deleteAllNotes);
        update = findViewById(R.id.update);
        rate = findViewById(R.id.rate);
        logout = findViewById(R.id.logout);

        deleteAllNotes.setOnClickListener(this);
        update.setOnClickListener(this);
        rate.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.deleteAllNotes:
                database.deleteAllNotes();
                startActivity(new Intent(AuthorActivity.this,MainActivity.class));
                break;

            case R.id.update:
                startActivity(new Intent(AuthorActivity.this,MainActivity.class));
                break;

            case R.id.rate:
                startActivity(new Intent(AuthorActivity.this,MainActivity.class));
                break;

            case R.id.logout:
                progressDialog.show();
                SystemClock.sleep(2000);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AuthorActivity.this,SplashActivity.class));
                progressDialog.dismiss();
                startActivity(new Intent(AuthorActivity.this,SplashActivity.class));
                break;



        }
    }
}