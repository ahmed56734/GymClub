package com.gym.gymclub.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.gym.gymclub.data.GymContract.PlayerEntry;
import com.gym.gymclub.data.GymContract.CoachEntry;
import com.gym.gymclub.data.GymContract.GameEntry;
import com.gym.gymclub.data.GymContract.GamesTrainersEntry;
import com.gym.gymclub.data.GymContract.ProgressEntry;
import com.gym.gymclub.data.GymContract.EnrollmentEntry;

/**
 * Created by ahmed on 5/8/17.
 */

public class GymDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "gym.db";
    static final int DATABASE_VERSION = 4;

    GymDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_PLAYER_TABLE = "CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +

                PlayerEntry._ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlayerEntry.COLUMN_NAME      + " VARCHAR ( 40 ) NOT NULL, " +
                PlayerEntry.COLUMN_TELEPHONE + " VARCHAR ( 15 ) NOT NULL, " +
                PlayerEntry.COLUMN_MAIL      + " TEXT NOT NULL, " +
                PlayerEntry.COLUMN_GENDER    + " CHAR ( 1 ) NOT NULL, " +
                PlayerEntry.COLUMN_PICTURE   + " TEXT, " +
                PlayerEntry.COLUMN_BIRTHDATE + " DATE NOT NULL, "+ //must be in format yyyy-mm-dd
                PlayerEntry.COLUMN_HEIGHT    + " TEXT not NULL "+


                " );";

        final String SQL_CREATE_COACH_TABLE = "CREATE TABLE " + CoachEntry.TABLE_NAME + " (" +

                CoachEntry._ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CoachEntry.COLUMN_NAME      + " VARCHAR ( 40 ) NOT NULL, " +
                CoachEntry.COLUMN_TELEPHONE + " VARCHAR ( 15 ) NOT NULL, " +
                CoachEntry.COLUMN_MAIL      + " TEXT NOT NULL, " +
                CoachEntry.COLUMN_GENDER    + " CHAR ( 1 ) NOT NULL, " +
                CoachEntry.COLUMN_PICTURE   + " TEXT, " +
                CoachEntry.COLUMN_BIRTHDATE + " DATE NOT NULL, " + //must be in format yyyy-mm-dd
                CoachEntry.COLUMN_SALARY    + " TEXT NOT NULL " +

                " );";

        final String SQL_CREATE_GAME_TABLE = "CREATE TABLE " + GameEntry.TABLE_NAME + " (" +

                GameEntry.COLUMN_NAME              + " TEXT PRIMARY KEY, " +
                GameEntry.COLUMN_FEES              + " TEXT NOT NULL, " +
                GameEntry.COLUMN_DESCRIPTION       + " TEXT NOT NULL" +
                " );";

        final String SQL_CREATE_ENROLLMENT_TABLE = "CREATE TABLE " + EnrollmentEntry.TABLE_NAME + " (" +

                EnrollmentEntry.COLUMN_PLAYERID          + " INTEGER, " +
                EnrollmentEntry.COLUMN_GAME_NAME         + " TEXY, " +
                EnrollmentEntry.COLUMN_SUBSCRIPTION_DATE + " date DEFAULT CURRENT_TIMESTAMP, " +
                EnrollmentEntry.COLUMN_SUBSCRIPTION_DAYS + " INTEGER NOT NULL DEFAULT 30, " +
                "PRIMARY KEY( " + EnrollmentEntry.COLUMN_PLAYERID + " , " + EnrollmentEntry.COLUMN_GAME_NAME + " ), " +
                "FOREIGN KEY("+ EnrollmentEntry.COLUMN_PLAYERID + ") REFERENCES " + PlayerEntry.TABLE_NAME + "(" + PlayerEntry._ID + " ) " +
                "FOREIGN KEY("+ EnrollmentEntry.COLUMN_GAME_NAME + ") REFERENCES " + GameEntry.TABLE_NAME + "(" + GameEntry.COLUMN_NAME + " ) " +

                " );";

        final String SQL_CREATE_GAMES_TRAINERS_TABLE = "CREATE TABLE " + GamesTrainersEntry.TABLE_NAME + " (" +

                GamesTrainersEntry.COLUMN_COACH_ID          + " integer, " +
                GamesTrainersEntry.COLUMN_GAME_NAME         + " text, " +
                "PRIMARY KEY( " + GamesTrainersEntry.COLUMN_COACH_ID + " , " + GamesTrainersEntry.COLUMN_GAME_NAME + " ) " +
                " FOREIGN KEY(" + GamesTrainersEntry.COLUMN_GAME_NAME + ") REFERENCES " + GameEntry.TABLE_NAME + " " + "(" + GameEntry.COLUMN_NAME + ") ON DELETE CASCADE ON UPDATE CASCADE, " +
                " FOREIGN KEY(" + GamesTrainersEntry.COLUMN_COACH_ID + ") REFERENCES " + CoachEntry.TABLE_NAME + " " + "(" + CoachEntry._ID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                " );";





        sqLiteDatabase.execSQL(SQL_CREATE_COACH_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PLAYER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ENROLLMENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GAME_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GAMES_TRAINERS_TABLE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CoachEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EnrollmentEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GamesTrainersEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
