package com.example.hong3.mybilibili.entity.bangumi;

import java.util.List;

/**
 * Created by hong3 on 2016/12/17.
 */

public class BangumiBean {


    private AdBean ad;
    /**
     * list : [{"cover":"http://i0.hdslb.com/bfs/bangumi/6b4ba7bd59be1f9de225294d18ca5e6819185c06.jpg","favourites":"1429520","is_finish":1,"last_time":1474725610,"newest_ep_index":"13","pub_time":1467468000,"season_id":5017,"season_status":2,"title":"食戟之灵 贰之皿","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/6e6a7524c42efb8061985f9fe1a0448e6913c3e2.jpg","favourites":"821658","is_finish":1,"last_time":1474813810,"newest_ep_index":"13","pub_time":1467556200,"season_id":5022,"season_status":2,"title":"热诚传说X","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/6bf7624d5b688b3e916c1ae1e94c2e16cd4714a7.jpg","favourites":"764721","is_finish":1,"last_time":1474903810,"newest_ep_index":"12","pub_time":1468251000,"season_id":5056,"season_status":2,"title":"弹丸论破3 -未来篇-","watching_count":0}]
     * season : 3
     * year : 2016
     */

    private PreviousBean previous;
    /**
     * cover : http://i0.hdslb.com/bfs/bangumi/9a374fd0ad45e7c6ad3be23a947725f53831780e.jpg
     * favourites : 420576
     * is_finish : 0
     * is_started : 1
     * last_time : 1481961077
     * newest_ep_index : 12
     * pub_time : 1475769600
     * season_id : 5529
     * season_status : 2
     * title : 亚人 第二季
     * watching_count : 634
     */

    private List<SerializingBean> serializing;

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public PreviousBean getPrevious() {
        return previous;
    }

    public void setPrevious(PreviousBean previous) {
        this.previous = previous;
    }

    public List<SerializingBean> getSerializing() {
        return serializing;
    }

    public void setSerializing(List<SerializingBean> serializing) {
        this.serializing = serializing;
    }

    public static class AdBean {
        /**
         * img : http://i0.hdslb.com/bfs/bangumi/b31987b5883c0bfbe0e4a1283e7972b90078381c.jpg
         * index : 1
         * link : http://bangumi.bilibili.com/moe/2016/jp/mobile
         * title : 72抽签
         */

        private List<BodyBean> body;
        /**
         * id : 0
         * img : http://i0.hdslb.com/bfs/bangumi/17b19bd8d984d9ee8fc993067c05c0a46ab9ffec.jpg
         * is_ad : 0
         * link : http://www.bilibili.com/video/av7506804/
         * pub_time : 2016-12-17 00:00:00
         * title : 蜡笔小新：梦境世界大突击
         */

        private List<HeadBean> head;

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public List<HeadBean> getHead() {
            return head;
        }

        public void setHead(List<HeadBean> head) {
            this.head = head;
        }

        public static class BodyBean {
            private String img;
            private int index;
            private String link;
            private String title;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class HeadBean {
            private int id;
            private String img;
            private int is_ad;
            private String link;
            private String pub_time;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getIs_ad() {
                return is_ad;
            }

            public void setIs_ad(int is_ad) {
                this.is_ad = is_ad;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }

    public static class PreviousBean {
        private int season;
        private int year;
        /**
         * cover : http://i0.hdslb.com/bfs/bangumi/6b4ba7bd59be1f9de225294d18ca5e6819185c06.jpg
         * favourites : 1429520
         * is_finish : 1
         * last_time : 1474725610
         * newest_ep_index : 13
         * pub_time : 1467468000
         * season_id : 5017
         * season_status : 2
         * title : 食戟之灵 贰之皿
         * watching_count : 0
         */

        private List<ListBean> list;

        public int getSeason() {
            return season;
        }

        public void setSeason(int season) {
            this.season = season;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String cover;
            private String favourites;
            private int is_finish;
            private int last_time;
            private String newest_ep_index;
            private int pub_time;
            private int season_id;
            private int season_status;
            private String title;
            private int watching_count;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getFavourites() {
                return favourites;
            }

            public void setFavourites(String favourites) {
                this.favourites = favourites;
            }

            public int getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(int is_finish) {
                this.is_finish = is_finish;
            }

            public int getLast_time() {
                return last_time;
            }

            public void setLast_time(int last_time) {
                this.last_time = last_time;
            }

            public String getNewest_ep_index() {
                return newest_ep_index;
            }

            public void setNewest_ep_index(String newest_ep_index) {
                this.newest_ep_index = newest_ep_index;
            }

            public int getPub_time() {
                return pub_time;
            }

            public void setPub_time(int pub_time) {
                this.pub_time = pub_time;
            }

            public int getSeason_id() {
                return season_id;
            }

            public void setSeason_id(int season_id) {
                this.season_id = season_id;
            }

            public int getSeason_status() {
                return season_status;
            }

            public void setSeason_status(int season_status) {
                this.season_status = season_status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWatching_count() {
                return watching_count;
            }

            public void setWatching_count(int watching_count) {
                this.watching_count = watching_count;
            }
        }
    }

    public static class SerializingBean {
        private String cover;
        private String favourites;
        private int is_finish;
        private int is_started;
        private int last_time;
        private String newest_ep_index;
        private int pub_time;
        private int season_id;
        private int season_status;
        private String title;
        private int watching_count;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getFavourites() {
            return favourites;
        }

        public void setFavourites(String favourites) {
            this.favourites = favourites;
        }

        public int getIs_finish() {
            return is_finish;
        }

        public void setIs_finish(int is_finish) {
            this.is_finish = is_finish;
        }

        public int getIs_started() {
            return is_started;
        }

        public void setIs_started(int is_started) {
            this.is_started = is_started;
        }

        public int getLast_time() {
            return last_time;
        }

        public void setLast_time(int last_time) {
            this.last_time = last_time;
        }

        public String getNewest_ep_index() {
            return newest_ep_index;
        }

        public void setNewest_ep_index(String newest_ep_index) {
            this.newest_ep_index = newest_ep_index;
        }

        public int getPub_time() {
            return pub_time;
        }

        public void setPub_time(int pub_time) {
            this.pub_time = pub_time;
        }

        public int getSeason_id() {
            return season_id;
        }

        public void setSeason_id(int season_id) {
            this.season_id = season_id;
        }

        public int getSeason_status() {
            return season_status;
        }

        public void setSeason_status(int season_status) {
            this.season_status = season_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWatching_count() {
            return watching_count;
        }

        public void setWatching_count(int watching_count) {
            this.watching_count = watching_count;
        }
    }
}
