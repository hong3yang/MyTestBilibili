package com.example.hong3.mybilibili.entity.recomment;

/**
 * Created by hong3 on 2016/12/7.
 */

public class RecommentBanner {


    /**
     * title : fgo
     * value : http://www.bilibili.com/blackboard/activity-B1byvvRmXl.html
     * image : http://i0.hdslb.com/bfs/archive/d9cae4e049c0fce3c2a0a606a96ff6fcb24fb1da.jpg
     * type : 2
     * weight : 1
     * remark :
     * hash : 2c5dc99f8cd655c19af2df0527709f22
     */

    private String title;
    private String value;
    private String image;
    private int type;
    private int weight;
    private String remark;
    private String hash;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
