package com.example.hong3.mybilibili.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.customView.NoScrollViewPager;
import com.example.hong3.mybilibili.util.ConstantUtil;
import com.flyco.tablayout.SlidingTabLayout;

/**
 * Created by hong3 on 2016/11/30.
 */

public class TotalStationSearchActivity extends BaseActivity implements View.OnClickListener {

    CardView cardView;
    ImageView search_back, search_text_clear, search_img;
    ImageView iv_search_loading;
    EditText search_edit;
    SlidingTabLayout slidingTabLayout;
    NoScrollViewPager noScrollViewPager;

    AnimationDrawable animationDrawable;
    String searchContent;

    @Override
    public int getlayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        super.initView();
        cardView = fastFindViewById(R.id.search_card_view);
        search_back = fastFindViewById(R.id.search_back);
        search_text_clear = fastFindViewById(R.id.search_text_clear);
        search_img = fastFindViewById(R.id.search_img);
        iv_search_loading = fastFindViewById(R.id.iv_search_loading);
        search_edit = fastFindViewById(R.id.search_edit);
        slidingTabLayout = fastFindViewById(R.id.sliding_tabs);
        noScrollViewPager = fastFindViewById(R.id.view_pager);

    }

    @Override
    public void initData() {
        super.initData();

        Intent intent  = getIntent();
        searchContent = intent.getStringExtra(ConstantUtil.EXTRA_CONTENT);


        iv_search_loading.setImageResource(R.drawable.anim_search_loading);
        animationDrawable = (AnimationDrawable) iv_search_loading.getDrawable();
        startAnimationDrawable();
        search_edit.clearFocus();
        search_edit.setText(searchContent);
        getSearchData();
        search_img.setOnClickListener(this);
        search_text_clear.setOnClickListener(this);
        search_back.setOnClickListener(this);
        setupSearchEdit();
    }

    //用于更新搜索框  有内容显示清空按钮，没有内容隐藏
    private void setupSearchEdit() {
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    search_text_clear.setVisibility(View.GONE);
                }else{
                    search_text_clear.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    //获取网络数据
    private void getSearchData() {
        //调取controller获取数据


    }

    private void startAnimationDrawable() {
        iv_search_loading.setVisibility(View.VISIBLE);
        animationDrawable.start();
    }

    public static void launch(Activity activity, String str) {

        Intent mIntent = new Intent(activity, TotalStationSearchActivity.class);
        mIntent.putExtra(ConstantUtil.EXTRA_CONTENT, str);
        activity.startActivity(mIntent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_img:
                fastToast("点击搜索");
                break;
            case R.id.search_back:
                finish();
                break;
            case R.id.search_text_clear:
                search_edit.setText("");
                break;

        }
    }
}
