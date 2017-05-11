package com.gym.gymclub;


import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gym.gymclub.data.GymContract;

import java.util.ArrayList;
import java.util.List;

/**
 * display a list of a game players knowing the game name
 */
public class GamePlayersListFragment extends Fragment implements PlayersListAdapter.PlayerClickListener {

    private RecyclerView mPlayersRecyclerView;
    private String mGameName;

    public GamePlayersListFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_players_list, container, false);

        mPlayersRecyclerView = (RecyclerView) view.findViewById(R.id.rv_players_list);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameName = "Kung Fu";

    }

    @Override
    public void onStart() {
        super.onStart();


        Uri uri = GymContract.EnrollmentEntry.CONTENT_URI.buildUpon().appendPath(mGameName).build();
        Cursor enrollmentCursor = getContext().getContentResolver().query(uri, null, null, null, null);

        if(enrollmentCursor == null)
            throw new android.database.SQLException("enrollmentCursor is null");

        List<Player> playersList = new ArrayList<Player>();



        while (enrollmentCursor.moveToNext()){

            int id = enrollmentCursor.getInt(enrollmentCursor.getColumnIndex(GymContract.EnrollmentEntry.COLUMN_PLAYERID));



             Cursor playersCursor = getContext().getContentResolver().query(ContentUris.withAppendedId(GymContract.PlayerEntry.CONTENT_URI,(long)id),
                    null,
                    null,
                    null,
                    null);


            playersCursor.moveToNext();
            String name = playersCursor.getString(playersCursor.getColumnIndex(GymContract.PlayerEntry.COLUMN_NAME));
            playersList.add(new Player(id, name, null, null, null, null, null, null));
            playersCursor.close();

        }


        enrollmentCursor.close();



        PlayersListAdapter playersListAdapter = new PlayersListAdapter(playersList, this);
        mPlayersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlayersRecyclerView.setAdapter(playersListAdapter);

    }

    @Override
    public void onPlayerClick(int id) {
        Toast.makeText(getContext(), "player id " + id , Toast.LENGTH_SHORT).show();
    }

}
