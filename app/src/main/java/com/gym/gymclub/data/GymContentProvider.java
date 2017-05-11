package com.gym.gymclub.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gym.gymclub.data.GymContract.CoachEntry;
import com.gym.gymclub.data.GymContract.PlayerEntry;
import com.gym.gymclub.data.GymContract.GameEntry;
import com.gym.gymclub.data.GymContract.GamesTrainersEntry;
import com.gym.gymclub.data.GymContract.ProgressEntry;
import com.gym.gymclub.data.GymContract.EnrollmentEntry;

import java.util.Date;

/**
 * Created by ahmed on 5/8/17.
 */

public class GymContentProvider extends ContentProvider {

    private GymDbHelper mGymDbHelper;

    private static final int PLAYERS = 100;
    private static final int PLAYERS_WITH_ID = 101;

    private static final int COACHES = 200;
    private static final int COACHES_WITH_ID = 201;

    private static final int GAMES = 300;
    private static final int GAMES_WITH_GAME_NAME = 301;

    private static final int ENROLLMENT = 400;
    private static final int ENROLLMENT_WITH_PLAYER_ID = 401;
    private static final int ENROLLMENT_WITH_GAME_NAME = 402;

    private static final int GAMES_TRAINERS = 500;
    private static final int GAMES_TRAINERS_WITH_COACH_ID = 501;
    private static final int GAMES_TRAINERS_WITH_GAME_NAME = 502;


    private static final UriMatcher sUriMatcher = buildUriMatcher();




    @Override
    public boolean onCreate() {
        mGymDbHelper = new GymDbHelper(getContext());
        return true;
    }

    private static  UriMatcher buildUriMatcher(){

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_PLAYERS, PLAYERS);
        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_PLAYERS + "/#", PLAYERS_WITH_ID);

        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_COACHES, COACHES);
        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_COACHES + "/#", COACHES_WITH_ID);

        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_GAMES, GAMES);
        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_GAMES + "/*", GAMES_WITH_GAME_NAME);

        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_ENROLLMENT, ENROLLMENT);
        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_ENROLLMENT + "/#", ENROLLMENT_WITH_PLAYER_ID);
        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_ENROLLMENT + "/*", ENROLLMENT_WITH_GAME_NAME);

        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_GAMES_TRAINERS, GAMES_TRAINERS);
        uriMatcher.addURI(GymContract.CONTENT_AUTHORITY, GymContract.PATH_GAMES_TRAINERS + "/#", GAMES_TRAINERS_WITH_COACH_ID);

        return uriMatcher;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mGymDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor cursor;



        if (match == PLAYERS) {
            cursor = db.query(PlayerEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);
        }

        else if(match == PLAYERS_WITH_ID) {
            selection = "_id =?";
            String id = String.valueOf(ContentUris.parseId(uri));
            selectionArgs = new String[]{id};

            cursor = db.query(PlayerEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);

        }

        else if(match == COACHES) {
            cursor = db.query(CoachEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);

        }

        else if(match == COACHES_WITH_ID) {
            selection = "_id =?";
            String id = String.valueOf(ContentUris.parseId(uri));
            selectionArgs = new String[]{id};

            cursor = db.query(CoachEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);

        }

        else if(match == GAMES){
            cursor = db.query(GameEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);
        }

        else if(match == GAMES_WITH_GAME_NAME){
            selection = GameEntry.COLUMN_NAME + " = ?" ;
            String gameName = uri.getLastPathSegment();
            selectionArgs = new String[] {gameName};

            cursor = db.query(GameEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);
        }

        else if(match == ENROLLMENT_WITH_PLAYER_ID){
            projection = new String[]{EnrollmentEntry.COLUMN_GAME_NAME};
            selection = EnrollmentEntry.COLUMN_PLAYERID + " = ?";
            String id = uri.getLastPathSegment();
            selectionArgs = new String[] {id};

            cursor = db.query(EnrollmentEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);
        }

        else if(match == ENROLLMENT_WITH_GAME_NAME){
            String gameName = uri.getLastPathSegment();
//
//            selectionArgs = new String[]{PlayerEntry.COLUMN_NAME, PlayerEntry._ID, PlayerEntry._ID, EnrollmentEntry.COLUMN_PLAYERID, EnrollmentEntry.COLUMN_GAME_NAME, gameName};
//
//            String sql = "SELECT ?, ? FROM "+PlayerEntry.TABLE_NAME+" WHERE ? IN ( SELECT ? AS "+PlayerEntry._ID+" FROM "+EnrollmentEntry.TABLE_NAME+" WHERE ? = ? )";
//            Log.d("sql", sql);
//
//            cursor = db.rawQuery(sql, selectionArgs);

            selection = EnrollmentEntry.COLUMN_GAME_NAME + " = ?";
            selectionArgs = new String[] {gameName};
            projection = new String[]{EnrollmentEntry.COLUMN_PLAYERID};

            cursor = db.query(EnrollmentEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs
                    , null, null, null, sortOrder);

        }

        else if(match == GAMES_TRAINERS_WITH_COACH_ID){
            projection = new String[] {GamesTrainersEntry.COLUMN_GAME_NAME};
            selection = GamesTrainersEntry.COLUMN_COACH_ID + " = ?" ;
            String id = String.valueOf(ContentUris.parseId(uri));
            selectionArgs = new String[]{id};

            cursor = db.query(GamesTrainersEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder);

        }

        else
            throw new UnsupportedOperationException("wrong uri " + uri);




        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mGymDbHelper.getWritableDatabase();
        Uri returnUri;
        int match = sUriMatcher.match(uri);



        if (match == PLAYERS) {
            Long id = db.insert(PlayerEntry.TABLE_NAME, null, contentValues);
            if (id == -1)
                throw new android.database.SQLException("failed to insert row into " + uri);
            returnUri = ContentUris.withAppendedId(PlayerEntry.CONTENT_URI, id);

            }


        else if( match == COACHES) {
            Long id = db.insert(CoachEntry.TABLE_NAME, null, contentValues);
            if (id == -1)
                throw new android.database.SQLException("failed to insert row into " + uri);
            returnUri = ContentUris.withAppendedId(CoachEntry.CONTENT_URI, id);

        }

        else if(match == GAMES){
            Long id = db.insert(GameEntry.TABLE_NAME, null, contentValues);
            if(id == -1)
                throw new android.database.SQLException("failed to insert row into " + uri);
            returnUri = ContentUris.withAppendedId(GameEntry.CONTENT_URI, id);
        }

        else if (match == ENROLLMENT){
            Long id = db.insert(EnrollmentEntry.TABLE_NAME, null, contentValues);
            if(id == -1)
                throw new android.database.SQLException("failed to insert row into " + uri);
            returnUri = ContentUris.withAppendedId(EnrollmentEntry.CONTENT_URI, id);
        }

        else if (match == GAMES_TRAINERS){
            Long id = db.insert(GamesTrainersEntry.TABLE_NAME, null, contentValues);
            if(id == -1)
                throw new android.database.SQLException("failed to insert row into " + uri);
            returnUri = ContentUris.withAppendedId(GamesTrainersEntry.CONTENT_URI, id);
        }


        else{
            throw new UnsupportedOperationException("wrong uri " + uri);
        }



        db.close();
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }



    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }



}
