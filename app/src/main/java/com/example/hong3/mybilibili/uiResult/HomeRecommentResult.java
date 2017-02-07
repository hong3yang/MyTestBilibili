package com.example.hong3.mybilibili.uiResult;

import com.example.hong3.mybilibili.entity.recomment.RecommentBanner;
import com.example.hong3.mybilibili.entity.recomment.RecommentInfo;

import java.util.List;

/**
 * Created by hong3 on 2016/12/7.
 */

public interface HomeRecommentResult {

    public void getBannerSuccess(List<RecommentBanner> banners);
    public void getContentSuccess(List<RecommentInfo> infos);
    public void getBannerFails();
}
