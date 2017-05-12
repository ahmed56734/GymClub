package com.gym.gymclub;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gym.gymclub.data.GymContract;


/**
 *
 * view game infromation knowing the game name
 */
public class GameProfileFragment extends Fragment {

    private String mGameName;
    private TextView mGameNameTextView;
    private TextView mGameFeesTextView;
    private TextView mGameDescripionTextView;

    public GameProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game_profile, container, false);

        mGameNameTextView = (TextView) view.findViewById(R.id.tv_game_name);
        mGameFeesTextView = (TextView) view.findViewById(R.id.tv_fees);
        mGameDescripionTextView = (TextView) view.findViewById(R.id.tv_description);

        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameName = "Kung Fu";
    }

    @Override
    public void onStart() {
        super.onStart();

        Uri uri = GymContract.GameEntry.CONTENT_URI.buildUpon().appendPath(mGameName).build();
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);

        if (cursor == null)
            throw new android.database.SQLException("cursor is null");

        cursor.moveToNext();

        String fees = cursor.getString(cursor.getColumnIndex(GymContract.GameEntry.COLUMN_FEES));
        String description = cursor.getString(cursor.getColumnIndex(GymContract.GameEntry.COLUMN_DESCRIPTION));

        mGameNameTextView.setText(mGameName);
        mGameFeesTextView.setText(fees);
        mGameDescripionTextView.setText(description);





    }
}
