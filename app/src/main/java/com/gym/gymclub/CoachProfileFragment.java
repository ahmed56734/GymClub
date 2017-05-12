package com.gym.gymclub;


import android.content.ContentUris;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gym.gymclub.data.GymContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoachProfileFragment extends Fragment {

    private TextView mCoachSalaryTestView;
    private TextView mCoachNameTextView;
    private TextView mCoachEmailTextView;
    private TextView mCoachPhoneTextView;
    private TextView mCoachAgeTextView;
    private int CoachID;

    public CoachProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoachID=1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_coach_profile, container, false);
        mCoachAgeTextView=(TextView)view.findViewById(R.id.tv_coach_age);
        mCoachEmailTextView=(TextView)view.findViewById(R.id.tv_coach_email);
        mCoachNameTextView=(TextView)view.findViewById(R.id.tv_coach_name);
        mCoachPhoneTextView=(TextView)view.findViewById(R.id.tv_coach_phone_num);
        mCoachSalaryTestView=(TextView)view.findViewById(R.id.tv_coach_salary);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Cursor cursor = getContext().getContentResolver().query(ContentUris.withAppendedId(GymContract.CoachEntry.CONTENT_URI, (long) CoachID), null, null, null, null);

        if (cursor == null)
            throw new android.database.SQLException("cursor is null");
        cursor.moveToNext();
        mCoachSalaryTestView.setText(cursor.getString(cursor.getColumnIndex(GymContract.CoachEntry.COLUMN_SALARY)));
        mCoachPhoneTextView.setText(cursor.getString(cursor.getColumnIndex(GymContract.CoachEntry.COLUMN_TELEPHONE)));
        mCoachNameTextView.setText(cursor.getString(cursor.getColumnIndex(GymContract.CoachEntry.COLUMN_NAME)));
        mCoachEmailTextView.setText(cursor.getString(cursor.getColumnIndex(GymContract.CoachEntry.COLUMN_MAIL)));
        mCoachAgeTextView.setText(cursor.getString(cursor.getColumnIndex(GymContract.CoachEntry.COLUMN_BIRTHDATE)));
        cursor.close();
    }
}
