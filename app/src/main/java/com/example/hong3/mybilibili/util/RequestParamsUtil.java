package com.example.hong3.mybilibili.util;

import com.example.hong3.mybilibili.url.GlobleUrl;
import com.example.hong3.mybilibili.url.ServiceGlobleUrlBase;

import org.xutils.http.RequestParams;

/**
 * Created by hong3 on 2016/12/3.
 */

public class RequestParamsUtil {

    public static RequestParams getHomeLiveData(){
        String url = ServiceGlobleUrlBase.LIVE_BASE_URL + GlobleUrl.home_live;
        return new RequestParams(url);
    }
}
