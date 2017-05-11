package com.gym.gymclub;


import android.content.ContentUris;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gym.gymclub.data.GymContract;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * display list of all players in the system
 */
public class PlayersListFragment extends Fragment implements PlayersListAdapter.PlayerClickListener {

    private RecyclerView mPlayersRecyclerView;
    private String[] mProjection;


    public PlayersListFragment() {
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
        mProjection = new String[]{GymContract.PlayerEntry.COLUMN_NAME, GymContract.PlayerEntry._ID};
    }

    @Override
    public void onStart() {
        super.onStart();


        Cursor cursor = getContext().getContentResolver().query(GymContract.PlayerEntry.CONTENT_URI, mProjection, null,null,null);

        if(cursor == null)
            throw new android.database.SQLException("cursor is null");

        List<Player> playersList = new ArrayList<Player>();

        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(GymContract.PlayerEntry._ID));
            String name = cursor.getString(cursor.getColumnIndex(GymContract.PlayerEntry.COLUMN_NAME));

            playersList.add(new Player(id, name, null, null, null, null, null, null));
        }

        cursor.close();


        PlayersListAdapter playersListAdapter = new PlayersListAdapter(playersList, this);
        mPlayersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlayersRecyclerView.setAdapter(playersListAdapter);

    }

    @Override
    public void onPlayerClick(int id) {
        Toast.makeText(getContext(), "player id " + id , Toast.LENGTH_SHORT).show();
    }
}
