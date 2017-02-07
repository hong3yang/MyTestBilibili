package com.example.hong3.mybilibili.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hong3 on 2016/11/29.
 */

public abstract class BaseFragment extends Fragment{


    public boolean isVisible;
    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;
    private View parentView;
    private FragmentActivity activity;

    int layoutResId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutResId = getLayoutResId();
        parentView = inflater.inflate(layoutResId, container, false);
        activity = getSupportActivity();
        return parentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initView();
        finishCreateView(savedInstanceState);
    }

    public void initView() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        this.activity = null;
    }

    public android.app.ActionBar getSupportActionBar()
    {

        return getSupportActivity().getActionBar();
    }

    public Context getApplicationContext()
    {

        return this.activity == null ? (getActivity() == null ? null :
                getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {

        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        } else
        {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible()
    {
        lazyLoad();
    }

    protected void lazyLoad() {}

    protected void onInvisible() {}

    protected void loadData() {}

    protected void showProgressBar() {}

    protected void hideProgressBar() {}

    protected void initRecyclerView() {}

    protected void initRefreshLayout() {}

    protected void finishTask() {}

    public <T extends View> T fastFindViewById(int id)
    {

        return (T) parentView.findViewById(id);
    }

    public abstract void finishCreateView(Bundle state);


    public abstract int getLayoutResId();


    public FragmentActivity getSupportActivity()
    {
        return super.getActivity();
    }


}
