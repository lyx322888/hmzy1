package com.huiminsheng.app.bean;


public class BeanConnectionInfo {


    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"official_img":"http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/gongzhonghao.jpg","service_img":"http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/guanghuitong.jpg","official_title":"关注微信公众号，了解更多惠民资讯!","service_title":"扫码加客服微信，及时解决您的需求!"}
     */

    private ResultBean result;
    private String link;
    private DataBean data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 10000
         * msg : 请求成功
         */

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class DataBean {
        /**
         * official_img : http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/gongzhonghao.jpg
         * service_img : http://hmsapi.zhuoranxiaoming.cn/uploads/qrcode/guanghuitong.jpg
         * official_title : 关注微信公众号，了解更多惠民资讯!
         * service_title : 扫码加客服微信，及时解决您的需求!
         */

        private String official_img;
        private String service_img;
        private String official_title;
        private String service_title;

        public String getOfficial_img() {
            return official_img;
        }

        public void setOfficial_img(String official_img) {
            this.official_img = official_img;
        }

        public String getService_img() {
            return service_img;
        }

        public void setService_img(String service_img) {
            this.service_img = service_img;
        }

        public String getOfficial_title() {
            return official_title;
        }

        public void setOfficial_title(String official_title) {
            this.official_title = official_title;
        }

        public String getService_title() {
            return service_title;
        }

        public void setService_title(String service_title) {
            this.service_title = service_title;
        }
    }
}
