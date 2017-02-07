package com.example.hong3.mybilibili.presenter.live;


import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.entity.live.EntranceIcons;
import com.example.hong3.mybilibili.entity.live.Partitions;
import com.example.hong3.mybilibili.entity.live.RecommendData;
import com.example.hong3.mybilibili.uiResult.HomeLiveResult;
import com.example.hong3.mybilibili.util.RequestParamsUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by hong3 on 2016/12/3.
 */

public class HomeLivePresenter {

    private static final String TAG = "HomeLivePresenter";
    HomeLiveResult homeLiveResult;

    public HomeLivePresenter(HomeLiveResult homeLiveResult) {
        this.homeLiveResult = homeLiveResult;
    }

    public void getLiveData() {
        RequestParams params = RequestParamsUtil.getHomeLiveData();
        Log.d("mytag",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess: " + result);

                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getInt("code") == 0) {
                        //success
                        JSONObject dataObj = obj.getJSONObject("data");
                        List<Banner> banners = JSON.parseArray(dataObj.getString("banner"), Banner.class);
                        List<EntranceIcons> entranceIconses = JSON.parseArray(dataObj.getString("entranceIcons"), EntranceIcons.class);
                        List<Partitions> partitionses = JSON.parseArray(dataObj.getString("partitions"),Partitions.class);
                        RecommendData recommendData = JSON.parseObject(dataObj.getString("recommend_data"),RecommendData.class);
                        homeLiveResult.getDataSuccess(banners,entranceIconses,partitionses,recommendData);

                    } else {
                        homeLiveResult.getDataFails(obj.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    homeLiveResult.getDataFails(e.getMessage());
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                homeLiveResult.getDataFails(ex.getMessage());
                Log.d(TAG, "onError:   "+ex.getMessage());
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
