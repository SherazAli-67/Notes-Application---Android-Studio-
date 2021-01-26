package com.example.notesapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Notes_Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "Notes_Database";
    private static final String TABLE="Notes";

    private static final String COL1 = "Title";
    private static final String COL2 = "Description";
    private static final String COL3 = "Date";
    private static final String COL4 = "Time";

    private static final int VERSION = 1;

    public Notes_Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE '"+TABLE+"' ('"+COL1 +"' TEXT , '"+COL2 +"' TEXT , '"+COL3 +"' TEXT, '"+COL4+"' TEXT ) " ;
        db.execSQL(sql);
    }

    public long insert(String title, String description, String date, String time)
    {
        if(!(title.equals("") || description.equals("") || date.equals(""))) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, title);
            contentValues.put(COL2, description);
            contentValues.put(COL3, date);
            contentValues.put(COL4, time);

            long result = db.insert(TABLE, null, contentValues);
            return result;
        }else{
            return -1;
        }
    }

    public ArrayList<Notes> getNotes()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Notes> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM '"+TABLE+"'",null);
        if(cursor.moveToFirst());
        while (cursor.moveToNext())
        {
            String title = cursor.getString(cursor.getColumnIndex(COL1));
            String description =  cursor.getString(cursor.getColumnIndex(COL2));
            String date = cursor.getString(cursor.getColumnIndex(COL3));
            String time = cursor.getString(cursor.getColumnIndex(COL4));

            arrayList.add(new Notes(title,description,date,time));
        }
        return arrayList;
    }

    public long updateNotes(String title, String description, String date,String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, title);
        contentValues.put(COL2,description);
        contentValues.put(COL3,date);
        contentValues.put(COL4,time);

        long result = db.update(TABLE,contentValues,"title =?",new String[]{title});
       return result;
    }

    public long deleteNotes(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE,"title = ?",new String[]{title});
        return result;
    }

    public void deleteAllNotes()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
