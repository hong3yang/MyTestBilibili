package com.example.hong3.mybilibili.ui.fragment.home;

import android.os.Bundle;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.fragment.BaseFragment;

/**
 * Created by hong3 on 2016/11/29.
 */
public class HomeRegionFragment extends BaseFragment {

    public static HomeRegionFragment newInstance()
    {
        return new HomeRegionFragment();
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_pager;
    }


}