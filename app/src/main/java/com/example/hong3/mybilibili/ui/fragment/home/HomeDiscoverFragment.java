package com.example.hong3.mybilibili.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.adapter.TagFlowLayoutAdapter;
import com.example.hong3.mybilibili.presenter.discover.HomeDiscoverController;
import com.example.hong3.mybilibili.ui.customView.SimpleChooseView;
import com.example.hong3.mybilibili.ui.fragment.BaseFragment;
import com.example.hong3.mybilibili.ui.fragment.HomePageFragment;
import com.example.hong3.mybilibili.uiResult.HomeDiscoverResult;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hong3 on 2016/11/29.
 */
public class HomeDiscoverFragment extends BaseFragment implements View.OnClickListener, HomeDiscoverResult, TagFlowLayout.OnTagClickListener {

    private static final String TAG = "HomeDiscoverFragment";

    private TextView search;
    private ImageView scan;
    private TagFlowLayout tagFlowLayout;
    private SimpleChooseView xingqu,
            huati,
            huodong,
            yuanchuang,
            quanqu,
            youxi,
            zhoubian;
    private TextView more;
    private TagFlowLayoutAdapter tagAdapter;
    private List<String> datas = new ArrayList<>();

    private HomeDiscoverController discoverController;


    public static HomeDiscoverFragment newInstance() {
        return new HomeDiscoverFragment();
    }

    @Override
    public void finishCreateView(Bundle state) {
        discoverController.getHotwords();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_discover;
    }

    @Override
    public void initView() {
        super.initView();

        discoverController = new HomeDiscoverController(this);

        search = fastFindViewById(R.id.discover_search);
        search.setOnClickListener(this);
        scan = fastFindViewById(R.id.discover_scan);

        tagFlowLayout = fastFindViewById(R.id.id_flowlayout);
        tagAdapter = new TagFlowLayoutAdapter(datas);
        tagFlowLayout.setAdapter(tagAdapter);
        tagFlowLayout.setOnTagClickListener(this);

        more = fastFindViewById(R.id.more);
        more.setOnClickListener(this);

        xingqu = fastFindViewById(R.id.xingqu);
        xingqu.setLeftImage(R.mipmap.ic_quanzi);
        xingqu.setTitle("兴趣圈");

        huati = fastFindViewById(R.id.huati);
        huati.setTitle("话题中心");
        huati.setLeftImage(R.mipmap.ic_header_topic_center);

        huodong = fastFindViewById(R.id.huodong);
        huodong.setLeftImage(R.mipmap.ic_header_activity_center);
        huodong.setTitle("活动中心");

        yuanchuang = fastFindViewById(R.id.yuanchuang);
        yuanchuang.setTitle("原创排行榜");
        yuanchuang.showRightImage();
        yuanchuang.setLeftImage(R.mipmap.ic_btn_rank_original);

        quanqu = fastFindViewById(R.id.quanqu);
        quanqu.setLeftImage(R.mipmap.ic_btn_rank_all);
        quanqu.showRightImage();
        quanqu.setTitle("全区排行榜");

        youxi = fastFindViewById(R.id.youxi);
        youxi.setTitle("游戏中心");
        youxi.setLeftImage(R.mipmap.ic_btn_game);

        zhoubian = fastFindViewById(R.id.zhoubian);
        zhoubian.setTitle("周边商城");
        zhoubian.setLeftImage(R.mipmap.ic_btn_shop);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discover_search:
                //搜索
                ((HomePageFragment) getParentFragment()).showSearchView();
                break;
            case R.id.more:
                if (tagAdapter.isShowAll()) {
                    tagAdapter.setShowAll(false);
                    more.setText("查看更多");
                } else {
                    tagAdapter.setShowAll(true);
                    more.setText("收起");
                }

                tagAdapter.notifyDataChanged();

                break;
        }
    }

    @Override
    public void getHotWordsSuccess(List<String> strs) {
        datas.clear();
        datas.addAll(strs);
        tagAdapter.setShowAll(false);
        tagAdapter.notifyDataChanged();
    }

    @Override
    public void getHotWordsFails(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (!view.isSelected()) {
            ((HomePageFragment) getParentFragment()).getmSearchView().setQuery(datas.get(position), true);
        }

        return true;
    }
}