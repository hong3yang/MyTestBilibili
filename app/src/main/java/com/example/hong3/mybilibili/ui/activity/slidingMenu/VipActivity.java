package com.example.hong3.mybilibili.ui.activity.slidingMenu;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.activity.BaseActivity;
import com.example.hong3.mybilibili.util.ConstantUtil;
import com.example.hong3.mybilibili.util.SystemBarHelper;

/**
 * Created by hong3 on 2016/12/1.
 */

public class VipActivity extends BaseActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView vip_image;
    View vip_layout;
    Toolbar toolbar;
    WebView webView;


    @Override
    public int getlayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    public void initView() {
        super.initView();

        collapsingToolbarLayout = fastFindViewById(R.id.collapsing_toolbar);
        vip_image = fastFindViewById(R.id.vip_image);
        vip_layout = fastFindViewById(R.id.vip_layout);
        toolbar = fastFindViewById(R.id.toolbar);
        webView = fastFindViewById(R.id.webView);


        webView.loadUrl(ConstantUtil.VIP_URL);
    }


    @Override
    public void initToolBar() {
        super.initToolBar();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {
        super.initData();
    }
}
