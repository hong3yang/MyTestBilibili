package com.example.hong3.mybilibili.uiResult;

import com.example.hong3.mybilibili.entity.bangumi.BangumiBean;

/**
 * Created by hong3 on 2016/12/17.
 */

public interface HomeBangumiResult {

    public void getBangumiDataSuccess(BangumiBean bean);
    public void getBangumiDataFails(String string);
}
