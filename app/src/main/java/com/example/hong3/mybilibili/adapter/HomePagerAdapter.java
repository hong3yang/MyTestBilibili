package com.example.hong3.mybilibili.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.fragment.home.HomeAttentionFragment;
import com.example.hong3.mybilibili.ui.fragment.home.HomeBangumiFragment;
import com.example.hong3.mybilibili.ui.fragment.home.HomeDiscoverFragment;
import com.example.hong3.mybilibili.ui.fragment.home.HomeLiveFragment;
import com.example.hong3.mybilibili.ui.fragment.home.HomeRecommendedFragment;
import com.example.hong3.mybilibili.ui.fragment.home.HomeRegionFragment;

/**
 * Created by hong3 on 2016/11/29.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public HomePagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        TITLES = context.getResources().getStringArray(R.array.sections);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    //直播
                    fragments[position] = HomeLiveFragment.newInstance();
                    break;
                case 1:
                    //推荐
                    fragments[position] = HomeRecommendedFragment.newInstance();
                    break;
                case 2:
                    //番剧
                    fragments[position] = HomeBangumiFragment.newInstance();
                    break;
                case 3:
                    //分区
                    fragments[position] = HomeRegionFragment.newInstance();
                    break;
                case 4:
                    //关注
                    fragments[position] = HomeAttentionFragment.newInstance();
                    break;
                case 5:
                    //发现
                    fragments[position] = HomeDiscoverFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {

        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return TITLES[position];
    }
}
