package com.gym.gymclub;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.gym.gymclub.data.GymContract;


/**
 *player profile info
 */
public class PlayerProfileFragment extends Fragment {

    private int mPlayerID;
    private TextView mPlayerNameTextVeiw;
    private TextView mPlayerAgeTextView;
    private TextView mPlayerPhoneTExtView;
    private TextView mPlayerEmailTextView;

    public PlayerProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_player_profile, container, false);
        mPlayerNameTextVeiw=(TextView)view.findViewById(R.id.tv_player_name);
        mPlayerAgeTextView=(TextView)view.findViewById(R.id.tv_player_age);
        mPlayerPhoneTExtView=(TextView)view.findViewById(R.id.tv_player_phone_num);
        mPlayerEmailTextView=(TextView)view.findViewById(R.id.tv_player_email);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayerID=1;

    }
    @Override
    public void onStart() {
        super.onStart();

        Cursor cursor = getContext().getContentResolver().query(ContentUris.withAppendedId(GymContract.PlayerEntry.CONTENT_URI, (long) mPlayerID), null, null, null, null);

        if (cursor == null)
            throw new android.database.SQLException("cursor is null");
        cursor.moveToNext();
        mPlayerPhoneTExtView.setText(cursor.getString(cursor.getColumnIndex(GymContract.PlayerEntry.COLUMN_TELEPHONE)));
        mPlayerNameTextVeiw.setText(cursor.getString(cursor.getColumnIndex(GymContract.PlayerEntry.COLUMN_NAME)));
        mPlayerEmailTextView.setText(cursor.getString(cursor.getColumnIndex(GymContract.PlayerEntry.COLUMN_MAIL)));
        mPlayerAgeTextView.setText(cursor.getString(cursor.getColumnIndex(GymContract.PlayerEntry.COLUMN_BIRTHDATE)));
        cursor.close();
    }




   }


