package com.example.hong3.mybilibili.ui.activity.slidingMenu;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.activity.BaseActivity;
import com.example.hong3.mybilibili.ui.customView.CustomEmptyView;
import com.example.hong3.mybilibili.ui.customView.NumberProgressBar;
import com.example.hong3.mybilibili.ui.customView.SpringScrollView;
import com.example.hong3.mybilibili.util.CommonUtil;

/**
 * Created by hong3 on 2016/11/30.
 */

public class OffLineDownloadActivity extends BaseActivity {

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    SpringScrollView springScrollView;
    CustomEmptyView customEmptyView;
    NumberProgressBar numberProgressBar;
    TextView cacheSize, startAll, downLoadEdit;
    ImageView refrsh;


    @Override
    public int getlayoutId() {
        return R.layout.activity_offline_download;
    }

    @Override
    public void initView() {
        super.initView();

        appBarLayout = fastFindViewById(R.id.app_bar_layout);
        toolbar = fastFindViewById(R.id.toolbar_toolbar);
        springScrollView = fastFindViewById(R.id.download_scrollview);
        customEmptyView = fastFindViewById(R.id.empty_layout);
        numberProgressBar = fastFindViewById(R.id.progress_bar);
        cacheSize = fastFindViewById(R.id.cache_size_text);
        startAll = fastFindViewById(R.id.btn_start_all);
        downLoadEdit = fastFindViewById(R.id.btn_download_edit);
        refrsh = fastFindViewById(R.id.download_refresh);


        //设置手机存储空间大小的提示（调用系统的格式化文件大小的方法）
        long phoneSize = CommonUtil.getPhoneTotalSize();
        long phoneAvailableSize = CommonUtil.getPhoneAvailableSize();
        cacheSize.setText("主存储:"+Formatter.formatFileSize(this,phoneSize)+"/可用"+Formatter.formatFileSize(this,phoneAvailableSize));

        //计算空间占用百分比，设置到进度条
        double progress = ((double)(phoneSize - phoneAvailableSize))/phoneSize;
        numberProgressBar.setProgress((int)(progress*100));


        customEmptyView.setEmptyImage(R.mipmap.img_tips_error_no_downloads);
        customEmptyView.setEmptyText("没有找到你的缓存哟");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recommend,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_more){
            fastToast("离线设置");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbar.setTitle("离线缓存");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.action_button_back_pressed_light);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
    }
}
