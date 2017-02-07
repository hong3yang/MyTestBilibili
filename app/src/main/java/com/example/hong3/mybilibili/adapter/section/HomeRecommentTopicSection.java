package com.example.hong3.mybilibili.adapter.section;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.activity.WebViewActivity;
import com.example.hong3.mybilibili.ui.customView.widget.StatusLessSection;
import com.squareup.picasso.Picasso;

/**
 * Created by hong3 on 2016/12/8.
 */

public class HomeRecommentTopicSection extends StatusLessSection {

    String link;
    Context context;
    String topicTitle;
    String topicImageUrl;


    public HomeRecommentTopicSection(String link,
                                     Context context,
                                     String topicTitle,
                                     String topicImageUrl) {
        super( R.layout.layout_empty,R.layout.layout_home_recommend_topic);
        this.link = link;
        this.context = context;
        this.topicTitle = topicTitle;
        this.topicImageUrl = topicImageUrl;
    }

    @Override
    public int getContentItemTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new EmptyViewHolder(view);

    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.startActivity(context, link);
            }
        });
        if (TextUtils.isEmpty(topicTitle)) {
            viewHolder.topicTitle.setVisibility(View.GONE);
        } else {
            viewHolder.topicTitle.setText(topicTitle);
        }
        Picasso.with(context).load(topicImageUrl).into(viewHolder.topicImage);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView more;
        CardView cardView;
        ImageView topicImage;
        TextView topicTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);


            more = (TextView) itemView.findViewById(R.id.item_type_more);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            topicImage = (ImageView) itemView.findViewById(R.id.topic_image);
            topicTitle = (TextView) itemView.findViewById(R.id.topic_title);


        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {


        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
