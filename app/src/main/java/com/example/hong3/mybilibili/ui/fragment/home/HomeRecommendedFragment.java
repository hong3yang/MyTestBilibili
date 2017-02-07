package com.example.hong3.mybilibili.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.adapter.HomeRecommendRecycleAdapter;
import com.example.hong3.mybilibili.adapter.section.HomeRecommentActivitySection;
import com.example.hong3.mybilibili.adapter.section.HomeRecommentBannerSection;
import com.example.hong3.mybilibili.adapter.section.HomeRecommentContentSection;
import com.example.hong3.mybilibili.adapter.section.HomeRecommentTopicSection;
import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.entity.recomment.RecommentBanner;
import com.example.hong3.mybilibili.entity.recomment.RecommentInfo;
import com.example.hong3.mybilibili.presenter.recomment.HomeRecommentController;
import com.example.hong3.mybilibili.ui.customView.CustomEmptyView;
import com.example.hong3.mybilibili.ui.fragment.BaseFragment;
import com.example.hong3.mybilibili.uiResult.HomeRecommentResult;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 推荐
 * Created by hong3 on 2016/11/29.
 */
public class HomeRecommendedFragment extends BaseFragment implements HomeRecommentResult, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private CustomEmptyView customEmptyView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<RecommentBanner> recommentBanners = new ArrayList<>();
    private List<RecommentInfo> recommentInfos = new ArrayList<>();
    private List<Banner> banners = new ArrayList<>();
    private HomeRecommendRecycleAdapter recycleAdapter;

    private HomeRecommentController homeRecommentController;

    private boolean mIsRefreshing = false;
    private boolean isGetBanner = false;
    private boolean isGetContent = false;

    public static HomeRecommendedFragment newInstance() {
        return new HomeRecommendedFragment();
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        if (!isPrepared || !isVisible)
            return;
        initRecyclerView();
        initRefreshLayout();
        isPrepared = false;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    public void initView() {
        super.initView();

        recyclerView = fastFindViewById(R.id.recomment_recycle);
        customEmptyView = fastFindViewById(R.id.recomment_empty);
        swipeRefreshLayout = fastFindViewById(R.id.recomment_refresh);

        homeRecommentController = new HomeRecommentController(this);
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();

        recycleAdapter = new HomeRecommendRecycleAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return recycleAdapter.getSpanSize(position);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mIsRefreshing;
            }
        });

    }

    @Override
    protected void initRefreshLayout() {
        super.initRefreshLayout();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                mIsRefreshing = true;
                loadData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        isGetBanner = true;
        isGetContent = true;
        recycleAdapter.clearSection();
        homeRecommentController.getBanners();
        homeRecommentController.getRecommentData();
    }

    public void initEmptyView() {
        swipeRefreshLayout.setRefreshing(false);
        customEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        customEmptyView.setEmptyImage(R.mipmap.img_tips_error_load_error);
        customEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        Toast.makeText(getActivity(), "数据加载失败,请重新加载或者检查网络是否链接", Toast.LENGTH_SHORT).show();
    }

    public void hideEmptyView() {
        customEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void showData() {
        Log.d(TAG, "showData: "+"   showData()  "+isGetBanner+ "  " +isGetContent);
        if (isGetBanner || isGetContent) {

        } else {

            swipeRefreshLayout.setRefreshing(false);  //关闭刷新状态
            mIsRefreshing = false;   //切换标记位
            hideEmptyView();    //隐藏空数据提示
            setData2Banners();   //二次封装banner数据

            recycleAdapter.addSection(new HomeRecommentBannerSection(banners));

            //解析网络获取的内容
            for (int i = 0, len = recommentInfos.size(); i < len; i++) {
                //获取话题部分内容  通过不能类型区分显示
                String type = recommentInfos.get(i).getType();
                RecommentInfo info = recommentInfos.get(i);
                switch (type) {
                    case "weblink":
                        recycleAdapter.addSection(new HomeRecommentTopicSection(
                                info.getBody().get(0).getParam(),
                                getActivity(),
                                info.getBody().get(0).getTitle(),
                                info.getBody().get(0).getCover()
                        ));
                        break;
                    case "activity":
                        recycleAdapter.addSection(new HomeRecommentActivitySection(
                                getActivity(),
                                info.getBody()
                        ));
                        break;
                    default:
                        recycleAdapter.addSection(new HomeRecommentContentSection(
                                getActivity(),
                                recommentInfos.get(i).getBody(),
                                type,
                                recommentInfos.get(i).getHead()));
                        break;
                }
//                String style = info.getHead().getStyle();
//                if (style.equals(ConstantUtil.STYLE_PIC))
//                {
//                    recycleAdapter.addSection(new HomeRecommendPicSection(getActivity(),
//                            results.get(i).getBody().get(0).getCover(),
//                            results.get(i).getBody().get(0).getParam()));
//                }
            }
            Log.d(TAG, "showData: -->  "+recycleAdapter.getSections().size());
            recycleAdapter.notifyDataSetChanged();
//            recyclerView.setAdapter(recycleAdapter);
        }
    }

    //二次封装banner数据
    private void setData2Banners() {
        banners.clear();
        for (int i = 0; i < recommentBanners.size(); i++) {
            Banner banner = new Banner();
            banner.setImg(recommentBanners.get(i).getImage());
            banner.setLink(recommentBanners.get(i).getValue());
            banner.setRemark(recommentBanners.get(i).getRemark());
            banner.setTitle(recommentBanners.get(i).getTitle());
            banners.add(banner);
        }
    }

    @Override
    public void getBannerSuccess(List<RecommentBanner> banners) {
        recommentBanners.clear();
        recommentBanners.addAll(banners);
        isGetBanner = false;
        showData();

    }

    @Override
    public void getContentSuccess(List<RecommentInfo> infos) {
        recommentInfos.clear();
        recommentInfos.addAll(infos);
        isGetContent = false;
        showData();

    }

    @Override
    public void getBannerFails() {
        initEmptyView();
    }

    @Override
    public void onRefresh() {
        clearData();
        loadData();
    }

    public void clearData() {
        recommentBanners.clear();
        recommentInfos.clear();
        banners.clear();
        mIsRefreshing = true;
        recycleAdapter.clearSection();

    }
}
