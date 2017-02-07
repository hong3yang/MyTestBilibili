package com.example.hong3.mybilibili.adapter.section;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.adapter.ActivityCenterRecyclerAdapter;
import com.example.hong3.mybilibili.entity.recomment.RecommentInfo;
import com.example.hong3.mybilibili.ui.customView.widget.StatusLessSection;

import java.util.List;

/**
 * Created by hong3 on 2016/12/12.
 */

public class HomeRecommentActivitySection extends StatusLessSection{

    Context context;
    List<RecommentInfo.BodyBean> lists;

    public HomeRecommentActivitySection(Context context, List<RecommentInfo.BodyBean> lists){
        super(R.layout.layout_empty,R.layout.layout_home_recommend_activity);
        this.context = context;
        this.lists = lists;
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
        return new ActivityViewHolder(view);
//
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ActivityViewHolder viewHolder = (ActivityViewHolder) holder;
        viewHolder.recyclerView.setHasFixedSize(false); //使用固定的item大小（非瀑布流）
        viewHolder.recyclerView.setNestedScrollingEnabled(false);//不使用嵌套滚动机制 （向上滚动隐藏toolbar，向下滚动显示toolbar）
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
//        GridLayoutManager manager = new GridLayoutManager(context,2);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 1;
//            }
//        });
        viewHolder.recyclerView.setLayoutManager(manager);
        viewHolder.recyclerView.setAdapter(new ActivityCenterRecyclerAdapter(lists,context));
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);

        }
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;

        public ActivityViewHolder(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.recomment_activity_recycle);
        }
    }

}
