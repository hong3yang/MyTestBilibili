package com.example.hong3.mybilibili.entity.live;

/**
 *
 * 入口图标
 * Created by hong3 on 2016/12/3.
 */

public class EntranceIcons {


    /**
     * id : 11
     * name : 手机直播
     * entrance_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/big/xxhdpi/11_big.png?2016120101","height":"132","width":"132"}
     */

    private int id;
    private String name;
    /**
     * src : http://static.hdslb.com/live-static/images/mobile/android/big/xxhdpi/11_big.png?2016120101
     * height : 132
     * width : 132
     */

    private EntranceIconBean entrance_icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntranceIconBean getEntrance_icon() {
        return entrance_icon;
    }

    public void setEntrance_icon(EntranceIconBean entrance_icon) {
        this.entrance_icon = entrance_icon;
    }

    public static class EntranceIconBean {
        private String src;
        private String height;
        private String width;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }
}
