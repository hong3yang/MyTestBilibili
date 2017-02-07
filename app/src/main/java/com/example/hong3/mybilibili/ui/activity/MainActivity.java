package com.example.hong3.mybilibili.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.activity.slidingMenu.OffLineDownloadActivity;
import com.example.hong3.mybilibili.ui.activity.slidingMenu.VipActivity;
import com.example.hong3.mybilibili.ui.fragment.HomePageFragment;
import com.example.hong3.mybilibili.util.ConstantUtil;
import com.example.hong3.mybilibili.util.PreferenceUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    private int index;
    private Fragment[] fragments;
    private int currentTabIndex;

    private long exitTime;

    private HomePageFragment mHomePageFragment;

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();

        navigationView = fastFindViewById(R.id.navigation_view);
        mDrawerLayout = fastFindViewById(R.id.drawer_layout);

        //初始化Fragment
        initFragments();
        //初始化侧滑菜单
        initNavigation();
    }

    private void initFragments() {

        mHomePageFragment = HomePageFragment.newInstance();

        fragments = new Fragment[]{
                mHomePageFragment};

        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, mHomePageFragment)
                .show(mHomePageFragment).commit();

    }

    private void initNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        CircleImageView mUserAvatarView = (CircleImageView) headerView.findViewById(R.id.user_avatar_view);
        TextView mUserName = (TextView) headerView.findViewById(R.id.user_name);
        TextView mUserSign = (TextView) headerView.findViewById(R.id.user_other_info);
        ImageView mSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        //设置头像
        mUserAvatarView.setImageResource(R.mipmap.ic_hotbitmapgg_avatar);
        //设置用户名 签名
        mUserName.setText(getResources().getText(R.string.hotbitmapgg));
        mUserSign.setText(getResources().getText(R.string.about_user_head_layout));
        //设置日夜间模式切换
        mSwitchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchNightMode();
            }
        });

        boolean flag = PreferenceUtil.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        if (flag) {
            mSwitchMode.setImageResource(R.mipmap.ic_switch_daily);
        } else {
            mSwitchMode.setImageResource(R.mipmap.ic_switch_night);
        }
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {

        boolean isNight = PreferenceUtil.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PreferenceUtil.putBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PreferenceUtil.putBoolean(ConstantUtil.SWITCH_MODE_KEY, true);
        }

        recreate();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                changeFragmentIndex(item, 0);
                return true;

            case R.id.item_download:
                // 离线缓存
                startActivity(new Intent(MainActivity.this, OffLineDownloadActivity.class));
                return true;

            case R.id.item_vip:
                //大会员
                startActivity(new Intent(MainActivity.this, VipActivity.class));
                return true;

            case R.id.item_favourite:
                // 我的收藏
                changeFragmentIndex(item, 1);
                return true;

            case R.id.item_history:
                // 历史记录
                changeFragmentIndex(item, 2);
                return true;

            case R.id.item_group:
                // 关注的人
                changeFragmentIndex(item, 3);
                return true;

            case R.id.item_tracker:
                // 我的钱包
                changeFragmentIndex(item, 4);
                return true;

            case R.id.item_theme:
                // 主题选择
//                CardPickerDialog dialog = new CardPickerDialog();
//                dialog.setClickListener(this);
//                dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
                return true;

            case R.id.item_app:
                // 应用推荐

                return true;

            case R.id.item_settings:
                // 设置中心
                changeFragmentIndex(item, 5);
                return true;
        }

        return false;
    }

    /**
     * @param item
     * @param currentIndex
     */
    private void changeFragmentIndex(MenuItem item, int currentIndex) {
        index = currentIndex;
        switchFragment();
        item.setChecked(true);
    }

    private void switchFragment() {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded()) {
            trx.add(R.id.main_content, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }


    /**
     * 监听back键处理DrawerLayout和SearchView
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1)))
            {
                mDrawerLayout.closeDrawers();
            } else
            {
                if (mHomePageFragment != null)
                {
                    if (mHomePageFragment.isOpenSearchView())
                    {
                        mHomePageFragment.closeSearchView();
                    } else
                    {
                        exitApp();
                    }
                } else
                {
                    exitApp();
                }
            }
        }

        return true;
    }


    /**
     * 双击退出App
     */
    private void exitApp()
    {

        if (System.currentTimeMillis() - exitTime > 2000)
        {
            fastToast("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else
        {
            PreferenceUtil.remove(ConstantUtil.SWITCH_MODE_KEY);
            finish();
        }
    }

}
