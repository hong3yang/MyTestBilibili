package com.example.hong3.mybilibili.uiResult;

import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.entity.live.EntranceIcons;
import com.example.hong3.mybilibili.entity.live.Partitions;
import com.example.hong3.mybilibili.entity.live.RecommendData;

import java.util.List;

/**
 * Created by hong3 on 2016/12/3.
 */

public interface HomeLiveResult {

    public void getDataSuccess(List<Banner> banners, List<EntranceIcons> entranceIconses, List<Partitions> partitionses,RecommendData recommendData);
    public void getDataFails(String str);
}
