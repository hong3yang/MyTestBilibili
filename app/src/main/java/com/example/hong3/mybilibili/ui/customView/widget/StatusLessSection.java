package com.example.hong3.mybilibili.ui.customView.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hong3 on 2016/12/8.
 */

public abstract class StatusLessSection extends Section {


    public StatusLessSection(int itemResourceId){
        super();
        this.itemResourceId = itemResourceId;
    }

    public StatusLessSection(int itemResourceId,int headerResourceId){
        this(itemResourceId);
        this.headerResourceId = headerResourceId;
        setHasHeader(true);
    }

    public StatusLessSection(int itemResourceId,int headerResourceId,int footerResourceId){
        this(itemResourceId,headerResourceId);
        this.footerResourceId = footerResourceId;
        setHasFooter(true);
    }


    /**
     *    LoadingViewHolder
     * @param view
     * @return
     */
    public final RecyclerView.ViewHolder getLoadingViewHolder(View view){
        return super.getLoadingViewHolder(view);
    }

    /**
     *    LoadingViewHolder
     * @param holder
     */
    public final void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindLoadingViewHolder(holder);
    }


    /**
     *    FailsViewHolder
     * @param view
     * @return
     */
    public final RecyclerView.ViewHolder getFailsViewHolder(View view)
    {
        return super.getFailsViewHolder(view);
    }

    /**
     *     FailsViewHolder
     * @param holder
     */
    public final void onBindFailsViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindFailsViewHolder(holder);
    }



}
