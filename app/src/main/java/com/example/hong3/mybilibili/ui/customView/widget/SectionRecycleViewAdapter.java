package com.example.hong3.mybilibili.ui.customView.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hong3 on 2016/12/7.
 */

public class SectionRecycleViewAdapter extends RecyclerView.Adapter{


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder
    {

        public EmptyViewHolder(View itemView)
        {

            super(itemView);
        }
    }
}
