package com.gym.gymclub;


import android.database.Cursor;
import android.database.SQLException;
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
 *
 * view a list of all coaches
 */
public class CoachesListFragment extends Fragment implements CoachesListAdapter.CoachClickListener {

    private RecyclerView mCoachesRecyclerView;
    private String[] mProjection;

    public CoachesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coaches_list, container, false);
        mCoachesRecyclerView = (RecyclerView) view.findViewById(R.id.rv_coaches_list);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProjection = new String[] {GymContract.CoachEntry._ID, GymContract.CoachEntry.COLUMN_NAME};
    }

    @Override
    public void onStart() {
        super.onStart();

        Cursor cursor = getContext().getContentResolver().query(GymContract.CoachEntry.CONTENT_URI, mProjection, null, null, null);

        if(cursor == null)
            throw new SQLException("cursor is null");

        List<Coach> coachesList = new ArrayList<Coach>();

        while (cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndex(GymContract.CoachEntry.COLUMN_NAME));
            int id = cursor.getInt(cursor.getColumnIndex(GymContract.CoachEntry._ID));

            coachesList.add(new Coach(id, name, null, null, null,null, null, null));

        }

        cursor.close();

        CoachesListAdapter coachesListAdapter = new CoachesListAdapter(coachesList, this);
        mCoachesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCoachesRecyclerView.setAdapter(coachesListAdapter);


    }

    @Override
    public void onCoachClick(int id) {

        Toast.makeText(getContext(), "coach id " + id , Toast.LENGTH_SHORT).show();
    }
}
