package com.example.hong3.mybilibili.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.adapter.HomePagerAdapter;
import com.example.hong3.mybilibili.ui.activity.MainActivity;
import com.example.hong3.mybilibili.ui.activity.TotalStationSearchActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hong3 on 2016/11/29.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    private MaterialSearchView mSearchView;
    private CircleImageView mCircleImageView;
    private Toolbar mToolbar;
    private SlidingTabLayout mSlidingTab;
    private ViewPager mViewPager;


    public static HomePageFragment newInstance()
    {

        return new HomePageFragment();
    }

    @Override
    public void initView() {
        super.initView();

        mCircleImageView = fastFindViewById(R.id.toolbar_user_avatar);
        mToolbar = fastFindViewById(R.id.toolbar);
        mSlidingTab= fastFindViewById(R.id.sliding_tabs);
        mViewPager = fastFindViewById(R.id.view_pager);
        mSearchView = fastFindViewById(R.id.search_view);

        mCircleImageView.setOnClickListener(this);
    }

    @Override
    public void finishCreateView(Bundle state) {
        setHasOptionsMenu(true);
        initToolBar();
        initSearchView();
        initViewPager();
    }

    private void initViewPager() {
        HomePagerAdapter mHomeAdapter = new HomePagerAdapter(getChildFragmentManager(),
                getApplicationContext());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mHomeAdapter);
        mSlidingTab.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }

    private void initToolBar() {
        mToolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        mCircleImageView.setImageResource(R.mipmap.ic_hotbitmapgg_avatar);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_pager;
    }

    void toggleDrawer()
    {
        Activity activity = getActivity();
        if (activity instanceof MainActivity)
            ((MainActivity) activity).toggleDrawer();
    }

    private void initSearchView()
    {

        //初始化SearchBar
        mSearchView.setVoiceSearch(false);
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);
        mSearchView.setEllipsize(true);
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                TotalStationSearchActivity.launch(getActivity(), query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        // 设置SearchViewItemMenu
        MenuItem item = menu.findItem(R.id.id_action_search);
        mSearchView.setMenuItem(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == Activity.RESULT_OK)
        {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0)
            {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd))
                {
                    mSearchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public MaterialSearchView getmSearchView() {
        return mSearchView;
    }

    public void showSearchView(){
        mSearchView.showSearch();
    }

    public boolean isOpenSearchView()
    {

        return mSearchView.isSearchOpen();
    }

    public void closeSearchView()
    {

        mSearchView.closeSearch();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_user_avatar:
                toggleDrawer();
                break;
        }
    }
}
