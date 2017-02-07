package com.example.hong3.mybilibili.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.adapter.HomeBangumiAdapter;
import com.example.hong3.mybilibili.entity.bangumi.BangumiBean;
import com.example.hong3.mybilibili.presenter.bangumi.HomeBangumiController;
import com.example.hong3.mybilibili.ui.fragment.BaseFragment;
import com.example.hong3.mybilibili.uiResult.HomeBangumiResult;

/**
 * Created by hong3 on 2016/11/29.
 */
public class HomeBangumiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeBangumiResult {

    private static final String TAG = "HomeBangumiFragment";

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private HomeBangumiAdapter homeBangumiAdapter;

    private HomeBangumiController homeBangumiController;

    public static HomeBangumiFragment newInstance() {
        return new HomeBangumiFragment();
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_bangumi;
    }

    @Override
    public void initView() {
        super.initView();

        recyclerView = fastFindViewById(R.id.bangumi_recycle);
        refreshLayout = fastFindViewById(R.id.home_bangumi);

        homeBangumiController = new HomeBangumiController(this);
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    protected void initRefreshLayout() {
        super.initRefreshLayout();

        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();

        homeBangumiAdapter = new HomeBangumiAdapter(getContext());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return homeBangumiAdapter.getSpanSize(position);
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(homeBangumiAdapter);
    }

    @Override
    protected void loadData() {
        super.loadData();
        refreshLayout.setRefreshing(true);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        //懒加载  实现数据刷新
        Log.d(TAG, "lazyLoad: ");

    }

    @Override
    public void onRefresh() {
        homeBangumiController.getBangumiData();
    }

    @Override
    public void getBangumiDataSuccess(BangumiBean bean) {


        homeBangumiAdapter.setBangumiBean(bean);
        recyclerView.setAdapter(homeBangumiAdapter);
//        homeBangumiAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);


    }

    @Override
    public void getBangumiDataFails(String string) {
        toast(string);
        refreshLayout.setRefreshing(false);
    }

    public void toast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}