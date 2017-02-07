package com.example.hong3.mybilibili.ui.customView.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hong3 on 2016/12/7.
 */

public abstract class Section {

    public enum State {
        LOADING, LOADED, FAILD
    }

    private State state = State.LOADED;

    private boolean hasFooter = false;

    private boolean hasHeader = false;

    private boolean isVisible = false;

     Integer footerResourceId;

     Integer headerResourceId;

    private Integer failsResourceId;

    private Integer loadingResourceId;

     Integer itemResourceId;

    public Section() {
    }

    public Section(int itemResourceId, int loadingResourceId, int failsResourceId) {
        this.itemResourceId = itemResourceId;
        this.loadingResourceId = loadingResourceId;
        this.failsResourceId = failsResourceId;
    }

    public Section(int itemResourceId, int loadingResourceId, int failsResourceId, int headerResourceId) {
        this(itemResourceId, loadingResourceId, failsResourceId);
        this.headerResourceId = headerResourceId;
        hasHeader = true;
    }

    public Section(int itemResourceId, int loadingResourceId, int failsResourceId, int footerResourceId, int headerResourceId) {
        this(itemResourceId, loadingResourceId, failsResourceId, headerResourceId);
        this.footerResourceId = footerResourceId;
        hasFooter = true;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public State getState() {
        return state;
    }

    public boolean isHasFooter() {
        return hasFooter;
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Integer getFooterResourceId() {
        return footerResourceId;
    }

    public Integer getHeaderResourceId() {
        return headerResourceId;
    }

    public Integer getFailsResourceId() {
        return failsResourceId;
    }

    public Integer getLoadingResourceId() {
        return loadingResourceId;
    }

    public Integer getItemResourceId() {
        return itemResourceId;
    }

    public final void bindContentViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (state) {
            case LOADED:
                onBindItemViewHolder(holder, position);
                break;
            case LOADING:
                onBindLoadingViewHolder(holder);
                break;
            case FAILD:
                onBindFailsViewHolder(holder);
                break;
            default:
                throw new IllegalStateException("position is illegal");
        }
    }

    public abstract int getContentItemTotal();


    public int getSectionItemTotal(){

        switch (state){
            case LOADED:
                return getContentItemTotal()+(hasHeader?1:0)+(hasFooter?1:0);

            case LOADING:

                return 1;
            case FAILD:

                return 1;
            default:
                throw new IllegalStateException("position is illegal");
        }

    }


    /**
     *    FooterViewHolder
     * @param view
     * @return
     */
    public RecyclerView.ViewHolder getFooterViewHolder(View view){
        return new SectionRecycleViewAdapter.EmptyViewHolder(view);
    }

    /**
     * FooterViewHolder
     * @param holder
     */
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder){

    }

    /**
     * HeaderViewHolder
     *
     * @param view
     * @return
     */
    public RecyclerView.ViewHolder getHeaderViewHolder(View view){
        return new SectionRecycleViewAdapter.EmptyViewHolder(view);
    }

    /**
     *   HeaderViewHolder
     * @param holder
     */
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder){

    }

    /**
     *    LoadingViewHolder
     * @param view
     * @return
     */
    public RecyclerView.ViewHolder getLoadingViewHolder(View view){
        return new SectionRecycleViewAdapter.EmptyViewHolder(view);
    }

    /**
     *    LoadingViewHolder
     * @param holder
     */
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
    }

    /**
     *    FailsViewHolder
     * @param view
     * @return
     */
    public RecyclerView.ViewHolder getFailsViewHolder(View view)
    {
        return new SectionRecycleViewAdapter.EmptyViewHolder(view);
    }

    /**
     *     FailsViewHolder
     * @param holder
     */
    public void onBindFailsViewHolder(RecyclerView.ViewHolder holder) {
    }

    /**
     *    ItemViewHolder
     * @param view
     */
    public abstract RecyclerView.ViewHolder getItemViewHolder(View view);

    /**
     *     ItemViewHolder
     * @param holder
     * @param position
     */
    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);
}
