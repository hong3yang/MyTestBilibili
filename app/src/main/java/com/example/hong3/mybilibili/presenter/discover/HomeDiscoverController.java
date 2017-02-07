package com.example.hong3.mybilibili.presenter.discover;

import com.alibaba.fastjson.JSON;
import com.example.hong3.mybilibili.entity.discover.HotSearchWordsBean;
import com.example.hong3.mybilibili.uiResult.HomeDiscoverResult;
import com.example.hong3.mybilibili.url.GlobleUrl;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hong3 on 2016-12-22.
 */

public class HomeDiscoverController {

    private HomeDiscoverResult discoverResult;

    public HomeDiscoverController(HomeDiscoverResult discoverResult) {
        this.discoverResult = discoverResult;
    }

    public void getHotwords(){
        RequestParams params = new RequestParams(GlobleUrl.SEARCH_HOTWORDS);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getInt("code") == 0){
                        String str = obj.getString("list");
                        List<HotSearchWordsBean> beans = JSON.parseArray(str,HotSearchWordsBean.class);
                        List<String> list=new ArrayList<String>();
                        for (int i=0,len=beans.size();i<len;i++){
                            list.add(beans.get(i).getKeyword());
                        }

                        discoverResult.getHotWordsSuccess(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    discoverResult.getHotWordsFails("数据解析失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                discoverResult.getHotWordsFails("数据获取失败");
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
