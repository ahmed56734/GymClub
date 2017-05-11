package com.gym.gymclub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahmed on 5/10/17.
 */

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GameViewHolder> {

    private List<Game> mGames;
    private GameClickListener mGameClickListener;

    interface GameClickListener{
        void onGameClick(String gameName);
    }

    GamesListAdapter(List<Game> games, GameClickListener gameClickListener){
        this.mGames = games;
        mGameClickListener = gameClickListener;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {

        String gameName = mGames.get(position).getGameName();

        holder.nameTextView.setText(gameName);

    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;

        public GameViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String gameName = mGames.get(getAdapterPosition()).getGameName();
            mGameClickListener.onGameClick(gameName);
        }
    }
}
