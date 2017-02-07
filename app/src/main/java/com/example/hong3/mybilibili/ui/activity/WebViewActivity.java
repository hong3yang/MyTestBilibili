package com.example.hong3.mybilibili.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.example.hong3.mybilibili.R;

/**
 * Created by hong3 on 2016/12/8.
 */

public class WebViewActivity extends BaseActivity{

    String link;
    @Override
    public int getlayoutId() {
        return R.layout.activity_wbview;
    }

    @Override
    public void getIntentData() {
        super.getIntentData();
        if (getIntent() != null){
            link = getIntent().getStringExtra("link");
        }
    }

    public static void startActivity(Context context, String link){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("link",link);
        context.startActivity(intent);
    }
}
