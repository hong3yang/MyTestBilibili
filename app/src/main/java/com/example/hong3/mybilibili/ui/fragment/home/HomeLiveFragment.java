package com.example.hong3.mybilibili.ui.fragment.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.adapter.LiveAppIndexAdapter;
import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.entity.live.EntranceIcons;
import com.example.hong3.mybilibili.entity.live.Partitions;
import com.example.hong3.mybilibili.entity.live.RecommendData;
import com.example.hong3.mybilibili.presenter.live.HomeLivePresenter;
import com.example.hong3.mybilibili.ui.customView.CustomEmptyView;
import com.example.hong3.mybilibili.ui.fragment.BaseFragment;
import com.example.hong3.mybilibili.uiResult.HomeLiveResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hong3 on 2016/11/29.
 */
public class HomeLiveFragment extends BaseFragment implements HomeLiveResult {

    private RecyclerView recyclerView;
    private CustomEmptyView customEmptyView;
    private SwipeRefreshLayout swipeRefreshLayout;
    HomeLivePresenter homeLivePresenter;

    private LiveAppIndexAdapter liveAppIndexAdapter;

    List<Banner> banners = new ArrayList<>();
    List<EntranceIcons> entranceIconses = new ArrayList<>();
    List<Partitions> partitionses = new ArrayList<>();
    List<RecommendData> recommendDatas = new ArrayList<>();


    public static HomeLiveFragment newInstance()
    {
        return new HomeLiveFragment();
    }

    @Override
    public void finishCreateView(Bundle state) {
       isPrepared = true;
        lazyLoad();

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_live;
    }

    @Override
    public void initView() {
        super.initView();

        recyclerView = fastFindViewById(R.id.live_recycleView);
        customEmptyView = fastFindViewById(R.id.live_empty);
        swipeRefreshLayout = fastFindViewById(R.id.swipe);

        homeLivePresenter = new HomeLivePresenter(this);

    }

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible)
            return;

        initRefreshLayout();
        initRecyclerView();
        isPrepared = false;
    }

    @Override
    protected void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        });


    }

    private void initEmptyView()
    {

        swipeRefreshLayout.setRefreshing(false);
        customEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        customEmptyView.setEmptyImage(R.mipmap.img_tips_error_load_error);
        customEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        Snackbar.make(recyclerView,"数据加载失败,请重新加载或者检查网络是否链接",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void loadData() {

        homeLivePresenter.getLiveData();
    }

    @Override
    protected void initRecyclerView() {

        liveAppIndexAdapter = new LiveAppIndexAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 12);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return liveAppIndexAdapter.getSpanSize(position);
            }
        });
        recyclerView.setAdapter(liveAppIndexAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    public void hideEmptyView()
    {

        customEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataSuccess(List<Banner> banners, List<EntranceIcons> entranceIconses, List<Partitions> partitionses, RecommendData recommendData) {
        hideEmptyView();
        this.banners.clear();
        this.entranceIconses.clear();
        this.partitionses.clear();
        this.recommendDatas.clear();
        this.banners.addAll(banners);
        this.entranceIconses.addAll(entranceIconses);
        this.partitionses.addAll(partitionses);
        recommendDatas.clear();
        recommendDatas.add(recommendData);
        swipeRefreshLayout.setRefreshing(false);

        liveAppIndexAdapter.setLiveInfo(this.banners, this.entranceIconses, this.partitionses, recommendDatas);
        liveAppIndexAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void getDataFails(String str) {
        initEmptyView();
    }

}
