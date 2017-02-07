package com.example.hong3.mybilibili.url;

import static com.example.hong3.mybilibili.url.ServiceGlobleUrlBase.APP_BASE_URL;

/**
 * Created by hong3 on 2016/12/3.
 */

public class GlobleUrl {

    //获取首页--直播数据
    public static final String home_live="AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758";

    //首页 -- 推荐 -- banner
    public static final String recomment_banner=APP_BASE_URL+"x/banner?plat=4&build=411007&channel=bilih5";
    //首页 -- 推荐 -- 内容
    public static final String recomment_content = APP_BASE_URL+"x/show/old?platform=android&device=&build=412001";

    //首页 -- 番剧
    public  static final String HOME_BANGUMI = "http://bangumi.bilibili.com/api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=android";

    //热门搜索
    public static  final String SEARCH_HOTWORDS = "http://s.search.bilibili.com/main/hotword?access_key=ec0f54fc369d8c104ee1068672975d6a&actionKey=appkey&appkey=27eb53fc9058f8c3";

    //直播  全部入口分类
    public static final String ENTRANCE= "http://live.bilibili.com/AppIndex/areas?_device=android&_hwid=debc2bbd2bac9245&appkey=1d8b6e7d45233436&build=430000&mobi_app=android&platform=android&scale=xxhdpi&sign=9abf78826e0ec6f4e6461061510d7a3e";



}