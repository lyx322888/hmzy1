package com.huiminsheng.app.bean;

public class Beancode {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"official_img":"http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/gongzhonghao.jpg","service_img":"http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/guanghuitong.jpg"}
     */

    public ResultBean result;
    public String link;
    public DataBean data;

    public static class ResultBean {
        /**
         * code : 10000
         * msg : 请求成功
         */

        public String code;
        public String msg;
    }

    public static class DataBean {
        /**
         * official_img : http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/gongzhonghao.jpg
         * service_img : http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/guanghuitong.jpg
         */

        public String official_img;
        public String service_img;
    }
}
