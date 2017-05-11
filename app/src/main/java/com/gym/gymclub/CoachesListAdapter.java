package com.gym.gymclub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahmed on 5/11/17.
 */

public class CoachesListAdapter extends RecyclerView.Adapter<CoachesListAdapter.CoachItemViewHolder> {

    private List<Coach> mCoaches;
    private CoachClickListener mCoachClickListener;

    interface CoachClickListener{
        void onCoachClick(int id);
    }

    public CoachesListAdapter(List<Coach> mCoaches, CoachClickListener mCoachClickListener) {
        this.mCoaches = mCoaches;
        this.mCoachClickListener = mCoachClickListener;
    }

    @Override
    public CoachItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_list_item, parent, false);
        return new CoachItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoachItemViewHolder holder, int position) {

        String coachName = mCoaches.get(position).getName();
        holder.coachNameTextView.setText(coachName);

    }

    @Override
    public int getItemCount() {
        return mCoaches.size();
    }

    public class CoachItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView coachNameTextView;

        public CoachItemViewHolder(View itemView) {
            super(itemView);
            coachNameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = mCoaches.get(getAdapterPosition()).getId();
            mCoachClickListener.onCoachClick(id);
        }
    }
}
