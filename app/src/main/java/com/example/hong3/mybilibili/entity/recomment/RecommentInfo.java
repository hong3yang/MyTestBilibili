package com.example.hong3.mybilibili.entity.recomment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hong3 on 2016/12/7.
 */

public class RecommentInfo {


    /**
     * type : recommend
     * head : {"param":"","goto":"","style":"gm_av","title":"热门焦点"}
     * body : [{"title":"【庞麦郎】前前前世【你的名字。】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/270ed41d478a48e456208203332131858088d199.png","param":"7382789","goto":"av","width":350,"height":219,"play":"37.6万","danmaku":"3729"},{"title":"当我遇到了网络暴力 我的妆容是什么样？","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/0e31b10024efedea7cb607d51372d2bb56b80933.jpg","param":"7401316","goto":"av","width":350,"height":219,"play":"19.3万","danmaku":"470"},{"title":"【国产】如果蜗牛有爱情 18-19","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/2134f489cdb10869f82eb4902c62d42ce49759db.jpg","param":"7403074","goto":"av","width":350,"height":219,"play":"12.9万","danmaku":"4740"},{"title":"新海诚新作 「你的名字」 之新宿圣地巡礼","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/e060e50d7bd4682096ee0878ec6bb685091382c6.png","param":"7354202","goto":"av","width":350,"height":219,"play":"10.2万","danmaku":"1975"}]
     */

    private String type;
    /**
     * param :
     * goto :
     * style : gm_av
     * title : 热门焦点
     */

    private HeadBean head;
    /**
     * title : 【庞麦郎】前前前世【你的名字。】
     * style : gm_av
     * cover : http://i0.hdslb.com/bfs/archive/270ed41d478a48e456208203332131858088d199.png
     * param : 7382789
     * goto : av
     * width : 350
     * height : 219
     * play : 37.6万
     * danmaku : 3729
     */

    private List<BodyBean> body;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class HeadBean {
        private String param;
        @SerializedName("goto")
        private String gotoX;
        private String style;
        private String title;



        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class BodyBean {
        private String title;
        private String style;
        private String cover;
        private String param;
        @SerializedName("goto")
        private String gotoX;
        private int width;
        private int height;
        private String play;
        private String danmaku;
        private String up_face;
        private String up;
        private String online;
        private String area;
        private String area_id;
        private String desc1;
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDesc1() {
            return desc1;
        }

        public void setDesc1(String desc1) {
            this.desc1 = desc1;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getUp_face() {
            return up_face;
        }

        public void setUp_face(String up_face) {
            this.up_face = up_face;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getPlay() {
            return play;
        }

        public void setPlay(String play) {
            this.play = play;
        }

        public String getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(String danmaku) {
            this.danmaku = danmaku;
        }
    }
}
