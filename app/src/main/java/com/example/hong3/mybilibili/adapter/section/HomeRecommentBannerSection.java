package com.example.hong3.mybilibili.adapter.section;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.ui.customView.banner.BannerView;
import com.example.hong3.mybilibili.ui.customView.widget.StatusLessSection;

import java.util.List;

/**
 * Created by hong3 on 2016/12/8.
 */

public class HomeRecommentBannerSection extends StatusLessSection{
    private static final String TAG = "HomeRecommentBannerSect";

    List<Banner> banners;

    public HomeRecommentBannerSection(List<Banner> banners) {
        super(R.layout.layout_empty,R.layout.layout_banner);
        this.banners = banners;
    }

    @Override
    public int getContentItemTotal() {
        return 1;  //显示的项数
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        RecyclerView.ViewHolder holder= new BannerViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: viewHolder--> ^^^ "+ holder);
        return holder;
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        Log.d(TAG, "onCreateViewHolder: viewHolder--> %% "+ holder);
        BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
        bannerViewHolder.bannerView.delayTime(5).build(banners);
    }


    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        BannerView bannerView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            bannerView = (BannerView) itemView.findViewById(R.id.banner_banner);
        }
    }
}
