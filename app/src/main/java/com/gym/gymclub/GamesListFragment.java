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
import android.widget.TextView;
import android.widget.Toast;

import com.gym.gymclub.data.GymContract;

import java.util.ArrayList;
import java.util.List;

/**
 * display list of all games in the system
 */
public class GamesListFragment extends Fragment implements GamesListAdapter.GameClickListener {


    private RecyclerView mRecyclerView;
    private String[] mProjection;


    public GamesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_games_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_games_list);

        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProjection = new String[] {GymContract.GameEntry.COLUMN_NAME};

    }

    @Override
    public void onStart() {
        super.onStart();

        Cursor cursor = getContext().getContentResolver().query(GymContract.GameEntry.CONTENT_URI, mProjection, null, null, null);
        if(cursor == null)
            throw new android.database.SQLException("cursor is null");

        List<Game> gamesList = new ArrayList<Game>();

        while (cursor.moveToNext()){
            String gameName = cursor.getString(cursor.getColumnIndex(GymContract.GameEntry.COLUMN_NAME));
            gamesList.add(new Game(gameName, null, null));
        }

        cursor.close();

        GamesListAdapter gamesListAdapter = new GamesListAdapter(gamesList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(gamesListAdapter);




    }

    @Override
    public void onGameClick(String gameName) {
        Toast.makeText(getContext(), gameName + " is clicked", Toast.LENGTH_SHORT).show();
    }
}
