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

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.PlayerItemViewHolder> {

    private List<Player> mPlayers;
    private PlayerClickListener  mPlayerClickListener;

    interface PlayerClickListener{
        void onPlayerClick(int id);
    }

    public PlayersListAdapter(List<Player> mPlayers, PlayerClickListener mPlayerClickListener) {
        this.mPlayers = mPlayers;
        this.mPlayerClickListener = mPlayerClickListener;
    }

    @Override
    public PlayerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_list_item, parent, false);

        return new PlayerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerItemViewHolder holder, int position) {

        String playerName = mPlayers.get(position).getName();
        holder.playerNameTextView.setText(playerName);
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class PlayerItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView playerNameTextView ;

        public PlayerItemViewHolder(View itemView) {
            super(itemView);
            playerNameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = mPlayers.get(getAdapterPosition()).getId();
            mPlayerClickListener.onPlayerClick(id);
        }
    }
}
