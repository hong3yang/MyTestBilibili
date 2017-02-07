package com.example.hong3.mybilibili.presenter.recomment;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.hong3.mybilibili.entity.recomment.RecommentBanner;
import com.example.hong3.mybilibili.entity.recomment.RecommentInfo;
import com.example.hong3.mybilibili.uiResult.HomeRecommentResult;
import com.example.hong3.mybilibili.url.GlobleUrl;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by hong3 on 2016/12/7.
 */

public class HomeRecommentController {


    HomeRecommentResult homeRecommentResult;

    public HomeRecommentController(HomeRecommentResult homeRecommentResult) {
        this.homeRecommentResult = homeRecommentResult;
    }

    public void getRecommentData(){
        RequestParams params = new RequestParams(GlobleUrl.recomment_content);
        Log.d(TAG, "getRecommentData: " + params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getInt("code") == 0){
                        JSONArray arr = obj.getJSONArray("result");
                        Gson gson = new Gson();
                        List<RecommentInfo> infos = new ArrayList<RecommentInfo>();
                        RecommentInfo info = null;

                        for (int i = 0; i < arr.length(); i++) {
                            String str = arr.getString(i);
                            info = gson.fromJson(str,RecommentInfo.class);
                            infos.add(info);
                        }

                        Log.d(TAG, "onSuccess: "+infos.size());
                        homeRecommentResult.getContentSuccess(infos);
                    }else{
                        homeRecommentResult.getBannerFails();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    homeRecommentResult.getBannerFails();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                homeRecommentResult.getBannerFails();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getBanners(){
        RequestParams params = new RequestParams(GlobleUrl.recomment_banner);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mytag",result);

                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getInt("code") == 0){
                        String str = obj.getString("data");
                        List<RecommentBanner> banners = JSON.parseArray(str,RecommentBanner.class);
                        homeRecommentResult.getBannerSuccess(banners);

                    }else{
                        homeRecommentResult.getBannerFails();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    homeRecommentResult.getBannerFails();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                homeRecommentResult.getBannerFails();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
