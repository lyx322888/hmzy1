package com.huiminsheng.app.bean;

public class LoginAd {

    /**
     * @params is_full
     * -1 不显示广告
     * 0 非全屏
     * 1 全屏
     * */
    private String is_full;
    private String url;
    private String image;
    private String width;
    private String height;

    public String getIs_full() {
        return is_full;
    }

    public void setIs_full(String is_full) {
        this.is_full = is_full;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}