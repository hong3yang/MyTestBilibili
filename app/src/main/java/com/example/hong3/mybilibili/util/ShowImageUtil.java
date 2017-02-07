package com.example.hong3.mybilibili.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by hong3 on 2016/12/3.
 */

public class ShowImageUtil {

    public static void showImage(Context context,String path, ImageView imageView){
        Picasso.with(context).load(path).into(imageView);
    }
}
