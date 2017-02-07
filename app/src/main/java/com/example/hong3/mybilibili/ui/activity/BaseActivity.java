package com.example.hong3.mybilibili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hong3 on 2016/11/29.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutId = getlayoutId();
        if(layoutId == 0){
            fastToast("布局资源未找到");
            return;
        }
        setContentView(layoutId);

        getIntentData();
        initView();
        initData();
        initToolBar();
    }

    public abstract int getlayoutId();

    public void getIntentData(){}

    public void initView(){

    }

    public void initData(){

    }

    public void initToolBar(){

    }

    public void fastToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    public <T extends View>T fastFindViewById(int id){
        return (T) findViewById(id);
    }


}
