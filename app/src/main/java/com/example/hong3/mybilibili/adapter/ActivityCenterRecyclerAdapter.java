package com.example.hong3.mybilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.entity.recomment.RecommentInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hong3 on 2016/12/12.
 */

public class ActivityCenterRecyclerAdapter extends RecyclerView.Adapter{

    Context context;
    List<RecommentInfo.BodyBean> beans;

    public ActivityCenterRecyclerAdapter(List<RecommentInfo.BodyBean> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity_center_list,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        Picasso.with(context).load(beans.get(position).getCover()).into(viewHolder.imageView);
        viewHolder.textView.setText(beans.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.activity_center_image);
            textView = (TextView) itemView.findViewById(R.id.avtivity_center_title);

        }
    }
}
