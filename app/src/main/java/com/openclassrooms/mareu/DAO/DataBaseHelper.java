package com.openclassrooms.mareu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.openclassrooms.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static java.sql.Types.INTEGER;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String MEETING_TABLE = "MEETING_TABLE";
    private static final String COLUMN_ID = "MEETING_ID";
    public static final String COLUMN_MEETING_NAME = "MEETING_NAME";
    public static final String COLUMN_MEETING_DATE = "MEETING_DATE";
    public static final String COLUMN_MEETING_HOUR = "MEETING_HOUR";
    public static final String COLUMN_MEETING_ROOM = "COLUMN_MEETING_ROOM";
    private static final String COLUMN_MEETING_EMAILS = "COLUMN_MEETING_EMAILS";
    public static final String COLUMN_MEETING_COLOR = "COLUMN_MEETING_COLOR";

    public DataBaseHelper(@Nullable Context context) {
        super ( context, "Meeting.db", null, 1 );
    }

    // this is called the first time a db is accessed. There should be code here to create a new db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + MEETING_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MEETING_NAME + " TEXT, " + COLUMN_MEETING_DATE + " TEXT, " + COLUMN_MEETING_HOUR + " TEXT, " + COLUMN_MEETING_ROOM + " TEXT, " + COLUMN_MEETING_EMAILS + " TEXT, " + COLUMN_MEETING_COLOR + " INT)";
        db.execSQL ( createTableStatement );

    }

    // this is called if the db version number. It prevents previous users apps from breaking when you change the db design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues cv = new ContentValues ();

        cv.put ( COLUMN_MEETING_NAME, meeting.getMeetingName ());
        cv.put ( COLUMN_MEETING_DATE, meeting.getDate () );
        cv.put ( COLUMN_MEETING_HOUR, meeting.getHour () );
        cv.put ( COLUMN_MEETING_ROOM, meeting.getRoom () );
        cv.put ( COLUMN_MEETING_EMAILS, meeting.getEmails () );
        cv.put ( COLUMN_MEETING_COLOR,meeting.getColor () );

        long insert = db.insert ( MEETING_TABLE, null, cv );
        return insert != -1;
    }

    public boolean deleteOne(Meeting meeting){
        // find meeting in the db. if it find, delete it and return true.
        // if not found, return false.

        SQLiteDatabase db = this.getWritableDatabase ();
        String queryString = " DELETE FROM " + MEETING_TABLE + " WHERE " + COLUMN_ID + " = " + meeting.getId ();

        Cursor cursor = db.rawQuery ( queryString, null );

        if (cursor.moveToFirst ()){
            return true;
        }
        else {
            return false;
        }
    }

    public List<Meeting> getEveryMeeting(){
        List<Meeting> returnList = new ArrayList<> ();

        // get data from db

        String queryString = " SELECT * FROM " + MEETING_TABLE;
        Log.d ("tagi","queryString : "+ queryString);

        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ( queryString,null);

        if (cursor.moveToFirst ()){
            // loop through the cursor (result set) and create new meeting objects. Put them into the return list.

            do {
                long meetingID = cursor.getInt ( 0 );
                String meetingName = cursor.getString ( 1 );
                String  meetingDate = cursor.getString ( 2 );
                String meetingHour = cursor.getString ( 3 );
                String  meetingRoom = cursor.getString ( 4 );
                String meetingEmails = cursor.getString ( 5 );
                int meetingColor = cursor.getInt ( 6 );

                Meeting newMeeting = new Meeting ( meetingID,meetingName,meetingDate,meetingHour,meetingRoom,meetingEmails,meetingColor );
                returnList.add ( newMeeting );
            } while (cursor.moveToNext ());
        }
        else {
            Log.d ("tagii","cursor else: "+ cursor);


            // failure. do not add anything to the list.
        }
        //close both the cursor and the db when done.
        cursor.close ();
        db.close ();
        return returnList;

    }

}

