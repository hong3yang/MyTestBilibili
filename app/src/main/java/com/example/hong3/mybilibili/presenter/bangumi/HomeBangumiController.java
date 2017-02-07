package com.example.hong3.mybilibili.presenter.bangumi;

import android.util.Log;

import com.example.hong3.mybilibili.entity.bangumi.BangumiBean;
import com.example.hong3.mybilibili.uiResult.HomeBangumiResult;
import com.example.hong3.mybilibili.url.GlobleUrl;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by hong3 on 2016/12/17.
 */

public class HomeBangumiController {
    private static final String TAG = "HomeBangumiController";


    HomeBangumiResult bangumiResult;

    public HomeBangumiController(HomeBangumiResult bangumiResult) {
        this.bangumiResult = bangumiResult;
    }

    public void getBangumiData(){
        RequestParams params = new RequestParams(GlobleUrl.HOME_BANGUMI);
        Log.d(TAG, "getBangumiData: "+params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    Log.d(TAG, "onSuccess: "+result);
                    if (obj.getInt("code")==0){

                        BangumiBean bean = new Gson().fromJson(obj.getString("result"), BangumiBean.class);
                        bangumiResult.getBangumiDataSuccess(bean);

                    }else{

                        bangumiResult.getBangumiDataFails(obj.getString("message"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    bangumiResult.getBangumiDataFails("数据解析失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                bangumiResult.getBangumiDataFails("数据获取失败");
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
