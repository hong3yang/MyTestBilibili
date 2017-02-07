package com.example.hong3.mybilibili.ui.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;

/**
 * Created by hong3 on 2016-12-22.
 */

public class SimpleChooseView extends LinearLayout {

    private ImageView leftImage;
    private TextView title;
    private TextView mark;
    private ImageView rightImage;
    private View view;

    public SimpleChooseView(Context context) {
        this(context, null);
    }

    public SimpleChooseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.layout_simple_choose, this,true);

    }


    public ImageView setLeftImage(int res) {
        if (leftImage == null) {
            leftImage = (ImageView) view.findViewById(R.id.leftImage);
        }
        if (leftImage.getVisibility() != VISIBLE) {
            leftImage.setVisibility(VISIBLE);
        }
        leftImage.setImageResource(res);
        return leftImage;
    }

    public void hideLeftImage() {
        if (leftImage == null) {
            leftImage = (ImageView) view.findViewById(R.id.leftImage);
        }
        leftImage.setVisibility(GONE);
    }

    public TextView setTitle(String str) {
        if (title == null) {
            title = (TextView) view.findViewById(R.id.title);
        }
        if (title.getVisibility() != VISIBLE) {
            title.setVisibility(VISIBLE);
        }
        title.setText(str);
        return title;
    }

    public void hideTitle() {
        if (title == null) {
            title = (TextView) view.findViewById(R.id.title);
        }
        title.setVisibility(GONE);
    }

    public TextView setMark(String str) {
        if (mark == null) {
            mark = (TextView) view.findViewById(R.id.marke);
        }
        if (mark.getVisibility() != VISIBLE) {
            mark.setVisibility(VISIBLE);
        }
        mark.setText(str);
        return mark;
    }

    public void hideMark() {
        if (mark == null) {
            mark = (TextView) view.findViewById(R.id.marke);
        }
        mark.setVisibility(GONE);
    }

    public void hideRightImage(){
        if (rightImage == null){
            rightImage = (ImageView) view.findViewById(R.id.right_image);
        }
        rightImage.setVisibility(GONE);
    }

    public void showRightImage(){
        if (rightImage == null){
            rightImage = (ImageView) view.findViewById(R.id.right_image);
        }
        rightImage.setVisibility(VISIBLE);
    }
}
