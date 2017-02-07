package com.example.hong3.mybilibili.uiResult;

import java.util.List;

/**
 * Created by hong3 on 2016-12-22.
 */

public interface HomeDiscoverResult {

    public void getHotWordsSuccess(List<String> strs);
    public void getHotWordsFails(String str);
}
