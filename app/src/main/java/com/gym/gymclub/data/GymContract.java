package com.gym.gymclub.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ahmed on 5/8/17.
 */

public  class GymContract {

    public static final String CONTENT_AUTHORITY = "com.gym.gymclub";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PLAYERS = "players";
    public static final String PATH_COACHES = "coaches";
    public static final String PATH_ENROLLMENT = "enrollment";
    public static final String PATH_GAMES = "games";
    public static final String PATH_GAMES_TRAINERS = "trainers";

    public static final class PlayerEntry implements BaseColumns  {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYERS).build();

        public static final String TABLE_NAME = "player";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TELEPHONE = "telephone";
        public static final String COLUMN_BIRTHDATE = "birthdate";
        public static final String COLUMN_PICTURE = "picture";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_MAIL = "mail";
        public static final String COLUMN_HEIGHT = "height";

    }

    public static final class CoachEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COACHES).build();

        public static final String TABLE_NAME = "coach";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TELEPHONE = "telephone";
        public static final String COLUMN_BIRTHDATE = "birthdate";
        public static final String COLUMN_PICTURE = "picture";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_MAIL = "mail";
        public static final String COLUMN_SALARY = "salary";
    }

    public static final class GameEntry{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GAMES).build();

        public static final String TABLE_NAME = "games";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_FEES = "fees";
        public static final String COLUMN_DESCRIPTION  = "description";
    }

    public static final class EnrollmentEntry{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENROLLMENT).build();


        public static final String TABLE_NAME = "enrollment";

        public static final String COLUMN_PLAYERID = "player_id";
        public static final String COLUMN_GAME_NAME = "game_name";
        public static final String COLUMN_SUBSCRIPTION_DATE = "subscription_date";
        public static final String COLUMN_SUBSCRIPTION_DAYS = "subscription_days";
    }

    public static final class GamesTrainersEntry {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GAMES_TRAINERS).build();

        public static final String TABLE_NAME = "games_trainers";

        public static final String COLUMN_COACH_ID = "coach_id";
        public static final String COLUMN_GAME_NAME = "game_name";

    }

    public static final class ProgressEntry {

        public static final String TABLE_NAME = "progress";

        public static final String COLUMN_PLAYER_ID = "player_id";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_COACH_ID = "coach_id";
        public static final String COLUMN_FAT_PERCENT = "fat_percent";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_WEIGHT = "weight";

    }
}
