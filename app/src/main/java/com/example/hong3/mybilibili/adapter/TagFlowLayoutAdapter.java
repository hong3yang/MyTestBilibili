package com.example.hong3.mybilibili.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by hong3 on 2016-12-22.
 */

public class TagFlowLayoutAdapter extends TagAdapter<String>{

    boolean showAll = true;

    public TagFlowLayoutAdapter(List<String> datas) {
        super(datas);
    }

    public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    @Override
    public int getCount() {
        if (showAll){
            return super.getCount();
        }else{
            if (super.getCount() < 6){
                return super.getCount();
            }
            return 6;
        }

    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flowlayout_textview,
                parent, false);
        tv.setText(s);
        return tv;
    }
}
